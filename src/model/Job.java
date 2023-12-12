package model;

public class Job {

	private Integer jobId;
	private Integer pcId;
	private String jobStatus;
	
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
