package com.nkl.page.action;

import java.util.ArrayList;
import java.util.List;

import com.nkl.common.action.BaseAction;
import com.nkl.common.util.Param;
import com.nkl.page.domain.HonorWall;
import com.nkl.page.domain.Sblog;
import com.nkl.page.domain.SblogAid;
import com.nkl.page.domain.SblogClick;
import com.nkl.page.domain.SblogHelp;
import com.nkl.page.domain.SblogPic;
import com.nkl.page.domain.SblogPk;
import com.nkl.page.domain.SblogReply;
import com.nkl.page.domain.SblogVote;
import com.nkl.page.domain.User;
import com.nkl.page.manager.IndexManager;

public class IndexAction  extends BaseAction {

	private static final long serialVersionUID = 1L;
	IndexManager indexManager = new IndexManager();

	//抓取页面参数
	User paramsUser;
	Sblog paramsSblog;
	SblogAid paramsSblogAid;
	SblogClick paramsSblogClick;
	SblogHelp paramsSblogHelp;
	SblogPic paramsSblogPic;
	SblogPk paramsSblogPk;
	SblogReply paramsSblogReply;
	SblogVote paramsSblogVote;
	HonorWall paramsHonorWall;
	String tip;
	
	public String index(){
		try {
			//查询5类帖子数量
			Sblog sblog = new Sblog();
			sblog.setSblog_type(1);
			sblog.setSblog_flag(1);
			int count1 = indexManager.listSblogsCount(sblog); 
			Param.setAttribute("count1", count1);
			
			sblog.setSblog_type(2);
			int count2 = indexManager.listSblogsCount(sblog); 
			Param.setAttribute("count2", count2);
			
			sblog.setSblog_type(3);
			int count3 = indexManager.listSblogsCount(sblog); 
			Param.setAttribute("count3", count3);
			
			sblog.setSblog_type(4);
			int count4 = indexManager.listSblogsCount(sblog); 
			Param.setAttribute("count4", count4);
			
			sblog.setSblog_type(5);
			int count5 = indexManager.listSblogsCount(sblog); 
			Param.setAttribute("count5", count5);
			
			//查询擂台帖子
			SblogPk sblogPk = new SblogPk();
			sblogPk.setPk_flag(1);
			int countPk = indexManager.listSblogPksCount(sblogPk); 
			Param.setAttribute("countPk", countPk);
			
			//查询求助站帖子
			SblogHelp sblogHelp = new SblogHelp();
			sblogHelp.setHelp_flag(1);
			int countHelp = indexManager.listSblogHelpsCount(sblogHelp); 
			Param.setAttribute("countHelp", countHelp);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "index";
	}
	
	/**
	 * @Title: honorWalls
	 * @Description: 荣誉墙信息
	 * @return String
	 */
	public String listHonorWalls(){
		try {
			//查询荣誉墙信息集合
			if (paramsHonorWall==null) {
				paramsHonorWall = new HonorWall();
			}
			//分页信息设置
			setPagination(paramsHonorWall);
			int[] sum={0};
			List<HonorWall> honorWalls = indexManager.listHonorWalls(paramsHonorWall,sum); 
			Param.setAttribute("honorWalls", honorWalls);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "honorWall";
	}
	
	/**
	 * @Title: sblogs
	 * @Description: 帖子信息
	 * @return String
	 */
	public String listSblogs(){
		try {
			//查询帖子信息集合
			if (paramsSblog==null) {
				paramsSblog = new Sblog();
			}
			//帖子标志正常
			paramsSblog.setSblog_flag(1);
			//分页信息设置
			setPagination(paramsSblog);
			int[] sum={0};
			List<Sblog> sblogs = indexManager.listSblogs(paramsSblog,sum); 
			Param.setAttribute("sblogs", sblogs);
			setTotalCount(sum[0]);
			
			//帖子类型
			Param.setAttribute("sblog_type", paramsSblog.getSblog_type());
			Param.setAttribute("sblog_typeDesc", paramsSblog.getSblog_typeDesc());
			
			//查询帖子Top5
			paramsSblog.setStart(0);
			paramsSblog.setEnd(5);
			List<Sblog> sblogTops = indexManager.listSblogTops(paramsSblog); 
			Param.setAttribute("sblogTops", sblogTops);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "sblog";
	}
	
	/**
	 * @Title: querySblog
	 * @Description: 帖子详情
	 * @return String
	 */
	public String querySblog(){
		try {
			 //帖子详情
			Sblog sblog = indexManager.getSblog(paramsSblog);
			Param.setAttribute("sblog", sblog);
			Param.setAttribute("sblog_type", sblog.getSblog_type());
			Param.setAttribute("sblog_typeDesc", sblog.getSblog_typeDesc());
			
			//帖子图片
			List<SblogPic> sblogPics = indexManager.listSblogPics(paramsSblog);
			Param.setAttribute("sblogPics", sblogPics);
			
			//帖子回复信息
			setPagination(paramsSblogReply);//分页信息
			int[] sum={0};
			List<SblogReply> sblogReplys = indexManager.listSblogReplys(paramsSblogReply,sum); 
			Param.setAttribute("sblogReplys", sblogReplys);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "sblogDetail";
	}
	
	/**
	 * @Title: addSblogReply
	 * @Description: 新增帖子回复信息
	 * @return String
	 */
	public String addSblogReply(){
		try {
			//验证码验证
			String random = (String)Param.getSession("random");
			if (!random.equals(paramsSblogReply.getRandom())) {
				setErrorReason("验证码错误！");
				return "error2";
			}
			//新增帖子回复信息
			indexManager.addSblogReply(paramsSblogReply);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("后台服务器繁忙！");
			return "error2";
		}
		
		return "success";
	}
	
	/**
	 * @Title: addSblogClick
	 * @Description: 新增帖子点赞
	 * @return String
	 */
	public String addSblogClick(){
		try {
			//检查是否已经点过赞
			SblogClick sblogClick = indexManager.getSblogClick(paramsSblogClick);
			if (sblogClick!=null) {
				setErrorReason("您已经点过赞了！");
				return "error2";
			}
			
			//新增帖子回复信息
			indexManager.addSblogClick(paramsSblogClick);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("后台服务器繁忙！");
			return "error2";
		}
		
		return "success";
	}
	
	
	/**
	 * @Title: listMySblogs
	 * @Description: 我的帖子信息
	 * @return String
	 */
	public String listMySblogs(){
		try {
			//查询留言板
			if (paramsSblog==null) {
				paramsSblog = new Sblog();
			}
			//用户身份
			User user = (User)Param.getSession("userFront");
			paramsSblog.setUser_id(user.getUser_id());
			//分页信息
			setPagination(paramsSblog);
			int[] sum={0};
			List<Sblog> sblogs = indexManager.listSblogs(paramsSblog,sum); 
			
			Param.setAttribute("sblogs", sblogs);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "sblogShow";
	}
	
	/**
	 * @Title: addSblogShow
	 * @Description: 新增帖子信息
	 * @return String
	 */
	public String addSblogShow(){
		Param.setAttribute("sblogPic", "");
		return "sblogEdit";
	}
	
	/**
	 * @Title: addSblog
	 * @Description: 新增帖子信息
	 * @return String
	 */
	public String addSblog(){
		try {
			//新增帖子信息
			indexManager.addSblog(paramsSblog);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("后台服务器繁忙！");
			return "error2";
		}
		
		return "success";
	}
	
	/**
	 * @Title: editSblog
	 * @Description: 编辑帖子信息
	 * @return String
	 */
	public String editSblog(){
		try {
			//查询帖子
			Sblog sblog = indexManager.getSblog(paramsSblog);
			Param.setAttribute("sblog", sblog);
			
			//帖子图片
			List<SblogPic> sblogPics = indexManager.listSblogPics(paramsSblog);
			if (sblogPics!=null && sblogPics.size()>0) {
				Param.setAttribute("sblogPic", sblogPics.get(0));
			}else {
				Param.setAttribute("sblogPic", "");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "sblogEdit";
	}
	
	/**
	 * @Title: saveSblog
	 * @Description: 保存帖子信息
	 * @return String
	 */
	public String saveSblog(){
		try {
			//保存帖子信息
			indexManager.updateSblog(paramsSblog);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("后台服务器繁忙！");
			return "error2";
		}
		
		return "success";
	}
	
	/**
	 * @Title: delSblogs
	 * @Description: 删除帖子
	 * @return String
	 */
	public String delSblogs(){
		try {
			 //删除帖子
			indexManager.delSblogs(paramsSblog);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
	/**
	 * @Title: addSblogPkShow
	 * @Description: 新增帖子PK
	 * @return String
	 */
	public String addSblogPkShow(){
		try {
			//获取帖子信息
			Sblog sblog = indexManager.getSblog(paramsSblog);
			Param.setAttribute("sblog", sblog);
			
			//查询用户要Pk的帖子
			Sblog sblog2 = new Sblog();
			sblog2.setSblog_flag(1);
			User user = (User)Param.getSession("userFront");
			sblog2.setUser_id(user.getUser_id());
			sblog2.setStart(-1);
			List<Sblog> sblogs = indexManager.listSblogs(sblog2, null);
			if (sblogs==null) {
				sblogs = new ArrayList<Sblog>();
			}
			Param.setAttribute("sblogs", sblogs);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "sblogPkAdd";
	}
	
	/**
	 * @Title: addSblogPk
	 * @Description: 新增帖子PK
	 * @return String
	 */
	public String addSblogPk(){
		try {
			//判断帖子PK是否存在
			SblogPk sblogPk = indexManager.getSblogPk(paramsSblogPk);
			if (sblogPk!=null) {
				setErrorReason("您已经PK过该帖子了，不能重复Pk！");
				return "error2";
			}
			
			//新增帖子PK
			indexManager.addSblogPk(paramsSblogPk);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("后台服务器繁忙！");
			return "error2";
		}
		
		return "success";
	}
	
	/**
	 * @Title: listSblogPks
	 * @Description: 擂台信息
	 * @return String
	 */
	public String listSblogPks(){
		try {
			//查询擂台信息
			if (paramsSblogPk==null) {
				paramsSblogPk = new SblogPk();
			}
			paramsSblogPk.setPk_flag(1);
			setPagination(paramsSblogPk);
			int[] sum={0};
			List<SblogPk> sblogPks = indexManager.listSblogPks(paramsSblogPk,sum); 
			
			Param.setAttribute("sblogPks", sblogPks);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "sblogPk";
	}
	
	/**
	 * @Title: querySblogPk
	 * @Description: 擂台详情
	 * @return String
	 */
	public String querySblogPk(){
		try {
			 //擂台详情
			SblogPk sblogPk = indexManager.getSblogPk(paramsSblogPk);
			Param.setAttribute("sblogPk", sblogPk);
			
			//守擂贴详情
			Sblog sblogDefend = new Sblog();
			sblogDefend.setSblog_id(sblogPk.getDefend_sblog_id());
			sblogDefend = indexManager.getSblog(sblogDefend);
			Param.setAttribute("sblogDefend", sblogDefend);
			//帖子图片
			List<SblogPic> sblogDefendPics = indexManager.listSblogPics(sblogDefend);
			Param.setAttribute("sblogDefendPics", sblogDefendPics);
			
			//挑战贴详情
			Sblog sblogReply = new Sblog();
			sblogReply.setSblog_id(sblogPk.getReply_sblog_id());
			sblogReply = indexManager.getSblog(sblogReply);
			Param.setAttribute("sblogReply", sblogReply);
			//帖子图片
			List<SblogPic> sblogReplyPics = indexManager.listSblogPics(sblogReply);
			Param.setAttribute("sblogReplyPics", sblogReplyPics);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "sblogPkDetail";
	}
	
	/**
	 * @Title: addSblogVote
	 * @Description: 新增帖子投票
	 * @return String
	 */
	public String addSblogVote(){
		try {
			//检查是否已经投票
			SblogVote sblogVote = indexManager.getSblogVote(paramsSblogVote);
			if (sblogVote!=null) {
				setErrorReason("您已经投过票了，不能重复投票！");
				return "error2";
			}
			
			//新增帖子投票信息
			indexManager.addSblogVote(paramsSblogVote);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("后台服务器繁忙！");
			return "error2";
		}
		
		return "success";
	}
	
	
	/**
	 * @Title: listMySblogPks
	 * @Description: 我的擂台信息
	 * @return String
	 */
	public String listMySblogPks(){
		try {
			//查询我的擂台信息
			if (paramsSblogPk==null) {
				paramsSblogPk = new SblogPk();
			}
			//用户身份
			User user = (User)Param.getSession("userFront");
			paramsSblogPk.setUser_id(user.getUser_id());
			//分页信息
			setPagination(paramsSblogPk);
			int[] sum={0};
			List<SblogPk> sblogPks = indexManager.listSblogPks(paramsSblogPk,sum); 
			
			Param.setAttribute("sblogPks", sblogPks);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "sblogPkShow";
	}
	
	/**
	 * @Title: defeatPk
	 * @Description: 擂台认输
	 * @return String
	 */
	public String defeatPk(){
		try {
			//擂台认输
			indexManager.defeatPk(paramsSblogPk);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("后台服务器繁忙！");
			return "error2";
		}
		
		return "success";
	}
	
	/**
	 * @Title: listSblogHelps
	 * @Description: 求助信息
	 * @return String
	 */
	public String listSblogHelps(){
		try {
			//查询求助信息
			if (paramsSblogHelp==null) {
				paramsSblogHelp = new SblogHelp();
			}
			paramsSblogHelp.setHelp_flag(1);
			setPagination(paramsSblogHelp);
			int[] sum={0};
			List<SblogHelp> sblogHelps = indexManager.listSblogHelps(paramsSblogHelp,sum); 
			
			Param.setAttribute("sblogHelps", sblogHelps);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "sblogHelp";
	}
	
	
	/**
	 * @Title: querySblogHelp
	 * @Description: 求助帖子详情
	 * @return String
	 */
	public String querySblogHelp(){
		try {
			 //查询求助帖子
			SblogHelp sblogHelp = indexManager.getSblogHelp(paramsSblogHelp);
			Param.setAttribute("sblogHelp", sblogHelp);
			//帖子图片
			Sblog sblog = new Sblog();
			sblog.setSblog_id(sblogHelp.getSblog_id());
			List<SblogPic> sblogPics = indexManager.listSblogPics(sblog);
			Param.setAttribute("sblogPics", sblogPics);
			
			//帖子回复信息
			setPagination(paramsSblogAid);//分页信息
			int[] sum={0};
			List<SblogAid> sblogAids = indexManager.listSblogAids(paramsSblogAid,sum); 
			Param.setAttribute("sblogAids", sblogAids);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "sblogHelpDetail";
	}
	
	/**
	 * @Title: addSblogAid
	 * @Description: 新增帖子援助
	 * @return String
	 */
	public String addSblogAid(){
		try {
			//新增帖子援助信息
			indexManager.addSblogAid(paramsSblogAid);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("后台服务器繁忙！");
			return "error2";
		}
		
		return "success";
	}
	
	
	/**
	 * @Title: listMySblogHelps
	 * @Description: 我的求助信息
	 * @return String
	 */
	public String listMySblogHelps(){
		try {
			//查询我的求助信息
			if (paramsSblogHelp==null) {
				paramsSblogHelp = new SblogHelp();
			}
			//用户身份
			User user = (User)Param.getSession("userFront");
			paramsSblogHelp.setUser_id(user.getUser_id());
			//分页信息
			setPagination(paramsSblogHelp);
			int[] sum={0};
			List<SblogHelp> sblogHelps = indexManager.listMySblogHelps(paramsSblogHelp,sum); 
			
			Param.setAttribute("sblogHelps", sblogHelps);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "sblogHelpShow";
	}
	
	/**
	 * @Title: addSblogHelpShow
	 * @Description: 新增帖子求助信息
	 * @return String
	 */
	public String addSblogHelpShow(){
		Sblog sblog = indexManager.getSblog(paramsSblog);
		Param.setAttribute("sblog", sblog);
		
		return "sblogHelpEdit";
	}
	
	/**
	 * @Title: addSblogHelp
	 * @Description: 新增帖子求助信息
	 * @return String
	 */
	public String addSblogHelp(){
		try {
			//新增帖子信息
			indexManager.addSblogHelp(paramsSblogHelp);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("后台服务器繁忙！");
			return "error2";
		}
		
		return "success";
	}
	
	/**
	 * @Title: delSblogHelps
	 * @Description: 删除帖子求助
	 * @return String
	 */
	public String delSblogHelps(){
		try {
			 //删除帖子
			indexManager.delSblogHelps(paramsSblogHelp);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
	/**
	 * @Title: rewardSblogHelp
	 * @Description: 奖励援助信息
	 * @return String
	 */
	public String rewardSblogHelp(){
		try {
			//奖励援助信息
			indexManager.rewardSblogAid(paramsSblogAid);
			
		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("后台服务器繁忙！");
			return "error2";
		}
		
		return "success";
	}
	
	/**
	 * @Title: saveUserFront
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	public String saveUserFront(){
		try {
			 //保存修改个人信息
			indexManager.updateUser(paramsUser);
			//更新session
			User userFront = new User();
			userFront.setUser_id(paramsUser.getUser_id());
			userFront = indexManager.getUser(userFront);
			Param.setSession("userFront", userFront);
			
		} catch (Exception e) {
			e.printStackTrace();
			tip = "修改失败";
			return "userInfo";
		}
		tip = "修改成功";
		return "userInfo";
	}
	
	/**
	 * @Title: saveUserFrontPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	public String saveUserFrontPass(){
		try {
			 //保存修改个人密码
			indexManager.updateUser(paramsUser);
			//更新session
			User UserFront = (User)Param.getSession("UserFront");
			if (UserFront!=null) {
				UserFront.setUser_pass(paramsUser.getUser_pass());
				Param.setSession("UserFront", UserFront);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			tip = "修改失败";
			return "userPwd";
		}
		tip = "修改成功";
		return "userPwd";
	}
	
	/**
	 * @Title: reg
	 * @Description: 跳转注册页面
	 * @return String
	 */
	public String reg(){
		return "reg";
	}
	
	/**
	 * @Title: myInfo
	 * @Description: 跳转个人信息页面
	 * @return String
	 */
	public String myInfo(){
		return "userInfo";
	}
	
	/**
	 * @Title: myPwd
	 * @Description: 跳转个人密码页面
	 * @return String
	 */
	public String myPwd(){
		return "userPwd";
	}
	
	public Sblog getParamsSblog() {
		return paramsSblog;
	}

	public void setParamsSblog(Sblog paramsSblog) {
		this.paramsSblog = paramsSblog;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public User getParamsUser() {
		return paramsUser;
	}

	public void setParamsUser(User paramsUser) {
		this.paramsUser = paramsUser;
	}

	public SblogClick getParamsSblogClick() {
		return paramsSblogClick;
	}

	public void setParamsSblogClick(SblogClick paramsSblogClick) {
		this.paramsSblogClick = paramsSblogClick;
	}

	public SblogHelp getParamsSblogHelp() {
		return paramsSblogHelp;
	}

	public void setParamsSblogHelp(SblogHelp paramsSblogHelp) {
		this.paramsSblogHelp = paramsSblogHelp;
	}

	public SblogPic getParamsSblogPic() {
		return paramsSblogPic;
	}

	public void setParamsSblogPic(SblogPic paramsSblogPic) {
		this.paramsSblogPic = paramsSblogPic;
	}

	public SblogPk getParamsSblogPk() {
		return paramsSblogPk;
	}

	public void setParamsSblogPk(SblogPk paramsSblogPk) {
		this.paramsSblogPk = paramsSblogPk;
	}

	public SblogReply getParamsSblogReply() {
		return paramsSblogReply;
	}

	public void setParamsSblogReply(SblogReply paramsSblogReply) {
		this.paramsSblogReply = paramsSblogReply;
	}

	public SblogVote getParamsSblogVote() {
		return paramsSblogVote;
	}

	public void setParamsSblogVote(SblogVote paramsSblogVote) {
		this.paramsSblogVote = paramsSblogVote;
	}

	public SblogAid getParamsSblogAid() {
		return paramsSblogAid;
	}

	public void setParamsSblogAid(SblogAid paramsSblogAid) {
		this.paramsSblogAid = paramsSblogAid;
	}

	public HonorWall getParamsHonorWall() {
		return paramsHonorWall;
	}

	public void setParamsHonorWall(HonorWall paramsHonorWall) {
		this.paramsHonorWall = paramsHonorWall;
	}

}
