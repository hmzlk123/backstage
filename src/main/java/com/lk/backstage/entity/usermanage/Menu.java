package com.lk.backstage.entity.usermanage;

import java.util.Date;
import java.util.List;

/**  
 * Title: Menu
 * Description: 
 * @author linkan  
 * @date 2019年1月22日  
 */
public class Menu {
	private String menuId;			//菜单编号
	private String parentId;		//父节点编号
	private String name;			//菜单名称
	private int isChild;			//是否子节点，0－否，1－是。
	private String url;				//菜单链接
	private String target;			//指向类型
	private int relative;			//是否相对路径，0－否，1－是。
	private String icon;			//图标
	private int seq;				//序列
	private int enable;				//是否可用，0－否，1－是。
	private int visible;			//是否可见，0－否，1－是。
	private Date createTime;		//创建时间
	private Date modifyTime;		//修改时间
	private List<Menu> childList;	//子节点菜单
	
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIsChild() {
		return isChild;
	}
	public void setIsChild(int isChild) {
		this.isChild = isChild;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public int getRelative() {
		return relative;
	}
	public void setRelative(int relative) {
		this.relative = relative;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public int getVisible() {
		return visible;
	}
	public void setVisible(int visible) {
		this.visible = visible;
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
	public List<Menu> getChildList() {
		return childList;
	}
	public void setChildList(List<Menu> childList) {
		this.childList = childList;
	}
}
