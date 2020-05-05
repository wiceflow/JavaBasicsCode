package list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class listTest {

    @Test
    public void nullListTest1(){
        List<String> list = null;
        System.out.println(list.size());
        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }

    @Test
    public void nullListTest2(){
        List<String> list = new ArrayList<>();
        System.out.println(list.size());
        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }
}
