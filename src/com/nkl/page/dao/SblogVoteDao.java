package com.nkl.page.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.page.domain.SblogVote;

public class SblogVoteDao {

	public int addSblogVote(SblogVote sblogVote, Connection conn){
		String sql = "INSERT INTO sblog_vote(vote_id,pk_id,sblog_id,user_id) values(null,?,?,?)";
		Object[] params = new Object[] {
			sblogVote.getPk_id(),
			sblogVote.getSblog_id(),
			sblogVote.getUser_id()

		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delSblogVote(String vote_id, Connection conn){
		String sql = "DELETE FROM sblog_vote WHERE vote_id=?";

		Object[] params = new Object[] { new Integer(vote_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delSblogVotes(String[] vote_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <vote_ids.length; i++) {
			sBuilder.append("?");
			if (i !=vote_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM sblog_vote WHERE vote_id IN(" +sBuilder.toString()+")";

		Object[] params = vote_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateSblogVote(SblogVote sblogVote, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE sblog_vote SET vote_id = " + sblogVote.getVote_id() +" ");

		sBuilder.append("where vote_id = " + sblogVote.getVote_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}

	public SblogVote getSblogVote(SblogVote sblogVote, Connection conn){
		SblogVote _sblogVote=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM sblog_vote WHERE 1=1");
		if (sblogVote.getVote_id()!=0) {
			sBuilder.append(" and vote_id = " + sblogVote.getVote_id() +" ");
		}
		if (sblogVote.getPk_id()!=0) {
			sBuilder.append(" and pk_id = " + sblogVote.getPk_id() +" ");
		}
		if (sblogVote.getSblog_id()!=0) {
			sBuilder.append(" and sblog_id = " + sblogVote.getSblog_id() +" ");
		}
		if (sblogVote.getUser_id()!=0) {
			sBuilder.append(" and user_id = " + sblogVote.getUser_id() +" ");
		}

		List<Object> list = BaseDao.executeQuery(SblogVote.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _sblogVote = (SblogVote)list.get(0);
		}
		return _sblogVote;
	}

	public List<SblogVote>  listSblogVotes(SblogVote sblogVote, Connection conn){
		List<SblogVote> sblogVotes = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT * FROM sblog_vote WHERE 1=1");

		if (sblogVote.getVote_id()!=0) {
			sBuilder.append(" and vote_id = " + sblogVote.getVote_id() +" ");
		}
		sBuilder.append(" order by vote_id asc) t");

		if (sblogVote.getStart() != -1) {
			sBuilder.append(" limit " + sblogVote.getStart() + "," + sblogVote.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(SblogVote.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			sblogVotes = new ArrayList<SblogVote>();
			for (Object object : list) {
				sblogVotes.add((SblogVote)object);
			}
		}
		return sblogVotes;
	}

	public int  listSblogVotesCount(SblogVote sblogVote, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM sblog_vote WHERE 1=1");

		if (sblogVote.getVote_id()!=0) {
			sBuilder.append(" and vote_id = " + sblogVote.getVote_id() +" ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
