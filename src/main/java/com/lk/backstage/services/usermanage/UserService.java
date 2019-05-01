package com.lk.backstage.services.usermanage;

import java.util.List;

import com.lk.backstage.entity.usermanage.Role;
import com.lk.backstage.entity.usermanage.User;
import com.lk.backstage.entity.usermanage.UserLockedRecord;


/**  
 * Title: UserService
 * Description: 用户服务层
 * @author linkan  
 * @date 2019年1月11日  
 */
public interface UserService {
	/**
	 * 根据用户名和密码寻找用户
	 * Title: findByNameAndPassword
	 * Description:  根据用户名和密码寻找用户
	 * @param userName用户名
	 * @param password密码
	 * @return
	 */
	public User findByNameAndPassword(String userName,String password);
	
	/**
	 * 根据用户查找用户
	 * Title: findByName
	 * Description:  
	 * @param userName
	 * @return
	 */
	public List<User> findByName(String userName);
	
	/**
	 * 根据用户查找用户
	 * Title: findAll
	 * Description:  
	 * @return
	 */
	public List<User> findAll();
	
	/**
	 * 记录用户锁定
	 * Title: recordLocked
	 * Description:  
	 * @param userLockedRecord
	 */
	public void recordLocked(UserLockedRecord userLockedRecord);
	
	/**
	 * 根据用户名查询角色
	 * Title: findRoleByName
	 * Description:  
	 * @param userName
	 * @return
	 */
	public List<Role> findRoleByName(String userName);
	
	/**
	 * 
	 * Title: changeUserEnable
	 * Description:  更改用户禁用状态
	 * @param user
	 */
	public void changeUserEnable(User user);
}
