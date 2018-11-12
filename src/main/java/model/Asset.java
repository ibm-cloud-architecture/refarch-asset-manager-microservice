package model;

import java.util.Date;


public class Asset {

	private String id;
	private String os;
	private String version;
	private String type;
	private String ipAddress;
	private String antivirus;
	protected long rotation;
	protected long current;
	protected long pressure;
	protected long flowRate;
	protected long temperature;
	protected long riskRating=-1;
	protected String latitude;
	protected String longitude;
	protected Date creationDate;
	
	public Asset() {}

	public Asset(String id,String os,String type) {
	  	this.id = id;
	  	this.os= os;
	  	this.type = type;
	    this.creationDate = new Date();
	}

	public Asset(String id, String os, String type,  String antivirus, String ipAddress, String version) {
		this.id = id;
	    this.type = type;
	    this.os = os;
	    this.antivirus = antivirus;
	    this.ipAddress = ipAddress;
	    this.version = version;
	    this.creationDate = new Date();
	}
	
	public Asset(String id, String os, String type,  String antivirus, String ipAddress, String version,  long rotation, long current, long pressure, long flowRate, long temperature, long riskRating, String latitude, String longitude, Date creationDate) {
		this.id = id;
	    this.type = type;
	    this.os = os;
	    this.antivirus = antivirus;
	    this.ipAddress = ipAddress;
	    this.version = version;
	    this.rotation = rotation;
	    this.current = current;
	    this.pressure = pressure;
	    this.flowRate = flowRate;
	    this.temperature = temperature;
	    this.riskRating = riskRating;
	    this.latitude = latitude;
	    this.longitude = longitude;
	    this.creationDate = creationDate;
	}

	@Override
    public String toString() {
		return "Asset:" 
					  + getId()
					  + " " + getType()
					  + " " + getIpAddress()
					  + " " + getLatitude()
					  + " " + getLongitude()
					  + " " + getCreationDate();
		  }
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getOs() {
		return os;
	}
	
	public void setOs(String os) {
		this.os = os;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getIpAddress() {
		return ipAddress;
	}
	
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	public String getAntivirus() {
		return antivirus;
	}
	
	public void setAntivirus(String antivirus) {
		this.antivirus = antivirus;
	}
	
	public long getRotation() {
		return rotation;
	}
	
	public void setRotation(long rotation) {
		this.rotation = rotation;
	}
	
	public long getCurrent() {
		return current;
	}
	
	public void setCurrent(long current) {
		this.current = current;
	}
	
	public long getPressure() {
		return pressure;
	}
	
	public void setPressure(long pressure) {
		this.pressure = pressure;
	}
	
	public long getFlowRate() {
		return flowRate;
	}
	
	public void setFlowRate(long flowRate) {
		this.flowRate = flowRate;
	}
	
	public long getTemperature() {
		return temperature;
	}
	
	public void setTemperature(long temperature) {
		this.temperature = temperature;
	}
	
	public String getLatitude() {
		return latitude;
	}
	
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	public String getLongitude() {
		return longitude;
	}
	
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Asset ) {
			Asset oa = (Asset) o;
			return (oa.latitude == this.latitude) && (oa.longitude == this.longitude);
		}
		return false;
	}

	public long getRiskRating() {
		return riskRating;
	}

	public void setRiskRating(long riskRating) {
		this.riskRating = riskRating;
	}

}