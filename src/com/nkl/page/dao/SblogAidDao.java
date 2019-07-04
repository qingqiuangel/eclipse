package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.SblogAid;

public class SblogAidDao {

	public int addSblogAid(SblogAid sblogAid, Connection conn){
		String sql = "INSERT INTO sblog_aid(aid_id,help_id,user_id,aid_content,aid_date) values(null,?,?,?,?)";
		Object[] params = new Object[] {
			sblogAid.getHelp_id(),
			sblogAid.getUser_id(),
			sblogAid.getAid_content(),
			sblogAid.getAid_date()

		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delSblogAid(String aid_id, Connection conn){
		String sql = "DELETE FROM sblog_aid WHERE aid_id=?";

		Object[] params = new Object[] { new Integer(aid_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delSblogAids(String[] aid_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <aid_ids.length; i++) {
			sBuilder.append("?");
			if (i !=aid_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM sblog_aid WHERE aid_id IN(" +sBuilder.toString()+")";

		Object[] params = aid_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateSblogAid(SblogAid sblogAid, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE sblog_aid SET aid_id = " + sblogAid.getAid_id() +" ");

		sBuilder.append("where aid_id = " + sblogAid.getAid_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}

	public SblogAid getSblogAid(SblogAid sblogAid, Connection conn){
		SblogAid _sblogAid=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT sa.*,u.nick_name,u.user_photo,u.user_level FROM sblog_aid sa ");
		sBuilder.append("  join user u on sa.user_id=u.user_id WHERE 1=1");
		if (sblogAid.getAid_id()!=0) {
			sBuilder.append(" and aid_id = " + sblogAid.getAid_id() +" ");
		}

		List<Object> list = BaseDao.executeQuery(SblogAid.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _sblogAid = (SblogAid)list.get(0);
		}
		return _sblogAid;
	}

	public List<SblogAid>  listSblogAids(SblogAid sblogAid, Connection conn){
		List<SblogAid> sblogAids = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT sa.*,u.nick_name,u.user_photo,u.user_level FROM sblog_aid sa ");
		sBuilder.append("  join user u on sa.user_id=u.user_id WHERE 1=1");

		if (sblogAid.getAid_id()!=0) {
			sBuilder.append(" and aid_id = " + sblogAid.getAid_id() +" ");
		}
		if (!StringUtil.isEmptyString(sblogAid.getNick_name())) {
			sBuilder.append(" and u.nick_name like '%" + sblogAid.getNick_name() +"%' ");
		}
		if (sblogAid.getHelp_id()!=0) {
			sBuilder.append(" and help_id = " + sblogAid.getHelp_id() +" ");
		}
		sBuilder.append(" order by help_id asc,aid_date desc,aid_id asc) t");

		if (sblogAid.getStart() != -1) {
			sBuilder.append(" limit " + sblogAid.getStart() + "," + sblogAid.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(SblogAid.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			sblogAids = new ArrayList<SblogAid>();
			for (Object object : list) {
				sblogAids.add((SblogAid)object);
			}
		}
		return sblogAids;
	}

	public int  listSblogAidsCount(SblogAid sblogAid, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM sblog_aid sa ");
		sBuilder.append("  join user u on sa.user_id=u.user_id WHERE 1=1");

		if (sblogAid.getAid_id()!=0) {
			sBuilder.append(" and aid_id = " + sblogAid.getAid_id() +" ");
		}
		if (!StringUtil.isEmptyString(sblogAid.getNick_name())) {
			sBuilder.append(" and u.nick_name like '%" + sblogAid.getNick_name() +"%' ");
		}
		if (sblogAid.getHelp_id()!=0) {
			sBuilder.append(" and help_id = " + sblogAid.getHelp_id() +" ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
