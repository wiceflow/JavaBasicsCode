package com.wiceflow.Io.convert;

import java.io.UnsupportedEncodingException;

/**
 * Created by BF on 2017/9/26.
 */
public class ConverDemo01 {

    /**
     * @param args
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "中国";
        byte[] data = str.getBytes();
        //字节数不完整也会导致乱码
        System.out.println(new String(data, 0, 3));
    }

    /**
     * 编码与解码字符集必须相同，否则乱码
     *
     * @throws UnsupportedEncodingException
     */
    public static void test1() throws UnsupportedEncodingException {
        //解码 byte -->char
        String str = "中国"; //gbk
        //编码 char -->byte
        byte[] data = str.getBytes();
        //编码与解码字符集同一
        System.out.println(new String(data));
        data = str.getBytes("utf-8"); //设定编码字符集
        //不同一出现乱码
        System.out.println(new String(data));

        //编码
        byte[] data2 = "中国".getBytes("utf-8");
        //解码
        str = new String(data2, "utf-8");
        System.out.println(str);
    }

}
