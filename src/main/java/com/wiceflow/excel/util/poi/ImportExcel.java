package com.wiceflow.excel.util.poi;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author BF
 * @date 2018/2/27
 * 利用POI导入excel数据
 * TODO 泛型先预留
 */
public class ImportExcel<T> {

    public List<List<Object>> parseExcel(String filePath) throws Exception {
        // 只做取消魔法值。
        String xlsx = "xlsx";
        String xls = "xls";

        File excelFile = new File(filePath);
        String fileName = excelFile.getName();
//        // 返回数据的list
//        List<T> list = new ArrayList<>();
        // 创建文件流
        InputStream input = new FileInputStream(filePath);
        Workbook wb = null;
        // 判断excel是 2003-2007版本的xls格式 还是2007以上版本的 xlsx格式
        if (fileName.endsWith(xlsx)) {
            wb = new XSSFWorkbook(input);
        } else if (fileName.endsWith(xls)) {
            wb = new HSSFWorkbook(input);
        } else {
            throw new Exception("文件格式错误");
        }
        // 获取表格
        Sheet sheet = wb.getSheetAt(0);
        List<List<Object>> lists = sheetToList(sheet);
        return lists;
    }

    /**
     * 获取当前表格中的所有行信息和列信息
     *
     * @param sheet 表格
     * @return 返回的是嵌套list结构，对象还没有转换
     */
    public List<List<Object>> sheetToList(Sheet sheet) throws Exception {
        List<List<Object>> lists = new ArrayList<>();
        // 获得第一个表格的迭代器
        Iterator<Row> rowIterator = sheet.rowIterator();
        // 迭代遍历每一行
        while (rowIterator.hasNext()) {
            // 获取行数据
            Row row = rowIterator.next();
            // 储存数据的list
            List<Object> list = new ArrayList<>();
            // 获取当前行的所有列信息
            list = rowToList(row);
            if (list != null && list.size() != 0) {
                lists.add(list);
            } else {
                // 留出空位
                throw new Exception("空");
            }
        }
        return lists;
    }

    /**
     * 获取表格某一行的所有列数据
     *
     * @param row 当前行
     * @return 返回
     */
    public List<Object> rowToList(Row row) throws Exception {
        List<Object> list = new ArrayList<>();
        // 当前行的 列 迭代器
        Iterator<Cell> cellIterator = row.cellIterator();
        // 迭代每一列
        while (cellIterator.hasNext()) {
            // 指向下一列
            Cell cell = cellIterator.next();
            // 判断类型 以对应方法获取对应的值
            switch (cell.getCellType()) {
                // 文本型
                case Cell.CELL_TYPE_STRING:
                    list.add(cell.getStringCellValue());
                    break;
                // 数值型  TODO 数值型的获取会以 都double形式取出
                case Cell.CELL_TYPE_NUMERIC:
                    // 日期型
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        list.add(cell.getDateCellValue());
                    } else {
                        list.add(cell.getNumericCellValue());
                    }
                    break;
                // 布尔型
                case Cell.CELL_TYPE_BOOLEAN:
                    list.add(cell.getBooleanCellValue());
                    break;
                // 公式型
                case Cell.CELL_TYPE_FORMULA:
                    list.add(cell.getCellFormula());
                    break;
                default:
                    throw new Exception("格式错误");
            }
        }
        return list;
    }
}
