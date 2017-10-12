package com.wiceflow.Socket.tcp.ThreadClient;

import java.io.Closeable;
import java.io.IOException;

/**
 * 关闭流方法
 * Created by BF on 2017/9/26.
 */
public class CloseUtil {
    public static void closeAll(Closeable... io){
        for (Closeable temp : io){
            if (temp!=null){
                try {
                    temp.close();
                } catch (IOException e) {
                    System.out.println("工具类关闭流失败");
                }
            }
        }
    }
}
