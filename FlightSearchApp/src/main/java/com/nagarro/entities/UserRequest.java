package com.nagarro.entities;

public class UserRequest {
	private String departureLoc;
	private String arrivalLoc;
	private String flightDate;
	private String flightClass;
	private String outputPreference;
	public String getDepartureLoc() {
		return departureLoc;
	}
	public void setDepartureLoc(String departureLoc) {
		this.departureLoc = departureLoc;
	}
	public String getArrivalLoc() {
		return arrivalLoc;
	}
	public void setArrivalLoc(String arrivalLoc) {
		this.arrivalLoc = arrivalLoc;
	}
	public String getFlightDate() {
		return flightDate;
	}
	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}
	public String getFlightClass() {
		return flightClass;
	}
	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}
	public String getOutputPreference() {
		return outputPreference;
	}
	public void setOutputPreference(String outputPreference) {
		this.outputPreference = outputPreference;
	}
	@Override
	public String toString() {
		return "UserRequest [departureLoc=" + departureLoc + ", arrivalLoc=" + arrivalLoc + ", flightDate=" + flightDate
				+ ", flightClass=" + flightClass + ", outputPreference=" + outputPreference + "]";
	}
	
	
	
	
}