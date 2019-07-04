package com.nkl.page.manager;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.DateUtil;
import com.nkl.common.util.Md5;
import com.nkl.common.util.Param;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;
import com.nkl.page.dao.HonorDao;
import com.nkl.page.dao.HonorWallDao;
import com.nkl.page.dao.SblogAidDao;
import com.nkl.page.dao.SblogClickDao;
import com.nkl.page.dao.SblogDao;
import com.nkl.page.dao.SblogHelpDao;
import com.nkl.page.dao.SblogPicDao;
import com.nkl.page.dao.SblogPkDao;
import com.nkl.page.dao.SblogReplyDao;
import com.nkl.page.dao.SblogVoteDao;
import com.nkl.page.dao.SensitiveDao;
import com.nkl.page.dao.UserDao;
import com.nkl.page.domain.HonorWall;
import com.nkl.page.domain.Sblog;
import com.nkl.page.domain.SblogAid;
import com.nkl.page.domain.SblogClick;
import com.nkl.page.domain.SblogHelp;
import com.nkl.page.domain.SblogPic;
import com.nkl.page.domain.SblogPk;
import com.nkl.page.domain.SblogReply;
import com.nkl.page.domain.SblogVote;
import com.nkl.page.domain.Sensitive;
import com.nkl.page.domain.User;

public class IndexManager {

	HonorDao honorDao = new HonorDao();
	HonorWallDao honorWallDao = new HonorWallDao();
	SblogAidDao sblogAidDao = new SblogAidDao();
	SblogClickDao sblogClickDao = new SblogClickDao();
	SblogDao sblogDao = new SblogDao();
	SblogHelpDao sblogHelpDao = new SblogHelpDao();
	SblogPicDao sblogPicDao = new SblogPicDao();
	SblogPkDao sblogPkDao = new SblogPkDao();
	SblogReplyDao sblogReplyDao = new SblogReplyDao();
	SblogVoteDao sblogVoteDao = new SblogVoteDao();
	SensitiveDao sensitiveDao = new SensitiveDao();
	UserDao userDao = new UserDao();
	
	/**
	 * @Title: getUser
	 * @Description: 用户查询
	 * @param user
	 * @return User
	 */
	public User  getUser(User user){
		Connection conn = BaseDao.getConnection();
		User _user = userDao.getUser(user, conn);
		BaseDao.closeDB(null, null, conn);
		return _user;
	}
	 
