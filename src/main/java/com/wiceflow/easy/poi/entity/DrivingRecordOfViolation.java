package com.wiceflow.easy.poi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author BF
 * @since 2018-12-19
 */
@ExcelTarget("DrivingRecordOfViolation")
public class DrivingRecordOfViolation implements Serializable {

    private String id;

    /**
     * 驾驶员工号
     */
    @Excel(name = "驾驶员工号")
    private String JiaShiYuanGongZuoKaHao;

    /**
     * 是否属实
     */
    @Excel(name = "是否属实")
    private String ShiFouShuShi;

    /**
     * 违章时间
     */
    @Excel(name = "违章时间")
    private Date WeiZhangShiJian;

    /**
     * 违章地点
     */
    @Excel(name = "违章地点")
    private String WeiZhangDiDian;

    /**
     * 违章行为
     */
    @Excel(name = "违章行为")
    private String WeiZhangXingWei;

    /**
     * 处罚条款 
     */
    @Excel(name = "处罚条款")
    private String ChuFaTiaoKuan;

    /**
     *  处罚决定
     */
    @Excel(name = "处罚决定")
    private String ChuLiQingKuang;

    /**
     * 检查来源
     */
    @Excel(name = "检查来源")
    private String ChaChuLaiYuan;

    /**
     * 查处单位
     */
    @Excel(name = "查处单位")
    private String JianChaRenDanWei;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJiaShiYuanGongZuoKaHao() {
        return JiaShiYuanGongZuoKaHao;
    }

    public void setJiaShiYuanGongZuoKaHao(String jiaShiYuanGongZuoKaHao) {
        JiaShiYuanGongZuoKaHao = jiaShiYuanGongZuoKaHao;
    }

    public String getShiFouShuShi() {
        return ShiFouShuShi;
    }

    public void setShiFouShuShi(String shiFouShuShi) {
        ShiFouShuShi = shiFouShuShi;
    }

    public Date getWeiZhangShiJian() {
        return WeiZhangShiJian;
    }

    public void setWeiZhangShiJian(Date weiZhangShiJian) {
        WeiZhangShiJian = weiZhangShiJian;
    }

    public String getWeiZhangDiDian() {
        return WeiZhangDiDian;
    }

    public void setWeiZhangDiDian(String weiZhangDiDian) {
        WeiZhangDiDian = weiZhangDiDian;
    }

    public String getWeiZhangXingWei() {
        return WeiZhangXingWei;
    }

    public void setWeiZhangXingWei(String weiZhangXingWei) {
        WeiZhangXingWei = weiZhangXingWei;
    }

    public String getChuFaTiaoKuan() {
        return ChuFaTiaoKuan;
    }

    public void setChuFaTiaoKuan(String chuFaTiaoKuan) {
        ChuFaTiaoKuan = chuFaTiaoKuan;
    }

    public String getChuLiQingKuang() {
        return ChuLiQingKuang;
    }

    public void setChuLiQingKuang(String chuLiQingKuang) {
        ChuLiQingKuang = chuLiQingKuang;
    }

    public String getChaChuLaiYuan() {
        return ChaChuLaiYuan;
    }

    public void setChaChuLaiYuan(String chaChuLaiYuan) {
        ChaChuLaiYuan = chaChuLaiYuan;
    }

    public String getJianChaRenDanWei() {
        return JianChaRenDanWei;
    }

    public void setJianChaRenDanWei(String jianChaRenDanWei) {
        JianChaRenDanWei = jianChaRenDanWei;
    }
}
