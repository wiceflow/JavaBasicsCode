package com.wiceflow.Io.byteIO;

import java.io.*;

/**
 * 1、建立联系   File对象   源头 目的地
 * 2、选择流
 * 文件输入流  InputStream FileInputStream
 * 文件输出流  OutputStream FileOutputStream
 * 3、操作  :  拷贝
 * byte[] flush =new byte[1024];
 * int len =0;
 * while(-1!=(len=输入流.read(flush))){
 * 输出流.write(flush,0,len)
 * }
 * 输出流.flush
 * 4、释放资源 :关闭 两个流
 * Created by BF on 2017/9/24.
 */
public class CopyFileDemo {
    /**
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) {
        String src = "d:/Jave";
        String dest = "d:/xp/test/4.jpg";
        try {
            copyFile(src, dest);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("文件不存在");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("拷贝文件失败|关闭流失败");
        }
    }

    /**
     * 文件的拷贝
     *
     * @param srcPath 源文件路径
     * @param destPath 目录文件路径
     * @return
     * @throws FileNotFoundException,IOException
     */
    public static void copyFile(String srcPath, String destPath) throws FileNotFoundException, IOException {
        // 1、建立联系 源(存在且为文件) +目的地(文件可以不存在)
        File src = new File(srcPath);
        File dest = new File(destPath);
        if (!src.isFile()) { //不是文件或者为null
            System.out.println("只能拷贝文件");
            throw new IOException("只能拷贝文件");
        }
        //2、选择流
        InputStream is = new FileInputStream(src);
        OutputStream os = new FileOutputStream(dest);
        //3、文件拷贝   循环+读取+写出
        byte[] flush = new byte[1024];
        int len = 0;
        //读取
        while (-1 != (len = is.read(flush))) {
            //写出
            os.write(flush, 0, len);
        }
        os.flush(); //强制刷出

        //关闭流
        os.close();
        is.close();
    }

}
