package org.yule.db.service.model;

import java.sql.Date;
import java.sql.Timestamp;

public class ImageLib {

	private String id;
	private String pageId;
	private String descr;
	private byte[] image;
	private String picName;
	private Timestamp createDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
	public static ImageLib createModel(String mid,
									   String mpageId,
									   String mdescr,
									   String mpicName,
									   byte [] mimage){
		ImageLib m = new ImageLib();
		m.setId(mid);
		m.setPageId(mpageId);
		m.setDescr(mdescr);
		m.setPicName(mpicName);
		m.setImage(mimage);
		
		return m;
	}
}
