package service;

import java.util.List;

import util.Condition;

import entity.*;

public interface UserService {
	/**
	 * ע��
	 * @param user ��װ���û���д��ע����Ϣ
	 * @return ע���Ժ��id
	 */
     long regist(User user);
     
     /**
      *  ��¼
      * @param email   �����û���
       * @param password ��������
      * @return  ���emil&password ��ȷ�����ظ��û����������ȷ0
      */
     User login(String email,String password);
     
     /**
      * �����û��ĸ�����Ϣ
      * @param user
      */
     void update(User user);
     /**
      * ����id�ҵ�������Ϣ
      * @param id
      * @return �ҵ����û�
      */
     User findUserById(long id);
     /**
      * ��Ӻ���
      * @param userId ��ǰ�û���id
      * @param fuserId Ҫ��ӵĺ��ѵ�id
      */
     void addFriend(long userId,long fuserId);
     
     /**
      * ɾ������
      * @param userId ��ǰ�û���id
      * @param fuserIds  Ҫɾ���ĺ��ѵ�id
      */
     void deleteFriend(long userId,long fuserIds);
     
     /**
      * �����ǳ� ģ���������Ƶ��û�
      * @param condition --������������װ���ǳơ���ҳ��Ϣ
      * @return
      */
     List<User> findUserByCondition(Condition condition);
     
     /**
      * ���Һ���
      * @param userId --Ҫ���� ˭ �ĺ���
      * @param condition --������������Ҫ��װ�� ��ҳ��Ϣ
      * @return  �ҵ��ĺ����б�
      */
     List<User> findFriend(long userId,Condition condition);
}

