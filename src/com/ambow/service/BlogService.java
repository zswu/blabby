package com.ambow.service;

import java.util.List;

import com.ambow.entity.*;
import com.ambow.util.Condition;

public interface BlogService {
	/**
	 * ����Χ��
	 * 
	 * @param blog --
	 *            Χ��
	 */
	void publishBlog(MicroBlog blog);

	/**
	 * ��ʾ����(һ����)��Χ����Ϣ: ����:�Լ����췢���Χ����Ϣ���Լ����ѷ��������µ�Χ����Ϣ
	 * 
	 * @Param userId --
	 *            ��ǰ�û�
	 * @return
	 */
	List<MicroBlog> findNewerBlog(long userId);

	/**
	 * ����Χ����id���������۵ĸ���
	 * 
	 * @param blogId --
	 *            Χ��id
	 * @return ���۵ĸ���
	 */
	int getCommentNum(long blogId);

	/**
	 * �������
	 * 
	 * @param comment --
	 *            ��������� user�� targetBlog����Ϣ
	 */
	void generateComment(Comment comment);

	/**
	 * ����Χ����Ϣ��id���ҳ������е�����
	 * 
	 * @param blogId --
	 *            Χ����Ϣ��id
	 * @return
	 */
	List<Comment> findCommentByBlogId(long blogId);

	/**
	 * ��Ҫ���� ��ʾ '�ҵ�Χ��'����
	 * 
	 * @param userId --
	 *            �û�(��ǰ�û�/�����û�)
	 * @param condition --
	 *            ��Ҫ��װ��:Χ����Ϣ �ؼ��֡���ҳ��Ϣ
	 * @return
	 */
	List<MicroBlog> findBlog(long userId, Condition condition);

	/**
	 * ����ָ����������blog��Ϣ
	 * 
	 * @param userId --
	 *            �û�(��ǰ�û�/�����û�)
	 * @param condition --
	 *            ��Ҫ��װ��:Χ����Ϣ�ؼ��֡���ҳ��Ϣ
	 * @return ���ҳ��
	 */
	int findBlogMaxPage(long userId, Condition condition);
}
