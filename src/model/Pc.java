package model;

public class Pc {
	
	private int pcId;
	private String pcStatus;
	
	public Pc(int pcId, String pcStatus) {
		super();
		this.pcId = pcId;
		this.pcStatus = pcStatus;
	}

	public int getPcId() {
		return pcId;
	}
	
	public void setPcId(int pcId) {
		this.pcId = pcId;
	}
	
	public String getPcStatus() {
		return pcStatus;
	}
	
	public void setPcStatus(String pcStatus) {
		this.pcStatus = pcStatus;
	}
	
	
	
	
}
