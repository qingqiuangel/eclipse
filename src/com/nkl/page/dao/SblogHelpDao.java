package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.SblogHelp;

public class SblogHelpDao {

	public int addSblogHelp(SblogHelp sblogHelp, Connection conn){
		String sql = "INSERT INTO sblog_help(help_id,sblog_id,user_id,help_score,help_flag) values(null,?,?,?,?)";
		Object[] params = new Object[] {
			sblogHelp.getSblog_id(),
			sblogHelp.getUser_id(),
			sblogHelp.getHelp_score(),
			sblogHelp.getHelp_flag()

		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delSblogHelp(String help_id, Connection conn){
		String sql = "DELETE FROM sblog_help WHERE help_id=?";

		Object[] params = new Object[] { new Integer(help_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delSblogHelps(String[] help_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <help_ids.length; i++) {
			sBuilder.append("?");
			if (i !=help_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM sblog_help WHERE help_id IN(" +sBuilder.toString()+")";

		Object[] params = help_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateSblogHelp(SblogHelp sblogHelp, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE sblog_help SET help_id = " + sblogHelp.getHelp_id() +" ");
		if (sblogHelp.getHelp_flag()!=0) {
			sBuilder.append("  ,help_flag = " + sblogHelp.getHelp_flag() +" ");
		}
		sBuilder.append("where help_id = " + sblogHelp.getHelp_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}

	public SblogHelp getSblogHelp(SblogHelp sblogHelp, Connection conn){
		SblogHelp _sblogHelp=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT sh.*,s.sblog_title,s.sblog_date,u.nick_name,u.user_photo,u.user_level FROM sblog_help sh join sblog s on sh.sblog_id=s.sblog_id join user u on sh.user_id=u.user_id WHERE 1=1");
		if (sblogHelp.getHelp_id()!=0) {
			sBuilder.append(" and help_id = " + sblogHelp.getHelp_id() +" ");
		}

		List<Object> list = BaseDao.executeQuery(SblogHelp.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _sblogHelp = (SblogHelp)list.get(0);
		}
		return _sblogHelp;
	}

	public List<SblogHelp>  listSblogHelps(SblogHelp sblogHelp, Connection conn){
		List<SblogHelp> sblogHelps = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT sh.*,s.sblog_title,s.sblog_date,u.nick_name,u.user_photo,u.user_level FROM sblog_help sh join sblog s on sh.sblog_id=s.sblog_id join user u on sh.user_id=u.user_id WHERE 1=1");

		if (sblogHelp.getHelp_id()!=0) {
			sBuilder.append(" and help_id = " + sblogHelp.getHelp_id() +" ");
		}
		if (sblogHelp.getUser_id()!=0) {
			sBuilder.append(" and sh.user_id = " + sblogHelp.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(sblogHelp.getSblog_title())) {
			sBuilder.append(" and s.sblog_title like '%" + sblogHelp.getHelp_id() +"%' ");
		}
		if (!StringUtil.isEmptyString(sblogHelp.getNick_name())) {
			sBuilder.append(" and u.nick_name like '%" + sblogHelp.getNick_name() +"%' ");
		}
		sBuilder.append(" order by help_id asc) t");

		if (sblogHelp.getStart() != -1) {
			sBuilder.append(" limit " + sblogHelp.getStart() + "," + sblogHelp.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(SblogHelp.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			sblogHelps = new ArrayList<SblogHelp>();
			for (Object object : list) {
				sblogHelps.add((SblogHelp)object);
			}
		}
		return sblogHelps;
	}

	public int  listSblogHelpsCount(SblogHelp sblogHelp, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM sblog_help sh join sblog s on sh.sblog_id=s.sblog_id join user u on sh.user_id=u.user_id WHERE 1=1");

		if (sblogHelp.getHelp_id()!=0) {
			sBuilder.append(" and help_id = " + sblogHelp.getHelp_id() +" ");
		}
		if (sblogHelp.getUser_id()!=0) {
			sBuilder.append(" and sh.user_id = " + sblogHelp.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(sblogHelp.getSblog_title())) {
			sBuilder.append(" and s.sblog_title like '%" + sblogHelp.getHelp_id() +"%' ");
		}
		if (!StringUtil.isEmptyString(sblogHelp.getNick_name())) {
			sBuilder.append(" and u.nick_name like '%" + sblogHelp.getNick_name() +"%' ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}
	
	public List<SblogHelp>  listMySblogHelps(SblogHelp sblogHelp, Connection conn){
		List<SblogHelp> sblogHelps = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT sh.*,s.sblog_title,sa.user_id aid_user_id,sa.aid_date,u.nick_name aid_nick_name FROM sblog_help sh join sblog s on sh.sblog_id=s.sblog_id ");
		sBuilder.append("  left join sblog_aid sa on sa.help_id=sh.help_id ");
		sBuilder.append("  left join user u on sa.user_id=u.user_id  WHERE 1=1 ");

		if (sblogHelp.getHelp_id()!=0) {
			sBuilder.append(" and help_id = " + sblogHelp.getHelp_id() +" ");
		}
		if (sblogHelp.getUser_id()!=0) {
			sBuilder.append(" and sh.user_id = " + sblogHelp.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(sblogHelp.getSblog_title())) {
			sBuilder.append(" and s.sblog_title like '%" + sblogHelp.getHelp_id() +"%' ");
		}
		if (!StringUtil.isEmptyString(sblogHelp.getAid_nick_name())) {
			sBuilder.append(" and u.nick_name like '%" + sblogHelp.getAid_nick_name() +"%' ");
		}
		sBuilder.append(" order by help_id asc) t");

		if (sblogHelp.getStart() != -1) {
			sBuilder.append(" limit " + sblogHelp.getStart() + "," + sblogHelp.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(SblogHelp.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			sblogHelps = new ArrayList<SblogHelp>();
			for (Object object : list) {
				sblogHelps.add((SblogHelp)object);
			}
		}
		return sblogHelps;
	}

	public int  listMySblogHelpsCount(SblogHelp sblogHelp, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM sblog_help sh join sblog s on sh.sblog_id=s.sblog_id ");
		sBuilder.append("  left join sblog_aid sa on sa.help_id=sh.help_id ");
		sBuilder.append("  left join user u on sa.user_id=u.user_id  WHERE 1=1 ");

		if (sblogHelp.getHelp_id()!=0) {
			sBuilder.append(" and help_id = " + sblogHelp.getHelp_id() +" ");
		}
		if (sblogHelp.getUser_id()!=0) {
			sBuilder.append(" and sh.user_id = " + sblogHelp.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(sblogHelp.getSblog_title())) {
			sBuilder.append(" and s.sblog_title like '%" + sblogHelp.getHelp_id() +"%' ");
		}
		if (!StringUtil.isEmptyString(sblogHelp.getAid_nick_name())) {
			sBuilder.append(" and u.nick_name like '%" + sblogHelp.getAid_nick_name() +"%' ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
