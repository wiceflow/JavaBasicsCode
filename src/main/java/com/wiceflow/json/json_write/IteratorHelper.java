package com.wiceflow.json.json_write;

/**
 * 迭代器助手类
 * @author BF
 */
public final class IteratorHelper {

    /**
     * 当前摘要,输出迭代器当前游标位置附近(最多10个字符)的字符,一般用来输出错误信息
     * <pre>
     * 当前游标如果已经越界了,只会输出附近不会越界部分数据,
     * 如果游标及其附近区域全部都越界了,则只会输出当前游标值
     * </pre>
     * @param iterator [Iterator<Character>]字符数组迭代器
     * @return
     */
    public static String currentSummary(Iterator<Character> iterator) {
        StringBuilder buf = new StringBuilder();
        int cursor = iterator.getCursor();
        int maxCursor = cursor + 10;
        int length = iterator.length();
        for (int i = cursor - 10; i <= maxCursor; i ++) {
            if (i >= 0 && i < length) {
                if (i == cursor) {
                    buf.append('^');
                }
                buf.append(iterator.getElement(i));
            }
        }
        buf.append("在第" + cursor + "个字符处,已用^符号标识出来!");
        return buf.toString();
    }
}
