package com.wiceflow.Io.charIO;

import java.io.*;

/**
 * 写出文件
 * Created by BF on 2017/9/26.
 */
public class Demo02 {
    public static void main(String[] args) {
        // 创建源
        File dest = new File("e:/java/test/char.txt");
        // 选择流
        Writer wr = null;
        try {
            // 追加文件，而不是覆盖文件
            // 默认为false false为覆盖
            wr = new FileWriter(dest,true);
            // 写出
            String msg ="追加.....锄禾日当午\r\n码农真辛苦\r\n一本小破书\r\n一读一上午";
            wr.write(msg);
            wr.append("倒萨发了看电视剧 ");

            wr.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if (null != wr) {
                    wr.close();
                }
            } catch (Exception e2) {
            }
        }
    }

}
