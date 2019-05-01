package com.lk.backstage.dao.usermanage;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lk.backstage.entity.usermanage.Menu;
import com.lk.backstage.entity.usermanage.Role;


/**  
 * Title: MenuDao
 * Description: 菜单Dao
 * @author linkan  
 * @date 2019年1月22日  
 */
@Mapper
public interface MenuDao {
	/**
	 * 
	 * Title: findByNameAndPassword
	 * Description:  根据用户名查找菜单
	 * @param userName
	 * @param password
	 * @return
	 */
	public List<Menu> findMenuByName(@Param("userName") String userName);
	
	/**
	 * 
	 * Title: findRoleByUrl
	 * Description:  根据URL寻找角色
	 * @param url
	 * @return
	 */
	public List<Role> findRoleByUrl(@Param("url") String url);
}
