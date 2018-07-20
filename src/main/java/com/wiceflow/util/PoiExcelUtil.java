package com.wiceflow.util;

import jdk.nashorn.internal.runtime.regexp.joni.constants.AnchorType;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author iceflow
 * @date 2018/7/20
 *      利用开源组件POI3.0.2动态导出EXCEL文档
 *      @param <T>
 *            应用泛型，代表任意一个符合javabean风格的类
 *            注意这里为了简单起见，boolean型的属性xxx的get器方式为getXxx(),而不是isXxx()
 *            byte[]表jpg格式的图片数据
 */
public class PoiExcelUtil<T> {

    /**
     * 3MB      上传配置
     */
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;
    /**
     * 40MB     上传配置
     */
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40;
    /**
     * 50MB     上传配置
     */
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50;

    public static final String PATTERN = "^//d+(//.//d+)?$";

    /**
     * 表格导出
     * @param title     [String]        表格标题名
     * @param headers   [String[]]      表格属性列名数组
     * @param dataList  [List<T>]  泛型 获取到的数据列表
     * @param out       [OutputStream]  与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     * @param pattern   [String]        如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
     */

    public static <T> void exportExcel(String title, String[] headers, Collection<T> dataList, OutputStream out, String pattern, String author,XSSFWorkbook workbook) throws IOException, IntrospectionException {

        if (workbook == null) {
            workbook = obtainWorkbook(title, author);
        }
        // 生成一个表格
        XSSFSheet sheet = workbook.getSheet(title);
        // 产生表格标题行
        XSSFRow row = sheet.createRow(0);
        // 声明一个画图的顶级管理器
        XSSFDrawing patriarch = sheet.createDrawingPatriarch();
        // 样式
        XSSFCellStyle style = workbook.getCellStyleAt((short)0);
        XSSFCellStyle style1 = workbook.getCellStyleAt((short)1);
        for (short i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        // 遍历集合数据，产生数据行
        Iterator<T> it = dataList.iterator();
        int index = 0;
        XSSFFont font3 = workbook.createFont();
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            T t =  it.next();
            // 利用java内省类对javaBean进行取值处理
            BeanInfo beanInfo = Introspector.getBeanInfo(t.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            try {
                // 必须进行空值判断
                if (propertyDescriptors != null && propertyDescriptors.length > 0) {
                    for (short i = 0 ; i < propertyDescriptors.length ; i++) {
                        PropertyDescriptor property = propertyDescriptors[i];
                        Object value = property.getReadMethod().invoke(t);
                        // 如果value值为空，代表这个属性没有值，则跳过
                        if (value == null){
                            continue;
                        }
                        XSSFCell cell = row.createCell(i);
                        cell.setCellStyle(style1);
                        // 判断值的类型后进行强制类型转换
                        String textValue = null;
                        if (value instanceof Date) {
                            Date date = (Date) value;
                            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                            textValue = sdf.format(date);
                        } else if (value instanceof byte[]) {
                            // 有图片时，设置行高为60px;
                            row.setHeightInPoints(60);
                            // 设置图片所在列宽度为80px,注意这里单位的一个换算
                            sheet.setColumnWidth(i, (short) (35.7 * 80));
                            byte[] bsValue = (byte[]) value;
                            XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0,
                                    1023, 255, (short) 6, index, (short) 6, index);
                            anchor.setAnchorType(AnchorType.BEGIN_LINE);
                            patriarch.createPicture(anchor, workbook.addPicture(
                                    bsValue, XSSFWorkbook.PICTURE_TYPE_JPEG));
                        } else {
                            // 其它数据类型都当作字符串简单处理
                            textValue = value.toString();
                        }
                        // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                        if (textValue != null) {
                            Pattern p = Pattern.compile(PATTERN);
                            Matcher matcher = p.matcher(textValue);
                            if (matcher.matches()) {
                                // 是数字当作double处理
                                cell.setCellValue(Double.parseDouble(textValue));
                            } else {
                                XSSFRichTextString richString = new XSSFRichTextString(textValue);
                                font3.setColor(IndexedColors.BLUE.getIndex());
                                richString.applyFont(font3);
                                cell.setCellValue(richString);
                            }
                        }
                    }
                }
            }catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }


    /**
     * 默认样式
     * @param title     [String] excel的单元格名
     * @param author    [String] 作者
     * @return  返回一个工作簿
     */
    private static XSSFWorkbook obtainWorkbook(String title,String author){
        // 声明一个工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 生成一个表格
        XSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 16);
        // 生成一个样式
        XSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        // 生成一个字体
        XSSFFont font = workbook.createFont();
        font.setColor(IndexedColors.VIOLET.getIndex());
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        XSSFCellStyle style1 = workbook.createCellStyle();
        style1.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style1.setBorderBottom(BorderStyle.THIN);
        style1.setBorderLeft(BorderStyle.THIN);
        style1.setBorderRight(BorderStyle.THIN);
        style1.setBorderTop(BorderStyle.THIN);
        style1.setAlignment(HorizontalAlignment.CENTER);
        style1.setVerticalAlignment(VerticalAlignment.CENTER);

        // 生成另一个字体
        XSSFFont font2 = workbook.createFont();
        font2.setBold(false);
        // 把字体应用到当前的样式
        style1.setFont(font2);
        // 声明一个画图的顶级管理器
        XSSFDrawing patriarch = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
        XSSFComment comment = patriarch.createCellComment(new XSSFClientAnchor(0,
                0, 0, 0, (short) 4, 2, (short) 6, 5));
        // 设置注释内容
        // comment.setString(new XSSFRichTextString("可以在POI中添加注释！"));
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
        comment.setAuthor(author);
        return workbook;
    }
}
