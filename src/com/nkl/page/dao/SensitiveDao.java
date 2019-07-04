package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.Sensitive;

public class SensitiveDao {

	public int addSensitive(Sensitive sensitive, Connection conn){
		String sql = "INSERT INTO sense(sensitive_id,sensitive_name) values(null,?)";
		Object[] params = new Object[] {
			sensitive.getSensitive_name()

		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delSensitive(String sensitive_id, Connection conn){
		String sql = "DELETE FROM sense WHERE sensitive_id=?";

		Object[] params = new Object[] { new Integer(sensitive_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delSensitives(String[] sensitive_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <sensitive_ids.length; i++) {
			sBuilder.append("?");
			if (i !=sensitive_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM sense WHERE sensitive_id IN(" +sBuilder.toString()+")";

		Object[] params = sensitive_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateSensitive(Sensitive sensitive, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE sense SET sensitive_id = " + sensitive.getSensitive_id() +" ");
		if (!StringUtil.isEmptyString(sensitive.getSensitive_name())) {
			sBuilder.append(" , sensitive_name ='" + sensitive.getSensitive_name() + "' ");
		}
		sBuilder.append("where sensitive_id = " + sensitive.getSensitive_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}

	public Sensitive getSensitive(Sensitive sensitive, Connection conn){
		Sensitive _sensitive=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM sense WHERE 1=1");
		if (sensitive.getSensitive_id()!=0) {
			sBuilder.append(" and sensitive_id = " + sensitive.getSensitive_id() +" ");
		}
		if (!StringUtil.isEmptyString(sensitive.getSensitive_name())) {
			sBuilder.append(" and sensitive_name = '" + sensitive.getSensitive_name() + "' ");
		}

		List<Object> list = BaseDao.executeQuery(Sensitive.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _sensitive = (Sensitive)list.get(0);
		}
		return _sensitive;
	}

	public List<Sensitive>  listSensitives(Sensitive sensitive, Connection conn){
		List<Sensitive> sensitives = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT * FROM sense WHERE 1=1");

		if (sensitive.getSensitive_id()!=0) {
			sBuilder.append(" and sensitive_id = " + sensitive.getSensitive_id() +" ");
		}
		if (!StringUtil.isEmptyString(sensitive.getSensitive_name())) {
			sBuilder.append(" and sensitive_name like '%" + sensitive.getSensitive_name() + "%' ");
		}
		sBuilder.append(" order by sensitive_id asc) t");

		if (sensitive.getStart() != -1) {
			sBuilder.append(" limit " + sensitive.getStart() + "," + sensitive.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(Sensitive.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			sensitives = new ArrayList<Sensitive>();
			for (Object object : list) {
				sensitives.add((Sensitive)object);
			}
		}
		return sensitives;
	}

	public int  listSensitivesCount(Sensitive sensitive, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM sense WHERE 1=1");

		if (sensitive.getSensitive_id()!=0) {
			sBuilder.append(" and sensitive_id = " + sensitive.getSensitive_id() +" ");
		}
		if (!StringUtil.isEmptyString(sensitive.getSensitive_name())) {
			sBuilder.append(" and sensitive_name like '%" + sensitive.getSensitive_name() + "%' ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