	/**
	 * @Title: updateUser
	 * @Description: 更新用户信息
	 * @param user
	 * @return void
	 */
	public void  updateUser(User user){
		Connection conn = BaseDao.getConnection();
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		userDao.updateUser(user, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: updateUserLevel
	 * @Description: 更新用户等级
	 * @param user
	 * @return void
	 */
	public void  updateUserLevel(User user){
		Connection conn = BaseDao.getConnection();
		//根据经验计算等级
		int user_level = (int) Math.ceil(user.getUser_score()/10.0);
		user.setUser_level(user_level);
		
		userDao.updateUser(user, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listSenstive
	 * @Description: 查询敏感词
	 * @param senstive
	 * @return List<Senstive>
	 */
	public List<Sensitive>  listSenstive(Sensitive senstive){
		Connection conn = BaseDao.getConnection();
		List<Sensitive> senstives = sensitiveDao.listSensitives(senstive,conn);
		BaseDao.closeDB(null, null, conn);
		return senstives;
	}
	
	/**
	 * @Title: listSblogs
	 * @Description: 查询帖子信息
	 * @param sblog
	 * @return List<Sblog>
	 */
	public List<Sblog>  listSblogs(Sblog sblog,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = sblogDao.listSblogsCount(sblog, conn);
		}
		List<Sblog> sblogs = sblogDao.listSblogs(sblog,conn);
		BaseDao.closeDB(null, null, conn);
		return sblogs;
	}
	
	public int listSblogsCount(Sblog sblog){
		Connection conn = BaseDao.getConnection();
		int sum = sblogDao.listSblogsCount(sblog, conn);
		BaseDao.closeDB(null, null, conn);
		return sum;
	}
	
	public List<Sblog>  listSblogTops(Sblog sblog){
		Connection conn = BaseDao.getConnection();
		List<Sblog> sblogs = sblogDao.listSblogTops(sblog,conn);
		BaseDao.closeDB(null, null, conn);
		return sblogs;
	}
	
	/**
	 * @Title: getSblog
	 * @Description: 帖子查询
	 * @param sblog
	 * @return Sblog
	 */
	public Sblog  getSblog(Sblog sblog){
		Connection conn = BaseDao.getConnection();
		Sblog _sblog = sblogDao.getSblog(sblog, conn);
		BaseDao.closeDB(null, null, conn);
		return _sblog;
	}
	
	/**
	 * @Title: listSblogPics
	 * @Description: 查询帖子图片
	 * @param Sblog
	 * @return List<SblogPic>
	 */
	public List<SblogPic>  listSblogPics(Sblog sblog){
		Connection conn = BaseDao.getConnection();
		
		List<SblogPic> list = new ArrayList<SblogPic>();
		SblogPic m1 = new SblogPic();
		m1.setSblog_id(sblog.getSblog_id());
		List<SblogPic> sblogPics = sblogPicDao.listSblogPics(m1, conn);
		if (sblogPics!=null) {
			for (SblogPic temp : sblogPics) {
				list.add(temp);
			}
		}
		BaseDao.closeDB(null, null, conn);
		return list;
	}
	
	/**
	 * @Title: listSblogReplys
	 * @Description: 查询帖子回复信息
	 * @param SblogReply
	 * @return List<SblogReply>
	 */
	public List<SblogReply>  listSblogReplys(SblogReply sblogReply,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = sblogReplyDao.listSblogReplysCount(sblogReply, conn);
		}
		List<SblogReply> sblogReplys = sblogReplyDao.listSblogReplys(sblogReply,conn);
		BaseDao.closeDB(null, null, conn);
		return sblogReplys;
	}
	
	/**
	 * @Title: addSblog
	 * @Description: 新增帖子
	 * @param sblog
	 * @return void
	 */
	public void  addSblog(Sblog sblog){
		Connection conn = BaseDao.getConnection();
		//内容敏感词过滤
		String sblog_content = sblog.getSblog_content();
		List<Sensitive> sensitives = listSenstive(new Sensitive());
		if (sensitives!=null && sensitives.size()>0) {
			Sensitive sensitive = sensitives.get(0);
			String[] strs = sensitive.getSensitive_name().split("，");
			for (String str : strs) {
				sblog_content = sblog_content.replaceAll(str, "**");
			}
		}
		//内容编码
		sblog.setSblog_content(Transcode.htmlEncode(sblog_content));
		//帖子时间
		sblog.setSblog_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		//正常
		sblog.setSblog_flag(1);
		sblogDao.addSblog(sblog, conn);
		
		//获取帖子ID
		User user = (User)Param.getSession("userFront");
		int sblog_id = sblogDao.getSblogId(user, conn);
		//添加图片
		String pics = sblog.getSblog_pics();
		if (!StringUtil.isEmptyString(pics)) {
			String[] sblog_pics = pics.split(",");
			for (String p : sblog_pics) {
				SblogPic sblogPic = new SblogPic();
				sblogPic.setSblog_id(sblog_id);
				sblogPic.setPic_picture(p);
				sblogPicDao.addSblogPic(sblogPic, conn);
			}
		}
		
		//奖励经验
		user.setUser_score(user.getUser_score()+2);
		updateUserLevel(user);
		Param.setSession("userFront",user);
		
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: updateSblog
	 * @Description: 更新帖子信息
	 * @param sblog
	 * @return void
	 */
	public void  updateSblog(Sblog sblog){
		Connection conn = BaseDao.getConnection();
		if (!StringUtil.isEmptyString(sblog.getSblog_content())) {
			//内容敏感词过滤
			String sblog_content = sblog.getSblog_content();
			List<Sensitive> sensitives = listSenstive(new Sensitive());
			if (sensitives!=null && sensitives.size()>0) {
				Sensitive sensitive = sensitives.get(0);
				String[] strs = sensitive.getSensitive_name().split("，");
				for (String str : strs) {
					sblog_content = sblog_content.replaceAll(str, "**");
				}
			}
			//内容编码
			sblog.setSblog_content(Transcode.htmlEncode(sblog.getSblog_content()));
		}
		sblogDao.updateSblog(sblog, conn);
		
		int sblog_id = sblog.getSblog_id();
		String pics = sblog.getSblog_pics();
		String pics_old = sblog.getSblog_pics_old();
		//更新图片
		if (!StringUtil.isEmptyString(pics)) {
			if (StringUtil.isEmptyString(pics_old) || !pics_old.equals(pics)) {
				//删除旧图片
				sblogPicDao.delSblogPics(sblog_id, conn);
				//添加新图片
				String[] sblog_pics = pics.split(",");
				for (String p : sblog_pics) {
					SblogPic sblogPic = new SblogPic();
					sblogPic.setSblog_id(sblog_id);
					sblogPic.setPic_picture(p);
					sblogPicDao.addSblogPic(sblogPic, conn);
				}
			}
		}
		
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: delSblogs
	 * @Description: 删除帖子
	 * @param sblog
	 * @return void
	 */
	public void  delSblogs(Sblog sblog){
		Connection conn = BaseDao.getConnection();
		sblogDao.delSblogs(sblog.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: addSblogReply
	 * @Description: 新增帖子回复
	 * @param SblogReply
	 * @return void
	 */
	public void  addSblogReply(SblogReply sblogReply){
		Connection conn = BaseDao.getConnection();
		//内容敏感词过滤
		String reply_content = sblogReply.getReply_content();
		List<Sensitive> sensitives = listSenstive(new Sensitive());
		if (sensitives!=null && sensitives.size()>0) {
			Sensitive sensitive = sensitives.get(0);
			String[] strs = sensitive.getSensitive_name().split("，");
			for (String str : strs) {
				reply_content = reply_content.replaceAll(str, "**");
			}
		}
		//内容编码
		sblogReply.setReply_content(Transcode.htmlEncode(reply_content));
		//回复时间
		sblogReply.setReply_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		sblogReplyDao.addSblogReply(sblogReply, conn);
		
		//回复帖子增加用户经验
		User user = new User();
		user.setUser_id(sblogReply.getSblog_user_id());
		user = userDao.getUser(user, conn);
		user.setUser_score(user.getUser_score()+1);
		updateUserLevel(user);
		
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: getSblogClick
	 * @Description: 查询帖子点赞
	 * @param sblogClick
	 * @return SblogClick
	 */
	public SblogClick  getSblogClick(SblogClick sblogClick){
		Connection conn = BaseDao.getConnection();
		SblogClick _sblogClick = sblogClickDao.getSblogClick(sblogClick, conn);
		BaseDao.closeDB(null, null, conn);
		return _sblogClick;
	}
	
	/**
	 * @Title: addSblogClick
	 * @Description: 帖子点赞
	 * @param sblogClick
	 * @return void
	 */
	public void  addSblogClick(SblogClick sblogClick){
		Connection conn = BaseDao.getConnection();
		//增加帖子点赞数
		Sblog sblog = new Sblog();
		sblog.setSblog_id(sblogClick.getSblog_id());
		sblog=sblogDao.getSblog(sblog, conn);
		sblog.setSblog_click(sblog.getSblog_click()+1);
		sblogDao.updateSblog(sblog, conn);
		
		//回复帖子增加用户经验
		User user = new User();
		user.setUser_id(sblog.getUser_id());
		user = userDao.getUser(user, conn);
		user.setUser_score(user.getUser_score()+1);
		updateUserLevel(user);
		
		//插入点赞表
		sblogClickDao.addSblogClick(sblogClick, conn);
		
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listSblogPks
	 * @Description: 帖子PK查询
	 * @param sblogPk
	 * @return List<SblogPk>
	 */
	public List<SblogPk>  listSblogPks(SblogPk sblogPk,int[] sum){
		Connection conn = BaseDao.getConnection();
		sblogPkDao.updateSblogPkFlag(conn);//更新过期PK
		sblogPkDao.updateSblogFlag(conn);//更新过期PK的失败方帖子为删除标志
		if (sum!=null) {
			sum[0] = sblogPkDao.listSblogPksCount(sblogPk, conn);
		}
		List<SblogPk> sblogPks = sblogPkDao.listSblogPks(sblogPk,conn);
		
		BaseDao.closeDB(null, null, conn);
		return sblogPks;
	}
	
	public int listSblogPksCount(SblogPk sblogPk){
		Connection conn = BaseDao.getConnection();
		int sum = sblogPkDao.listSblogPksCount(sblogPk, conn);
		BaseDao.closeDB(null, null, conn);
		return sum;
	}
	
	/**
	 * @Title: getSblogPk
	 * @Description: 查询Pk详情
	 * @param sblogPk
	 * @return SblogPk
	 */
	public SblogPk getSblogPk(SblogPk sblogPk){
		Connection conn = BaseDao.getConnection();
		SblogPk _sblogPk = sblogPkDao.getSblogPk(sblogPk, conn);
		
		BaseDao.closeDB(null, null, conn);
		return _sblogPk;
	}
	
	/**
	 * @Title: addSblogPk
	 * @Description: 添加帖子PK
	 * @param SblogPk
	 * @return void
	 */
	public void  addSblogPk(SblogPk sblogPk){
		Connection conn = BaseDao.getConnection();
		sblogPk.setPk_flag(1);//打擂中
		sblogPk.setPk_reward(1);//未奖励
		sblogPkDao.addSblogPk(sblogPk, conn);
		
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: getSblogVote
	 * @Description: 查询帖子投票
	 * @param SblogVote
	 * @return SblogVote
	 */
	public SblogVote  getSblogVote(SblogVote sblogVote){
		Connection conn = BaseDao.getConnection();
		SblogVote _sblogVote = sblogVoteDao.getSblogVote(sblogVote, conn);
		BaseDao.closeDB(null, null, conn);
		return _sblogVote;
	}
	
	/**
	 * @Title: addSblogVote
	 * @Description: 添加帖子投票
	 * @param SblogVote
	 * @return void
	 */
	public void  addSblogVote(SblogVote sblogVote){
		Connection conn = BaseDao.getConnection();
		User user = (User)Param.getSession("userFront");
		//更新票数
		SblogPk sblogPk = new SblogPk();
		sblogPk.setPk_id(sblogVote.getPk_id());
		if (sblogVote.getVote_type()==1) {
			//更新守擂票
			sblogPk.setDefend_votes(sblogPk.getDefend_votes()+user.getUser_level());
		} else {
			//更新挑战票
			sblogPk.setReply_votes(sblogPk.getReply_votes()+user.getUser_level());
		}
		sblogPkDao.updateSblogPk(sblogPk, conn);
		
		//添加投票
		sblogVoteDao.addSblogVote(sblogVote, conn);
		
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: defeatPk
	 * @Description: 擂台认输
	 * @param SblogVote
	 * @return void
	 */
	public void  defeatPk(SblogPk sblogPk){
		Connection conn = BaseDao.getConnection();
		sblogPk.setPk_flag(3);//挑战成功
		sblogPkDao.updateSblogPk(sblogPk, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listHonorWalls
	 * @Description: 查询荣誉墙信息
	 * @param honorWall
	 * @return List<HonorWall>
	 */
	public List<HonorWall>  listHonorWalls(HonorWall honorWall,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = honorWallDao.listHonorWallsCount(honorWall, conn);
		}
		List<HonorWall> honorWalls = honorWallDao.listHonorWalls(honorWall,conn);
		BaseDao.closeDB(null, null, conn);
		return honorWalls;
	}
	
	/**
	 * @Title: listSblogHelps
	 * @Description: 帖子求助查询
	 * @param sblogHelp
	 * @return List<SblogHelp>
	 */
	public List<SblogHelp>  listSblogHelps(SblogHelp sblogHelp,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = sblogHelpDao.listSblogHelpsCount(sblogHelp, conn);
		}
		List<SblogHelp> sblogHelps = sblogHelpDao.listSblogHelps(sblogHelp,conn);
		
		BaseDao.closeDB(null, null, conn);
		return sblogHelps;
	}
	
	public List<SblogHelp>  listMySblogHelps(SblogHelp sblogHelp,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = sblogHelpDao.listMySblogHelpsCount(sblogHelp, conn);
		}
		List<SblogHelp> sblogHelps = sblogHelpDao.listMySblogHelps(sblogHelp,conn);
		
		BaseDao.closeDB(null, null, conn);
		return sblogHelps;
	}
	
	public int  listSblogHelpsCount(SblogHelp sblogHelp){
		Connection conn = BaseDao.getConnection();
		int sum = sblogHelpDao.listSblogHelpsCount(sblogHelp, conn);
		BaseDao.closeDB(null, null, conn);
		return sum;
	}
	
	/**
	 * @Title: getSblogHelp
	 * @Description: 查询帖子求助
	 * @param SblogHelp
	 * @return SblogHelp
	 */
	public SblogHelp  getSblogHelp(SblogHelp sblogHelp){
		Connection conn = BaseDao.getConnection();
		SblogHelp _sblogHelp = sblogHelpDao.getSblogHelp(sblogHelp, conn);
		BaseDao.closeDB(null, null, conn);
		return _sblogHelp;
	}
	
	/**
	 * @Title: addSblogHelp
	 * @Description: 添加帖子到求助站
	 * @param SblogHelp
	 * @return void
	 */
	public void  addSblogHelp(SblogHelp sblogHelp){
		Connection conn = BaseDao.getConnection();
		//添加求助
		sblogHelp.setHelp_flag(1);//悬赏中
		sblogHelpDao.addSblogHelp(sblogHelp, conn);
		
		//扣除经验
		User user = (User)Param.getSession("userFront");
		user.setUser_score(user.getUser_score()-sblogHelp.getHelp_score());
		updateUserLevel(user);
		Param.setSession("userFront",user);
		
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: delSblogHelps
	 * @Description: 删除帖子求助
	 * @param sblog
	 * @return void
	 */
	public void  delSblogHelps(SblogHelp sblogHelp){
		Connection conn = BaseDao.getConnection();
		sblogHelpDao.delSblogHelps(sblogHelp.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: addSblogAid
	 * @Description: 新增帖子援助
	 * @param SblogAid
	 * @return void
	 */
	public void  addSblogAid(SblogAid sblogAid){
		Connection conn = BaseDao.getConnection();
		//内容敏感词过滤
		String aid_content = sblogAid.getAid_content();
		List<Sensitive> sensitives = listSenstive(new Sensitive());
		if (sensitives!=null && sensitives.size()>0) {
			Sensitive sensitive = sensitives.get(0);
			String[] strs = sensitive.getSensitive_name().split("，");
			for (String str : strs) {
				aid_content = aid_content.replaceAll(str, "**");
			}
		}
		//内容编码
		sblogAid.setAid_content(Transcode.htmlEncode(aid_content));
		//帖子时间
		sblogAid.setAid_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		
		sblogAidDao.addSblogAid(sblogAid, conn);
		
		
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listSblogAids
	 * @Description: 查询援助信息
	 * @param sblogAid
	 * @return List<SblogAid>
	 */
	public List<SblogAid>  listSblogAids(SblogAid sblogAid,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = sblogAidDao.listSblogAidsCount(sblogAid, conn);
		}
		List<SblogAid> sblogAids = sblogAidDao.listSblogAids(sblogAid,conn);
		BaseDao.closeDB(null, null, conn);
		return sblogAids;
	}
	
	/**
	 * @Title: rewardSblogAid
	 * @Description: 奖励援助
	 * @param SblogHelp
	 * @return void
	 */
	public void  rewardSblogAid(SblogAid sblogAid){
		Connection conn = BaseDao.getConnection();
		//更新求助为已结束
		SblogHelp sblogHelp = new SblogHelp();
		sblogHelp.setHelp_id(sblogAid.getHelp_id());
		sblogHelp.setHelp_flag(2);
		sblogHelpDao.updateSblogHelp(sblogHelp, conn);
		
		//赠送经验
		User user = new User();
		user.setUser_id(sblogAid.getUser_id());
		user = userDao.getUser(user, conn);
		user.setUser_score(user.getUser_score()+sblogAid.getHelp_score());
		updateUserLevel(user);
		
		BaseDao.closeDB(null, null, conn);
	}
	
}
