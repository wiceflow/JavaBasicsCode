package com.wiceflow.Sort.Comparator;
/**
 * 排序规则的业务类
 * Created by BF on 2017/9/19.
 */
public class StringComp  implements java.util.Comparator<String>{
	
	/**
	 * 按长度比较大小 
	 * 正数 >
	 * 负数 <
	 * 0 ==
	 */
	@Override
	public int compare(String o1, String o2) {
		int len1 =o1.length();
		int len2 =o2.length();		
		return -(len1-len2);
	}
	

}
