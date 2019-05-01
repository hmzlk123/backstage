package com.lk.backstage.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lk.backstage.entity.usermanage.Menu;
import com.lk.backstage.services.usermanage.MenuService;



/**  
 * Title: IndexController
 * Description: 首页控制器
 * @author linkan  
 * @date 2019年1月28日  
 */
@Controller
public class IndexController {
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping("/login")
	public String login(Model model) {
		return "login";
	}
	
	@RequestMapping("/")
	public ModelAndView index(ModelAndView mv) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		List<Menu> menuList = menuService.findMenuByName(userName);
		List<Menu> treeMenuList = new ArrayList<Menu>(); // 树递归
		for (Menu menu : menuList) {
			if ("0".equals(menu.getParentId())) {
				treeMenuList.add(menu);
			}
		}
		
		for (Menu menu : treeMenuList) {
			menu.setChildList(getChild(menu.getMenuId(), menuList));
		}
		
		mv.addObject("menulist", treeMenuList);	
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping("/home")
	public String home(Model model) {
		return "home";
	}
	
	private List<Menu> getChild(String id,List<Menu> rootMenu){
		// 子菜单
	    List<Menu> childList = new ArrayList<Menu>();
	    for (Menu menu : rootMenu) {
	        // 遍历所有节点，将父菜单id与传过来的id比较
            if (menu.getParentId().equals(id)) {
                childList.add(menu);
            }
	    }

	    // 把子菜单的子菜单再循环一遍
	    for (Menu menu : childList) {
	        menu.setChildList(getChild(menu.getMenuId(), rootMenu));// 递归
	    } 
	    
	    // 判断递归结束
	    if (childList.size() == 0) {
	        return null;
	    }
		return childList;
	}
}
