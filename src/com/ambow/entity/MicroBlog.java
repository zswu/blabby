package com.ambow.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 围脖类,代表每一条围脖信息
 * 
 * @author Administrator
 * 
 */
public class MicroBlog {
	private long id;

	private String content;

	private Date publishTime;

	private User user;

	private Set<Comment> comments = new HashSet<Comment>();
	
	private int commentNum;

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

	public Date getPublishTime() {
		Date d = new java.sql.Date(this.publishTime.getTime()) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String toString() {
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				return format.format(this);
			}
		};
		return d;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	

}
