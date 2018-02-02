package com.wiceflow.companyLearn.subway.TransforMap;

import com.mchange.lang.IntegerUtils;

import java.util.*;

/**
 * @author BF
 * @date 2018/2/2
 * 获取候车时间新版
 */
public class NewGetWaitingTime {

    private static NewDao dao = new NewDao();

    private static Map<String, Integer> waitingTimeMap = null;

    public static Map<String, Integer> getWaitingTimeMap(long version) {
        if (waitingTimeMap != null) {
            return waitingTimeMap;
        }
        List<String> interChanges = new ArrayList<>();
        Map<String, Integer> waitTimeMap = new HashMap<>();
        Map<String, Double> lineMap = dao.getLineDate(version);
        Set<String> interChangeSet = dao.getInterChange(version);
        // 这个list肯定是无序的
        interChanges.addAll(interChangeSet);
        for (int i = 0; i < interChanges.size(); i++) {
            String[] str = interChanges.get(i).split("-");
            String staLineNo = str[0];
            String subLineNO = str[1];

            String SELine = staLineNo + "-" + subLineNO;
            String ESLine = subLineNO + "-" + staLineNo;

            if (waitTimeMap.get(SELine) == null && waitTimeMap.get(ESLine) == null) {
                double waitTimeS = lineMap.get(staLineNo);
                double waitTimeE = lineMap.get(subLineNO);
                // 以
                waitTimeMap.put(SELine, (int) ((waitTimeS + waitTimeE) / 2));
            }
        }
        waitingTimeMap = new HashMap<>();
        waitingTimeMap.putAll(waitTimeMap);
        return waitTimeMap;
    }

    /**
     * 获取等待时间，直接传入两条路线
     *
     * @param startLine 起始线路
     * @param endLine   需要到达的转线线路
     * @param version   版本号
     * @return 返回-1代表不是换乘站
     */
    public Integer getWaitingTime(int startLine, int endLine, long version) {
        String line = startLine + "-" + endLine;
        Map<String, Integer> map = getWaitingTimeMap(version);
        System.out.println(map.size());
        if (map.get(line) != null) {
            return map.get(line);
        } else {
            line = endLine + "-" + startLine;
            if (map.get(line) != null) {
                return map.get(line);
            } else {
                System.out.println("输出的站点错误，不是可换乘站");
                return -1;
            }
        }
    }

}
