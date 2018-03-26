package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderFormUtil {

	public OrderFormUtil() {
		// TODO Auto-generated constructor stub
	}
	//生成随机流水号的方式为日期的yyMMddhhmmss+账号+(1-100)随机码
	public static String getRandomNumber(Integer account) {
		Random random = new Random();
        Integer a=random.nextInt(100);
		Date date=new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("yyMMddhhmmss"+a.toString());
		String id=ft.format(date)+account.toString();
		return id;
	}
	public static String getDat() {
		Date date=new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		String id=ft.format(date);
		return id;
	}
	public static void main(String ags[]) {
		System.out.println(OrderFormUtil.getDat());
	}
}
