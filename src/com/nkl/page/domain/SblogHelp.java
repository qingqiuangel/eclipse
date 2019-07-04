package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;

public class SblogHelp extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -2367114970718847927L;
	private int help_id; // 
	private int sblog_id; // 
	private int user_id; // 
	private int help_score; // 
	private int help_flag; // 1-悬赏中 2-已结束

	private String sblog_title;
	private String sblog_content;
	private String sblog_date;
	private String nick_name;
	private String user_photo;
	private int user_level; // 
	private String aid_date;
	
	private int aid_user_id; // 
	private String aid_nick_name;
	
	private String ids;
	private String random;

	public String getHelp_flagDesc(){
		switch (help_flag) {
		case 1:
			return "悬赏中";
		case 2:
			return "已结束";
		default:
			return "";
		}
	}
	
	public void setHelp_id(int help_id){
		this.help_id=help_id;
	}

	public int getHelp_id(){
		return help_id;
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

	public void setHelp_score(int help_score){
		this.help_score=help_score;
	}

	public int getHelp_score(){
		return help_score;
	}

	public void setHelp_flag(int help_flag){
		this.help_flag=help_flag;
	}

	public int getHelp_flag(){
		return help_flag;
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

	public String getSblog_title() {
		return sblog_title;
	}

	public void setSblog_title(String sblog_title) {
		this.sblog_title = sblog_title;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public int getAid_user_id() {
		return aid_user_id;
	}

	public void setAid_user_id(int aid_user_id) {
		this.aid_user_id = aid_user_id;
	}

	public String getAid_nick_name() {
		return aid_nick_name;
	}

	public void setAid_nick_name(String aid_nick_name) {
		this.aid_nick_name = aid_nick_name;
	}

	public String getUser_photo() {
		return user_photo;
	}

	public void setUser_photo(String user_photo) {
		this.user_photo = user_photo;
	}

	public int getUser_level() {
		return user_level;
	}

	public void setUser_level(int user_level) {
		this.user_level = user_level;
	}

	public String getSblog_date() {
		return sblog_date;
	}

	public void setSblog_date(String sblog_date) {
		this.sblog_date = sblog_date;
	}

	public String getSblog_contentShow(){
		if (!StringUtil.isEmptyString(sblog_content)) {
			return Transcode.htmlDiscode(sblog_content);
		}
		return sblog_content;
	}
	
	public String getSblog_content() {
		return sblog_content;
	}

	public void setSblog_content(String sblog_content) {
		this.sblog_content = sblog_content;
	}

	public String getAid_date() {
		return aid_date;
	}

	public void setAid_date(String aid_date) {
		this.aid_date = aid_date;
	}

}
