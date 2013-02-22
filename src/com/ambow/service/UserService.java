package com.ambow.service;

import com.ambow.entity.*;
import com.ambow.util.Condition;

import java.util.*;

public interface UserService {
	/**
	 * 注册
	 * 
	 * @param user
	 *            封装了用户填写的注册信息
	 * @return 注册以后的id
	 */
	long regist(User user);

	/**
	 * 登陆
	 * 
	 * @param email
	 *            代表用户名
	 * @param pwd
	 *            代表密码
	 * @return 如果email & passwod 正确，返回该user对象 如果不正确，返回null
	 */
	User login(String email, String pwd);

	/**
	 * 更新用户的个人信息
	 * 
	 * @param user
	 *            封装了修改以后的个人信息/id不能修改
	 */
	void update(User user);

	/**
	 * 根据id来查找对应的用户
	 * 
	 * @param id
	 * @return 找到的用户
	 */
	User findUserById(long id);

	/**
	 * 添加好友
	 * 
	 * @param userId
	 *            当前用户的id
	 * @param fuserId
	 *            要添加的好友的id
	 */
	void addFriend(long userId, long fuserId);

	/**
	 * 删除好友
	 * 
	 * @param userId
	 *            当前用户的id
	 * @param fuserId
	 *            要删除的好友的id
	 */
	void deleteFriend(long userId, long fuserId);

	/**
	 * 根据查找条件 模糊查找 相似的用户
	 * 
	 * @param condition --
	 *            查找条件:封装了 昵称、分页信息
	 * @return 找到的用户列表
	 */
	List<User> findUserByCondition(Condition condition);

	/**
	 * 查找好友
	 * 
	 * @Param userId --
	 *            要查找 谁 的好友
	 * @param condition
	 *            --查找条件:主要封装了 分页信息
	 * @return 找到的好友列表
	 */
	List<User> findFriend(long userId, Condition condition);
	
	/**
	 * 获取好友列表的最大页数
	 * @param condition
	 * @return
	 */
	int getFriendMaxPage(long userId,Condition condition);

	/**
	 * 获取用户列表的最大页数
	 * @param condition
	 * @return
	 */
	int getUserMaxPage(Condition condition);
}
