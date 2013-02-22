package entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户类， 代表着微博的用户
 * @author Administrator
 *
 */
public class User {
    private long id;
    private String email;
    private String password;
    private String nickName;
    //真实姓名
    private String name;
    private String gender;
    private Date bornDate;
    private String accessAddress;
    
    private Address address;
    
    private Set<User> friends=new HashSet<User>();
    private Set<MicroBlog> blogs=new HashSet<MicroBlog>();
    private Set<Comment> comments=new HashSet<Comment>();
   
    
	public String getAccessAddress() {
		return accessAddress;
	}
	public void setAccessAddress(String accessAddress) {
		this.accessAddress = accessAddress;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Date getBornDate() {
		return bornDate;
	}
	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<User> getFriends() {
		return friends;
	}
	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<MicroBlog> getBlogs() {
		return blogs;
	}
	public void setBlogs(Set<MicroBlog> blogs) {
		this.blogs = blogs;
	}
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	
    
    
}
