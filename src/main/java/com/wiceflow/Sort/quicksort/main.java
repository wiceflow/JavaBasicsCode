package com.wiceflow.Sort.quicksort;

import java.util.Scanner;

/**
 * Created by BF on 2017/10/2.
 */
public class main {
    public static void main(String[] args) {
        int[] a = new int[101];
        // 控制台输入
        Scanner in = new Scanner(System.in);
        // 代表有N个数
        Integer count = in.nextInt();
        for (int i=0;i<count;i++){
            // 6 1 2 7 9 3 4 5 10 8
            Scanner in2 = new Scanner(System.in);
            a[i] = in2.nextInt();
        }
        quicksort quicksort = new quicksort(a);
        quicksort.quicksort(0,count-1);

        a = quicksort.getB();
        for (int i=0;i<count;i++){
            System.out.println(a[i]);
        }
    }
}
