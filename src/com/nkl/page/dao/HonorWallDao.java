package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.HonorWall;

public class HonorWallDao {

	public int addHonorWall(HonorWall honorWall, Connection conn){
		String sql = "INSERT INTO honor_wall(wall_id,honor_id,user_id,wall_date) values(null,?,?,?)";
		Object[] params = new Object[] {
			honorWall.getHonor_id(),
			honorWall.getUser_id(),
			honorWall.getWall_date()

		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delHonorWall(String wall_id, Connection conn){
		String sql = "DELETE FROM honor_wall WHERE wall_id=?";

		Object[] params = new Object[] { new Integer(wall_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delHonorWalls(String[] wall_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <wall_ids.length; i++) {
			sBuilder.append("?");
			if (i !=wall_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM honor_wall WHERE wall_id IN(" +sBuilder.toString()+")";

		Object[] params = wall_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateHonorWall(HonorWall honorWall, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE honor_wall SET wall_id = " + honorWall.getWall_id() +" ");

		sBuilder.append("where wall_id = " + honorWall.getWall_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}

	public HonorWall getHonorWall(HonorWall honorWall, Connection conn){
		HonorWall _honorWall=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM honor_wall WHERE 1=1");
		if (honorWall.getWall_id()!=0) {
			sBuilder.append(" and wall_id = " + honorWall.getWall_id() +" ");
		}

		List<Object> list = BaseDao.executeQuery(HonorWall.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _honorWall = (HonorWall)list.get(0);
		}
		return _honorWall;
	}

	public List<HonorWall>  listHonorWalls(HonorWall honorWall, Connection conn){
		List<HonorWall> honorWalls = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT hw.*,h.honor_name,h.honor_pic,u.real_name,u.nick_name FROM honor_wall hw join user u on hw.user_id=u.user_id join honor h on hw.honor_id=h.honor_id WHERE 1=1");

		if (honorWall.getWall_id()!=0) {
			sBuilder.append(" and wall_id = " + honorWall.getWall_id() +" ");
		}
		if (!StringUtil.isEmptyString(honorWall.getHonor_name())) {
			sBuilder.append(" and h.honor_name like '%" + honorWall.getHonor_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(honorWall.getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + honorWall.getReal_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(honorWall.getNick_name())) {
			sBuilder.append(" and u.nick_name like '%" + honorWall.getNick_name() +"%' ");
		}
		
		sBuilder.append(" order by wall_id asc) t");

		if (honorWall.getStart() != -1) {
			sBuilder.append(" limit " + honorWall.getStart() + "," + honorWall.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(HonorWall.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			honorWalls = new ArrayList<HonorWall>();
			for (Object object : list) {
				honorWalls.add((HonorWall)object);
			}
		}
		return honorWalls;
	}

	public int  listHonorWallsCount(HonorWall honorWall, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM honor_wall hw join user u on hw.user_id=u.user_id join honor h on hw.honor_id=h.honor_id WHERE 1=1");

		if (honorWall.getWall_id()!=0) {
			sBuilder.append(" and wall_id = " + honorWall.getWall_id() +" ");
		}
		if (!StringUtil.isEmptyString(honorWall.getHonor_name())) {
			sBuilder.append(" and h.honor_name like '%" + honorWall.getHonor_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(honorWall.getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + honorWall.getReal_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(honorWall.getNick_name())) {
			sBuilder.append(" and u.nick_name like '%" + honorWall.getNick_name() +"%' ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
