package com.lk.backstage.entity.usermanage;

import java.util.Date;

/**  
 * Title: Role
 * Description: 角色实体
 * @author linkan  
 * @date 2019年1月18日  
 */
public class Role {
	private String roleId;		//角色编号
	private String roleName;	//角色名称
	private String description;	//描述
	private String remark;		//备注
	private Date createTime;	//创建时间
	private Date modifyTime;	//修改时间
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
