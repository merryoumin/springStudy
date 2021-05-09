package com.test.springStudy.board.model.dto;

import java.sql.Date;

public class BoardChkDTO {
	 private int no; 
	 private String  checktbl;
	 private String  tblName; 
	 private String   surviceGubun; 
	 private Date    regiDate;
	 
	 
	 
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getChecktbl() {
		return checktbl;
	}
	public void setChecktbl(String checktbl) {
		this.checktbl = checktbl;
	}
	public String getTblName() {
		return tblName;
	}
	public void setTblName(String tblName) {
		this.tblName = tblName;
	}
	public String getSurviceGubun() {
		return surviceGubun;
	}
	public void setSurviceGubun(String surviceGubun) {
		this.surviceGubun = surviceGubun;
	}
	public Date getRegiDate() {
		return regiDate;
	}
	public void setRegiDate(Date regiDate) {
		this.regiDate = regiDate;
	}
	 
	 
	 
	 
	 
}
