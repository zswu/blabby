package util;
/**
 * ��������ȥ�Һ���
 * @author Administrator
 *
 */
public class Condition {
	 //�û��ǳƣ���Ҫ����findUserByCondition����
    private String nickName;
    
    //����΢���Ĳ�ѯ��Ϣ�ؼ��֣���Ҫ����findBlog����
    private String blogKeyWord;
    //������Ƿ�ҳ��Ϣ��Ĭ��Ϊ��һҳ��ÿҳ5����¼
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
