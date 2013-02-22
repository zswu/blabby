package com.ambow.service;

import java.util.List;

import com.ambow.entity.*;
import com.ambow.util.Condition;

public interface BlogService {
	/**
	 * 发布围脖
	 * 
	 * @param blog --
	 *            围脖
	 */
	void publishBlog(MicroBlog blog);

	/**
	 * 显示最新(一周内)的围脖信息: 包括:自己今天发表的围脖信息、自己好友发布的最新的围脖信息
	 * 
	 * @Param userId --
	 *            当前用户
	 * @return
	 */
	List<MicroBlog> findNewerBlog(long userId);

	/**
	 * 根据围脖的id，查找评论的个数
	 * 
	 * @param blogId --
	 *            围脖id
	 * @return 评论的个数
	 */
	int getCommentNum(long blogId);

	/**
	 * 添加评论
	 * 
	 * @param comment --
	 *            本身包含了 user和 targetBlog的信息
	 */
	void generateComment(Comment comment);

	/**
	 * 根据围脖信息的id，找出它所有的评论
	 * 
	 * @param blogId --
	 *            围脖信息的id
	 * @return
	 */
	List<Comment> findCommentByBlogId(long blogId);

	/**
	 * 主要用于 显示 '我的围脖'界面
	 * 
	 * @param userId --
	 *            用户(当前用户/其他用户)
	 * @param condition --
	 *            主要封装了:围脖信息 关键字、分页信息
	 * @return
	 */
	List<MicroBlog> findBlog(long userId, Condition condition);

	/**
	 * 按照指定条件查找blog信息
	 * 
	 * @param userId --
	 *            用户(当前用户/其他用户)
	 * @param condition --
	 *            主要封装了:围脖信息关键字、分页信息
	 * @return 最大页数
	 */
	int findBlogMaxPage(long userId, Condition condition);
}
