package com.chinasoft.shop.utils.domain;

import java.util.List;

/**
 * �����ҳ���������
 */
public class PageBean<T> {
	private int pageSize;//ÿҳ�ļ�¼����
	private int pageCode;//��ǰҳ��
	private int totalRecord;//�ܼ�¼��
	private List<T> beanList;//��ǰҳ��ľ�������
	private String url;//�������
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	//��ȡ��ҳ��
	public int getTotalPage(){
		int totalPage = totalRecord/pageSize;
		return totalRecord%pageSize==0?totalPage:totalPage+1;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCode() {
		return pageCode;
	}
	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
}
