package com.lk.backstage.entity;

import java.util.List;

/**  
 * Title: DataTable
 * Description: 问问
 * @author linkan  
 * @date 2019年1月30日  
 */
public class DataTable<T> {
	private List<T> data;	   			//数据
    private int recordsTotal;			//数据库中记录数	
    private int draw; 					//请求服务器端次数
    private int recordsFiltered;
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public int getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	
}
