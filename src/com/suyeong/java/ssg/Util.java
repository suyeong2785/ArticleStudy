package com.suyeong.java.ssg;

import java.text.SimpleDateFormat;
import java.util.Date;

class Util {

	public static String getNowDate() {

		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date time = new Date();

		String time1 = format1.format(time);

		return time1;
	}

}