package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;

public class Sblog extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -674161960515333295L;
	private int sblog_id; // 
	private int user_id; // 
	private String sblog_title; // 
	private String sblog_content; // 
	private String sblog_date; // 
	private int sblog_click; // 
	private int sblog_type; // 1-公司 2-校园 3-家庭 4-恋爱 5-日常
	private int sblog_flag; // 1-正常 2-已删除
	
	private String sblog_pics; // 
	private String sblog_pics_old; // 
	
	private String user_name;
	private String nick_name;
	private String real_name;
	private String user_photo;
	private int user_level;
	
	private int help_id; // 
	
	private String random; // 
	private String ids; // 

	
	public String getSblog_typeDesc(){
		switch (sblog_type) {
		case 1:
			return "公司";
		case 2:
			return "校园";
		case 3:
			return "家庭";
		case 4:
			return "恋爱";
		case 5:
			return "日常";
		default:
			return "";
		}
	}
	public String getSblog_flagDesc(){
		switch (sblog_flag) {
		case 1:
			return "正常";
		case 2:
			return "已删除";
		default:
			return "";
		}
	}
	
	public void setSblog_id(int sblog_id){
		this.sblog_id=sblog_id;
	}

	public int getSblog_id(){
		return sblog_id;
	}

	public void setUser_id(int user_id){
		this.user_id=user_id;
	}

	public int getUser_id(){
		return user_id;
	}

	public void setSblog_title(String sblog_title){
		this.sblog_title=sblog_title;
	}

	public String getSblog_title(){
		return sblog_title;
	}

	public void setSblog_content(String sblog_content){
		this.sblog_content=sblog_content;
	}

	public String getSblog_contentShow(){
		if (!StringUtil.isEmptyString(sblog_content)) {
			return Transcode.htmlDiscode(sblog_content);
		}
		return sblog_content;
	}
	
	public String getSblog_content(){
		return sblog_content;
	}

	public void setSblog_date(String sblog_date){
		this.sblog_date=sblog_date;
	}

	public String getSblog_date(){
		return sblog_date;
	}

	public void setSblog_click(int sblog_click){
		this.sblog_click=sblog_click;
	}

	public int getSblog_click(){
		return sblog_click;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public int getSblog_flag() {
		return sblog_flag;
	}

	public void setSblog_flag(int sblog_flag) {
		this.sblog_flag = sblog_flag;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getSblog_type() {
		return sblog_type;
	}
	public void setSblog_type(int sblog_type) {
		this.sblog_type = sblog_type;
	}
	public String getSblog_pics() {
		return sblog_pics;
	}
	public void setSblog_pics(String sblog_pics) {
		this.sblog_pics = sblog_pics;
	}
	public String getUser_photo() {
		return user_photo;
	}
	public void setUser_photo(String user_photo) {
		this.user_photo = user_photo;
	}
	public String getSblog_pics_old() {
		return sblog_pics_old;
	}
	public void setSblog_pics_old(String sblog_pics_old) {
		this.sblog_pics_old = sblog_pics_old;
	}
	public int getHelp_id() {
		return help_id;
	}
	public void setHelp_id(int help_id) {
		this.help_id = help_id;
	}
	public int getUser_level() {
		return user_level;
	}
	public void setUser_level(int user_level) {
		this.user_level = user_level;
	}

}
