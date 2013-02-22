package entity;

import java.util.Date;
import java.util.*;
/**
 * 微博类，代表着每一条微博信息
 * @author Administrator
 *
 */
public class MicroBlog {
   private long id;
   private String content;
   private Date publishTime;
   private User user;
   private Set<Comment> comments=new HashSet<Comment>();
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public Date getPublishTime() {
	return publishTime;
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
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}

}


