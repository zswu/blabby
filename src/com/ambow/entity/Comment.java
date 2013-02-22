package com.ambow.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 评论类,代表对某条围脖信息的评论
 * 
 * @author Administrator
 * 
 */
public class Comment {
	private long id;

	private String content;

	private Date commentTime;

	// user属性代表该评论 是哪个用户发出的
	private User user;

	// tagetBlog属性代表该评论 针对的是哪条围脖信息
	private MicroBlog targetBlog;

	public Date getCommentTime() {
		return new java.sql.Date(commentTime.getTime()) {
		
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public String toString() {
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				return format.format(this);
			}
		};
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
