package com.wiceflow.easy.poi.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "SUBROUTE_BD")
public class SubRouteOc {
	private Integer id;
	private String 	subrouteid;
	private String 	subroutename;
	private String 	routeid;
	private Integer ismainsub;
	private Integer routestyle;
	private String 	routetype;
	private String 	routegrade;
	private Integer dispatchtype;
	private Integer buscount;
	private Integer routelength;
	private String 	memos;
	private String 	upszsubrouteid;
	private String 	upszsubrouteidtmp;
	private String 	relatedid;;
	private Date 	begindate;
	private Date 	enddate;
	private String 	hold1;
	private String 	hold2;
	private String 	hold3;
	private String 	hold4;
	private String 	hold5;
	private String 	hold6;
	private String 	hold7;
	private String 	hold8;
	private String 	hold9;
	private String 	hold10;
	private Integer audit_id;
	private Date 	activestartdate;
	private Date 	activeenddate;
	private Integer isactive;
	private Date 	created;
	private String 	createdby;
	private String 	updated;
	private String 	updatedby;
	private Integer dayrunstyle;
	private String 	subroutestatus;
	public SubRouteOc() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSubrouteid() {
		return subrouteid;
	}
	public void setSubrouteid(String subrouteid) {
		this.subrouteid = subrouteid;
	}
	public String getSubroutename() {
		return subroutename;
	}
	public void setSubroutename(String subroutename) {
		this.subroutename = subroutename;
	}
	public String getRouteid() {
		return routeid;
	}
	public void setRouteid(String routeid) {
		this.routeid = routeid;
	}
	public Integer getIsmainsub() {
		return ismainsub;
	}
	public void setIsmainsub(Integer ismainsub) {
		this.ismainsub = ismainsub;
	}
	public Integer getRoutestyle() {
		return routestyle;
	}
	public void setRoutestyle(Integer routestyle) {
		this.routestyle = routestyle;
	}
	public String getRoutetype() {
		return routetype;
	}
	public void setRoutetype(String routetype) {
		this.routetype = routetype;
	}
	public String getRoutegrade() {
		return routegrade;
	}
	public void setRoutegrade(String routegrade) {
		this.routegrade = routegrade;
	}
	public Integer getDispatchtype() {
		return dispatchtype;
	}
	public void setDispatchtype(Integer dispatchtype) {
		this.dispatchtype = dispatchtype;
	}
	public Integer getBuscount() {
		return buscount;
	}
	public void setBuscount(Integer buscount) {
		this.buscount = buscount;
	}
	public Integer getRoutelength() {
		return routelength;
	}
	public void setRoutelength(Integer routelength) {
		this.routelength = routelength;
	}
	public String getMemos() {
		return memos;
	}
	public void setMemos(String memos) {
		this.memos = memos;
	}
	public String getUpszsubrouteid() {
		return upszsubrouteid;
	}
	public void setUpszsubrouteid(String upszsubrouteid) {
		this.upszsubrouteid = upszsubrouteid;
	}
	public String getUpszsubrouteidtmp() {
		return upszsubrouteidtmp;
	}
	public void setUpszsubrouteidtmp(String upszsubrouteidtmp) {
		this.upszsubrouteidtmp = upszsubrouteidtmp;
	}
	public String getRelatedid() {
		return relatedid;
	}
	public void setRelatedid(String relatedid) {
		this.relatedid = relatedid;
	}
	
	public Date getBegindate() {
		return begindate;
	}
	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}

	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public String getHold1() {
		return hold1;
	}
	public void setHold1(String hold1) {
		this.hold1 = hold1;
	}
	public String getHold2() {
		return hold2;
	}
	public void setHold2(String hold2) {
		this.hold2 = hold2;
	}
	public String getHold3() {
		return hold3;
	}
	public void setHold3(String hold3) {
		this.hold3 = hold3;
	}
	public String getHold4() {
		return hold4;
	}
	public void setHold4(String hold4) {
		this.hold4 = hold4;
	}
	public String getHold5() {
		return hold5;
	}
	public void setHold5(String hold5) {
		this.hold5 = hold5;
	}
	public String getHold6() {
		return hold6;
	}
	public void setHold6(String hold6) {
		this.hold6 = hold6;
	}
	public String getHold7() {
		return hold7;
	}
	public void setHold7(String hold7) {
		this.hold7 = hold7;
	}
	public String getHold8() {
		return hold8;
	}
	public void setHold8(String hold8) {
		this.hold8 = hold8;
	}
	public String getHold9() {
		return hold9;
	}
	public void setHold9(String hold9) {
		this.hold9 = hold9;
	}
	public String getHold10() {
		return hold10;
	}
	public void setHold10(String hold10) {
		this.hold10 = hold10;
	}
	public Integer getAudit_id() {
		return audit_id;
	}
	public void setAudit_id(Integer audit_id) {
		this.audit_id = audit_id;
	}
	public Date getActivestartdate() {
		return activestartdate;
	}
	public void setActivestartdate(Date activestartdate) {
		this.activestartdate = activestartdate;
	}
	
	public Date getActiveenddate() {
		return activeenddate;
	}
	public void setActiveenddate(Date activeenddate) {
		this.activeenddate = activeenddate;
	}
	public Integer getIsactive() {
		return isactive;
	}
	public void setIsactive(Integer isactive) {
		this.isactive = isactive;
	}
	
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public String getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	public Integer getDayrunstyle() {
		return dayrunstyle;
	}
	public void setDayrunstyle(Integer dayrunstyle) {
		this.dayrunstyle = dayrunstyle;
	}
	public String getSubroutestatus() {
		return subroutestatus;
	}
	public void setSubroutestatus(String subroutestatus) {
		this.subroutestatus = subroutestatus;
	}

}
