package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

public class HonorWall extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -8095496757919500647L;
	private int wall_id; // 
	private int honor_id; // 
	private int user_id; // 
	private String wall_date; // 
	
	private String honor_name; // 
	private String honor_pic; // 
	private String real_name; // 
	private String nick_name; // 

	private String ids;
	private String random;

	public void setWall_id(int wall_id){
		this.wall_id=wall_id;
	}

	public int getWall_id(){
		return wall_id;
	}

	public void setHonor_id(int honor_id){
		this.honor_id=honor_id;
	}

	public int getHonor_id(){
		return honor_id;
	}

	public void setUser_id(int user_id){
		this.user_id=user_id;
	}

	public int getUser_id(){
		return user_id;
	}

	public void setWall_date(String wall_date){
		this.wall_date=wall_date;
	}

	public String getWall_date(){
		return wall_date;
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

	public String getHonor_name() {
		return honor_name;
	}

	public void setHonor_name(String honor_name) {
		this.honor_name = honor_name;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getHonor_pic() {
		return honor_pic;
	}

	public void setHonor_pic(String honor_pic) {
		this.honor_pic = honor_pic;
	}

}
