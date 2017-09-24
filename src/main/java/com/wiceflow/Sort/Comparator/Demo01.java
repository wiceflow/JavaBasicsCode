package com.wiceflow.Sort.Comparator;
/**
 * 内置引用数据类型(常用)的比较
 * Created by BF on 2017/9/19.
 */
public class Demo01 {

	public static void main(String[] args) {
		Integer  a ; //根据基本数据类型大小
		Character ch; //根据Unicode编码顺序
		String str="abc"; //如果其中一个是例外一个起始开始的子串，返回长度之差
		String str2 ="abcd123";  //否则返回第一个不相等的unicode码之差
		System.out.println(str.compareTo(str2));
		str ="abc";
		str2 ="acd";
		System.out.println(str.compareTo(str2));
		
		
		java.util.Date d ;  //根据日期的长整形数比较
	}

}
