package com.wiceflow.companyLearn.subway.TransforMap;

import com.wiceflow.companyLearn.subway.CVSTODB.TransferStation;

import java.io.FileInputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by BF on 2018/1/2.
 * 连接数据库Dao JDBC
 */
public class Dao {
    private Connection connection = null;
    private int version = 1;
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
     * 候车起始线路，候车终点线路
     * @param  version 版本号
     * @return map数组,只存储了数据库的一半信息
     *          起始路线-终点路线 -> (起始路线候车时间+终点路线候车时间)/2
     */
    public Map<String,Integer> getWaitTime(int version){
        Map<String,Integer> map = new HashMap<>();
        String sql = "select start_line,end_line,waiting_time from t_waiting_time WHERE version = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            // 添加版本控制
            preparedStatement.setInt(1,version);
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
    /**
     * 从数据库获得最新的版本号
     *
     * @param type
     *            0代表线路信息的版本，1代表票价信息的版本
     * @return -1，出现异常；0，说明表中没有数据；>0，正常
     */
    public int getLastVersion(int type) {
        String sql = "select version from t_version where type = ? order by version desc limit 1";
        PreparedStatement statement = null;
        int version = -1;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, type);
            ResultSet result = statement.executeQuery();
            if (result.next()) {// 不能直接getInt，要先用next()，
                version = result.getInt(1);
            } else {// 没有数据
                version = 0;
            }
            return version;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("version查询失败");
            return version;
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 更新版本，实际是插入最新的版本信息
     *
     * @return
     */
    public boolean updateVersion(int type, int version, Timestamp date,
                                 String remark) throws SQLException {
        String sql = "insert into t_version(version,time,type,remark) values(?,?,?,?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, version);
            statement.setTimestamp(2, date);
            statement.setInt(3, type);
            statement.setString(4, remark);
            return statement.executeUpdate() == 1;
        } finally {
           statement.close();
        }
    }
    /**
     * 将候车等待时间存入数据库
     * @param transferStations
     */
    public void saveTransferStation(List<TransferStation> transferStations) throws SQLException {
        String sql = "insert into t_waiting_time(start_line,end_line,waiting_time,version)values(?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for(TransferStation t :transferStations){
                System.out.println(t.toString());
                preparedStatement.setInt(1,t.getStartLine());
                preparedStatement.setInt(2,t.getEndLine());
                preparedStatement.setInt(3,t.getWaitingTime());
                preparedStatement.setInt(4,version);
                // 一次性将多个查询发送给数据库
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            System.out.println("插入候车等待时间成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            preparedStatement.close();
        }
    }

}
