package com.wiceflow.Thread.start;

/**
 * Created by BF on 2017/9/12.
 * 外部干涉线程体停止
 */
public class Demo01 {
    public static void main(String[] args) throws InterruptedException {
        Study s = new Study();
        new Thread(s).start();

        for (long i=0;i<1000000000;i++){
            if (i==5000){
                Thread.sleep(100);
                s.stop();
            }
            if (i==2000000){
                Thread.sleep(100);
                s.go();
            }
        }
    }
}
class Study implements Runnable{
    // 定义标识
    private boolean flag = true;
    private int i = 0;
    @Override
    public void run() {
        while (flag){
            i++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Study Thread..." + i);
        }
    }
    // 自定义线程的终止方法
    public void stop(){
        this.flag = false;
    }
    // 自定义线程的重启方法
    public void go(){this.flag = true;}
}
