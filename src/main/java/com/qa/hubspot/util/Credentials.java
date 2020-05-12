package com.qa.hubspot.util;

public class Credentials {
	
	//give ref. names 
	
	String appUsername;
	String appPassword;
	
	//create constructor 
	
	public Credentials(String appUsername,String appPassword) {
		
		this.appUsername = appUsername;
		this.appPassword = appPassword;
	}

	//use setter and getter from source (shortcut)
	
	public String getAppUsername() {
		return appUsername;
	}

	public void setAppUsername(String appUsername) {
		this.appUsername = appUsername;
	}

	public String getAppPassword() {
		return appPassword;
	}

	public void setAppPassword(String appPassword) {
		this.appPassword = appPassword;
	}
	
	
	
	

}
