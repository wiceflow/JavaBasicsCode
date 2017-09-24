package com.wiceflow.Sort.Bubble;

import java.util.Arrays;

/**
 * 冒泡排序优化
 * 壹.减少对比次数
 * 贰.减少循环的趟数，当发现第二趟对比的时候没有发生交换，则退出循环
 * Created by BF on 2017/9/19.
 */
public class bubbleSort02 {
    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 5, 6};
        int[] brr = {9,1,2,3,4};
        sortSec(arr);
        sortThree(brr);
    }

    /**
     * 最终版
     * 判断是否有序，有则直接退出循环
     * @param arr
     */
    public static void sortThree(int[] arr){
        // 判断是否退出循环标识
        boolean isBreak = true;
        // 总共有n+-1趟  每一趟都是n-1次
        for (int i = 0; i < arr.length - 1; i++) {
            // 每次进行循环前都将其初始化
            isBreak = true;
            // 当越来越大的数往后面填充的时候，则与最大的数进行对比就是多余的
            for (int j = 0; j < arr.length-1-i; j++) {
                System.out.print("第" + (i + 1) + "趟  " + "第" + (j + 1) + "次");
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    // 若进行了排序则设为false
                    isBreak = false;
                }
                System.out.println(Arrays.toString(arr));
            }
            if (!isBreak){
                break;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 简单优化，每走一趟就减少一次
     * @param arr
     */
    public static void sortSec(int[] arr){
        // 总共有n+-1趟  每一趟都是n-1次
        for (int i = 0; i < arr.length - 1; i++) {
            // 当越来越大的数往后面填充的时候，则与最大的数进行对比就是多余的
            for (int j = 0; j < arr.length-1-i; j++) {
                System.out.print("第" + (i + 1) + "趟  " + "第" + (j + 1) + "次");
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
                System.out.println(Arrays.toString(arr));
            }
        }
        System.out.println(Arrays.toString(arr));
    }


    /**
     * 第一个版本
     * 这里的每次排序都会进行对比，第一趟将最大的数放到最后后，接下来每进行一趟对比，都会和最大数对比，这样就浪费了时间
     * @param arr
     */
    public static void sortFirst(int[] arr) {
        // 总共有n+-1趟  每一趟都是n-1次
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length-1; j++) {
                System.out.print("第" + (i + 1) + "趟  " + "第" + (j + 1) + "次");
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
                System.out.println(Arrays.toString(arr));
            }
        }
        System.out.println(Arrays.toString(arr));
    }

}
