package com.wiceflow.easy.poi;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.wiceflow.easy.poi.entity.DrivingFamilyRelation;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author BF
 * @date 2019/1/12
 * 测试easy poi 数据到 excel
 */
public class DataToFile {

    public static void main(String[] args) throws FileNotFoundException {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        long start = System.currentTimeMillis();
        List<DrivingFamilyRelation> list = ExcelImportUtil.importExcelBySax(
                new FileInputStream(
                        new File("F:/编码学习/easy_poi/data/1.xlsx")),
                DrivingFamilyRelation.class, params);
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(list.size());
        System.out.println(ReflectionToStringBuilder.toString(list.get(0)));

    }



}
