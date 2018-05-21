package org.model;

import java.sql.Date;

public class WebCrawlDataModel implements Cloneable{

	private String id;
	private String title;
	private String content;
	private String url;
	private String type;
	private Date createDate;
	private Date modifyDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	public WebCrawlDataModel createModel(String id,String title,String content,String url,String type) throws CloneNotSupportedException{
		WebCrawlDataModel model = new WebCrawlDataModel();
		model.setId(id);
		model.setTitle(title);
		model.setContent(content);
		model.setUrl(url);
		model.setType(type);
		return (WebCrawlDataModel) model.clone();
	}
}
