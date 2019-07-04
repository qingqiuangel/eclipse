package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.Honor;

public class HonorDao {

	public int addHonor(Honor honor, Connection conn){
		String sql = "INSERT INTO honor(honor_id,honor_name,honor_pic) values(null,?,?)";
		Object[] params = new Object[] {
			honor.getHonor_name(),
			honor.getHonor_pic()

		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delHonor(String honor_id, Connection conn){
		String sql = "DELETE FROM honor WHERE honor_id=?";

		Object[] params = new Object[] { new Integer(honor_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delHonors(String[] honor_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <honor_ids.length; i++) {
			sBuilder.append("?");
			if (i !=honor_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM honor WHERE honor_id IN(" +sBuilder.toString()+")";

		Object[] params = honor_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateHonor(Honor honor, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE honor SET honor_id = " + honor.getHonor_id() +" ");
		if(!StringUtil.isEmptyString(honor.getHonor_name())){
			sBuilder.append("  ,honor_name = '" + honor.getHonor_name() +"' ");
		}
		if(!StringUtil.isEmptyString(honor.getHonor_pic())){
			sBuilder.append("  ,honor_pic = '" + honor.getHonor_pic() +"' ");
		}
		sBuilder.append("where honor_id = " + honor.getHonor_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}

	public Honor getHonor(Honor honor, Connection conn){
		Honor _honor=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM honor WHERE 1=1");
		if (honor.getHonor_id()!=0) {
			sBuilder.append(" and honor_id = " + honor.getHonor_id() +" ");
		}
		if(!StringUtil.isEmptyString(honor.getHonor_name())){
			sBuilder.append("  and honor_name = '" + honor.getHonor_name() +"' ");
		}

		List<Object> list = BaseDao.executeQuery(Honor.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _honor = (Honor)list.get(0);
		}
		return _honor;
	}

	public List<Honor>  listHonors(Honor honor, Connection conn){
		List<Honor> honors = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT * FROM honor WHERE 1=1");

		if (honor.getHonor_id()!=0) {
			sBuilder.append(" and honor_id = " + honor.getHonor_id() +" ");
		}
		if(!StringUtil.isEmptyString(honor.getHonor_name())){
			sBuilder.append("  and honor_name like '%" + honor.getHonor_name() +"%' ");
		}
		sBuilder.append(" order by honor_id asc) t");

		if (honor.getStart() != -1) {
			sBuilder.append(" limit " + honor.getStart() + "," + honor.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(Honor.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			honors = new ArrayList<Honor>();
			for (Object object : list) {
				honors.add((Honor)object);
			}
		}
		return honors;
	}

	public int  listHonorsCount(Honor honor, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM honor WHERE 1=1");

		if (honor.getHonor_id()!=0) {
			sBuilder.append(" and honor_id = " + honor.getHonor_id() +" ");
		}
		if(!StringUtil.isEmptyString(honor.getHonor_name())){
			sBuilder.append("  and honor_name like '%" + honor.getHonor_name() +"%' ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
