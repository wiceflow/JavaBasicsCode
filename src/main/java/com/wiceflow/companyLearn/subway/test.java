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

//        Query que = session.createQuery("from LineEntity");
//        // 处理成Map形式
//        List<LineEntity> lineEntitylist = que.list();

//        Map<String,Integer> map = getLineName(lineEntitylist);
        // 读取Excel表获取换乘路线，将中文名换成路线编号
        List<WaitingTime> waitingTimeList = getWaitTime();
        // 设置换乘时间
        List<WaitingTime> waitingTimeList2 = getWaitTime2(waitingTimeList);

        for (WaitingTime w:waitingTimeList2)
        {
            session.save(w);
        }
        session.getTransaction().commit();

    }

    /**
     * 封装成t_transfer_time 实体类
     * @return
     */
    private static List<WaitingTime> getWaitTime() {
        List<WaitingTime> w = new ArrayList<>();
        // 读取Excel表，获取换乘站点
        Set<String> s = new HashSet<>();
        ReadUtil readUtil = new ReadUtil("F:\\换乘表.xlsx");
        s = readUtil.readxlsx();
        for(String value:s){
            String[] values = value.split("-");
            String startName = values[0];
            String endName = values[1];
            // 反转
            WaitingTime waitingTime1 = new WaitingTime();
            WaitingTime waitingTime2 = new WaitingTime();
            waitingTime1.setStartLine(Integer.parseInt(startName));
            waitingTime1.setEndLine(Integer.parseInt(endName));
            // waitingTime2
            waitingTime2.setStartLine(Integer.parseInt(endName));
            waitingTime2.setEndLine(Integer.parseInt(startName));
            w.add(waitingTime1);
            w.add(waitingTime2);
        }
        return w;
    }

    /**
     * 将实体类转换成 键值对形式
     * @return key=lineName(chinese) value=lineNO
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
            System.out.println(w.get(i).getEndLine());
            switch (waitingTime.getEndLine()){
                case 1:
                    waitingTime.setWaitingTime(90);
                    break;
                case 2:
                    waitingTime.setWaitingTime(120);
                    break;
                case 3:
                    waitingTime.setWaitingTime(90);
                    break;
                case 4:
                    waitingTime.setWaitingTime(90);
                    break;
                case 5:
                    waitingTime.setWaitingTime(120);
                    break;
                case 7:
                    waitingTime.setWaitingTime(180);
                    break;
                case 9:
                    waitingTime.setWaitingTime(180);
                    break;
                case 11:
                    waitingTime.setWaitingTime(180);
                    break;
                default:
                    System.out.println("数据出错");
                    break;
            }
        }
        return w;
    }
}
