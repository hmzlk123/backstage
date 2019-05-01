package com.lk.backstage.entity.usermanage;

import java.util.Date;

/**  
 * Title: UserLockedRecord
 * Description: 用户锁定记录表
 * @author linkan  
 * @date 2019年1月16日  
 */
public class UserLockedRecord {	
	
	private String recordId;			//记录编号
	private String userId;				//用户编号
	private Date lockedTime;			//锁定时间
	private Date unlockedTime;			//解锁时间
	
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getLockedTime() {
		return lockedTime;
	}
	public void setLockedTime(Date lockedTime) {
		this.lockedTime = lockedTime;
	}
	public Date getUnlockedTime() {
		return unlockedTime;
	}
	public void setUnlockedTime(Date unlockedTime) {
		this.unlockedTime = unlockedTime;
	}
}
