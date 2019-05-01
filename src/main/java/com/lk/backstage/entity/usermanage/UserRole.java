package com.lk.backstage.entity.usermanage;

import java.util.Date;

/**  
 * Title: UserRole
 * Description: 用户角色实体
 * @author linkan  
 * @date 2019年1月18日  
 */
public class UserRole {
	private String resId;		//编号
	private String userId;		//用户编号
	private String roleId;		//角色编号
	private Date createTime;	//创建时间
	private Date modifyTime;	//修改时间
	
	public String getResId() {
		return resId;
	}
	public void setResId(String resId) {
		this.resId = resId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
}
