<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>我发布的求助</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/store.css">
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
	
</script>
<style type="text/css">
 body,td,div
 {
   font-size:12px;
 }
</style>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div id="middle">
	 <div id="product_menu">
	 	<table class="ptable_menu">
			<tr class="ptable_menu_title">
				<td>个人中心</td>
			</tr>
			<tr class="ptable_menu_text">
				<td><a href="page_myInfo.action">修改个人信息</a></td>
			</tr>
			<tr class="ptable_menu_text">
				<td><a href="page_myPwd.action">修改登录密码</a></td>
			</tr>
			<tr class="ptable_menu_text">
				<td><a href="page_addSblogShow.action">发布新的帖子</a></td>
			</tr>
			<tr class="ptable_menu_text">
				<td><a href="page_listMySblogs.action">我发布的帖子</a></td>
			</tr>
			<tr class="ptable_menu_text">
				<td><a href="page_listMySblogHelps.action">我的帖子擂台</a></td>
			</tr>
			<tr class="ptable_menu_text">
				<td><a href="page_listMySblogHelps.action">我发布的求助</a></td>
			</tr>
		 </table>
	 </div>
	 <!--  购物车 -->
	 <div id="product_info">
			<div class="title">个人中心  &gt;&gt;  我发布的求助</div>
			<div style="margin-top:5px">
				 <table class="ptable" style="margin-bottom:5px;">
					<tr class="head_text">
						<td align="center">帖子标题</td>
						<td align="center">悬赏经验</td>
						<td align="center">援助人</td>
						<td align="center">援助日期</td>
						<td align="center">状态</td>
						<td align="center">操作</td>
					</tr>
					<s:if test="#attr.sblogHelps!=null && #attr.sblogHelps.size()>0">
   					<s:iterator value="#attr.sblogHelps" id="sblogHelp" status="status">
					<tr>
						 <td width="" align="center" title="<s:property value='#sblogHelp.sblog_title'/>">
						 	<s:property value="#sblogHelp.sblog_title.length()>14?#sblogHelp.sblog_title.substring(0,12)+'..。':#sblogHelp.sblog_title"/>
						 </td>
					     <td width="" align="center"><s:property value="#sblogHelp.help_score"/></td>  
						 <td width="" align="center"><s:property value="#sblogHelp.aid_nick_name"/></td>  
					     <td width="" align="center"><s:property value="#sblogHelp.aid_date.substring(0,10)"/></td>
					     <td width="" align="center"><s:property value="#sblogHelp.help_flagDesc"/></td>
					     <td width="" align="center">
					     	<s:if test="#sblogHelp.help_flag==1 && #sblogHelp.aid_user_id!=null && #sblogHelp.aid_user_id!=''">
					     		<s:a id="rewardSblogHelp_%{#sblogHelp.help_id}_%{#sblogHelp.help_score}_%{#sblogHelp.aid_user_id}" href="javascript:void(0)">发放经验</s:a>
					     	</s:if>
					     </td>
					</tr>
					</s:iterator>
				    </s:if>
				    <s:else>
				    <tr>
				      <td height="60" colspan="6" align="center"><b>&lt;不存在发布的求助信息&gt;</b></td>
				    </tr>
				    </s:else>
				 </table>
			</div>
			<div class="pages">
				<jsp:include page="page.jsp"></jsp:include>
			</div>
		</div>
	 <!--  购物车 -->
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
	function GoPage()
	{
	  var pagenum=document.getElementById("goPage").value;
	  var patten=/^\d+$/;
	  if(!patten.exec(pagenum))
	  {
	    alert("页码必须为大于0的数字");
	    return false;
	  }
	  window.location.href="page_listMySblogHelps.action?pageNo="+pagenum;
	}
	function ChangePage(pagenum)
	{
		window.location.href="page_listMySblogHelps.action?pageNo="+pagenum;
	}
	$(document).ready(function(){
		$("a[id^='rewardSblogHelp']").bind('click',function(){
		    if(confirm('确认发放经验吗!?'))
		    {
		    	var helps=$(this).attr('id').split('_');
		    	var help_id = helps[1];
		    	var help_score = helps[2];
		    	var user_id =  helps[3];
		    	var aQuery = {
						'paramsSblogAid.help_id':help_id,
						'paramsSblogAid.help_score':help_score,
						'paramsSblogAid.user_id':user_id
				};  
				$.post('page_rewardSblogHelp.action',aQuery,
					function(responseObj) {
							if(responseObj.success) {	
								 alert('发放成功！');
								 window.location.href="page_listMySblogHelps.action";
							}else  if(responseObj.err.msg){
								 alert('发放失败：'+responseObj.err.msg);
							}else{
								 alert('发放失败：服务器异常！');
							}	
				},'json');
		    }
		 });
		
	});
</script>
</body>
</html>