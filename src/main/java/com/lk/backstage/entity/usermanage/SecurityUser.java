package com.lk.backstage.entity.usermanage;

import java.util.Collection;
import java.util.Date;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;

/**  
 * Title: SecurityUser
 * Description: 用于SpringSecurity登录的安全用户(UserDetails)
 * @author linkan  
 * @date 2019年1月15日  
 */
public class SecurityUser extends org.springframework.security.core.userdetails.User implements UserDetails,CredentialsContainer{
		
	public SecurityUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}
	
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	private String userId;			//用户编号
	private String userName;		//登录名
	private String password;		//密码
	private byte[] profile_picture;	//头像
	private String name;			//姓名
	private int sex;				//性别 0-男性 1-女性
	private String address;			//地址
	private String email;			//邮箱
	private String department;		//部门
	private String introduction;	//个人简介
	private int enabled;			//是否可用：0 禁用,1 可用 默认1
	private int locked;				//是否锁定：0 未锁定，1 锁定 默认1
	private Date birthday;			//出生日期
	private Date createTime;		//创建时间
	private Date modifyTime;		//修改时间
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public byte[] getProfile_picture() {
		return profile_picture;
	}
	public void setProfile_picture(byte[] profile_picture) {
		this.profile_picture = profile_picture;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public int getLocked() {
		return locked;
	}
	public void setLocked(int locked) {
		this.locked = locked;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
