package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.page.domain.SblogPic;

public class SblogPicDao {

	public int addSblogPic(SblogPic sblogPic, Connection conn){
		String sql = "INSERT INTO sblog_pic(pic_id,sblog_id,pic_picture) values(null,?,?)";
		Object[] params = new Object[] {
			sblogPic.getSblog_id(),
			sblogPic.getPic_picture()

		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delSblogPic(String pic_id, Connection conn){
		String sql = "DELETE FROM sblog_pic WHERE pic_id=?";

		Object[] params = new Object[] { new Integer(pic_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}
	
	public int delSblogPics(int sblog_id, Connection conn){
		String sql = "DELETE FROM sblog_pic WHERE sblog_id=?";

		Object[] params = new Object[] { sblog_id};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delSblogPics(String[] pic_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <pic_ids.length; i++) {
			sBuilder.append("?");
			if (i !=pic_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM sblog_pic WHERE pic_id IN(" +sBuilder.toString()+")";

		Object[] params = pic_ids;

		return BaseDao.executeUpdate(sql, params, conn);	}

	public int updateSblogPic(SblogPic sblogPic, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE sblog_pic SET pic_id = " + sblogPic.getPic_id() );

		sBuilder.append("where pic_id = " + sblogPic.getPic_id());

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);	}

	public SblogPic getSblogPic(SblogPic sblogPic, Connection conn){
		SblogPic _sblogPic=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM sblog_pic WHERE 1=1");

		List<Object> list = BaseDao.executeQuery(SblogPic.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _sblogPic = (SblogPic)list.get(0);
		}
		return _sblogPic;
	}

	public List<SblogPic>  listSblogPics(SblogPic sblogPic, Connection conn){
		List<SblogPic> sblogPics = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM sblog_pic WHERE 1=1");
		
		if (sblogPic.getSblog_id()!=0) {
			sBuilder.append(" and sblog_id = " + sblogPic.getSblog_id());
		}

		List<Object> list = BaseDao.executeQuery(SblogPic.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			sblogPics = new ArrayList<SblogPic>();
			for (Object object : list) {
				sblogPics.add((SblogPic)object);
			}
		}
		return sblogPics;
	}

}
