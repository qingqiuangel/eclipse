package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

public class Sensitive extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -8738777721805535695L;
	private int sensitive_id; // 
	private String sensitive_name; // 

	private String ids;
	private String random;

	public void setSensitive_id(int sensitive_id){
		this.sensitive_id=sensitive_id;
	}

	public int getSensitive_id(){
		return sensitive_id;
	}

	public void setSensitive_name(String sensitive_name){
		this.sensitive_name=sensitive_name;
	}

	public String getSensitive_name(){
		return sensitive_name;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIds() {
		return ids;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getRandom() {
		return random;
	}

}
