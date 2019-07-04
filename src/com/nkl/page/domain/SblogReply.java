package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;

public class SblogReply extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 5974537055144250656L;
	private int sblog_reply_id; // 
	private int sblog_id; // 
	private int user_id; // 
	private String reply_content; // 
	private String reply_date; // 
	
	private String user_name;
	private String nick_name;
	private String real_name;
	private String user_photo;
	private int user_level;
	private int sblog_user_id; // 
	
	private String random; // 

	public void setSblog_reply_id(int sblog_reply_id){
		this.sblog_reply_id=sblog_reply_id;
	}

	public int getSblog_reply_id(){
		return sblog_reply_id;
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

	public void setReply_content(String reply_content){
		this.reply_content=reply_content;
	}

	public String getReply_contentShow(){
		if (!StringUtil.isEmptyString(reply_content)) {
			return Transcode.htmlDiscode(reply_content);
		}
		return reply_content;
	}
	
	public String getReply_content(){
		return reply_content;
	}

	public void setReply_date(String reply_date){
		this.reply_date=reply_date;
	}

	public String getReply_date(){
		return reply_date;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getUser_photo() {
		return user_photo;
	}

	public void setUser_photo(String user_photo) {
		this.user_photo = user_photo;
	}

	public int getSblog_user_id() {
		return sblog_user_id;
	}

	public void setSblog_user_id(int sblog_user_id) {
		this.sblog_user_id = sblog_user_id;
	}

	public int getUser_level() {
		return user_level;
	}

	public void setUser_level(int user_level) {
		this.user_level = user_level;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

}
