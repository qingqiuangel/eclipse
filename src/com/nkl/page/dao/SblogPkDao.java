package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.SblogPk;

public class SblogPkDao {

	public int addSblogPk(SblogPk sblogPk, Connection conn){
		String sql = "INSERT INTO sblog_pk(pk_id,defend_user_id,defend_sblog_id,defend_votes,reply_user_id,reply_sblog_id,reply_votes,pk_date,pk_flag,pk_reward) values(null,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] {
			sblogPk.getDefend_user_id(),
			sblogPk.getDefend_sblog_id(),
			sblogPk.getDefend_votes(),
			sblogPk.getReply_user_id(),
			sblogPk.getReply_sblog_id(),
			sblogPk.getReply_votes(),
			sblogPk.getPk_date(),
			sblogPk.getPk_flag(),
			sblogPk.getPk_reward()

		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delSblogPk(String pk_id, Connection conn){
		String sql = "DELETE FROM sblog_pk WHERE pk_id=?";

		Object[] params = new Object[] { new Integer(pk_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delSblogPks(String[] pk_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <pk_ids.length; i++) {
			sBuilder.append("?");
			if (i !=pk_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM sblog_pk WHERE pk_id IN(" +sBuilder.toString()+")";

		Object[] params = pk_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateSblogPk(SblogPk sblogPk, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE sblog_pk SET pk_id = " + sblogPk.getPk_id() +" ");
		if (sblogPk.getDefend_votes()!=0) {
			sBuilder.append(" ,defend_votes = " + sblogPk.getDefend_votes() +" ");
		}
		if (sblogPk.getReply_votes()!=0) {
			sBuilder.append(" ,reply_votes = " + sblogPk.getReply_votes() +" ");
		}
		if (sblogPk.getPk_flag()!=0) {
			sBuilder.append(" ,pk_flag = " + sblogPk.getPk_flag() +" ");
		}
		if (sblogPk.getPk_reward()!=0) {
			sBuilder.append(" ,pk_reward = " + sblogPk.getPk_reward() +" ");
		}
		sBuilder.append("where pk_id = " + sblogPk.getPk_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}
	
	public int updateSblogPkFlag(Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(" UPDATE sblog_pk s ");
		sBuilder.append("    SET s.pk_flag = (case when s.defend_votes>=s.reply_votes then 2 else 3 end) ");
		sBuilder.append(" WHERE s.pk_flag=1 AND TO_DAYS(s.pk_date+1) < TO_DAYS(SYSDATE()) ");
		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}
	
	public int updateSblogFlag(Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(" UPDATE sblog s SET s.sblog_flag = 2 ");
		sBuilder.append(" WHERE s.sblog_flag = 1 and (s.sblog_id in (select defend_sblog_id from sblog_pk where pk_flag=3) ");
		sBuilder.append("  					or	s.sblog_id in (select reply_sblog_id from sblog_pk where pk_flag=2)) ");
		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}

	public SblogPk getSblogPk(SblogPk sblogPk, Connection conn){
		SblogPk _sblogPk=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT sp.*,u1.nick_name defend_nick_name, u1.user_photo defend_user_photo,u1.user_level defend_user_level,s1.sblog_title defend_sblog_title,  ");
		sBuilder.append("       u2.nick_name reply_nick_name, u2.user_photo reply_user_photo,u2.user_level reply_user_level,s2.sblog_title reply_sblog_title FROM sblog_pk sp  ");
		sBuilder.append(" join user u1 on sp.defend_user_id=u1.user_id ");
		sBuilder.append(" join sblog s1 on sp.defend_sblog_id=s1.sblog_id ");
		sBuilder.append(" join user u2 on sp.reply_user_id=u2.user_id ");
		sBuilder.append(" join sblog s2 on sp.reply_sblog_id=s2.sblog_id ");
		if (sblogPk.getPk_id()!=0) {
			sBuilder.append(" and pk_id = " + sblogPk.getPk_id() +" ");
		}
		if (sblogPk.getDefend_user_id()!=0) {
			sBuilder.append(" and defend_user_id = " + sblogPk.getDefend_user_id() +" ");
		}
		if (sblogPk.getDefend_sblog_id()!=0) {
			sBuilder.append(" and defend_sblog_id = " + sblogPk.getDefend_sblog_id() +" ");
		}
		if (sblogPk.getReply_user_id()!=0) {
			sBuilder.append(" and reply_user_id = " + sblogPk.getReply_user_id() +" ");
		}
		if (sblogPk.getReply_sblog_id()!=0) {
			sBuilder.append(" and reply_sblog_id = " + sblogPk.getReply_sblog_id() +" ");
		}

		List<Object> list = BaseDao.executeQuery(SblogPk.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _sblogPk = (SblogPk)list.get(0);
		}
		return _sblogPk;
	}

	public List<SblogPk>  listSblogPks(SblogPk sblogPk, Connection conn){
		List<SblogPk> sblogPks = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT sp.*,u1.nick_name defend_nick_name, u1.user_photo defend_user_photo,u1.user_level defend_user_level,s1.sblog_title defend_sblog_title,  ");
		sBuilder.append("       u2.nick_name reply_nick_name, u2.user_photo reply_user_photo,u2.user_level reply_user_level,s2.sblog_title reply_sblog_title FROM sblog_pk sp  ");
		sBuilder.append(" join user u1 on sp.defend_user_id=u1.user_id ");
		sBuilder.append(" join sblog s1 on sp.defend_sblog_id=s1.sblog_id ");
		sBuilder.append(" join user u2 on sp.reply_user_id=u2.user_id ");
		sBuilder.append(" join sblog s2 on sp.reply_sblog_id=s2.sblog_id ");

		if (sblogPk.getPk_id()!=0) {
			sBuilder.append(" and pk_id = " + sblogPk.getPk_id() +" ");
		}
		if (sblogPk.getPk_flag()!=0) {
			sBuilder.append(" and pk_flag = " + sblogPk.getPk_flag() +" ");
		}
		if (sblogPk.getPk_reward()!=0) {
			sBuilder.append(" and reply_reward = " + sblogPk.getPk_reward() +" ");
		}
		if (!StringUtil.isEmptyString(sblogPk.getDefend_sblog_title())) {
			sBuilder.append(" and s1.sblog_title like '%" + sblogPk.getDefend_sblog_title() +"%'");
		}
		if (!StringUtil.isEmptyString(sblogPk.getDefend_nick_name())) {
			sBuilder.append(" and u1.nick_name like '%" + sblogPk.getDefend_nick_name() +"%'");
		}
		if (sblogPk.getUser_id()!=0) {
			sBuilder.append(" and (defend_user_id = " + sblogPk.getUser_id() +"  or reply_user_id= " + sblogPk.getUser_id() +")");
		}
		sBuilder.append(" order by pk_date desc,pk_id asc) t");

		if (sblogPk.getStart() != -1) {
			sBuilder.append(" limit " + sblogPk.getStart() + "," + sblogPk.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(SblogPk.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			sblogPks = new ArrayList<SblogPk>();
			for (Object object : list) {
				sblogPks.add((SblogPk)object);
			}
		}
		return sblogPks;
	}

	public int  listSblogPksCount(SblogPk sblogPk, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM sblog_pk sp  ");
		sBuilder.append(" join user u1 on sp.defend_user_id=u1.user_id ");
		sBuilder.append(" join sblog s1 on sp.defend_sblog_id=s1.sblog_id ");
		sBuilder.append(" join user u2 on sp.reply_user_id=u2.user_id ");
		sBuilder.append(" join sblog s2 on sp.reply_sblog_id=s2.sblog_id ");

		if (sblogPk.getPk_id()!=0) {
			sBuilder.append(" and pk_id = " + sblogPk.getPk_id() +" ");
		}
		if (sblogPk.getPk_flag()!=0) {
			sBuilder.append(" and pk_flag = " + sblogPk.getPk_flag() +" ");
		}
		if (sblogPk.getPk_reward()!=0) {
			sBuilder.append(" and reply_reward = " + sblogPk.getPk_reward() +" ");
		}
		if (!StringUtil.isEmptyString(sblogPk.getDefend_sblog_title())) {
			sBuilder.append(" and s1.sblog_title like '%" + sblogPk.getDefend_sblog_title() +"%'");
		}
		if (!StringUtil.isEmptyString(sblogPk.getDefend_nick_name())) {
			sBuilder.append(" and u1.nick_name like '%" + sblogPk.getDefend_nick_name() +"%'");
		}
		if (sblogPk.getUser_id()!=0) {
			sBuilder.append(" and (defend_user_id = " + sblogPk.getUser_id() +"  or reply_user_id= " + sblogPk.getUser_id() +")");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
