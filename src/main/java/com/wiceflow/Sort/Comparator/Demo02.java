package com.wiceflow.Sort.Comparator;
import java.util.Arrays;

/**
 * String �������
 * Created by BF on 2017/9/19.
 */
public class Demo02 {
	public static void main(String[] args) {
		String[] arr ={"a","abcd","abc","def"};
		//�ӵ�С���� ����
		boolean sorted= true;
		int len =arr.length;
		for(int j=0;j<len-1;j++){ //����
			sorted =true; //�ٶ�����
			for(int i=0;i<len-1-j;i++){ //����
				if(((Comparable)arr[i]).compareTo(arr[i+1])<0){
					String temp = arr[i];
					arr[i] =arr[i+1];
					arr[i+1] =temp;
					sorted =false; //�ٶ�ʧ��
				}
			}
			if(sorted){ //��������
				break;
			}
		}
		
		System.out.println(Arrays.toString(arr));
	}

}
