package model;

import java.util.Date;

public class AssetEvent {
	
	private String id;
	protected int rotation;
	protected double current;
	protected int pressure;
	protected int temperature;
	private Date ts;
	
    public AssetEvent(){
		
	}
	
	public AssetEvent(String id, int rotation, double current, int pressure, int temperature){
		this.id = id;
		this.rotation = rotation;
		this.current = current;
		this.pressure = pressure;
		this.temperature = temperature;
	}
	
	
	public AssetEvent(String id, int rotation, double current, int pressure, int temperature, Date ts){
		this.id = id;
		this.rotation = rotation;
		this.current = current;
		this.pressure = pressure;
		this.temperature = temperature;
		this.ts = ts;
	}
	
	@Override
    public String toString() {
		return "Asset:" 
					  + getId()
					  + " " + getRotation()
					  + " " + getPressure()
					  + " " + getTemperature()
					  + " " + getCurrent()
					  + " " + getTs();
		  }
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public int getRotation() {
		return rotation;
	}
	
	public void setRotation(int rotation) {
		this.rotation = rotation;
	}
	
	public double getCurrent() {
		return current;
	}
	
	public void setCurrent(double current) {
		this.current = current;
	}
	
	public int getPressure() {
		return pressure;
	}
	
	public void setPressure(int pressure) {
		this.pressure = pressure;
	}
	
	public int getTemperature() {
		return temperature;
	}
	
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	
	public Date getTs() {
		return ts;
	}
	
	public void setTs(Date ts) {
		this.ts = ts;
	}

}
