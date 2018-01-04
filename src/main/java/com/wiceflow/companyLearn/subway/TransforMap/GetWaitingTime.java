package com.wiceflow.companyLearn.subway.TransforMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by BF on 2018/1/2.
 * 获取换乘时间
 */
public class GetWaitingTime {

    private static Map<String,Integer> waitingTimeMap = new HashMap<>();
    private static int version = -1;
    private  Dao dao = null;
    public GetWaitingTime(){
        init();
    }

    private void init() {
        dao = new Dao();
    }
    /**
     * 获取站点-等待时间map
     * 这个map已经去重，保证了所有站点都是可以换乘的。
     * @return
     */
    public Map<String,Integer> getWaitingTimeMap(int version){
        Map<String,Integer> map = new HashMap<>();
        map = dao.getWaitTime(version);
        return map;
    }

    /**
     * 获取等待时间，直接传入两条路线
     * @param startLine 起始线路
     * @param endLine 需要到达的转线线路
     * @param version 版本号
     * @return 返回-1代表不是换乘站
     */
    public int getWaitingTime(int startLine,int endLine,int version){
        String line = startLine + "-" + endLine;
        Map<String,Integer> map = getWaitingTimeMap(version);
        if (map.get(line)!=null){
            return map.get(line);
        }else
        {
            line = endLine + "-" + startLine;
            if (map.get(line)!=null){
                return map.get(line);
            }
            else {
                System.out.println("输出的站点错误，不是可换乘站");
                return -1;
            }
        }
    }
}
