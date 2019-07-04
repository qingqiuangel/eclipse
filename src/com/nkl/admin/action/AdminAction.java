package com.nkl.admin.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nkl.admin.manager.AdminManager;
import com.nkl.common.action.BaseAction;
import com.nkl.common.util.DateUtil;
import com.nkl.common.util.Param;
import com.nkl.page.domain.Honor;
import com.nkl.page.domain.Sblog;
import com.nkl.page.domain.SblogHelp;
import com.nkl.page.domain.SblogPk;
import com.nkl.page.domain.Sensitive;
import com.nkl.page.domain.User;

public class AdminAction  extends BaseAction {

	private static final long serialVersionUID = 1L;
	AdminManager adminManager = new AdminManager();

	//抓取页面参数
	User paramsUser;
	Sensitive paramsSensitive;
	Sblog paramsSblog;
	SblogPk paramsSblogPk;
	SblogHelp paramsSblogHelp;
	Honor paramsHonor;
	String tip;
	
	/**
	 * @Title: saveAdmin
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	public String saveAdmin(){
		try {
			//验证用户会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			 //保存修改个人信息
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = new User();
			admin.setUser_id(paramsUser.getUser_id());
			admin = adminManager.getUser(admin);
			Param.setSession("admin", admin);
			
		} catch (Exception e) {
			setErrorTip("编辑异常", "modifyInfo.jsp");
		}
		setSuccessTip("编辑成功", "modifyInfo.jsp");
		return "infoTip";
	}
	
	/**
	 * @Title: saveAdminPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	public String saveAdminPass(){
		try {
			//验证用户会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			 //保存修改个人密码
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = (User)Param.getSession("admin");
			if (admin!=null) {
				admin.setUser_pass(paramsUser.getUser_pass());
				Param.setSession("admin", admin);
			}
			
		} catch (Exception e) {
			setErrorTip("修改异常", "modifyPwd.jsp");
		}
		setSuccessTip("修改成功", "modifyPwd.jsp");
		return "infoTip";
	}
	
	/**
	 * @Title: listUsers
	 * @Description: 查询用户
	 * @return String
	 */
	public String listUsers(){
		try {
			if (paramsUser==null) {
				paramsUser = new User();
			}
			//查询注册用户
			paramsUser.setUser_type(1);
			//设置分页信息
			setPagination(paramsUser);
			int[] sum={0};
			List<User> users = adminManager.listUsers(paramsUser,sum); 
			
			Param.setAttribute("users", users);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询用户异常", "main.jsp");
			return "infoTip";
		}
		
		return "userShow";
	}
	
	/**
	 * @Title: addUserShow
	 * @Description: 显示添加用户页面
	 * @return String
	 */
	public String addUserShow(){
		return "userEdit";
	}
	
