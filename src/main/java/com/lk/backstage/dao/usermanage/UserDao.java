package com.lk.backstage.dao.usermanage;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lk.backstage.entity.usermanage.Role;
import com.lk.backstage.entity.usermanage.User;
import com.lk.backstage.entity.usermanage.UserLockedRecord;

/**  
 * Title: UserDao
 * Description: 用户Dao接口
 * @author linkan  
 * @date 2019年1月11日  
 */
@Mapper
public interface UserDao {
	/**
	 * 
	 * Title: findByNameAndPassword
	 * Description:  根据用户名和密码查找用户
	 * @param userName
	 * @param password
	 * @return
	 */
	public User findByNameAndPassword(@Param("userName") String userName,@Param("password") String password);
	
	/**
	 * 
	 * Title: findAll
	 * Description:  查找全部用户
	 * @return
	 */
	public List<User> findAll();
	
	/**
	 * 
	 * Title: findByName
	 * Description:  根据用户查找用户
	 * @param userName
	 * @return
	 */
	public List<User> findByName(@Param("userName") String userName);
	
	/**
	 * 
	 * Title: saveUserLockedRecord
	 * Description: 记录用户锁定
	 * @param userLockedRecord
	 */
	public void saveUserLockedRecord(UserLockedRecord userLockedRecord);
	
	/**
	 * 
	 * Title: findRoleByName
	 * Description: 根据用户名查询角色
	 * @param userName
	 * @return
	 */
	public List<Role> findRoleByName(@Param("userName") String userName);
	
	/**
	 * 
	 * Title: changeUserEnable
	 * Description: 根据用户名查询角色
	 * @param userName
	 */
	public void changeUserEnable(User user);
}
