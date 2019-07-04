package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

public class SblogPic extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -7282358362763681513L;
	private int pic_id; // 
	private int sblog_id; // 
	private String pic_picture; // 

	public void setPic_id(int pic_id){
		this.pic_id=pic_id;
	}

	public int getPic_id(){
		return pic_id;
	}

	public void setPic_picture(String pic_picture){
		this.pic_picture=pic_picture;
	}

	public String getPic_picture(){
		return pic_picture;
	}

	public int getSblog_id() {
		return sblog_id;
	}

	public void setSblog_id(int sblog_id) {
		this.sblog_id = sblog_id;
	}

}
