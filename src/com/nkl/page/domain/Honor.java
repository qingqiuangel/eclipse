package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

public class Honor extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -4184361035072877521L;
	private int honor_id; // 
	private String honor_name; // 
	private String honor_pic; // 

	private String ids;
	private String random;

	public void setHonor_id(int honor_id){
		this.honor_id=honor_id;
	}

	public int getHonor_id(){
		return honor_id;
	}

	public void setHonor_name(String honor_name){
		this.honor_name=honor_name;
	}

	public String getHonor_name(){
		return honor_name;
	}

	public void setHonor_pic(String honor_pic){
		this.honor_pic=honor_pic;
	}

	public String getHonor_pic(){
		return honor_pic;
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
