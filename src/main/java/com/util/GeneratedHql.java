package com.util;

public class GeneratedHql {
	public GeneratedHql() {
		// TODO �Զ����ɵĹ��캯�����
	}

	/**
	 * ����hql find���
	 * 
	 * @param tameName
	 *            //�������
	 * @param columnName
	 *            //����
	 * @param keyName
	 *            //���ҵĹؼ����� ��'123'
	 * @return
	 */
	public static String genHql(String tameName, String columnName, String keyName) {
		return new String("from " + tameName + " where " + columnName + " ='" + keyName + "'");
	}

	public static void main(String args[]) {
		System.out.println(GeneratedHql.genHql("Account", "account", "123"));
	}
}
