package com.wiceflow.Sort.Bubble;

import java.util.Arrays;

/**
 * 冒泡排序，正常的冒泡
 * Created by BF on 2017/9/19.
 */
public class bubbleSort01 {
    public static void main(String[] args) {
        int[] arr = {9,8,7,5,6};
        // 总共有n+-1趟  每一趟都是n-1次
        for (int i=0;i<arr.length-1;i++){
            for (int j=0;j<arr.length-1;j++){
                System.out.print("第" + (i+1) + "趟  " + "第" + (j+1) + "次");
                if (arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
                System.out.println(Arrays.toString(arr));
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
