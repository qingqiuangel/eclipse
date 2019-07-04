package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.Sblog;
import com.nkl.page.domain.User;

public class SblogDao {

	public int addSblog(Sblog sblog, Connection conn){
		String sql = "INSERT INTO sblog(sblog_id,user_id,sblog_title,sblog_content,sblog_date,sblog_click,sblog_type,sblog_flag) values(null,?,?,?,?,?,?,?)";
		Object[] params = new Object[] {
			sblog.getUser_id(),
			sblog.getSblog_title(),
			sblog.getSblog_content(),
			sblog.getSblog_date(),
			sblog.getSblog_click(),
			sblog.getSblog_type(),
			sblog.getSblog_flag()

		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delSblog(String sblog_id, Connection conn){
		String sql = "DELETE FROM sblog WHERE sblog_id=?";

		Object[] params = new Object[] { new Integer(sblog_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delSblogs(String[] sblog_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <sblog_ids.length; i++) {
			sBuilder.append("?");
			if (i !=sblog_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM sblog WHERE sblog_id IN(" +sBuilder.toString()+")";

		Object[] params = sblog_ids;

		return BaseDao.executeUpdate(sql, params, conn);	
	}
	
	public int delSblogs2(String[] sblog_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <sblog_ids.length; i++) {
			sBuilder.append("?");
			if (i !=sblog_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "UPDATE sblog SET sblog_flag=2 WHERE sblog_id IN(" +sBuilder.toString()+")";

		Object[] params = sblog_ids;

		return BaseDao.executeUpdate(sql, params, conn);	
	}

	public int updateSblog(Sblog sblog, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE sblog SET sblog_id = " + sblog.getSblog_id() +" ");
		if (!StringUtil.isEmptyString(sblog.getSblog_title())) {
			sBuilder.append(" ,sblog_title = '" + sblog.getSblog_title()+"'" );
		}
		if (!StringUtil.isEmptyString(sblog.getSblog_content())) {
			sBuilder.append(" ,sblog_content = '" + sblog.getSblog_content()+"'" );
		}
		if (sblog.getSblog_click()!=0) {
			sBuilder.append(" ,sblog_click = " + sblog.getSblog_click() );
		}
		if (sblog.getSblog_type()!=0) {
			sBuilder.append(" ,sblog_type = " + sblog.getSblog_type() );
		}
		if (sblog.getSblog_flag()!=0) {
			sBuilder.append(" ,sblog_flag = " + sblog.getSblog_flag() );
		}
		sBuilder.append(" where sblog_id = " + sblog.getSblog_id() );

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);	}

	public Sblog getSblog(Sblog sblog, Connection conn){
		Sblog _sblog=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT s.*,u.nick_name,u.real_name,u.user_name,u.user_photo,u.user_level,IFNULL(sh.help_id,0) help_id from sblog s ");
		sBuilder.append("  join user u on s.user_id = u.user_id ");
		sBuilder.append("  left join sblog_help sh on sh.sblog_id=s.sblog_id and sh.help_flag=1 ");
		sBuilder.append(" WHERE s.sblog_id = " + sblog.getSblog_id() );

		List<Object> list = BaseDao.executeQuery(Sblog.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _sblog = (Sblog)list.get(0);
		}
		return _sblog;
	}

	public List<Sblog>  listSblogs(Sblog sblog, Connection conn){
		List<Sblog> sblogs = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT s.*,u.nick_name,u.real_name,u.user_name,u.user_photo,u.user_level,IFNULL(sh.help_id,0) help_id from sblog s ");
		sBuilder.append("  join user u on s.user_id = u.user_id ");
		sBuilder.append("  left join sblog_help sh on sh.sblog_id=s.sblog_id and sh.help_flag=1 WHERE 1=1");
		if (sblog.getSblog_id()!=0) {
			sBuilder.append(" and s.sblog_id = " + sblog.getSblog_id() );
		}
		if (sblog.getUser_id()!=0) {
			sBuilder.append(" and s.user_id = " + sblog.getUser_id() );
		}
		if (!StringUtil.isEmptyString(sblog.getSblog_title())) {
			sBuilder.append(" and s.sblog_title like '%" + sblog.getSblog_title() +"%'");
		}
		if (!StringUtil.isEmptyString(sblog.getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + sblog.getReal_name() +"%'");
		}
		if (!StringUtil.isEmptyString(sblog.getNick_name())) {
			sBuilder.append(" and u.nick_name like '%" + sblog.getNick_name() +"%'");
		}
		if (sblog.getSblog_type()!=0) {
			sBuilder.append(" and s.sblog_type = " + sblog.getSblog_type() );
		}
		if (sblog.getSblog_flag()!=0) {
			sBuilder.append(" and s.sblog_flag = " + sblog.getSblog_flag() );
		}
		
		sBuilder.append(" order by s.sblog_date desc,s.sblog_id asc) t");
		
		if (sblog.getStart() != -1) {
			sBuilder.append(" limit " + sblog.getStart() + "," + sblog.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(Sblog.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			sblogs = new ArrayList<Sblog>();
			for (Object object : list) {
				sblogs.add((Sblog)object);
			}
		}
		return sblogs;
	}
	
	public int  listSblogsCount(Sblog sblog, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM sblog s ");
		sBuilder.append("  join user u on s.user_id = u.user_id ");
		sBuilder.append("  left join sblog_help sh on sh.sblog_id=s.sblog_id and sh.help_flag=1 WHERE 1=1");
		if (sblog.getSblog_id()!=0) {
			sBuilder.append(" and s.sblog_id = " + sblog.getSblog_id() );
		}
		if (sblog.getUser_id()!=0) {
			sBuilder.append(" and s.user_id = " + sblog.getUser_id() );
		}
		if (!StringUtil.isEmptyString(sblog.getSblog_title())) {
			sBuilder.append(" and s.sblog_title like '%" + sblog.getSblog_title() +"%'");
		}
		if (!StringUtil.isEmptyString(sblog.getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + sblog.getReal_name() +"%'");
		}
		if (!StringUtil.isEmptyString(sblog.getNick_name())) {
			sBuilder.append(" and u.nick_name like '%" + sblog.getNick_name() +"%'");
		}
		if (sblog.getSblog_type()!=0) {
			sBuilder.append(" and s.sblog_type = " + sblog.getSblog_type() );
		}
		if (sblog.getSblog_flag()!=0) {
			sBuilder.append(" and s.sblog_flag = " + sblog.getSblog_flag() );
		}
		
		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}
	
	public int  getSblogId(User user, Connection conn){
		int sblog_id = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT max(sblog_id) FROM sblog WHERE user_id="+user.getUser_id());
		
		long count = (Integer)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sblog_id = (int)count;
		return sblog_id;
	}
	
	public List<Sblog>  listSblogTops(Sblog sblog, Connection conn){
		List<Sblog> sblogs = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT s.*,u.nick_name,u.real_name,u.user_name,u.user_photo,IFNULL(sh.help_id,0) help_id from sblog s ");
		sBuilder.append("  join user u on s.user_id = u.user_id ");
		sBuilder.append("  left join sblog_help sh on sh.sblog_id=s.sblog_id and sh.help_flag=1 WHERE 1=1");
		if (sblog.getSblog_type()!=0) {
			sBuilder.append(" and sblog_type = " + sblog.getSblog_type() );
		}
		if (sblog.getSblog_flag()!=0) {
			sBuilder.append(" and sblog_flag = " + sblog.getSblog_flag() );
		}
		
		sBuilder.append(" and s.sblog_date BETWEEN subdate(curdate(),case when date_format(curdate(),'%w')=0 then 7 else date_format(curdate(),'%w') end -1)  ");
		sBuilder.append(" and subdate(curdate(),case when date_format(curdate(),'%w')=0 then 7 else date_format(curdate(),'%w') end -7) ");
		sBuilder.append(" order by sblog_click desc,sblog_id asc) t");
		
		if (sblog.getStart() != -1) {
			sBuilder.append(" limit " + sblog.getStart() + "," + sblog.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(Sblog.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			sblogs = new ArrayList<Sblog>();
			for (Object object : list) {
				sblogs.add((Sblog)object);
			}
		}
		return sblogs;
	}

}
