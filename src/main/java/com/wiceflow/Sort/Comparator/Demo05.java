package com.wiceflow.Sort.Comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Demo05 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> list =new ArrayList<String>();
		list.add("a");
		list.add("abcd");
		list.add("abc");
		list.add("def");
		Collections.sort(list,new com.wiceflow.Sort.Comparator.StringComp());
		System.out.println(list);
		
		
		list =new ArrayList<String>();
		list.add("a");
		list.add("abcd");
		list.add("abc");
		list.add("def");
		Collections.sort(list);
		System.out.println(list);
		
		
	}

}
