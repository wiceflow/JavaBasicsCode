package com.wiceflow.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author BF
 * @date 2017/12/15
 * String 的工具类
 */
public class StringUtil {
    /**
     * slf4j日志接口
     */
    private static final Logger _logger = LoggerFactory.getLogger(StringUtil.class);


    /**
     * 把字符型数字转换成整型.
     *
     * @param str 字符型数字
     * @return int 返回整型值。如果不能转换则返回默认值defaultValue.
     */
    public static int stringToInt(String str, int defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        if (isInt(str)) {
            return Integer.parseInt(str);
        } else {
            _logger.info(new Date() + " stringToInteger is fail");
            return defaultValue;
        }
    }

    /**
     * String 转 Double
     * @param str           [String]需要转换的字符串
     * @param defaultValue  [Double]转换失败默认值
     * @return              返回double类型数值
     */
    public static double stringToDouble(String str, double defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            _logger.info(new Date() + " stringToDouble is fail");
            return defaultValue;
        }
    }

    /**
     * String 转 Float
     * @param str           [String]需要转换的字符串
     * @param defaultValue  [Float] 转换失败后默认值
     * @return              返回float类型数值
     */
    public static float stringToFloat(String str, float defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            _logger.info(new Date() + " stringToFloat is fail");
            return defaultValue;
        }
    }

    /**
     * String 转成 long
     * @param str           [String]需要转换的字符串
     * @param defaultValue  [long]  转换失败后返回的默认值
     * @return              返回 long 类型
     */
    public static long stringToLong(String str, long defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            _logger.info(new Date() + " stringToLong is fail");
            return defaultValue;
        }
    }

    /**
     * 判断一个字符串是否为数字
     * @param str   [String] 需要转换的字符串
     * @return      通过正则验证返回
     */
    public static boolean isInt(String str) {
        return str.matches("\\d+");
    }

    /**
     * 判断一个字符串是否空
     * @param str   [String]需要判断的字符串
     * @return      若字符串为null则肯定为true 若字符串为 "",去除空格后则长度必为0
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 首字母大写
     * @param str   [String] 需要判断的字符串
     * @return      [String] 返回首字母大写的字符串
     */
    public static String upperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    /**
     * 驼峰命名转下划线分割
     * @param str   [String] 需要转换的字符串
     * @return      [String] 转后好的字符串
     */
    public static String humpTransformation(String str){
        // 定义下划线
        String underLine = "_";
        StringBuilder sb = new StringBuilder();
        char[] ch = str.toCharArray();
        for (int i = 0;i < ch.length;i++){
            char c = ch[i];
            // 首字母转小写
            if (c >= 'A' && c <= 'Z'){
                c = (char)(c + 32);
                // 首字母不需要先加 _
                if (i == 0){
                    sb.append(c);
                }else {
                    sb.append(underLine).append(c);
                }
            }else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
