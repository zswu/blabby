package com.ambow.util;

/**
 * ���� ���� ȥ��ѯ
 * 
 * @author Administrator
 * 
 */
public class Condition {
	// �û����ǳ�,��Ҫ����findUserByCondition����
	private String nickName="";

	// Χ����Ϣ�Ĳ�ѯ�ؼ���,��Ҫ����findBlog����
	private String blogKeyWord="";

	// ������Ƿ�ҳ��Ϣ,Ĭ���ǵ�һҳ��ÿҳ5����¼
	private Pagination pagination=new Pagination();

	public String getBlogKeyWord() {
		return blogKeyWord;
	}

	public void setBlogKeyWord(String blogKeyWord) {
		this.blogKeyWord = blogKeyWord;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

}
