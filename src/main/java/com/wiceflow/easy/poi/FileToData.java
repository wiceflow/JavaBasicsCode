package com.wiceflow.easy.poi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.wiceflow.easy.poi.entity.DrivingFamilyRelation;
import com.wiceflow.util.FileUtil;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author BF
 * @date 2019/1/12
 * 测试 easy poi excel 到程序
 */
public class FileToData {

    /**
     * 严格遵循驼峰命名
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        FileToData fileToData = new FileToData();
        List<DrivingFamilyRelation> list = fileToData.readFile();
        // 第一个参数 大标题  第二个参数 sheet
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("违章信息","数据接入",ExcelType.XSSF),
                DrivingFamilyRelation.class, list);
        FileOutputStream fileInputStream = new FileOutputStream("F:/编码学习/easy_poi/data/1.xlsx");
        workbook.write(fileInputStream);

    }


    public List<DrivingFamilyRelation> readFile(){
        String filePath = "F:\\编码学习\\easy_poi\\data\\2018-12-27-18-31-22.txt";
        List<DrivingFamilyRelation> list = FileUtil.FILE_TO_DATA_BASE.readFile(new File(filePath),DrivingFamilyRelation.class);
        return list;
    }
}
