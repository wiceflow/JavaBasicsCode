package com.wiceflow.companyLearn.subway.TransforMap;

import java.io.FileInputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by BF on 2018/1/2.
 * 连接数据库Dao JDBC
 */
public class Dao {
    private Connection connection = null;
    public Dao(){
        init();
    }
    private void init(){
        Properties properties = new Properties();
        try {
            // 获取配置信息
            properties.load(new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/conf.properties"));
            String url = properties.getProperty("url");
            String userName = properties.getProperty("username");
            String password = properties.getProperty("password");
            String driver = properties.getProperty("driver");
            Class.forName(driver);
            connection = DriverManager.getConnection(url,userName,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取候车时间的Map数组
     * @return
     */
    public Map<String,Integer> getWaitTime(){
        Map<String,Integer> map = new HashMap<>();
        String sql = "select start_line,end_line,waiting_time from t_waiting_time";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int startLine = resultSet.getInt("start_line");
                int endLine = resultSet.getInt("end_line");
                int waitingTime = resultSet.getInt("waiting_time");
                String SELine = startLine + "-" + endLine;
                String ESLine = endLine + "-" + startLine;
                // map数组中只存一半的信息 等待时间采取两条线路的总时间除以2
                if (map.get(SELine)==null&&map.get(ESLine)==null){
                    map.put(SELine,waitingTime);
                }else if (map.get(SELine)!=null){
                    waitingTime = (waitingTime + map.get(SELine))/2;
                    map.put(SELine,waitingTime);
                }else if (map.get(ESLine)!=null){
                    waitingTime = (waitingTime + map.get(ESLine))/2;
                    map.put(ESLine,waitingTime);
                }
            }
        }catch (Exception e){
            System.out.println("获取数据出错");
        }
        return map;
    }
}
