package com.wiceflow.json.json_write;

import java.util.ArrayList;
import java.util.List;
/**
 * JSON数据解析器
 * @author BF
 */
public class DefaultJsonParser implements BaseJsonParser {

    @Override
    public Entry parse(String json) {
        Iterator<Character> iterator = 
                new Iterator<Character>(CharUtil.toCharacterArray(json.toCharArray()));
        return getObject(iterator);
    }

    /**
     * 判断下一个非空字符是否是指定字符
     * @param iterator [Iterator<Character>]JSON字符数组迭代器
     * @param c        [char]判断下一个字符的参照值
     * @param move     [boolean]判断成功后是否游标偏移
     * @return 找到了指定字符返回true,否者返回false
     */
    private boolean nextCharIs(Iterator<Character> iterator, char c, boolean move) {
        // 第一个非空字符下标
        skipWhiteSpace(iterator);
        if (iterator.getElement() == c) {
            // 游标指向下一个字符
            if (move) {
                iterator.next();
            }
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 获取小数
     * @param iterator [Iterator<Character>]JSON字符数组迭代器
     * @return
     */
    private double getDecimal(Iterator<Character> iterator) {
        int start = iterator.getCursor();
        iterator.last();
        while (iterator.hasNext() && CharUtil.isNumber(iterator.nextElement()));
        double result = 0;
        for (int i = iterator.getCursor() - 1; i >= start; i --) {
            result = result / 10 + iterator.getElement(i) - '0'; 
        }
        return result / 10;
    }
    
    /**
     * 获取有符号数值
     * @param iterator [Iterator<Character>]JSON字符数组迭代器
     * @return
     */
    private double getSignNumber(Iterator<Character> iterator) {
        int sign = 1;
        switch (iterator.getElement()) {
            case '-': sign = -1;
            case '+': iterator.next();
            default: break;
        }
        double result = 0;
        while (iterator.isNotEnd() && CharUtil.isNumber(iterator.getElement())) {
            result = result * 10 + iterator.elementNext() - '0';
        }
        return sign * result;
    }
    
    /**
     * 获取无符号数值
     * @param iterator [Iterator<Character>]JSON字符数组迭代器
     * @return
     */
    private double getUnsignNumber(Iterator<Character> iterator) {
        double result = 0;
        while (iterator.isNotEnd() && CharUtil.isNumber(iterator.getElement())) {
            result = result * 10 + iterator.elementNext() - '0';
        }
        return result;
    }
    
    /**
     * 获取数值(double)
     * @param iterator [Iterator<Character>]JSON字符数组迭代器
     * @return
     */
    private double getNumber0(Iterator<Character> iterator) {
     // 数值的符号
        int sign = 1;
        switch (iterator.getElement()) {
            case '-': sign = -1;
            case '+': iterator.next();
            default: break;
        }
        StringBuilder buf = new StringBuilder();
        // 获取无符号数值
        while (iterator.isNotEnd() && CharUtil.isNumber(iterator.getElement())) {
            buf.append(iterator.elementNext());
        }
        // 是否能够寻找小数
        boolean canGetDecimal = true;
        // 是否能够累乘指数
        boolean canGetExponent = true;
        while(iterator.isNotEnd()) {
            switch (iterator.getElement()) {
                case '.': 
                    // 读取小数部分
                    if (canGetDecimal) {
                        buf.append(iterator.elementNext());
                        while (CharUtil.isNumber(iterator.getElement())) {
                            buf.append(iterator.elementNext());
                        }
                        // 不能再读取小数
                        canGetDecimal = false;
                    } else {
                        throw new IllegalArgumentException("json data format error, "
                                + "number format error for '.' position"
                                + IteratorHelper.currentSummary(iterator));
                    }
                    break;
                case 'e': case 'E': 
                    if(canGetExponent) {
                        // 开始读取指数之后就不允许读取小数了
                        canGetDecimal = false;
                        // 只能读取一次指数
                        canGetExponent = false;
                        buf.append(iterator.elementNext());
                        while (CharUtil.isNumber(iterator.getElement())) {
                            buf.append(iterator.elementNext());
                        }
                    } else {
                        throw new IllegalArgumentException("json data format error, "
                                + "number format error for 'e'('E') position"
                                + IteratorHelper.currentSummary(iterator));
                    }
                    break;
                // 出口
                default: return sign * Double.parseDouble(buf.toString());
            }
        }
        throw new IllegalArgumentException("json data format error, "
                + "can not read a vaild number"
                + IteratorHelper.currentSummary(iterator));
    }
    
    /**
     * 获取数值(double)
     * @param iterator [Iterator<Character>]JSON字符数组迭代器
     * @return
     */
    private double getNumber(Iterator<Character> iterator) {
        // 数值的符号
        int sign = 1;
        switch (iterator.getElement()) {
            case '-': sign = -1;
            case '+': iterator.next();
            default: break;
        }
        // 获取无符号数值
        double result = getUnsignNumber(iterator);
        // 是否能够寻找小数
        boolean canGetDecimal = true;
        // 是否能够累乘指数
        boolean canGetExponent = true;
        while(iterator.isNotEnd()) {
            switch (iterator.getElement()) {
                case '.': 
                    // 读取小数部分
                    if (canGetDecimal) {
                        iterator.next(); 
                        result += getDecimal(iterator);
                        canGetDecimal = false;
                    } else {
                        throw new IllegalArgumentException("json data format error, "
                                + "number format error for '.' position"
                                + IteratorHelper.currentSummary(iterator));
                    }
                    break;
                case 'e': case 'E': 
                    if(canGetExponent) {
                        // 开始读取指数之后就不允许读取小数了
                        canGetDecimal = false;
                        iterator.next();
                        result *= Math.pow(10, getSignNumber(iterator));
                    } else {
                        throw new IllegalArgumentException("json data format error, "
                                + "number format error for 'e'('E') position"
                                + IteratorHelper.currentSummary(iterator));
                    }
                    break;
                // 出口
                default: return sign * result;
            }
        }
        throw new IllegalArgumentException("json data format error, "
                + "can not read a vaild number"
                + IteratorHelper.currentSummary(iterator));
    }
    
    /** 数组起始字符 */
    private static final char ARRAY_START     = '[';
    /** 数组终止字符 */
    private static final char ARRAY_END       = ']';
    /** 数组分隔符 */
    private static final char ARRAY_SEPARATOR = ',';
    
    /**
     * 获取数组值
     * @param iterator [Iterator<Character>]JSON字符数组迭代器
     * @return
     */
    private Object[] getArray(Iterator<Character> iterator) {
        if (!nextCharIs(iterator, ARRAY_START, true)) {
            throw new IllegalArgumentException("json data format error, "
                    + "array must start with '['"
                    + IteratorHelper.currentSummary(iterator));
        } else {
            // 空数组
            if (nextCharIs(iterator, ARRAY_END, true)) {
                return new Object[0];
            } else {
                // 存储值的不定长数组
                List<Object> list = new ArrayList<Object>();
                // 是否寻找分隔符
                boolean findSeparator = false;
                for(; iterator.isNotEnd();) {
                    if (findSeparator) {
                        if (nextCharIs(iterator, ARRAY_SEPARATOR, true)) {
                            findSeparator = false;
                        } else if (nextCharIs(iterator, ARRAY_END, true)) {
                            return list.toArray();
                        } else {
                            throw new IllegalArgumentException("json data format error, "
                                    + "separator ',' must be exist between value and value " 
                                    + "and terminator ']' must be exit in end of array"
                                    + IteratorHelper.currentSummary(iterator));
                        };
                    } else {
                        list.add(getValue(iterator));
                        findSeparator = true;
                    }
                }
                // 遍历完数组还没截出一个数组
                throw new IllegalArgumentException("json data format error, "
                        + "can not read a vaild array"
                        + IteratorHelper.currentSummary(iterator));
            }
        }
    }
    
    /** null值字符数组 */
    private static final char[] NULL_CHAR_ARR = "null".toCharArray();
    
    /**
     * 获取null值
     * @param iterator [Iterator<Character>]JSON字符数组迭代器
     * @return
     */
    private Object getNull(Iterator<Character> iterator) {
        for(int i = 0; i < NULL_CHAR_ARR.length; i ++, iterator.next()) {
            if (iterator.isEnd()) {
                // 遍历完数组还没截出一个null
                throw new IllegalArgumentException("json data format error, "
                        + "can not read a vaild null value"
                        + IteratorHelper.currentSummary(iterator));
            } else if(Character.toLowerCase(iterator.getElement()) != NULL_CHAR_ARR[i]) {
                // 转换成小写字符串再进行比较
                throw new IllegalArgumentException("json data format error, "
                        + "null value format error"
                        + IteratorHelper.currentSummary(iterator));
            } else {
                continue;
            }
        }
        return null;
    }
    
    /** 布尔值true字符数组 */
    private static final char[] TRUE_CHAR_ARR  = "true".toCharArray();
    /** 布尔值false字符数组 */
    private static final char[] FALSE_CHAR_ARR = "false".toCharArray();
    
    /**
     * 获取布尔值
     * @param iterator [Iterator<Character>]JSON字符数组迭代器
     * @param value    [boolean]待获取的布尔值
     * @return
     */
    private boolean getBoolean(Iterator<Character> iterator, boolean value) {
        char[] booleanArr = value ? TRUE_CHAR_ARR : FALSE_CHAR_ARR;
        for (int i = 0; i < booleanArr.length; i ++, iterator.next()) {
            if (iterator.isEnd()) {
                // 遍历完数组还没截出一个布尔值
                throw new IllegalArgumentException("json data format error, "
                        + "can not read a vaild boolean value [" + value + "]"
                        + IteratorHelper.currentSummary(iterator));
            } else if(Character.toLowerCase(iterator.getElement()) != booleanArr[i]) {
                // 转换成小写字符再比较
                throw new IllegalArgumentException("json data format error, "
                        + "boolean value format error for [" + value + "]"
                        + IteratorHelper.currentSummary(iterator));
            } else {
                continue;
            }
        }
        return value;
    }
    
    /**
     * 获取value值
     * @param iterator [Iterator<Character>]JSON字符数组迭代器
     * @return
     */
    private Object getValue(Iterator<Character> iterator) {
        // 第一个非空字符下标
        skipWhiteSpace(iterator);
        switch (iterator.getElement()) {
        case '\"': return getString(iterator);
        case '[' : return getArray(iterator);
        case 'f' : return getBoolean(iterator, false);
        case 't' : return getBoolean(iterator, true);
        case 'n' : return getNull(iterator);
        case '0': case '1': case '2': case '3': case '4': case '-':
        case '5': case '6': case '7': case '8': case '9': case '+':
        return getNumber0(iterator);
        case '{': return getObject(iterator);
        default: 
            throw new IllegalArgumentException("json data format error, "
                    + "can not recognize a vaild value"
                    + IteratorHelper.currentSummary(iterator));
        }
    }
    
    /**
     * 滤掉字符数组中从指定下标开始的前导空格,并让迭代器游标指向第一个非空白字符
     * @param iterator [Iterator<Character>]JSON字符数组迭代器
     * @throws IllegalArgumentException 起始下标之后数组全为空格
     */
    private void skipWhiteSpace(Iterator<Character> iterator) throws IllegalArgumentException {
        while (iterator.isNotEnd()) {
            if (CharUtil.isWhitespace(iterator.getElement())) {
                iterator.next();
            } else {
                return;
            }
        }
        throw new IllegalArgumentException("json data format error, "
                + "the array of characters should not end with white spaces"
                + IteratorHelper.currentSummary(iterator));
    }
    
    /**
     * 获取一个键值对节点
     * @param iterator [Iterator<Character>]JSON字符数组迭代器
     * @return
     */
    private Entry getEntry(Iterator<Character> iterator) {
        Entry entry = new Entry();
        // 获取key
        entry.setKey(getString(iterator));
        if (nextCharIs(iterator, ':', true)) {
            // 获取value
            entry.setValue(getValue(iterator));
        } else {
            throw new IllegalArgumentException("json data format error, "
                    + "after key is ':'"
                    + IteratorHelper.currentSummary(iterator));
        }
        return entry;
    }
    
    /**
     * 读取一个对象,如果对象为空,则返回null
     * @param iterator [Iterator<Character>]JSON字符数组迭代器
     * @return
     * @throws IllegalArgumentException
     */
    private Entry getObject(Iterator<Character> iterator) throws IllegalArgumentException {
        if (!nextCharIs(iterator, '{', true)) {
            throw new IllegalArgumentException("json data format error, "
                    + "object must start with '{'"
                    + IteratorHelper.currentSummary(iterator));
        } else {
            // 如果遇到右括号,直接返回空的Entry
            if (iterator.getElement() == '}') {
                iterator.next();
                return new Entry();
            } else {
                // 根节点
                Entry root = getEntry(iterator);
                // 下标结点
                Entry index = root;
                while (!nextCharIs(iterator, '}', true)) {
                    if(iterator.getElement() == ',') {
                        // 游标偏移到下一位
                        iterator.next();
                        index.setNext(getEntry(iterator));
                        index = index.getNext();
                    } else {
                        throw new IllegalArgumentException("json data format error, "
                                + "object must be end with '}' or key-value must separate by ','"
                                + IteratorHelper.currentSummary(iterator));
                    }
                }
                return root;
            }
        }
    }
    
    /**
     * 获取一段双引号包裹的字符串
     * @param iterator [Iterator<Character>]JSON字符数组迭代器
     * @return
     */
    private String getString(Iterator<Character> iterator) {
        if (!nextCharIs(iterator, '\"', true)) {
            throw new IllegalArgumentException("json data format error, "
                    + "string must start with '\"'"
                    + IteratorHelper.currentSummary(iterator));
        } else {
            StringBuilder buf = new StringBuilder();
            // 上一个字符是转义符(\)
            boolean precedingBackslash = false;
            for (; iterator.isNotEnd(); iterator.next()) {
                if (precedingBackslash) {
                    precedingBackslash = false;
                    // 获取前置反斜杠的字符
                    buf.append(afterBackslash(iterator));
                } else if (iterator.getElement() == '\\') {
                    precedingBackslash = true;
                } else if (iterator.getElement() != '\"') {
                    buf.append(iterator.getElement());
                } else {
                    // 下标指向下一个字符
                    iterator.next();
                    return buf.toString();
                }
            }
            throw new IllegalArgumentException("json data format error, "
                    + "string must be between double quotes"
                    + IteratorHelper.currentSummary(iterator));
        }
    }
    
    /**
     * 获取前置反斜杠的字符
     * @param iterator [Iterator<Character>]JSON字符数组迭代器
     * @param c        [char]当前字符
     * @return
     */
    private char afterBackslash(Iterator<Character> iterator) {
        char c = iterator.getElement();
        switch (c) {
            case 'b': case 'B': return '\b';
            case 'f': case 'F': return '\f';
            case 'n': case 'N': return '\n';
            case 'r': case 'R': return '\r';
            case 't': case 'T': return '\t';
            case 'u': case 'U': 
                int value=0;
                for (int i = 0; iterator.hasNext() && i < 4; i ++) {
                    c = iterator.nextElement();
                    switch (c) {
                        case '0': case '1': case '2': case '3': case '4':
                        case '5': case '6': case '7': case '8': case '9':
                            value = (value << 4) + c - '0';
                            break;
                        case 'a': case 'b': case 'c':
                        case 'd': case 'e': case 'f':
                            value = (value << 4) + 10 + c - 'a';
                            break;
                        case 'A': case 'B': case 'C':
                        case 'D': case 'E': case 'F':
                            value = (value << 4) + 10 + c - 'A';
                            break;
                        default:
                            throw new IllegalArgumentException(
                                    "Malformed \\uxxxx encoding."
                                            + IteratorHelper.currentSummary(iterator));
                    }
                };
                return (char) value;
            default: return c;
        }
    }
    
}
