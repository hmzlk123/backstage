package com.lk.backstage.services.usermanage.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lk.backstage.dao.usermanage.UserDao;
import com.lk.backstage.entity.usermanage.Role;
import com.lk.backstage.entity.usermanage.User;
import com.lk.backstage.entity.usermanage.UserLockedRecord;
import com.lk.backstage.services.usermanage.UserService;


/**  
 * Title: UserServiceImpl
 * Description: 用户服务实现类
 * @author linkan  
 * @date 2019年1月11日  
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;
	 

	@Override
	public User findByNameAndPassword(String userName, String password) {
		return userDao.findByNameAndPassword(userName, password);
	}

	@Override
	public List<User> findByName(String userName) {
		return userDao.findByName(userName);
	}
	
	@Override
	public void recordLocked(UserLockedRecord userLockedRecord) {
		userDao.saveUserLockedRecord(userLockedRecord);
	}

	@Override
	public List<Role> findRoleByName(String userName) {
		return userDao.findRoleByName(userName);
	}

	@Override
	public List<User> findAll() {
		
		return userDao.findAll();
	}

	@Override
	public void changeUserEnable(User user) {
		userDao.changeUserEnable(user);
	}

}
