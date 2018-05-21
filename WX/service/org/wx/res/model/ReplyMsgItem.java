package org.wx.res.model;

/**
 * 图文消息回复体
 * @author Administrator
 *
 */
public class ReplyMsgItem {

	//-- 图文消息标题
	String title;
	//-- 图文消息描述
	String description;
	//-- 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
	String picUrl;
	//-- 点击图文消息跳转链接
	String url;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
