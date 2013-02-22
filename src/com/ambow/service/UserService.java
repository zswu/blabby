package com.ambow.service;

import com.ambow.entity.*;
import com.ambow.util.Condition;

import java.util.*;

public interface UserService {
	/**
	 * ע��
	 * 
	 * @param user
	 *            ��װ���û���д��ע����Ϣ
	 * @return ע���Ժ��id
	 */
	long regist(User user);

	/**
	 * ��½
	 * 
	 * @param email
	 *            �����û���
	 * @param pwd
	 *            ��������
	 * @return ���email & passwod ��ȷ�����ظ�user���� �������ȷ������null
	 */
	User login(String email, String pwd);

	/**
	 * �����û��ĸ�����Ϣ
	 * 
	 * @param user
	 *            ��װ���޸��Ժ�ĸ�����Ϣ/id�����޸�
	 */
	void update(User user);

	/**
	 * ����id�����Ҷ�Ӧ���û�
	 * 
	 * @param id
	 * @return �ҵ����û�
	 */
	User findUserById(long id);

	/**
	 * ��Ӻ���
	 * 
	 * @param userId
	 *            ��ǰ�û���id
	 * @param fuserId
	 *            Ҫ��ӵĺ��ѵ�id
	 */
	void addFriend(long userId, long fuserId);

	/**
	 * ɾ������
	 * 
	 * @param userId
	 *            ��ǰ�û���id
	 * @param fuserId
	 *            Ҫɾ���ĺ��ѵ�id
	 */
	void deleteFriend(long userId, long fuserId);

	/**
	 * ���ݲ������� ģ������ ���Ƶ��û�
	 * 
	 * @param condition --
	 *            ��������:��װ�� �ǳơ���ҳ��Ϣ
	 * @return �ҵ����û��б�
	 */
	List<User> findUserByCondition(Condition condition);

	/**
	 * ���Һ���
	 * 
	 * @Param userId --
	 *            Ҫ���� ˭ �ĺ���
	 * @param condition
	 *            --��������:��Ҫ��װ�� ��ҳ��Ϣ
	 * @return �ҵ��ĺ����б�
	 */
	List<User> findFriend(long userId, Condition condition);
	
	/**
	 * ��ȡ�����б�����ҳ��
	 * @param condition
	 * @return
	 */
	int getFriendMaxPage(long userId,Condition condition);

	/**
	 * ��ȡ�û��б�����ҳ��
	 * @param condition
	 * @return
	 */
	int getUserMaxPage(Condition condition);
}
