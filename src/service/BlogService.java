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
	 * 发布微博
	 * @param blog--微博
	 */
	 void publishBlog(MicroBlog blog);
	 /**
	  * 显示最新的微博信息 
	  * 包括自己发表的微博信息、自己好友发布的最新的好友信息
	  * @param userId --当前用户
	  * @return
	  */
	 List<MicroBlog> findNewerBlog(long userId);
	 /**
	  * 添加评论
	  * @param comment --本身包含了user和targetBlog信息
	  */
	 void generateComment(Comment comment);
	 
	 /**
	  * 根据微博的id，找出它所有的评论
	  * @param blogId --微博信息的id
	  * @return
	  */
	 List<Comment> findCommentByBlogId(long blogId);
	 
	 /**
	  * 主要显示‘我的微博’ 界面
	  * @param userId 用户（当前用户/其他用户）
	  * @param condition--主要封装了：微博信息的关键字、分页信息
	  * @return
	  */
	 List<MicroBlog> findBlog(long userId,Condition condition);
	 
}
