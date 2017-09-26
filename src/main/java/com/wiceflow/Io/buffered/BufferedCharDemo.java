package com.wiceflow.Io.buffered;

import java.io.*;

/**
 * 字符缓冲流 +新增方法(不能发生多态)
 * Created by BF on 2017/9/26.
 */
public class BufferedCharDemo {
    public static void main(String[] args) {
        //创建源 仅限于 字符的纯文本
        File src =new File("E:/java/test/Demo03.java");
        File dest =new File("e:/java/test/char.txt");
        //选择流
        BufferedReader reader =null;
        BufferedWriter wr =null;
        try {
            // 不能使用多态
            reader =new BufferedReader(new FileReader(src));
            wr =new BufferedWriter(new FileWriter(dest));
            //读取操作
			/*
			char[] flush =new char[1024];
			int len =0;
			while(-1!=(len=reader.read(flush))){
				wr.write(flush, 0, len);
			}*/
            //新增方法的操作
            String line =null;
            while(null!=(line=reader.readLine())){
                wr.write(line);
                //wr.append("\r\n");
                wr.newLine(); //换行符号
            }
            wr.flush();//强制刷出
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("源文件不存在");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件读取失败");
        }finally{
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
