package com.service.AccountManage;

import com.model.UserInfor;

public interface RegisterService {
/**
 * �ж��˺��Ƿ�ע��ɹ� 
 * @return 1:�ɹ� 2:ʧ��
 */
	int isRegister(UserInfor userInfor);
	/**
	 * �жϿͻ�ע����Ϣ���˺��Ƿ����
	 * 
	 * @param userInfor
	 * @return true:���� false:������
	 */
	boolean isExisted(UserInfor userInfor);
	void saveUserInfor(UserInfor userInfor);
}
