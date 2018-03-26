package com.util;

public class GeneratedHql {
	public GeneratedHql() {
		// TODO 自动生成的构造函数存根
	}

	/**
	 * 生成hql find语句
	 * 
	 * @param tameName
	 *            //数库表名
	 * @param columnName
	 *            //列名
	 * @param keyName
	 *            //查找的关键字名 如'123'
	 * @return
	 */
	public static String genHql(String tameName, String columnName, String keyName) {
		return new String("from " + tameName + " where " + columnName + " ='" + keyName + "'");
	}

	public static void main(String args[]) {
		System.out.println(GeneratedHql.genHql("Account", "account", "123"));
	}
}
