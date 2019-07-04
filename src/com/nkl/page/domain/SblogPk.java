package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

public class SblogPk extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1739213325645694954L;
	private int pk_id; // 
	private int defend_user_id; // 
	private int defend_sblog_id; // 
	private int defend_votes; // 
	private int reply_user_id; // 
	private int reply_sblog_id; // 
	private int reply_votes; // 
	private String pk_date; // 
	private int pk_flag; // 1-打擂中 2-守擂成功 3-挑战成功
	private int pk_reward; // 1-未奖励 2-已奖励

	private String defend_sblog_title; // 
	private String defend_nick_name;
	private String defend_user_photo;
	private int defend_user_level;
	private String reply_sblog_title; // 
	private String reply_nick_name;
	private String reply_user_photo;
	private int reply_user_level;
	
	private int user_id; // 
	
	private int user_score; // 
	private int honor_id; // 
	
	private String ids;
	private String random;
	
	public String getPk_flagDesc(){
		switch (pk_flag) {
		case 1:
			return "打擂中";
		case 2:
			return "守擂成功";
		case 3:
			return "挑战成功";
		default:
			return "";
		}
	}
	
	public String getPk_rewardDesc(){
		switch (pk_reward) {
		case 1:
			return "未奖励";
		case 2:
			return "已奖励";
		default:
			return "";
		}
	}

	public void setPk_id(int pk_id){
		this.pk_id=pk_id;
	}

	public int getPk_id(){
		return pk_id;
	}

	public void setDefend_user_id(int defend_user_id){
		this.defend_user_id=defend_user_id;
	}

	public int getDefend_user_id(){
		return defend_user_id;
	}

	public void setDefend_sblog_id(int defend_sblog_id){
		this.defend_sblog_id=defend_sblog_id;
	}

	public int getDefend_sblog_id(){
		return defend_sblog_id;
	}

	public void setDefend_votes(int defend_votes){
		this.defend_votes=defend_votes;
	}

	public int getDefend_votes(){
		return defend_votes;
	}

	public void setReply_user_id(int reply_user_id){
		this.reply_user_id=reply_user_id;
	}

	public int getReply_user_id(){
		return reply_user_id;
	}

	public void setReply_sblog_id(int reply_sblog_id){
		this.reply_sblog_id=reply_sblog_id;
	}

	public int getReply_sblog_id(){
		return reply_sblog_id;
	}

	public void setReply_votes(int reply_votes){
		this.reply_votes=reply_votes;
	}

	public int getReply_votes(){
		return reply_votes;
	}

	public void setPk_date(String pk_date){
		this.pk_date=pk_date;
	}

	public String getPk_date(){
		return pk_date;
	}

	public void setPk_flag(int pk_flag){
		this.pk_flag=pk_flag;
	}

	public int getPk_flag(){
		return pk_flag;
	}

	public void setPk_reward(int pk_reward){
		this.pk_reward=pk_reward;
	}

	public int getPk_reward(){
		return pk_reward;
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

	public String getDefend_sblog_title() {
		return defend_sblog_title;
	}

	public void setDefend_sblog_title(String defend_sblog_title) {
		this.defend_sblog_title = defend_sblog_title;
	}

	public String getDefend_nick_name() {
		return defend_nick_name;
	}

	public void setDefend_nick_name(String defend_nick_name) {
		this.defend_nick_name = defend_nick_name;
	}

	public String getDefend_user_photo() {
		return defend_user_photo;
	}

	public void setDefend_user_photo(String defend_user_photo) {
		this.defend_user_photo = defend_user_photo;
	}

	public String getReply_sblog_title() {
		return reply_sblog_title;
	}

	public void setReply_sblog_title(String reply_sblog_title) {
		this.reply_sblog_title = reply_sblog_title;
	}

	public String getReply_nick_name() {
		return reply_nick_name;
	}

	public void setReply_nick_name(String reply_nick_name) {
		this.reply_nick_name = reply_nick_name;
	}

	public String getReply_user_photo() {
		return reply_user_photo;
	}

	public void setReply_user_photo(String reply_user_photo) {
		this.reply_user_photo = reply_user_photo;
	}

	public int getUser_score() {
		return user_score;
	}

	public void setUser_score(int user_score) {
		this.user_score = user_score;
	}

	public int getHonor_id() {
		return honor_id;
	}

	public void setHonor_id(int honor_id) {
		this.honor_id = honor_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getDefend_user_level() {
		return defend_user_level;
	}

	public void setDefend_user_level(int defend_user_level) {
		this.defend_user_level = defend_user_level;
	}

	public int getReply_user_level() {
		return reply_user_level;
	}

	public void setReply_user_level(int reply_user_level) {
		this.reply_user_level = reply_user_level;
	}

}
