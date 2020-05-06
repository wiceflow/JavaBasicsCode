package list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BF
 * @date 2019/8/1 11:11
 */
public class ListTest {

    @Test
    public void test(){
        List<String> list1 = new ArrayList<>();
        list1.add("iceflow");
        list1.add("bf");
        list1.add("yn");
        list1.add("hahaha");


        List<String> list2 = new ArrayList<>(list1);

        list2.remove("hahaha");

        System.out.println(list2);
        System.out.println(list1);
    }

    @Test
    public void test2(){
        Boolean a = null;
        if (a){
            System.out.println("true");
        }else if (!a){
            System.out.println("false");
        }else {
            System.out.println("null");
        }


    }
}
