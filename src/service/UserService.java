package service;

import java.util.List;

import util.Condition;

import entity.*;

public interface UserService {
	/**
	 * 注册
	 * @param user 封装了用户填写的注册信息
	 * @return 注册以后的id
	 */
     long regist(User user);
     
     /**
      *  登录
      * @param email   代表用户名
       * @param password 代表密码
      * @return  如果emil&password 正确，返回该用户，如果不正确0
      */
     User login(String email,String password);
     
     /**
      * 更新用户的个人信息
      * @param user
      */
     void update(User user);
     /**
      * 根据id找到好友信息
      * @param id
      * @return 找到的用户
      */
     User findUserById(long id);
     /**
      * 添加好友
      * @param userId 当前用户的id
      * @param fuserId 要添加的好友的id
      */
     void addFriend(long userId,long fuserId);
     
     /**
      * 删除好友
      * @param userId 当前用户的id
      * @param fuserIds  要删除的好友的id
      */
     void deleteFriend(long userId,long fuserIds);
     
     /**
      * 根据昵称 模糊查找相似的用户
      * @param condition --查找条件：封装了昵称、分页信息
      * @return
      */
     List<User> findUserByCondition(Condition condition);
     
     /**
      * 查找好友
      * @param userId --要查找 谁 的好友
      * @param condition --查找条件：主要封装了 分页信息
      * @return  找到的好友列表
      */
     List<User> findFriend(long userId,Condition condition);
}

