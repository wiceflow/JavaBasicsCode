package com.wiceflow.util;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author BF
 * @date 2018/12/22
 * 文件操作类
 */
public enum FileUtil {


    /**
     * 从数据库读取数据写到文件
     */
    DATA_BASE_TO_FILE,

    /**
     * 从文件读取数据到数据库
     */
    FILE_TO_DATA_BASE;

    private Logger logger = LoggerFactory.getLogger(FileUtil.class);

    private BufferedWriter bufferedWriter;

    private BufferedReader bufferedReader;

    /**
     * 将数据写出到文件
     * @param path    路径前缀
     * @param <T>     数据
     */
    public <T> Boolean dBToFile(String path, List<T> list) {
        // 将数据全部读取出来，这个会很大  注意内存溢出
        // 构建完整路径
        File file = new File(path);
        // 创建文件夹
        fileMkdirs(file);
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            for (T t : list) {
                bufferedWriter.write(JSON.toJSONString(t));
                bufferedWriter.write("\r\n");
                bufferedWriter.flush();
            }
            return true;
        } catch (IOException e) {
            logger.error("数据备份到文件失败！具体原因为：" + e.getMessage() );
            return false;
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                logger.error("关闭文件写出流失败！！");
            }
        }
    }

    /**
     * 读取文件数据
     * @param file   文件
     * @param c      需转换的实体 Class
     * @param <T>    实体
     * @return       若操作失败返回空的列表
     */
    @SuppressWarnings("unchecked")
    public  <T >  List<T> readFile(File file,Class c){
        List<T> list = new ArrayList<>();
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            while (null != line){
                T t = (T)JSON.parseObject(line,c);
                list.add(t);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            logger.error("从文件读取数据失败，具体原因为：" + e.getMessage());
            return new ArrayList<>(1);
        }
        return list;
    }


    /**
     * 加锁创建文件夹
     *
     * @param file [File] 文件目录
     */
    private synchronized void fileMkdirs(File file) {
        File parentFile = file.getParentFile();
        if (parentFile == null) {
            logger.error("文件路径不合法");
        } else if (parentFile.isDirectory()) {
            // do nothing
        } else {
            boolean mkdirs = parentFile.mkdirs();
            if (!mkdirs) {
                if (!(parentFile.exists() && parentFile.isDirectory())) {
                    logger.error("创建文件夹失败");
                }
            }
        }
    }
}
