package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;

public class SblogAid extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -2662731556032353374L;
	private int aid_id; // 
	private int help_id; // 
	private int user_id; // 
	private String aid_content; // 
	private String aid_date; // 

	private int help_score; // 
	private String nick_name;
	private String user_photo;
	private int user_level;
	
	private String ids;
	private String random;

	public void setAid_id(int aid_id){
		this.aid_id=aid_id;
	}

	public int getAid_id(){
		return aid_id;
	}

	public void setHelp_id(int help_id){
		this.help_id=help_id;
	}

	public int getHelp_id(){
		return help_id;
	}

	public void setUser_id(int user_id){
		this.user_id=user_id;
	}

	public int getUser_id(){
		return user_id;
	}

	public void setAid_content(String aid_content){
		this.aid_content=aid_content;
	}

	public String getAid_contentShow(){
		if (!StringUtil.isEmptyString(aid_content)) {
			return Transcode.htmlDiscode(aid_content);
		}
		return aid_content;
	}
	
	public String getAid_content(){
		return aid_content;
	}

	public void setAid_date(String aid_date){
		this.aid_date=aid_date;
	}

	public String getAid_date(){
		return aid_date;
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

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getUser_photo() {
		return user_photo;
	}

	public void setUser_photo(String user_photo) {
		this.user_photo = user_photo;
	}

	public int getHelp_score() {
		return help_score;
	}

	public void setHelp_score(int help_score) {
		this.help_score = help_score;
	}

	public int getUser_level() {
		return user_level;
	}

	public void setUser_level(int user_level) {
		this.user_level = user_level;
	}

}
