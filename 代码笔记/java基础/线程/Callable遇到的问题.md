## Callable 遇到的问题  

创建线程常用的有两种方法，一种是 Runnable，一种是 Callable，今天我们来讲讲 Callable  

### 有返回值的线程 Callable  

在线程方法 Callable 中，Future 是该线程返回的一个有值对象，其中里面的泛型定义了返回的值。

Future 中的两个获取值方法  

```java
/**
     * Waits if necessary for the computation to complete, and then
     * retrieves its result.
     *
     * @return the computed result
     * @throws CancellationException if the computation was cancelled
     * @throws ExecutionException if the computation threw an
     * exception
     * @throws InterruptedException if the current thread was interrupted
     * while waiting
     */
    V get() throws InterruptedException, ExecutionException;

    /**
     * Waits if necessary for at most the given time for the computation
     * to complete, and then retrieves its result, if available.
     *
     * @param timeout the maximum time to wait
     * @param unit the time unit of the timeout argument
     * @return the computed result
     * @throws CancellationException if the computation was cancelled
     * @throws ExecutionException if the computation threw an
     * exception
     * @throws InterruptedException if the current thread was interrupted
     * while waiting
     * @throws TimeoutException if the wait timed out
     */
    V get(long timeout, TimeUnit unit)
        throws InterruptedException, ExecutionException, TimeoutException;
```

从它的源码可以看出，当调用 `future.get();` 时，是会造成当前线程阻塞的，所以在线程池中，如果用了 Callable 方法创建线程，则不能在将线程丢入线程池后立马对返回值 Future 操作，否则将会造成阻塞，实际上就变成了单线程模式。  



应当将所有返回值 Future 都保存到一个 list 中，等待所有线程都丢入线程池后，再对 Future 进行取值操作。此时要是线程池任务还没做完，当前 Future 阻塞后并不会已存在线程池中的其他线程运行，所以不会造成阻塞。  

```java
for (List<String> employeeCode : lists) {
                // 通过线程池进行 http 连接获取数据  获取的 Future 不能在这取数据，否则会阻塞其他线程，先把线程全部提交了先
                Future<List<DriverEmployeeInfo>> result = ExecuteServiceUtil.execute(new EmployeeInfoThread(employeeCode, tokenCache.getValue("token")));
                futures.add(result);
            }
            for (Future<List<DriverEmployeeInfo>> future : futures){
                List<DriverEmployeeInfo> list = future.get(60,TimeUnit.MINUTES);
                for (DriverEmployeeInfo driverEmployeeInfo : list){
                    // 截取日期的时间
                    driverEmployeeInfo.setBirthday(changeDate(driverEmployeeInfo.getBirthday()));
                    // 初次领证时间
                    driverEmployeeInfo.setHrMsUserField52(changeDate(driverEmployeeInfo.getHrMsUserField52()));
                    driverEmployeeInfos.add(driverEmployeeInfo);
                }
            }
```

