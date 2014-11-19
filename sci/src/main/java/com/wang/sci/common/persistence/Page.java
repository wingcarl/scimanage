package com.wang.sci.common.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.wang.sci.common.config.Global;

public class Page<T> {
	private int pageNo = 1;
	private int pageSize = Integer.valueOf(Global.getConfig("page.pageSize"));
	
	private long count;// 总记录数，设置为“-1”表示不查询总数
	
	private int first;// 首页索引
	private int last;// 尾页索引
	private int prev;// 上一页索引
	private int next;// 下一页索引
	
	private boolean firstPage;//是否是第一页
	private boolean lastPage;//是否是最后一页

	private int length = 8;// 显示页面长度
	private int slider = 1;// 前后显示页面长度
	
	private List<T> list = new ArrayList<T>();
	
	private String orderBy = ""; // 标准查询有效， 实例： updatedate desc, name asc
	
	private String funcName = "page"; // 设置点击页码调用的js函数名称，默认为page，在一页有多个分页对象时使用。
	
	private String message = ""; // 设置提示消息，显示在“共n条”之后

	public Page() {
		this.pageSize = -1;
	}
	
	public Page(HttpServletRequest request,HttpServletResponse response){
		this(request,response,-2);
	}
	
	public Page(HttpServletRequest request,HttpServletResponse response,int defaultPageSize){
		String no = request.getParameter("pageNo");
		if(StringUtils.isNumeric(no)){
			//CookieUtils.setCookie(response,"pageNo",no);
			this.setPageNo(Integer.parseInt(no));
		}
		else if(request.getParameter("repage")!= null){
			//no = CookieUtils.getCookie("pageNo");
			if(StringUtils.isNumeric(no)){
				this.setPageNo(Integer.parseInt(no));
			}
		}
		String pageSize = request.getParameter("pageSize");
		if(StringUtils.isNumeric(pageSize)){
			//CookieUtils.setCookie(response,"pageSize",pageSize);
			this.setPageSize(Integer.parseInt(pageSize));
		}
		else if(request.getParameter("repage")!= null){
		//	pageSize = CookieUtils.getCookie("pageSize");
			if(StringUtils.isNumeric(pageSize)){
				this.setPageSize(Integer.parseInt(pageSize));
			}
		}
		else if(defaultPageSize!=-2){
			this.setPageSize(Integer.parseInt(pageSize));
		}
		String orderBy = request.getParameter("orderBy");
		if(StringUtils.isNotBlank(orderBy)){
			this.setOrderBy(orderBy);
		}
	}
	
	public int getPageNo(){
		return pageNo;
	}
	public void setPageNo(int pageNo){
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
}
