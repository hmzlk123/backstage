package com.lk.backstage.controller.usermanage;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lk.backstage.entity.DataTable;
import com.lk.backstage.entity.DataTableParameter;
import com.lk.backstage.entity.usermanage.User;
import com.lk.backstage.services.usermanage.UserService;
import com.lk.backstage.utils.DataTableUtils;

/**  
 * Title: UserController
 * Description: 
 * @author linkan  
 * @date 2019年1月29日  
 */
@Controller
@RequestMapping("/um")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("")
	public String userList(Model model) {
		return "/usermanage/userlist";
	}
	
	/**
	 * 查询首页滚动图配置信息列表
	 * @param request
	 * @param response
	 * @return
	 */
    @ResponseBody
    @RequestMapping("/userlist")
    public Object userlist(String jsonParam,HttpServletResponse response){
    	DataTableParameter dataTableParam = DataTableUtils.getDataTableParameterByJsonParam(jsonParam);
    	
    	StringBuffer orderBy = new StringBuffer();
    	List<String> iSortColsName = dataTableParam.getiSortColsName();
    	List<String> sSortDirs = dataTableParam.getsSortDirs();
    	for(int i=0; i<iSortColsName.size(); i++){
            String sortColName = iSortColsName.get(i);
            if("userName".equals(sortColName)) {
            	sortColName = "user_name";
            }
            String sortDir = sSortDirs.get(+i);
            if(StringUtils.isEmpty(orderBy))
            	orderBy.append(sortColName+" "+sortDir);
            else
            	orderBy.append(","+sortColName+" "+sortDir);
        }
    	
    	
    	PageHelper.startPage(dataTableParam.getiDisplayStart()/dataTableParam.getiDisplayLength()+1, dataTableParam.getiDisplayLength(), orderBy.toString());
    	List<User> userList = userService.findAll();
    	
    	PageInfo<User> userPageList = new PageInfo<User>(userList);
    	
    	DataTable<User> dt = new DataTable<User>();
        int sEcho = dataTableParam.getsEcho()+1;
        dt.setData(userList);
        dt.setDraw(sEcho);
        dt.setRecordsTotal((int)userPageList.getTotal());
        dt.setRecordsFiltered((int)userPageList.getTotal());
        return JSONObject.toJSONString(dt);
    }
    
	/**
	 *  
	 * @param request
	 * @param response
	 * @return
	 */
    @ResponseBody
    @RequestMapping("/changeenable")
    public Object changeUserEnable(@RequestBody User user,HttpServletResponse response){
    	user.getUserId();
    	if(user.getEnabled()==0)
    		user.setEnabled(1);
    	else
    		user.setEnabled(0);
    	userService.changeUserEnable(user);
        return user;
    }
    
}
