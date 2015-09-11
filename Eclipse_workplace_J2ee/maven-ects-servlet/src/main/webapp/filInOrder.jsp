<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>   
<jsp:useBean id="now" class="java.util.Date" />    
<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy/MM/dd" var="dataTime"/>   
<!DOCTYPE HTML PUBLIC "-//W3C//DTD Xjsp 1.0 Transitional//EN" "http://www.w3.org/TR/xjsp1/DTD/xjsp1-transitional.dtd">
<html xmlns=http://www.w3.org/1999/xhtml>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>Book Store</title>
		<link rel="stylesheet" type="text/css" href="style.css" />
		<script LANGUAGE = "JavaScript" >
 			function  checkvalue()
  			 {
  				 document.form1.action="${pageContext.request.contextPath}/AddOrderServlet";
  				 document.form1.submit();
 				  return true;

			 } 
</script>
	</head>
	<body>
		<div id="wrap">

			<div class="header">
				<div class="logo">
					<a href="index.jsp"><img src="images/logo.gif" alt="" title=""
							border="0" /> </a>
				</div>
				<div id="menu">
					<ul>
						<li class="selected">
							<a href="index.jsp">首页 </a>
						</li>
						<li>
							<a href="about.jsp">关于我们</a>
						</li>
						<li>
							<a
								href="${pageContext.request.contextPath}/FildAllProductServlet">书架</a>
						</li>
						<li>
							<a href="specials.jsp">特别书籍</a>
						</li>
						<li>
							<a href="myaccount.jsp">我的账户</a>
						</li>
						<li>
							<a href="register.jsp">注册</a>
						</li>
						<li>
							<a href="details.jsp">价格</a>
						</li>
						<li>
							<a href="contact.jsp">联系</a>
						</li>
					</ul>
				</div>

				<div class="center_content">
					<div class="left_content">
						<div class="title">
							<span class="title_icon"><img src="images/bullet1.gif"
									alt="" title="" /> </span>订单
						</div>

						<div class="feat_prod_box_details">
							<p class="details">
								谢谢您的支持
							</p>
							<div class="contact_form">
								<div class="form_subtitle">
									订单信息:
								</div>
								<table width="350" height="20" align="center">
									<tr class="cart_title" align="center">
                                    	<td>
											日期
										</td>
										<td>
											用户名
										</td>
										<td>
											商品数目
										</td>
										<td>
											商品总花费
										</td>
									</tr>
									
									<tr align="center">
                                    <td>${dataTime}</td>
                                    <td>${ sessionScope.user.name}</td>
                                    <td>${ sessionScope.cart.items}</td>
                                    <td>${ sessionScope.cart.money}</td>
                                    </tr>
								</table>
                            </div>
                            
                            
                            
                            
							<div class="contact_form">
								<div class="form_subtitle">
									您的详细信息:
								</div>
								<table>

									<tr>
										<td>
											<label class="contact">
												<strong>账号:</strong>
											</label>
										</td>
										<td>
											<div class="form_row">
												${sessionScope.user.name}
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<label class="contact">
												<strong>邮箱:</strong>
											</label>
										</td>
										<td>
											<div class="form_row">
												${sessionScope.user.contcatinfo.email}
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<label class="contact">
												<strong>家庭号码:</strong>
											</label>
										</td>
										<td>
											<div class="form_row">
												${sessionScope.user.contcatinfo.homePhone}
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<label class="contact">
												<strong>手机号码:</strong>
											</label>
										</td>
										<td>
											<div class="form_row">
												${sessionScope.user.contcatinfo.cellPhone}
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<label class="contact">
												<strong>地址:</strong>
											</label>
										</td>
										<td>
											<table>
												<tr>
													<td>
														国家:
													</td>
													<td>
														<font color="color: #333333;">${sessionScope.user.contcatinfo.country.name}</font>
													</td>
													<td>
														省份:
													</td>
													<td>
														<font color="color: #333333;">${sessionScope.user.contcatinfo.province.name}</font>
													</td>
													<td>
														城市:
													</td>
													<td>
														<font color="color: #333333;">${sessionScope.user.contcatinfo.city}</font>
													</td>
												</tr>
											</table>
										</td>
									</tr>


								</table>




								<div class="form_row">
									<form name="form1" action="" >
										<table width="100%" align="center">
											<tr>
												<td>
													付款方式:
												</td>
												<td>
													<input type="radio" name="paywayId" value="1" checked="checked">
													现金付款
												</td>
												<td>
													<input type="radio" name="paywayId" value="2">
													网银付款
												</td>
												<td>
													<input type="radio" name="paywayId" value="3">
													电汇付款
												</td>
											</tr>

										</table>
										<table>
											<tr>
												<td align="center">
													<a href="alterUser.jsp" class="checkout"> 修改信息</a>
												</td>
												<td align="center">
                                                	<a href="#" onClick="javascript:return checkvalue();" class="checkout"> 提交表单</a>
												</td>
											</tr>
										</table>
										<input type="hidden" name="userId" value="${sessionScope.user.userId}"> 
									</form>
								</div>
								<div class="form_row">

								</div>



							</div>



						</div>
						<div class="clear"></div>
					</div>
					<!--end of left content-->

					<div class="right_content">
						<div class="languages_box">
							<span class="red">语言:</span>
							<a href="#" class="selected"><img src="images/gb.gif" alt=""
									title="" border="0" /> </a>
							<a href="#"><img src="images/fr.gif" alt="" title=""
									border="0" /> </a>
							<a href="#"><img src="images/de.gif" alt="" title=""
									border="0" /> </a>
						</div>
						<div class="currency">
							<span class="red">货币: </span>
							<a href="#">GBP</a>
							<a href="#">EUR</a>
							<a href="#" class="selected">USD</a>
						</div>

						<div class="cart">
							<c:if test="${(empty sessionScope.user)}">
								<form action="${pageContext.request.contextPath}/LoginServlet"
									method="post">
									<table>
										<tr>
											<td>
												账号:
											</td>
											<td>
												<input type="text" size="10" name="loginname">
											</td>
											<td>
												密码:
											</td>
											<td>
												<input type="password" size="12" name="loginpassword">
											</td>
											<td>
												<input type="submit" value="提交">
											</td>
										</tr>
									</table>
								</form>
							</c:if>
							<c:if test="${!(empty sessionScope.user)}">
								<table>
									<tr>
										<td>
											欢迎${sessionScope.user.name}!!!
										</td>
										<td>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
										<td>
											<a href="${pageContext.request.contextPath}/LogoutServlet">注销</a>
										</td>
									</tr>
								</table>
								<table>
									<tr>
										<td>
											<span class="title_icon"><img src="images/cart.gif"
													alt="" title="" /> </span>购物车
										</td>
										<td>
											${sessionScope.cart.items} x items |
											<span class="red">TOTAL: ${sessionScope.cart.money}$</span>
										</td>
										<td>
											<a href="cart.jsp" class="view_cart">进入购物车</a>
										</td>
									</tr>
								</table>

							</c:if>

						</div>

						<div class="title">
							<span class="title_icon"><img src="images/bullet3.gif"
									alt="" title="" /> </span>书城介绍 Story
						</div>
						<div class="about">
							<p>
								<img src="images/about.gif" alt="" title="" class="right" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我们书店是全较最大的综合性中文网上购物商城，由国内著名出版机构科文公司、美国
								当当网 老虎基金、美国IDG集团、卢森堡剑桥集团、亚洲创业投资基金（原名软银中国创业基金）共同投资成立。
							</p>

						</div>

						<div class="right_box">

							<div class="title">
								<span class="title_icon"><img src="images/bullet4.gif"
										alt="" title="" /> </span>Promotions
							</div>
							<div class="new_prod_box">
								<a href="details.jsp">product name</a>
								<div class="new_prod_bg">
									<span class="new_icon"><img src="images/promo_icon.gif"
											alt="" title="" /> </span>
									<a href="details.jsp"><img src="images/thumb1.gif" alt=""
											title="" class="thumb" border="0" /> </a>
								</div>
							</div>

							<div class="new_prod_box">
								<a href="details.jsp">product name</a>
								<div class="new_prod_bg">
									<span class="new_icon"><img src="images/promo_icon.gif"
											alt="" title="" /> </span>
									<a href="details.jsp"><img src="images/thumb2.gif" alt=""
											title="" class="thumb" border="0" /> </a>
								</div>
							</div>

							<div class="new_prod_box">
								<a href="details.jsp">product name</a>
								<div class="new_prod_bg">
									<span class="new_icon"><img src="images/promo_icon.gif"
											alt="" title="" /> </span>
									<a href="details.jsp"><img src="images/thumb3.gif" alt=""
											title="" class="thumb" border="0" /> </a>
								</div>
							</div>

						</div>

						<div class="right_box">

							<div class="title">
								<span class="title_icon"><img src="images/bullet5.gif"
										alt="" title="" /> </span>类别
							</div>

							<ul class="list">
								<li>
									<a href="#">网站建设</a>
								</li>
								<li>
									<a href="#">数据库类</a>
								</li>
								<li>
									<a href="#">图形图像</a>
								</li>
								<li>
									<a href="#">程序设计</a>
								</li>
								<li>
									<a href="#">现代办公</a>
								</li>
								<li>
									<a href="#">操作系统</a>
								</li>
								<li>
									<a href="#">考试认证</a>
								</li>
								<li>
									<a href="#">网络技术</a>
								</li>
								<li>
									<a href="#">软件工程</a>
								</li>
								<li>
									<a href="#">电脑刊物</a>
								</li>
								<li>
									<a href="#">文学作品</a>
								</li>
							</ul>

							<div class="title">
								<span class="title_icon"><img src="images/bullet6.gif"
										alt="" title="" /> </span>合伙人
							</div>

							<ul class="list">
								<li>
									<a href="#">新浪微博</a>
								</li>
								<li>
									<a href="#">腾讯QQ</a>
								</li>
								<li>
									<a href="#">网邮箱易</a>
								</li>
								<li>
									<a href="#">暴雪公司</a>
								</li>
								<li>
									<a href="#">当当网</a>
								</li>
								<li>
									<a href="#">京都商城</a>
								</li>
								<li>
									<a href="#">文博电脑公司</a>
								</li>
								<li>
									<a href="#">安博园区</a>
								</li>
								<li>
									<a href="#">云舟广告公司</a>
								</li>
							</ul>

						</div>

					</div>
					<!--end of right content-->




					<div class="clear"></div>
				</div>
				<!--end of center content-->


				<div class="footer">
					<div class="left_footer">
						<img src="images/footer_logo.gif" alt="" title="" />
						<br />
						<a href="http://csscreme.com"><img src="images/csscreme.gif"
								alt="by csscreme.com" title="by csscreme.com" border="0" /> </a>
					</div>
					<div class="right_footer">
						<a href="index.jsp">首页</a><a href="#"></a>
						<a href="about.jsp">关于我们</a><a href="#"></a>
						<a href="#">服务</a>
						<a href="#">私人权限</a>
						<a href="#">联系我们</a>

					</div>


				</div>


			</div>
	</body>
</html>