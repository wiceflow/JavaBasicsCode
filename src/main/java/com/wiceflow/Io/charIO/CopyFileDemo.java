package com.wiceflow.Io.charIO;

import java.io.*;

/**
 * 纯文本拷贝
 * Created by BF on 2017/9/26.
 */
public class CopyFileDemo {
    public static void main(String[] args) {
        // 创建源 仅限于 字符的纯文本
        File src = new File("e:/java/test/Demo03.java");
        File dest = new File("e:/java/test/char.txt");
        // 选择流
        Reader reader = null;
        Writer wr = null;
        try {
            reader = new FileReader(src);
            wr = new FileWriter(dest);
            // 读取操作
            char[] flush = new char[1024];
            int len = 0;
            while (-1 != (len = reader.read(flush))) {
                wr.write(flush, 0, len);
            }
            wr.flush();// 强制刷出
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("源文件不存在");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件读取失败");
        } finally {
            try {
                if (null != wr) {
                    wr.close();
                }
            } catch (Exception e2) {
            }
            try {
                if (null != reader) {
                    reader.close();
                }
            } catch (Exception e2) {
            }
        }

    }

}