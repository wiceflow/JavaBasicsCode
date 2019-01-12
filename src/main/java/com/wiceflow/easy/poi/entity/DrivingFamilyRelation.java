package com.wiceflow.easy.poi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * <p>
 * 
 * </p>
 *
 * @author BF
 * @since 2018-12-19
 */

public class DrivingFamilyRelation {

    private String id;

    /**
     * 职员代码
     */
    @Excel(name = "职员代码")
    private String emid;

    /**
     * 职员工号
     */
    @Excel(name = "职员工号")
    private String employeeCode;

    /**
     * 职员姓名
     */
    @Excel(name = "职员姓名")
    private String employeeName;

    /**
     * 与本人关系
     */
    @Excel(name = "与本人关系")
    private String familyRelation;

    /**
     * 家人姓名
     */
    @Excel(name = "家人姓名")
    private String familyRelationName;

    /**
     * 联系方式
     */
    @Excel(name = "联系方式")
    private String telPhone;

    /**
     * 工作单位及职务
     */
    @Excel(name = "工作单位及职务")
    private String workCorporationlDuty;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmid() {
        return emid;
    }

    public void setEmid(String emid) {
        this.emid = emid;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getFamilyRelation() {
        return familyRelation;
    }

    public void setFamilyRelation(String familyRelation) {
        this.familyRelation = familyRelation;
    }

    public String getFamilyRelationName() {
        return familyRelationName;
    }

    public void setFamilyRelationName(String familyRelationName) {
        this.familyRelationName = familyRelationName;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public String getWorkCorporationlDuty() {
        return workCorporationlDuty;
    }

    public void setWorkCorporationlDuty(String workCorporationlDuty) {
        this.workCorporationlDuty = workCorporationlDuty;
    }
}
