package entity;

import java.util.Date;

/**
 * �����࣬������ĳ��΢����Ϣ������
 * @author Administrator
 *
 */
public class Comment {
     private long id;
     private String content;
     private Date commentTime;
     //user���Դ�����������ĸ��û�������
     private User user;
     //tagetBlog���Դ���������ĸ�΢��
     private MicroBlog targetBlog;
     
	public Date getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public MicroBlog getTargetBlog() {
		return targetBlog;
	}
	public void setTargetBlog(MicroBlog targetBlog) {
		this.targetBlog = targetBlog;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
     
}
