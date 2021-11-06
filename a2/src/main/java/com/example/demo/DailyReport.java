package com.example.demo;

public class DailyReport {
	public String Province_State;
	public String Country_Region;
	public String Confirmed;
	public String Deaths;
	public String Recovered;
	public String Active;
	public String Date;
	
	public DailyReport(String Province_State, String Country_Region, String Confirmed, String Deaths,String Recovered, 
			String Active, String Date) {
		this.Province_State = Province_State;
		this.Country_Region = Country_Region;
		this.Confirmed = Confirmed;
		this.Deaths = Deaths;
		this.Recovered = Recovered;
		this.Active = Active;
		this.Date = Date;
	}
	
	public void setProvince(String newProvince) {
		this.Province_State = newProvince;
	}
	
	public void setCountry(String newCountry) {
		this.Country_Region = newCountry;
	}
	
	public void setConfirmed(String newConfirmed) {
		this.Confirmed = newConfirmed;
	}
	
	public void setDeaths(String newDeaths) {
		this.Deaths = newDeaths;
	}
	
	public void setRecovered(String newRecovered) {
		this.Recovered = newRecovered;
	}
	
	public void setActive(String newActive) {
		this.Active = newActive;
	}
	
}
