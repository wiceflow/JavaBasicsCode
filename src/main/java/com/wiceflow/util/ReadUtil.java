package com.wiceflow.util;
import com.wiceflow.companyLearn.subway.CVSTODB.TransferStation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * Created by BF on 2017/12/29.
 */
public class ReadUtil {
    private Logger logger = LoggerFactory.getLogger(ReadUtil.class);
    private Workbook wb;
    private Sheet sheet;
    private Row row;

    public ReadUtil() {
    }

    public ReadUtil(String filepath) {
        if(filepath==null){
            return;
        }
        String ext = filepath.substring(filepath.lastIndexOf("."));
        try {
            InputStream is = new FileInputStream(filepath);
            if(".xls".equals(ext)){
                wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(ext)){
                wb = new XSSFWorkbook(is);
            }else{
                wb=null;
            }
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        }
    }

    /**
     * 读取Excel表格表头的内容
     *
     * @return String 表头内容的数组
     * @author zengwendong
     */
    public String[] readExcelTitle() throws Exception{
        if(wb==null){
            throw new Exception("Workbook对象为空！");
        }
        sheet = wb.getSheetAt(0);
        row = sheet.getRow(0);
        // 标题总列数
        int colNum = row.getPhysicalNumberOfCells();
        System.out.println("colNum:" + colNum);
        String[] title = new String[colNum];
        for (int i = 0; i < colNum; i++) {
            // title[i] = getStringCellValue(row.getCell((short) i));
            title[i] = row.getCell(i).getCellFormula();
        }
        return title;
    }

    /**
     * 读取Excel数据内容
     *
     * @return Map 包含单元格数据内容的Map对象
     * @author zengwendong
     */
    public Map<Integer, Map<Integer,Object>> readExcelContent() throws Exception{
        if(wb==null){
            throw new Exception("Workbook对象为空！");
        }
        Map<Integer, Map<Integer,Object>> content = new HashMap<Integer, Map<Integer,Object>>();

        sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            Map<Integer,Object> cellValue = new HashMap<Integer, Object>();
            while (j < colNum) {
                Object obj = getCellFormatValue(row.getCell(j));
                cellValue.put(j, obj);
                j++;
            }
            content.put(i, cellValue);
        }
        return content;
    }

    /**
     *
     * 根据Cell类型设置数据
     *
     * @param cell
     * @return
     * @author zengwendong
     */
    private Object getCellFormatValue(Cell cell) {
        Object cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:// 如果当前Cell的Type为NUMERIC
                case Cell.CELL_TYPE_FORMULA: {
                    // 判断当前的cell是否为Date
                    if (DateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        cellvalue = date;
                    } else {// 如果是纯数字

                        // 取得当前Cell的数值
                        cellvalue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING:// 如果当前Cell的Type为STRING
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                default:// 默认的Cell值
                    cellvalue = "";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }

    public Set<String> readxlsx(){
        // set集合去重
        Set<String> start_end_line = new HashSet<>();
        try {
            // 对读取Excel表格内容测试
            Map<Integer, Map<Integer,Object>> map = readExcelContent();
            for (int i : map.keySet()){
                Map<Integer,Object> a = map.get(i);
                String startLine = (String) a.get(1);
                String endLind = (String) a.get(2);
                String seLine = changeString(startLine) + "-" + changeString(endLind);
                start_end_line.add(seLine);
            }
        } catch (FileNotFoundException e) {
            System.out.println("未找到指定路径的文件!");
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return start_end_line;
    }
    /**
     * 将1.0 变成1
     * @return
     */
    private int changeString(String s){
        int sName = 0;
        switch (s){
            case "1.0":
                sName = 1;
                break;
            case "2.0":
                sName = 2;
                break;
            case "3.0":
                sName = 3;
                break;
            case "4.0":
                sName = 4;
                break;
            case "5.0":
                sName = 5;
                break;
            case "7.0":
                sName = 7;
                break;
            case "9.0":
                sName = 9;
                break;
            case "11.0":
                sName = 11;
                break;
            default:
                System.out.println("出错");
                break;
        }
        return sName;
    }

    /**
     * 设置换乘时间
     * @param transferPath 换乘表的路径，获取可换乘站点
     * @param linePath 线路表 获取的候车时间
     * @return
     */
    public List<TransferStation> getTransferStation(String transferPath,String linePath){
        // TransferStation对象是包括了换乘起始站，换乘终点站，换乘时间
        List<TransferStation> transferStationList = new ArrayList<>();
        // 用来存储换乘时间的map 从线路表中读取出来
        Map<Integer,Integer> map = new HashMap<>();
        File transferFile = new File(transferPath);
        File lineFile = new File(linePath);
        // java1.7自动关闭流
        try(FileInputStream tFileInputStream = new FileInputStream(transferFile)) {
            // 若读取中文出现乱码，修改这里的格式
            Scanner transferScanner = new Scanner(tFileInputStream,"gbk");
            // 跳过第一行注的解释
            transferScanner.nextLine();
            while (transferScanner.hasNext()){
                String transferLine = transferScanner.nextLine();
                String[] lines = transferLine.split(",");
                int startLine = Integer.parseInt(lines[1]);
                int endLine = Integer.parseInt(lines[2]);
                TransferStation t1 = new TransferStation(startLine,endLine);
                // 反转站点候车
                TransferStation t2 = new TransferStation(endLine,startLine);
                // transferStation对象中重写了equals方法，只要startLine与endLine相等则判为存在相同对象，
                //
                if (transferStationList.contains(t1)) {
                    continue;
                }
                transferStationList.add(t1);
                transferStationList.add(t2);
            }
        }catch (Exception e){
            System.out.println("读取换乘站点Excel表失败");
            e.printStackTrace();
        }
        // java1.7自动关闭流
        try(FileInputStream lFileInpuntStream = new FileInputStream(lineFile)) {
            // 若读取中文出现乱码，修改这里的格式
            Scanner lineScanner = new Scanner(lFileInpuntStream,"gbk");
            // 跳过第一行注的解释
            lineScanner.nextLine();
            while(lineScanner.hasNext()){
                String lineString = lineScanner.nextLine();
                String[] lines = lineString.split(",");
                int line = Integer.parseInt(lines[0]);
                int waitingTime = Integer.parseInt(lines[2]);
                map.put(line,waitingTime);
            }
        }catch (Exception e){
            System.out.println("读取线路表获取候车时间失败");
            e.printStackTrace();
        }
        // 将对应的候车时间加入list中
        setWaitingTime(transferStationList,map);
        return transferStationList;
    }

    /**
     * 将相应的候车时间加入到存放 transferStation 对应的list中
     * @param list transferStation数组
     * @param map 存放线路候车时间的map
     *
     *            这里的规则是，加入从1号线换到5号，那么我要在5号线候车，所以候车时间相应的就为
     *            5号线的候车时间。
     */
    public void setWaitingTime(List<TransferStation> list,Map<Integer,Integer> map){
        for (int i=0;i<list.size();i++){
           list.get(i).setWaitingTime(map.get(list.get(i).getEndLine()));
        }
    }

}
