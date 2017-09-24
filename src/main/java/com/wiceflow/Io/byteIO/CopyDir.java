package com.wiceflow.Io.byteIO;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 文件夹的拷贝
 * 1、文件 赋值  copyFile
 * 2、文件 创建 mkdirs()
 * 3、递归查找子孙级
 * Created by BF on 2017/9/24.
 */
public class CopyDir {
    public static void main(String[] args) {
        //源目录
        String srcPath = "E:/xp/test/a/aaa.doc";
        //目标目录
        String destPath = "d:/java/test/a";
        try {
            FileUtil.copyDir(srcPath, destPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}