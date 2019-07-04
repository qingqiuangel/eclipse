package com.nkl.admin.manager;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.DateUtil;
import com.nkl.common.util.Md5;
import com.nkl.common.util.StringUtil;
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
import com.nkl.page.domain.Honor;
import com.nkl.page.domain.HonorWall;
import com.nkl.page.domain.Sblog;
import com.nkl.page.domain.SblogHelp;
import com.nkl.page.domain.SblogPk;
import com.nkl.page.domain.Sensitive;
import com.nkl.page.domain.User;

public class AdminManager {

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
	 * @Title: listUsers
	 * @Description: 用户查询
	 * @param user
	 * @return List<User>
	 */
	public List<User>  listUsers(User user,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = userDao.listUsersCount(user, conn);
		}
		List<User> users = userDao.listUsers(user,conn);
		
		BaseDao.closeDB(null, null, conn);
		return users;
	}
	
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
	 * @Title: addUser
	 * @Description: 添加用户
	 * @param user
	 * @return void
	 */
	public void  addUser(User user){
		Connection conn = BaseDao.getConnection();
		userDao.addUser(user, conn);
		BaseDao.closeDB(null, null, conn);
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
	 * @Title: delUsers
	 * @Description: 删除用户信息
	 * @param user
	 * @return void
	 */
	public void  delUsers(User user){
		Connection conn = BaseDao.getConnection();
		userDao.delUsers(user.getIds().split(","), conn);
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
	 * @Title: listSensitives
	 * @Description: 敏感词查询
	 * @param sensitive
	 * @return List<Sensitive>
	 */
	public List<Sensitive>  listSensitives(Sensitive sensitive,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = sensitiveDao.listSensitivesCount(sensitive, conn);
		}
		List<Sensitive> sensitives = sensitiveDao.listSensitives(sensitive,conn);
		
		BaseDao.closeDB(null, null, conn);
		return sensitives;
	}
	
	/**
	 * @Title: getSensitive
	 * @Description: 敏感词查询
	 * @param sensitive
	 * @return Sensitive
	 */
	public Sensitive  getSensitive(Sensitive sensitive){
		Connection conn = BaseDao.getConnection();
		Sensitive _sensitive = sensitiveDao.getSensitive(sensitive, conn);
		BaseDao.closeDB(null, null, conn);
		return _sensitive;
	}
	
	/**
	 * @Title: updateSensitive
	 * @Description: 更新敏感词信息
	 * @param sensitive
	 * @return void
	 */
	public void  updateSensitive(Sensitive sensitive){
		Connection conn = BaseDao.getConnection();
		sensitive.setSensitive_name(sensitive.getSensitive_name().replaceAll(",", "，"));
		sensitiveDao.updateSensitive(sensitive, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listSblogs
	 * @Description: 帖子查询
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
	
	/**
	 * @Title: delSblogs
	 * @Description: 删除帖子
	 * @param sblog
	 * @return void
	 */
	public void  delSblogs(Sblog sblog){
		Connection conn = BaseDao.getConnection();
		sblogDao.delSblogs2(sblog.getIds().split(","), conn);
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
	
	/**
	 * @Title: getSblogPk
	 * @Description: 帖子PK详情
	 * @param sblogPk
	 * @return SblogPk
	 */
	public SblogPk  getSblogPk(SblogPk sblogPk){
		Connection conn = BaseDao.getConnection();
		SblogPk _sblogPk = sblogPkDao.getSblogPk(sblogPk, conn);
		BaseDao.closeDB(null, null, conn);
		return _sblogPk;
	}
	
	/**
	 * @Title: rewardSblogPk
	 * @Description: 帖子PK奖励
	 * @param sblogPk
	 * @return void
	 */
	public void  rewardSblogPk(SblogPk sblogPk){
		Connection conn = BaseDao.getConnection();
		//获奖人
		int user_id = sblogPk.getPk_flag()==2?sblogPk.getDefend_user_id():sblogPk.getReply_user_id();
		//奖励经验值
		User user = new User();
		user.setUser_id(user_id);
		user = userDao.getUser(user, conn);
		user.setUser_score(user.getUser_score()+sblogPk.getUser_score());
		updateUserLevel(user);
		//奖励荣誉
		HonorWall honorWall = new HonorWall();
		honorWall.setHonor_id(sblogPk.getHonor_id());
		honorWall.setUser_id(user_id);
		honorWall.setWall_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd"));
		honorWallDao.addHonorWall(honorWall, conn);
		//设置PK结果为已奖励
		sblogPk.setPk_reward(2);
		sblogPkDao.updateSblogPk(sblogPk, conn);
		BaseDao.closeDB(null, null, conn);
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
	
	/**
	 * @Title: delSblogHelps
	 * @Description: 删除帖子求助
	 * @param sblogHelp
	 * @return void
	 */
	public void  delSblogHelps(SblogHelp sblogHelp){
		Connection conn = BaseDao.getConnection();
		sblogHelpDao.delSblogHelps(sblogHelp.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listHonors
	 * @Description: 荣誉查询
	 * @param honor
	 * @return List<Honor>
	 */
	public List<Honor>  listHonors(Honor honor,int[] sum){
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = honorDao.listHonorsCount(honor, conn);
		}
		List<Honor> honors = honorDao.listHonors(honor,conn);
		
		BaseDao.closeDB(null, null, conn);
		return honors;
	}
	
	/**
	 * @Title: getHonor
	 * @Description: 荣誉查询
	 * @param honor
	 * @return Honor
	 */
	public Honor  getHonor(Honor honor){
		Connection conn = BaseDao.getConnection();
		Honor _honor = honorDao.getHonor(honor, conn);
		BaseDao.closeDB(null, null, conn);
		return _honor;
	}
	 
	/**
	 * @Title: addHonor
	 * @Description: 添加荣誉
	 * @param honor
	 * @return void
	 */
	public void  addHonor(Honor honor){
		Connection conn = BaseDao.getConnection();
		honorDao.addHonor(honor, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: updateHonor
	 * @Description: 更新荣誉信息
	 * @param honor
	 * @return void
	 */
	public void  updateHonor(Honor honor){
		Connection conn = BaseDao.getConnection();
		honorDao.updateHonor(honor, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: delHonors
	 * @Description: 删除荣誉信息
	 * @param honor
	 * @return void
	 */
	public void  delHonors(Honor honor){
		Connection conn = BaseDao.getConnection();
		honorDao.delHonors(honor.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
}
