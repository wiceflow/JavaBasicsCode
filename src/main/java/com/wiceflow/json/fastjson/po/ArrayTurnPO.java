package com.wiceflow.json.fastjson.po;

/**
 * Created by BF on 2017/12/13.
 * 将解析的数组转成po对象
 */
public class ArrayTurnPO {

    private static TableForJSON table = new TableForJSON();

    /**
     * 注册
     * @param table1
     * @return
     */
    public static TableForJSON trun(table table1){
        turnAllTrafficClumn(table1);
        trunTrafficInAll(table1);
        trunSec(table1);
        return table;
    }

    /**
     * 对应all
     * 将 数组的0位置设置成key  1位置设置成value
     * @param table1
     */
    private static void turnAllTrafficClumn(table table1){
        String[][] allTraffic = table1.getAll();
        AllTrafficColumn a = new AllTrafficColumn();
        for (int x=0;x<allTraffic.length;x++){
            switch (x){
                case 0:
                    System.out.println(x);
                    a.setWeek(allTraffic[x][1]);break;
                case 1:
                    System.out.println(x);
                    a.setStartDate(Integer.parseInt(allTraffic[x][1]));break;
                case 2:
                    System.out.println(x);
                    a.setFullTimeSpeed(Double.parseDouble(allTraffic[x][1]));break;
                case 3:
                    System.out.println(x);
                    a.setEarlySpeed(Double.parseDouble(allTraffic[x][1]));break;
                case 4:
                    System.out.println(x);
                    a.setLateSpeed(Double.parseDouble(allTraffic[x][1]));break;
                case 5:
                    System.out.println(x);
                    a.setPeakSpeed(Double.parseDouble(allTraffic[x][1]));break;
                case 6:
                    a.setCentralCityPeakSpeed(Double.parseDouble(allTraffic[x][1]));break;
                case 7:
                    a.setLastYearPeakSpeed(Double.valueOf(allTraffic[x][1]));break;
                case 8:
                    a.setLastYearCentralCityPeakSpeed(Double.valueOf(allTraffic[x][1]));break;
                case 9:
                    a.setCityPeakSpeedChain(allTraffic[x][1]); break;
                case 10:
                    a.setCityPeakSpeedYoy(allTraffic[x][1]);break;
                case 11:
                    a.setCentralCityPeakSpeedChain(allTraffic[x][1]);break;
                case 12:
                    a.setCentralCityPeakSpeedYoy(allTraffic[x][1]);break;
            }
        }
        table.setPeriod(a.getWeek());
        table.setAllTrafficColumn(a);
    }

    /**
     * 对应area  5 4
     * @param table1
     */
    private static void trunTrafficInAll(table table1){
        String[][] trafficInAll = table1.getArea();
        for (int x=0;x<trafficInAll.length;x++){
            TrafficInAll t = new TrafficInAll();
            // 11时取平均值
            if (x==10){
                t.setDministrativeRegion(trafficInAll[x][0]);
                t.setLastWeek(Double.parseDouble(trafficInAll[x][1]));
                t.setThisWeek(Double.parseDouble(trafficInAll[x][2]));
                t.setChainRatio(trafficInAll[x][3]);
                t.setIndex(999999);
            }else{
                t.setDministrativeRegion(trafficInAll[x][0]);
                t.setLastWeek(Double.parseDouble(trafficInAll[x][1]));
                t.setThisWeek(Double.parseDouble(trafficInAll[x][2]));
                t.setChainRatio(trafficInAll[x][3]);
                t.setIndex(Double.parseDouble(trafficInAll[x][4]));
            }
            // 注册
            table.getTrafficInAll().add(t);
        }
    }

    /**
     * 对应cross  11 10
     * @param table1
     */
    private static void trunSec(table table1){
        String[][] secondLine = table1.getCross();
        for (int x=0;x<secondLine.length;x++){
            SecondLineTraffic second = new SecondLineTraffic();
            // 早高峰
            second.setPass(secondLine[x][0]);
            second.setAmSameAsLastYear(Double.parseDouble(secondLine[x][1]));
            second.setAmLastWeek(Double.parseDouble(secondLine[x][2]));
            second.setAmWeek(Double.parseDouble(secondLine[x][3]));
            second.setAmSpeedMom(secondLine[x][4]);
            second.setAmOperatingStatus(secondLine[x][5]);
            // 晚高峰
            second.setPmSameAsLastYear(Double.parseDouble(secondLine[x][6]));
            second.setPmLastWeek(Double.parseDouble(secondLine[x][7]));
            second.setPmWeek(Double.parseDouble(secondLine[x][8]));
            second.setPmSpeedMom(secondLine[x][9]);
            second.setPmOperatingStatus(secondLine[x][10]);
            // 注册
            table.getSecondLineTraffic().add(second);
        }
    }
}
