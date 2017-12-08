package com.wiceflow.json.json_write;

/**
 * 字符工具类
 * @author BF
 */
public final class CharUtil {

    /**
     * 判断是否是空白字符
     * @param c [char]待判断字符
     * @return 如果是空白字符返回true,否则返回false
     */
    public static boolean isWhitespace(char c) {
        // \t\n\x0B\f\r
        return Character.isWhitespace(c);
    }
    
    /**
     * 判断字符是否是数字
     * @param c [char]待判断字符
     * @return 如果字符是数字字符则返回true,否则返回false
     */
    public static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }
    
    /**
     * 字符数组转换成封装类Character数组
     * @param chars [char[]]待转换数组
     * @return
     */
    public static Character[] toCharacterArray(char[] chars) {
        Character[] res = new Character[chars.length];
        for (int i = 0; i < chars.length; i ++) {
            res[i] = chars[i];
        }
        return res;
    }
    
}
