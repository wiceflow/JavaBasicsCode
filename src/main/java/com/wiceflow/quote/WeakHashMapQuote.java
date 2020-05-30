package com.wiceflow.quote;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author BF
 * @date 2020/5/17
 * <p>
 * �����ò���
 * 1��WeakHashMap
 * 2��HashMap
 * <p> �û�ѧϰ��������� </p>
 * <p>
 * �����ȿ�ǰ��  HashMap
 */
public class WeakHashMapQuote {

    public static void main(String[] args) {

        WeakHashMapQuote quote = new WeakHashMapQuote();
        quote.strongQuote();


    }

    /**
     * ǿ���õ� hashMap ��ֵ�Ա�
     */
    public void strongQuote() {
        House house1 = new House("һ�ŷ�Դ");
        People people1 = new People("BF", 24);

        House house2 = new House("���ŷ�Դ");
        People people2 = new People("YN", 23);

        // hashMap ǿ����
        Map<People, House> strongMap = new HashMap<>(2);
        strongMap.put(people1, house1);
        strongMap.put(people2, house2);
        // size =2
        System.out.println(strongMap.toString());
        // ���� gc
        people1 = null;
        System.gc();
        System.runFinalization();
        // strongMap �л��Ǵ�������Ԫ��
        System.out.println(strongMap.toString());
        // ��Ϊ people1 ָ���� null���������ﲻ���ٻ�ȡ��Ԫ����
        System.out.println(strongMap.get(people1));
        System.out.println(strongMap.get(people2));

        // ͨ���������Է��֣�strongMap �е�Ԫ��δ�������ı�
        Set<People> peopleSet = strongMap.keySet();
        System.out.println(peopleSet);
        for (People people : peopleSet) {
            System.out.println(people.toString());
            System.out.println(strongMap.get(people));
        }
    }
}
