package util;
/**
 * 按照条件去找好友
 * @author Administrator
 *
 */
public class Condition {
	 //用户昵称，主要用于findUserByCondition方法
    private String nickName;
    
    //用与微博的查询信息关键字，主要用于findBlog方法
    private String blogKeyWord;
    //代表的是分页信息，默认为第一页、每页5条记录
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
