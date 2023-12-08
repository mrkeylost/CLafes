package model;

public class Pc {
	
	private int pcId;
	private String pcStatus;
	private String pcAvailability;
	
	public Pc(int pcId, String pcStatus, String pcAvailability) {
		super();
		this.pcId = pcId;
		this.pcStatus = pcStatus;
		this.pcAvailability = pcAvailability;
	}
	
	public String getPcAvailability() {
		return pcAvailability;
	}

	public void setPcAvailability(String pcAvailability) {
		this.pcAvailability = pcAvailability;
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
