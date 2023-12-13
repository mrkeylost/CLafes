package model;

import java.util.Date;

public class Report {
	private Integer reportId;
	private Integer pcId;
	private String reportNote;
	private Date reportDate;
	private String userRole;

	public Report(Integer reportId, Integer pcId, String reportNote, Date reportDate, String userRole) {
		super();
		this.reportId = reportId;
		this.pcId = pcId;
		this.reportNote = reportNote;
		this.reportDate = reportDate;
		this.userRole = userRole;
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public Integer getPcId() {
		return pcId;
	}

	public void setPcId(Integer pcId) {
		this.pcId = pcId;
	}

	public String getReportNote() {
		return reportNote;
	}

	public void setReportNote(String reportNote) {
		this.reportNote = reportNote;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

}
