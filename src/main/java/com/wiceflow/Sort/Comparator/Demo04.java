package com.wiceflow.Sort.Comparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
/**
 * String Date List 利用工具类比较大小
 * Created by BF on 2017/9/19.
 */
public class Demo04 {

	public static void main(String[] args) {
		Date[] arr =new Date[3];
		arr[0] =new Date();
		arr[1] =new Date(System.currentTimeMillis()-1000*60*60);
		arr[2] =new Date(System.currentTimeMillis()+1000*60*60);
		Utils.sort(arr); //降序
		System.out.println(Arrays.toString(arr));
		
		
		//字符串
		String[] arr2 ={"a","abcd","abc","def"};
		Utils.sort(arr2);
		System.out.println(Arrays.toString(arr2));
		
		
		System.out.println("==========List排序===========");
		//存放容器中
		List<String> list =new ArrayList<String>();
		list.add("a");
		list.add("abcd");
		list.add("abc");
		list.add("def");
		Utils.sort(list);
		System.out.println(list);
		
		
		
		
		System.out.println("==========使用Comparator 排序数组===============");
		arr2 =new String[]{"a","abcd","abc","def"};
		Utils.sort(arr2,new StringComp());
		System.out.println(Arrays.toString(arr2));
		
		System.out.println("==========List排序+比较器===========");
		list =new ArrayList<String>();
		list.add("a");
		list.add("abcd");
		list.add("abc");
		list.add("def");
		Utils.sort(list,new StringComp());
		System.out.println(list);
		
	}

}
