package com.nkl.page.dao;

import java.sql.Connection;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.page.domain.SblogClick;

public class SblogClickDao {

	public int addSblogClick(SblogClick sblogClick, Connection conn){
		String sql = "INSERT INTO sblog_click(click_id,sblog_id,user_id) values(null,?,?)";
		Object[] params = new Object[] {
			sblogClick.getSblog_id(),
			sblogClick.getUser_id()

		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delSblogClick(String click_id, Connection conn){
		String sql = "DELETE FROM sblog_click WHERE click_id=?";

		Object[] params = new Object[] { new Integer(click_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delSblogClicks(String[] click_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <click_ids.length; i++) {
			sBuilder.append("?");
			if (i !=click_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM sblog_click WHERE click_id IN(" +sBuilder.toString()+")";

		Object[] params = click_ids;

		return BaseDao.executeUpdate(sql, params, conn);	}

	public int updateSblogClick(SblogClick sblogClick, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE sblog_click SET click_id = " + sblogClick.getClick_id() );

		sBuilder.append("where click_id = " + sblogClick.getClick_id());

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);	}

	public SblogClick getSblogClick(SblogClick sblogClick, Connection conn){
		SblogClick _sblogClick=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM sblog_click WHERE 1=1");
		if (sblogClick.getSblog_id()!=0) {
			sBuilder.append(" and sblog_id = " + sblogClick.getSblog_id());
		}
		if (sblogClick.getUser_id()!=0) {
			sBuilder.append(" and user_id = " + sblogClick.getUser_id());
		}

		List<Object> list = BaseDao.executeQuery(SblogClick.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _sblogClick = (SblogClick)list.get(0);
		}
		return _sblogClick;
	}

}
