package com.wiceflow.Proxy;

import java.util.Objects;
import java.util.Scanner;

/**
 * Created by BF on 2017/10/11.
 */
//public class Test {
//    public static void main(String[] args) {
//        System.out.println(System.getProperty("user.dir"));
//        System.out.println(System.getProperty("java.class.path"));
//
//    }
//}
public class Test {
    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            int length = input.length();
            if (length < 2) {
                System.out.println("false");
            } else {
                int first = 0;
                int last = length - 1;
                boolean isString = true;
                for (int j = 0; j < length; j++) {
                    String str = input.substring(j,j+1);
                    if (j == first || j == last){
                        if (!Objects.equals(str,"\"")){
                            isString = false;
                            break;
                        }
                        continue;
                    }
                    if (Objects.equals(str,"\"")){
                        String st = input.substring(j-1,j);
                        if (!Objects.equals(st, "\\")) {
                            isString = false;
                            break;
                        }
                    }
                }
                if (isString){
                    System.out.println("true");
                }else {
                    System.out.println("false");
                }
            }
        }
    }
}