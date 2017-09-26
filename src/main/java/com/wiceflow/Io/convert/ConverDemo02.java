package com.wiceflow.Io.convert;

import java.io.*;

/**
 * * 转换流: 字节转为字符
 * 1、输出流 OutputStreamWriter 编码
 * 2、输入流 InputStreamReader  解码
 * <p>
 * 确保源不能为乱码
 * Created by BF on 2017/9/26.
 */
public class ConverDemo02 {
    public static void main(String[] args) throws IOException {
        // 指定解码字符集
        BufferedReader br = new BufferedReader(
                // 只有在字节流中才可以转换编码 转换流
                new InputStreamReader(
                        new BufferedInputStream(
                                new FileInputStream(
                                        new File("E:/java/test/Demo03.java"))), "UTF-8")
        );
        // 写出文件 编码
        BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(
                        new BufferedOutputStream(
                                new FileOutputStream(new File("E:/java/test/encode.java")))));

        String info = null;
        while (null != (info = br.readLine())) {
            //System.out.println(info);
            bw.write(info);
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

}