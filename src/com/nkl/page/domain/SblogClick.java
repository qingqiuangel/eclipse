package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

public class SblogClick extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -7282358362763681513L;
	private int click_id; // 
	private int sblog_id; // 
	private int user_id; // 

	public void setClick_id(int click_id){
		this.click_id=click_id;
	}

	public int getClick_id(){
		return click_id;
	}

	public int getSblog_id() {
		return sblog_id;
	}

	public void setSblog_id(int sblog_id) {
		this.sblog_id = sblog_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

}
