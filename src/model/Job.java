package model;

public class Job {

	private Integer jobId;
	private Integer userId;
	private Integer pcId;
	private String jobStatus;

	public Job(Integer jobId, Integer userId, Integer pcId, String jobStatus) {
		super();
		this.jobId = jobId;
		this.userId = userId;
		this.pcId = pcId;
		this.jobStatus = jobStatus;
	}

	
	public Job(Integer jobId, Integer pcId, String jobStatus) {
		super();
		this.jobId = jobId;
		this.pcId = pcId;
		this.jobStatus = jobStatus;
	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPcId() {
		return pcId;
	}

	public void setPcId(Integer pcId) {
		this.pcId = pcId;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
}
