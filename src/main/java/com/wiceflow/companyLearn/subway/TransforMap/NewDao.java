package com.wiceflow.companyLearn.subway.TransforMap;

import com.wiceflow.companyLearn.subway.CVSTODB.NetLine;
import sun.nio.ch.Net;

import java.io.FileInputStream;
import java.sql.*;
import java.util.*;

/**
 * @author BF
 * @date 2018/2/2
 */
public class NewDao {
    private Connection connection = null;

    public NewDao() {
        init();
    }

    private void init() {
        Properties properties = new Properties();
        try {
            // 获取配置信息
            properties.load(new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/conf.properties"));
            String url = properties.getProperty("url");
            String userName = properties.getProperty("username");
            String password = properties.getProperty("password");
            String driver = properties.getProperty("driver");
            Class.forName(driver);
            connection = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取线路信息和候车时间
     *
     * @param version
     * @return
     */
    public Map<String, Double> getLineDate(long version) {
        Map<String, Double> lineTimeMap = new HashMap<>();
        String sql = "select line_no,depart_interval from t_net_line where conf_version = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, version);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String lineNo = resultSet.getString("line_no");
                double departInterval = resultSet.getDouble("depart_interval");
                lineTimeMap.put(lineNo, departInterval);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lineTimeMap;
    }

    /**
     * 获取换乘站信息，这里因为不需要具体的站点名称所有会滤掉重复的
     *
     * @param version 版本号
     * @return
     */
    public Set<String> getInterChange(long version) {
        Set<String> interChangeSet = new HashSet<>();
        String sql = "select line_no_main,line_no_sub from t_net_interchange where conf_version = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, version);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String line = resultSet.getString("line_no_main") + "-" + resultSet.getString("line_no_sub");
                interChangeSet.add(line);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return interChangeSet;
    }
}
