package com.lk.backstage.services.usermanage.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.lk.backstage.dao.usermanage.MenuDao;
import com.lk.backstage.entity.usermanage.Menu;
import com.lk.backstage.entity.usermanage.Role;
import com.lk.backstage.services.usermanage.MenuService;

/**  
 * Title: MenuServiceImpl
 * Description: 
 * @author linkan  
 * @date 2019年1月22日  
 */
@Service
public class MenuServiceImpl implements MenuService {
	
	@Resource
	private MenuDao menuDao;
	
	@Override
	public List<Menu> findMenuByName(String userName) {
		return menuDao.findMenuByName(userName);
	}

	@Override
	public List<Role> findRoleByUrl(String url) {
		return menuDao.findRoleByUrl(url);
	}

}
