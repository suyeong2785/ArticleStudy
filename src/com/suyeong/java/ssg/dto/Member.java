package com.suyeong.java.ssg.dto;

public class Member {
	public String logInId;
	public String logInPassword;
	String name;

	public Member(String logInId, String logInPassword, String name) {
		this.logInId = logInId;
		this.logInPassword = logInPassword;
		this.name = name;
	}

}
