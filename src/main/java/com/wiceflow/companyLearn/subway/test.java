package com.wiceflow.companyLearn.subway;


import com.wiceflow.util.ReadUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.*;

/**
 * Created by BF on 2017/12/29.
 */
public class test {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure("company/hibernateCPY.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Query que = session.createQuery("from LineEntity");
        // 处理成Map形式
        List<LineEntity> lineEntitylist = que.list();
        Map<String,Integer> map = getLineName(lineEntitylist);

        List<WaitingTime> waitingTimeList = getWaitTime(map);

        List<WaitingTime> waitingTimeList2 = getWaitTime2(waitingTimeList);

        for (WaitingTime w:waitingTimeList2)
        {
            session.save(w);
        }


        session.getTransaction().commit();

    }

    /**
     * 封装成t_transfer_time 实体类
     * @param m
     * @return
     */
    private static List<WaitingTime> getWaitTime(Map<String,Integer> m) {
        List<WaitingTime> w = new ArrayList<>();
        Set<String> s = new HashSet<>();
        ReadUtil readUtil = new ReadUtil("F:\\换乘表.xlsx");
        s = readUtil.readxlsx();
        // 死循环
//        while(s.iterator().hasNext()){
//            String start_end_Name = s.iterator().next();
//            System.out.println(start_end_Name);
//        }

        for(String value:s){
            String[] values = value.split("-");
            String startName = values[0];
            String endName = values[1];
            int startCode = m.get(startName);
            int endCode = m.get(endName);
            WaitingTime waitingTime = new WaitingTime();
            waitingTime.setStartLine(startCode);
            waitingTime.setEndLine(endCode);
            w.add(waitingTime);
        }
        return w;
    }

    /**
     * 将实体类转换成 键值对形式
     * @return
     */
    private static Map<String,Integer> getLineName(List<LineEntity> lists){
        Map<String,Integer> map = new HashMap<>();
        for (LineEntity e:lists){
            map.put(e.getLineName(), Integer.valueOf(e.getLineNo()));
        }
        return map;
    }
    private static List<WaitingTime> getWaitTime2(List<WaitingTime> w){
        for (int i=0;i<w.size();i++){
            WaitingTime waitingTime = w.get(i);
            switch (waitingTime.getEndLine()){
                case 268:
                    waitingTime.setWaitingTime(90);
                    break;
                case 260:
                    waitingTime.setWaitingTime(120);
                    break;
                case 261:
                    waitingTime.setWaitingTime(90);
                    break;
                case 262:
                    waitingTime.setWaitingTime(90);
                    break;
                case 263:
                    waitingTime.setWaitingTime(120);
                    break;
                case 265:
                    waitingTime.setWaitingTime(180);
                    break;
                case 267:
                    waitingTime.setWaitingTime(180);
                    break;
                case 241:
                    waitingTime.setWaitingTime(180);
                    break;
            }
        }
        return w;
    }
}
