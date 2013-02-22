package service;

import java.util.List;

import util.Condition;
import entity.Comment;
import entity.MicroBlog;

/**
 * 
 * @author Administrator
 *
 */
public interface BlogService {

	/**
	 * ����΢��
	 * @param blog--΢��
	 */
	 void publishBlog(MicroBlog blog);
	 /**
	  * ��ʾ���µ�΢����Ϣ 
	  * �����Լ������΢����Ϣ���Լ����ѷ��������µĺ�����Ϣ
	  * @param userId --��ǰ�û�
	  * @return
	  */
	 List<MicroBlog> findNewerBlog(long userId);
	 /**
	  * �������
	  * @param comment --���������user��targetBlog��Ϣ
	  */
	 void generateComment(Comment comment);
	 
	 /**
	  * ����΢����id���ҳ������е�����
	  * @param blogId --΢����Ϣ��id
	  * @return
	  */
	 List<Comment> findCommentByBlogId(long blogId);
	 
	 /**
	  * ��Ҫ��ʾ���ҵ�΢���� ����
	  * @param userId �û�����ǰ�û�/�����û���
	  * @param condition--��Ҫ��װ�ˣ�΢����Ϣ�Ĺؼ��֡���ҳ��Ϣ
	  * @return
	  */
	 List<MicroBlog> findBlog(long userId,Condition condition);
	 
}
