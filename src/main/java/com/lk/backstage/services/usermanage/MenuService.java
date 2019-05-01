package com.lk.backstage.services.usermanage;

import java.util.List;
import com.lk.backstage.entity.usermanage.Menu;
import com.lk.backstage.entity.usermanage.Role;

/**  
 * Title: MenuService
 * Description: 菜单服务接口
 * @author linkan  
 * @date 2019年1月22日  
 */
public interface MenuService {
	
	/**
	 * 
	 * Title: findMenuByName
	 * Description:  根据用户名查找菜单
	 * @param userName
	 * @return
	 */
	public List<Menu> findMenuByName(String userName);
	
	/**
	 * 
	 * Title: findRoleByUrl
	 * Description:  根据URL寻找角色
	 * @param url
	 * @return
	 */
	public List<Role> findRoleByUrl(String url);
}
