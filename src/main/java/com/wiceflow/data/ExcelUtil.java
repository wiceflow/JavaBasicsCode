//package com.wiceflow.data;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.UUID;
//
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//
///**
// * 表格操作助手类。
// *
// * @author xieqing
// *
// */
//public abstract class ExcelUtil {
//
//    /**
//     * TODO
//     * @return
//     */
//    public static List<Map<String, String>> readExcel(String path) {
//        try (   InputStream is = new FileInputStream(new File(path));
//                Workbook workbook = new HSSFWorkbook(is)) {
//
//            Sheet sheet = workbook.getSheetAt(0);
//
//            int index = 0;
//            boolean isFirst = true;
//            List<String> keys = new ArrayList<>(16);
//            List<Map<String, String>> excelContent = new ArrayList<>(16);
//
//            for (Row row : sheet) {
//                if (isFirst) {
//                    isFirst = false;
//                    for (Cell cell : row) {
//                        keys.add(cell.getStringCellValue().trim());
//                    }
//                } else {
//                    index = 0;
//                    Map<String, String> rowContent = new HashMap<>(16);
//                    for (Cell cell : row) {
//                        rowContent.put(keys.get(index ++), cell.getStringCellValue().trim());
//                    }
//                    excelContent.add(rowContent);
//                }
//            }
//            return excelContent;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return Collections.emptyList();
//        }
//    }
//
//    private static String toString(Map<String, String> rowContent) {
//        return rowContent.get("流水号") + "\t" +
//               rowContent.get("来源") + "\t" +
//               rowContent.get("乘务员") + "\t" +
//               rowContent.get("司机") + "\t" +
//               rowContent.get("所属车队") + "\t" +
//               rowContent.get("详细类型") + "\t" +
//               rowContent.get("投诉类型") + "\t" +
//               rowContent.get("信访日期");
//    }
//
//    public static void main(String[] args) throws Exception {
//        String dirPath = "C:\\Users\\xieqing\\Desktop\\工作\\应急保障公交\\投诉数据录入";
//        File dirFile = new File(dirPath);
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        Class.forName("com.mysql.jdbc.Driver");
//        Connection conn = DriverManager.getConnection(
//                "jdbc:mysql://192.168.40.113:3306/bus_accident?useUnicode=true&characterEncoding=utf8&useSSL=false",
//                "bus",
//                "bus123");
//        for (File file : dirFile.listFiles()) {
//            String filePath = file.getAbsolutePath();
//            if (filePath.endsWith(".xls")) {
//                // 第一步：读取文件内容
//                List<Map<String, String>> excelContent = readExcel(filePath);
//
//                // 第二步：获取带路径的文件名前缀
//                String fileName = filePath.substring(0, filePath.indexOf("."));
//                System.out.println(fileName);
//                System.out.println("读取到" + excelContent.size() + "行数据！");
//
//                // 第三步：记录并移除下不合法的记录
//                Map<String, List<Map<String, String>>> driverNameMap = new HashMap<>(16);
//                PrintWriter writer = new PrintWriter(fileName + "-数据格式不合法记录.txt");
//                Map<String, String> rowContent;
//                int deletedCount = 0;
//                for (int i = 0; i < excelContent.size(); i ++) {
//                    rowContent = excelContent.get(i);
//                    // 司机名
//                    String driverName = rowContent.get("司机");
//                    driverName = driverName
//                            .replaceAll("^[\\d\\D]*?,/*#*", "")
//                            .replaceAll("\\d* *", "")
//                            .trim();
//                    // 所属队伍
//                    String orgName = rowContent.get("所属车队")
//                            .replaceAll("^\\d*", "")
//                            .replaceAll("(（[^（）]+）)*", "")
//                            .trim();
//                    if (driverName.length() <= 1 ||
//                            driverName.contains("、") ||
//                            driverName.contains(",")) {
//                        // 3.1、过滤不合法数据
//                        writer.println(toString(rowContent));
//                        excelContent.remove(i);
//                        i --;
//                        deletedCount ++;
//                    } else {
//                        // 3.2、建立司机名和行记录映射关系
//                        String key = driverName + ":" + orgName;
//                        List<Map<String, String>> rowList = driverNameMap.get(key);
//                        if (rowList == null) {
//                            rowList = new ArrayList<>(8);
//                            driverNameMap.put(key, rowList);
//                        }
//                        rowList.add(rowContent);
//                    }
//                }
//                writer.flush();
//                writer.close();
//                System.out.println("数据格式不合法的有" + deletedCount + "行数据！");
//
//                // 第四步：获取司机工号中并过滤出不合法的
//                Map<String, String> driverNameAndCodeMap = new HashMap<>(16);
//                writer = new PrintWriter(fileName + "-无法确定司机工号的数据.txt");
//                int ambiguityCount = 0;
//                for (Entry<String, List<Map<String, String>>> entry : driverNameMap.entrySet()) {
//                    // 4.1、执行sql语句
//                    String[] datas = entry.getKey().split(":");
//                    PreparedStatement prepareStatement = conn.prepareStatement(
//                            "select emp_code from employee where "
//                                    + "emp_name = ? and emp_position = '1' and org_name = ?");
//                    prepareStatement.setString(1, datas[0]);
//                    prepareStatement.setString(2, datas[1]);
//                    ResultSet resultSet = prepareStatement.executeQuery();
//                    // 4.2、尝试获取司机工号
//                    String empCode = null;
//                    int count = 0;
//                    while (resultSet.next()) {
//                        empCode = resultSet.getString("emp_code");
//                        count ++;
//                        if (count > 1) { break; }
//                    }
//                    // 4.3、删除多查询结果的数据
//                    if (count > 1 || empCode == null) {
//                        for (Map<String, String> row : entry.getValue()) {
//                            excelContent.remove(row);
//                            writer.println(toString(row));
//                            ambiguityCount ++;
//                        }
//                    } else {
//                        driverNameAndCodeMap.put(datas[0], empCode);
//                    }
//                }
//                writer.flush();
//                writer.close();
//                System.out.println("无法确定司机工号的数据有" + ambiguityCount + "条！");
//
//                // 第五步：插入数据
//                String dateStr = format.format(new Date());
//                StringBuilder insertSql = new StringBuilder(
//                        "insert into driver_complaint_registration"
//                        + "(id, driver_number, create_time, complaint_content, "
//                        + "complaint_code, create_date, modify_date, complaint_type, registrant) "
//                        + "values ");
//                for (Map<String, String> row : excelContent) {
//                    String driverName = row.get("司机");
//                    driverName = driverName
//                            .replaceAll("^[\\d\\D]*?,/*#*", "")
//                            .replaceAll("\\d* *", "")
//                            .trim();
//                    insertSql.append("('")
//                        .append(UUID.randomUUID().toString().replace("-", "")).append("', '")
//                        .append(driverNameAndCodeMap.get(driverName)).append("', '")
//                        .append(row.get("信访日期")).append("', '")
//                        .append(row.get("详细类型")).append("', '")
//                        .append(row.get("流水号")).append("', '")
//                        .append(dateStr).append("', '")
//                        .append(dateStr).append("', '")
//                        .append(row.get("投诉类型")).append("', ''),");
//                }
//                insertSql.setLength(insertSql.length() - 1);
//                Statement statement = conn.createStatement();
//                boolean result = statement.execute(insertSql.toString());
//                System.out.println("插入操作返回值：" + result);
//                System.out.println("最后剩余" + excelContent.size() + "条数据！");
//            }
//
//        }
//
//
//
//
//
////        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
////        PrintWriter writer = new PrintWriter(new File(
////                "C:\\Users\\xieqing\\Desktop\\工作\\应急保障公交\\投诉数据录入\\2013年投诉-发智能部.txt"));
////        for (Map<String, String> rowContent : excelContent) {
////
////            String driverName = rowContent.get("司机");
////            driverName = driverName.replaceAll("^[^,]*?,/*", "").trim();
////
////            String orgName = rowContent.get("所属车队")
////                    .replaceAll("^\\d*", "")
////                    .replaceAll("(（[^（）]+）)*", "")
////                    .trim();
////            String dateStr = format.format(new Date());
////
////            if (driverName.length() > 0 && !driverName.contains("、")) {
////                writer.println(String.format("insert into driver_complaint_registration"
////                        + "(id, driver_number, create_time, complaint_content, complaint_code, "
////                        + "create_date, modify_date, complaint_type) \r\n" +
////                        "select '%s', emp_code, '%s', '%s', "
////                        + "'%s', '%s', '%s', "
////                        + "'%s' from employee where emp_name = '%s' and emp_position = '1' "
////                        + "and org_name = '%s';",
////                        UUID.randomUUID().toString().replace("-", ""),
////                        rowContent.get("信访日期"), rowContent.get("详细类型"), rowContent.get("流水号"),
////                        dateStr, dateStr, rowContent.get("投诉类型"), driverName, orgName));
////            }
////
////        }
////        writer.flush();
////        writer.close();
//    }
//
//}
