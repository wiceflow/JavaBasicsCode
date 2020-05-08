package com.wiceflow.Io.byteIO;

import java.io.*;

/**
 * 文件操作
 * 1、文件拷贝
 * 2、文件夹拷贝  拒绝自己拷贝给自己
 * Created by BF on 2017/9/24.
 */
public class FileUtil {
    /**
     * 拷贝文件夹
     *
     * @param srcPath  源路径
     * @param destPath 目标路径
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void copyDir(String srcPath, String destPath) throws FileNotFoundException, IOException {
        File src = new File(srcPath);
        File dest = new File(destPath);
        copyDir(src, dest);
    }

    /**
     * 拷贝文件夹
     *
     * @param src  源File对象
     * @param dest 目标File对象
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void copyDir(File src, File dest) throws FileNotFoundException, IOException {
        // 拒绝自己拷贝给自己
        if (src.getAbsolutePath().equals(dest.getAbsolutePath())){
            System.out.println("拒绝自己拷贝给自己");
            return;
        }
        if (src.isDirectory()) { //文件夹
            dest = new File(dest, src.getName());
            if (dest.getAbsolutePath().contains(src.getAbsolutePath())) {
                System.out.println("父目录不能拷贝到子目录中");
                return;
            }
        }
        copyDirDetail(src, dest);
    }

    /**
     * 拷贝文件夹细节
     *
     * @param src
     * @param dest
     */
    public static void copyDirDetail(File src, File dest) throws FileNotFoundException, IOException {
        if (src.isFile()) { //文件
            try {
                copyFile(src, dest);
            } catch (FileNotFoundException e) {
                //e.printStackTrace();
                throw e;
            } catch (IOException e) {
                //e.printStackTrace();
                throw e;
            }
        } else if (src.isDirectory()) { //文件夹
            //确保目标文件夹存在 创建所有不存在的文件夹
            dest.mkdirs();
            //获取下一级目录|文件
            File[] files = src.listFiles();
            for (File sub : files) {
                // 利用递归创建文件夹与文件
                copyDirDetail(sub, new File(dest, sub.getName()));
            }
        }
    }

    /**
     * 文件的拷贝
     *
     * @param srcPath  源文件路径
     * @param destPath 目录文件路径
     * @return
     * @throws FileNotFoundException,IOException
     */
    public static void copyFile(String srcPath, String destPath) throws FileNotFoundException, IOException {
        //1、建立联系 源(存在且为文件) +目的地(文件可以不存在)
        copyFile(new File(srcPath), new File(destPath));
    }

    /**
     * 文件的拷贝
     *
     * @param src 源文件File对象
     * @param dest 目录文件File对象
     * @return
     * @throws FileNotFoundException,IOException
     */
    public static void copyFile(File src, File dest) throws FileNotFoundException, IOException {
        if (!src.isFile()) { //不是文件或者为null
            System.out.println("只能拷贝文件");
            throw new IOException("只能拷贝文件");
        }
        //dest为已经存在的文件夹，不能建立于文件夹同名的文件 这里指的是创建的文件没有指定后缀的时候
        if (dest.isDirectory()&&dest.getName().equals(src.getName())) {
            System.out.println(dest.getAbsolutePath() + "不能建立于文件夹同名的文件");
            throw new IOException(dest.getAbsolutePath() + "不能建立于文件夹同名的文件");
        }
        // 如果前面没有将生成后的文件名传入生成路径中，则合成
        if (dest.isDirectory()){
            dest = new File(dest ,src.getName());
        }
        System.out.println(src.getAbsolutePath());
        System.out.println(dest.getAbsolutePath());
        //2、选择流
        InputStream is = new BufferedInputStream(new FileInputStream(src));
        OutputStream os = new BufferedOutputStream(new FileOutputStream(dest));
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
