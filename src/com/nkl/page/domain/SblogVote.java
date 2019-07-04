package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

public class SblogVote extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 2361026619843367804L;
	private int vote_id; // 
	private int pk_id; // 
	private int sblog_id; // 
	private int user_id; // 

	private int user_level; // 
	private int vote_type; // 1-defend_votes 2-reply_votes
	
	private String ids;
	private String random;

	public void setVote_id(int vote_id){
		this.vote_id=vote_id;
	}

	public int getVote_id(){
		return vote_id;
	}

	public void setPk_id(int pk_id){
		this.pk_id=pk_id;
	}

	public int getPk_id(){
		return pk_id;
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

	public int getUser_level() {
		return user_level;
	}

	public void setUser_level(int user_level) {
		this.user_level = user_level;
	}

	public int getVote_type() {
		return vote_type;
	}

	public void setVote_type(int vote_type) {
		this.vote_type = vote_type;
	}

}
