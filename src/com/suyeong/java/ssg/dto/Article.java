package com.suyeong.java.ssg.dto;

public class Article {
	public int id;
	public String date;
	public String title;
	public String body;
	public int hit;

	public Article(int id, String date, String title, String body ) {
		this(id, date, title, body, 0);
	}
	
	public Article(int id, String date, String title, String body, int hit) {
		this.id = id;
		this.date = date;
		this.title = title;
		this.body = body;
		this.hit = hit;
	}
	

}