	/**
	 * @Title: addUser
	 * @Description: 添加用户
	 * @return String
	 */
	public String addUser(){
		try {
			 //添加用户
			paramsUser.setUser_type(1);
			paramsUser.setUser_level(1);
			paramsUser.setReg_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			adminManager.addUser(paramsUser);
			
		} catch (Exception e) {
			setErrorTip("添加用户异常", "Admin_listUsers.action");
		}
		setSuccessTip("添加用户成功", "Admin_listUsers.action");
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editUser
	 * @Description: 编辑用户
	 * @return String
	 */
	public String editUser(){
		try {
			 //得到用户
			User user = adminManager.getUser(paramsUser);
			Param.setAttribute("user", user);
			
		} catch (Exception e) {
			setErrorTip("查询用户异常", "Admin_listUsers.action");
			return "infoTip";
		}
		
		return "userEdit";
	}
	
	/**
	 * @Title: saveUser
	 * @Description: 保存编辑用户
	 * @return String
	 */
	public String saveUser(){
		try {
			 //保存编辑用户
			adminManager.updateUser(paramsUser);
			
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("user", paramsUser);
			return "userEdit";
		}
		setSuccessTip("编辑用户成功", "Admin_listUsers.action");
		return "infoTip";
	}
	
	/**
	 * @Title: delUsers
	 * @Description: 删除用户
	 * @return String
	 */
	public String delUsers(){
		try {
			 //删除用户
			adminManager.delUsers(paramsUser);
			
		} catch (Exception e) {
			setErrorTip("删除用户异常", "Admin_listUsers.action");
		}
		setSuccessTip("删除用户成功", "Admin_listUsers.action");
		return "infoTip";
	}
	
	/**
	 * @Title: listSensitives
	 * @Description: 查询敏感词
	 * @return String
	 */
	public String listSensitives(){
		try {
			if (paramsSensitive==null) {
				paramsSensitive = new Sensitive();
			}
			//设置分页信息
			setPagination(paramsSensitive);
			int[] sum={0};
			List<Sensitive> sensitives = adminManager.listSensitives(paramsSensitive,sum); 
			
			Param.setAttribute("sensitives", sensitives);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询敏感词异常", "main.jsp");
			return "infoTip";
		}
		
		return "sensitiveShow";
	}
	
	/**
	 * @Title: editSensitive
	 * @Description: 编辑敏感词
	 * @return String
	 */
	public String editSensitive(){
		try {
			 //得到敏感词
			Sensitive sensitive = adminManager.getSensitive(paramsSensitive);
			Param.setAttribute("sensitive", sensitive);
			
		} catch (Exception e) {
			setErrorTip("查询敏感词异常", "Admin_listSensitives.action");
			return "infoTip";
		}
		
		return "sensitiveEdit";
	}
	
	/**
	 * @Title: saveSensitive
	 * @Description: 保存编辑敏感词
	 * @return String
	 */
	public String saveSensitive(){
		try {
			 //保存编辑敏感词
			adminManager.updateSensitive(paramsSensitive);
			
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("sensitive", paramsSensitive);
			return "sensitiveEdit";
		}
		setSuccessTip("编辑敏感词成功", "Admin_listSensitives.action");
		return "infoTip";
	}
	
	/**
	 * @Title: listHonors
	 * @Description: 查询荣誉奖状
	 * @return String
	 */
	public String listHonors(){
		try {
			if (paramsHonor==null) {
				paramsHonor = new Honor();
			}
			//设置分页信息
			setPagination(paramsHonor);
			int[] sum={0};
			List<Honor> honors = adminManager.listHonors(paramsHonor,sum); 
			
			Param.setAttribute("honors", honors);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询荣誉奖状异常", "main.jsp");
			return "infoTip";
		}
		
		return "honorShow";
	}
	
	/**
	 * @Title: addHonorShow
	 * @Description: 显示添加荣誉奖状页面
	 * @return String
	 */
	public String addHonorShow(){
		return "honorEdit";
	}
	
	/**
	 * @Title: addHonor
	 * @Description: 添加荣誉奖状
	 * @return String
	 */
	public String addHonor(){
		try {
			 //添加荣誉奖状
			adminManager.addHonor(paramsHonor);
			
		} catch (Exception e) {
			setErrorTip("添加荣誉奖状异常", "Admin_listHonors.action");
		}
		setSuccessTip("添加荣誉奖状成功", "Admin_listHonors.action");
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editHonor
	 * @Description: 编辑荣誉奖状
	 * @return String
	 */
	public String editHonor(){
		try {
			 //得到荣誉奖状
			Honor honor = adminManager.getHonor(paramsHonor);
			Param.setAttribute("honor", honor);
			
		} catch (Exception e) {
			setErrorTip("查询荣誉奖状异常", "Admin_listHonors.action");
			return "infoTip";
		}
		
		return "honorEdit";
	}
	
	/**
	 * @Title: saveHonor
	 * @Description: 保存编辑荣誉奖状
	 * @return String
	 */
	public String saveHonor(){
		try {
			 //保存编辑荣誉奖状
			adminManager.updateHonor(paramsHonor);
			
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("honor", paramsHonor);
			return "honorEdit";
		}
		setSuccessTip("编辑荣誉奖状成功", "Admin_listHonors.action");
		return "infoTip";
	}
	
	/**
	 * @Title: delHonors
	 * @Description: 删除荣誉奖状
	 * @return String
	 */
	public String delHonors(){
		try {
			 //删除荣誉奖状
			adminManager.delHonors(paramsHonor);
			
		} catch (Exception e) {
			setErrorTip("删除荣誉奖状异常", "Admin_listHonors.action");
		}
		setSuccessTip("删除荣誉奖状成功", "Admin_listHonors.action");
		return "infoTip";
	}
	
	/**
	 * @Title: listSblogs
	 * @Description: 查询帖子
	 * @return String
	 */
	public String listSblogs(){
		try {
			//查询帖子
			if (paramsSblog==null) {
				paramsSblog = new Sblog();
			}
			//分页设置
			setPagination(paramsSblog);
			//正常帖子
			paramsSblog.setSblog_flag(1);
			int[] sum={0};
			List<Sblog> sblogs = adminManager.listSblogs(paramsSblog,sum); 
			
			Param.setAttribute("sblogs", sblogs);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询异常", "main.jsp");
			return "infoTip";
		}
		
		return "sblogShow";
	}
	
	/**
	 * @Title: delSblogs
	 * @Description: 删除帖子
	 * @return String
	 */
	public String delSblogs(){
		try {
			 //删除帖子
			adminManager.delSblogs(paramsSblog);
			
		} catch (Exception e) {
			setErrorTip("删除异常", "Admin_listSblogs.action");
		}
		setSuccessTip("删除成功", "Admin_listSblogs.action");
		return "infoTip";
	}
	
	/**
	 * @Title: listSblogPks
	 * @Description: 擂台查询
	 * @return String
	 */
	public String listSblogPks(){
		try {
			if (paramsSblogPk==null) {
				paramsSblogPk = new SblogPk();
			}
			//设置分页信息
			setPagination(paramsSblogPk);
			int[] sum={0};
			List<SblogPk> sblogPks = adminManager.listSblogPks(paramsSblogPk,sum); 
			
			Param.setAttribute("sblogPks", sblogPks);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("擂台查询异常", "main.jsp");
			return "infoTip";
		}
		
		return "sblogPkShow";
	}
	
	/**
	 * @Title: querySblogPk
	 * @Description: 获取擂台信息
	 * @return String
	 */
	public String querySblogPk(){
		try {
			 //得到擂台信息
			SblogPk sblogPk = adminManager.getSblogPk(paramsSblogPk);
			Param.setAttribute("sblogPk", sblogPk);
			
			//查询奖状信息
			Honor honor = new Honor();
			honor.setStart(-1);
			List<Honor> honors = adminManager.listHonors(honor, null);
			if (honors==null) {
				honors = new ArrayList<Honor>();
			}
			Param.setAttribute("honors", honors);
			
		} catch (Exception e) {
			setErrorTip("查询擂台信息异常", "Admin_listSblogPks.action");
			return "infoTip";
		}
		
		return "sblogPkEdit";
	}
	
	/**
	 * @Title: rewardSblogPk
	 * @Description: 擂台奖励
	 * @return String
	 */
	public String rewardSblogPk(){
		try {
			 //擂台奖励
			adminManager.rewardSblogPk(paramsSblogPk);
			
		} catch (Exception e) {
			setErrorTip("擂台奖励异常", "Admin_listSblogPks.action");
		}
		setSuccessTip("擂台奖励成功", "Admin_listSblogPks.action");
		return "infoTip";
	}
	
	/**
	 * @Title: listSblogHelps
	 * @Description: 查询求助帖子
	 * @return String
	 */
	public String listSblogHelps(){
		try {
			//查询求助帖子
			if (paramsSblogHelp==null) {
				paramsSblogHelp = new SblogHelp();
			}
			setPagination(paramsSblogHelp);
			int[] sum={0};
			List<SblogHelp> sblogHelps = adminManager.listSblogHelps(paramsSblogHelp,sum); 
			
			Param.setAttribute("sblogHelps", sblogHelps);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询异常", "main.jsp");
			return "infoTip";
		}
		
		return "sblogHelpShow";
	}
	
	/**
	 * @Title: delSblogHelps
	 * @Description: 删除求助帖子
	 * @return String
	 */
	public String delSblogHelps(){
		try {
			 //删除求助帖子
			adminManager.delSblogHelps(paramsSblogHelp);
			
		} catch (Exception e) {
			setErrorTip("删除异常", "Admin_listSblogHelps.action");
		}
		setSuccessTip("删除成功", "Admin_listSblogHelps.action");
		return "infoTip";
	}
	
	private boolean validateAdmin(){
		User admin = (User)Param.getSession("admin");
		if (admin!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	private void setErrorTip(String tip,String url){
		Param.setAttribute("tipType", "error");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
	}
	
	private void setSuccessTip(String tip,String url){
		Param.setAttribute("tipType", "success");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
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

	public Sensitive getParamsSensitive() {
		return paramsSensitive;
	}

	public void setParamsSensitive(Sensitive paramsSensitive) {
		this.paramsSensitive = paramsSensitive;
	}

	public SblogHelp getParamsSblogHelp() {
		return paramsSblogHelp;
	}

	public void setParamsSblogHelp(SblogHelp paramsSblogHelp) {
		this.paramsSblogHelp = paramsSblogHelp;
	}

	public Honor getParamsHonor() {
		return paramsHonor;
	}

	public void setParamsHonor(Honor paramsHonor) {
		this.paramsHonor = paramsHonor;
	}

	public SblogPk getParamsSblogPk() {
		return paramsSblogPk;
	}

	public void setParamsSblogPk(SblogPk paramsSblogPk) {
		this.paramsSblogPk = paramsSblogPk;
	}


}
