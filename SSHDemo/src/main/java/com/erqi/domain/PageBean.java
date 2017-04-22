package com.erqi.domain;

import java.util.List;

/**
 * 作 者: ErQi
 * 时 间: 2017.4.22.
 * 备 注: 客户类
 */
public class PageBean<T> {
	
	/** 当前页 */
	private int pageCode;
	/** 总记录条数 */
	private int totalCount;
	/** 每页显示数量 */
	private int pageSize;
	/** 每页显示的数据 */
	private List<T> list;
	public int getTotalPage() {
		int totalPage = totalCount / pageSize;
		if(totalCount % pageSize == 0){
			return totalPage;
		}else{
			return totalPage + 1;
		}
	}

	public int getPageCode() {
		return pageCode;
	}

	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "PageBean{" +
				"pageCode=" + pageCode +
				", totalCount=" + totalCount +
				", pageSize=" + pageSize +
				", list=" + list +
				'}';
	}
}
