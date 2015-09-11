<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD Xjsp 1.0 Transitional//EN" "http://www.w3.org/TR/xjsp1/DTD/xjsp1-transitional.dtd">
<html xmlns=http://www.w3.org/1999/xhtml>
	<head>
		<meta http-equiv="Content-Type" content="text/jsp; charset=utf-8" />
		<title>Book Store</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/book_store/style.css" />
	</head>
	<body>
		<div id="wrap">

			<div class="header">
				<div class="logo">
					<a href="index.jsp"><img src="${pageContext.request.contextPath}/book_store/images/logo.gif" alt="" title=""
							border="0" />
					</a>
				</div>
				<div id="menu">
				  <ul>                                                                       
            <li class="selected"><a href="${pageContext.request.contextPath}/book_store/index.jsp">首页 </a></li>
            <li><a href="${pageContext.request.contextPath}/book_store/about.jsp">关于我们</a></li>
              <li><a href="${pageContext.request.contextPath}/FildAllProductServlet.action">书架</a></li>
            <li><a href="${pageContext.request.contextPath}/book_store/specials.jsp">特别书籍</a></li>
            <li><a href="${pageContext.request.contextPath}/book_store/myaccount.jsp">我的账户</a></li>
            <li><a href="${pageContext.request.contextPath}/book_store/register.jsp">注册</a></li>
              <li><a href="${pageContext.request.contextPath}/book_store/items.jsp">条款</a></li>
            <li><a href="${pageContext.request.contextPath}/book_store/contact.jsp">联系</a></li>
            </ul>
				</div>

				<div class="center_content">
					<div class="left_content">
						<div class="title">
							<span class="title_icon"><img src="${pageContext.request.contextPath}/book_store/images/bullet1.gif"
									alt="" title="" />
							</span>我的账户
						</div>

						<div class="feat_prod_box_details">
							<p class="details">
								
							</p>
														
							 <div align="justify">
																				<strong><font color="5B0106">网上购物条款及细则:
																				</font> </strong>
																				<br>
																				本条款适用于阁下对本网站之游漤及使用、通过本网站向协景国际/协景酒行(下称「本公司」)
																				购买货品或服务之交易。请在使用本网页之前仔细阅读本条款及细则。阁下使用本网页或其中任何部分，即表示阁下已阅读本条款及细则，并且接受和同意受本条款及细则约束。
							</div>
							<p align="justify">
																				法例规定不可售卖酒精类饮品予18岁以下的人士。任何顾客于网上订购酒精类饮品，该顾客须保证其已年满18岁或以上并有权合法购买上述货品。
																			</p>
																			<p align="justify">
																				本公司须尽一切努力确保网页上载列的货品价格、资料和大小均已更新。本公司保留权利可更改货品的价格而不作另行通知，所有订购须由本公司全权决定并根据存货供应而接受。如任何货品因缺货而未能提供予顾客，本公司有权提供相同种类及价格的货品以供替代。
																			</p>
																			<p align="justify">
																				<strong><font color="5B0106">登记</font> </strong>
																				<br>
																				阁下可能(在网上或以其他方式)须填写本公司登记或申请表格方可使用若干服务。本公司有权拒绝任何登记人仕之申请或终止任何已登记之户口，不作另行通知而无须提供任何理由。
																			</p>

																			<p align="justify">
																				如阁下所登记的资料有任何更改，需立即通知客户服务部以确保本公司能够将最新的信息带给阁下。
																			</p>
																			<p align="justify">
																				<font color="5B0106"><strong>货物损毁</strong> </font>
																				<br>
																				顾客于收取货品时必须检查所订购之货品是否有损毁。如发现货品损毁，请立即通知客户服务部安排更换货品。本公司有权拒绝更换已签收之货品。
																			</p>
																			<p align="justify">
																				<strong><font color="5B0106">退款保证</font> </strong>
																			</p>
																			<p align="justify">
																				当收货人士收取已订购之货品的同时，有责任检查该货品是否有损毁情况，包括所有玻璃物品及酒具。
																			</p>

																			<p align="justify">
																				若任何产品运送时损毁 或错误赴运
																				或未曾列于订购单上本公司有权按情况选择退回受影响货款，安排换货或补回正确产品。
																			</p>
																			<p align="justify">
																				所有网上货品图片及资料只作参考，顾客不能要求退回货品或要求退款因为货品与网上图片不一致或称资料不符。
																			</p>
																			<p align="justify">
																				顾客必须出示有效的收据作退款，同时任何葡萄酒/香槟价格超过$750元(每支)或超过10年酒龄皆不设退款。
																			</p>
																			<p align="justify">
																				订购任何玻璃物品或酒具，收货人确认收取货品后将不能退换。
																			</p>
																			<p align="justify">
																				顾客如需要退货或退款，必须于送货日起3日内致电客户服务热线+852 2856-0308与客户服务员联络。
																			</p>
																			<p align="justify">
																				<font color="5B0106"><strong>货品拥有权</strong> </font>
																				<br>

																				货品的所有风险在货品送抵后转移给顾客，但协景酒行保留该等货品的所有权，直至有关货品的价款结清为止。协景酒行有权取回未能付款的货品，协景酒行保留所有因未能付款引致的损失的追究权。
																			</p>
																			<p align="justify">
																				<font color="5B0106"><strong>不可抗力</strong> </font>
																				<br>
																				本公司倘若由于不可抗力的原因(包括由于天灾、火灾、水灾、意外、暴乱、战争、政府政策、罢工或任何本公司不能控制的情况)而未能准确地提供阁下所需的服务，本公司均不会向使用者或任何第三者承担任何责任。
																			</p>
																			<p align="justify">
																				<strong><font color="5B0106">付款方法:</font> </strong>
																			</p>
																			<p align="justify">
																				所有货品之价值均以港币计算。
																			</p>
																			<p align="justify">
																				所有以信用卡、 万事达卡和美国运通卡皆以PayPal 系统结算。
																			</p>

																			<p align="justify">
																				所有网上订购的货品须由协景酒行全权决定并根据存货供应而接受订购。顾客将于收取货品的同时收到最终的购物收据以作记录。
																			</p>
																			<p align="justify">
																				协景酒行保留权利就任何销售设立限制，包括有权拒绝将货品销售予转售货品的人士。
																			</p>
																			<p align="justify">
																				协景酒行保留权利于任何时间更改网上购物的购买条款方式而不作另行通知。
																			</p>
																			<p align="justify">
																				货品的所有风险在货品送抵后转移给顾客，但协景酒行保留该等货品的所有权，直至有关货品的价款结清为止。
																			</p>
																			<p align="justify">
																				<font color="5B0106"><strong>送货服务：</strong> </font>
																			</p>
																			<p align="justify">
																				凡购物满HK$1,200或以上，即可享免费送货服务。惠顾低于HK$1,200，送货服务费亦只需HK$100。
																			</p>

																			<p align="justify">
																				送货服务仅限于香港特别行政区的香港岛、九龙及新界，送货服务不适用于附属岛屿，珀丽湾，愉景湾。
																				东涌及赤鱲角将有额外HKD200.- 收费•
																			</p>
																			<p align="justify">
																				送货时间为星期一至星期六早上10时至晚上6时 (公众假期例外)。
																			</p>
																			<p align="justify">
																				送货服务或未能提供，因为送货合理范围内并无合法的卸货区或没有升降机设备。
																			</p>
																			<p align="justify">
																				当八号烈风悬挂或黑色暴雨警告生效时，送货服务时间或会推迟，本公司将会联络顾客更改送货日期。
																			</p>
																			<p align="justify">
																				如收货人士未能于约定时间内收取已订购之货品，本公司将保留权利向该顾客收取额外的送货费用。
																			</p>
																			<p align="justify">
																				送货地址如有任何变更，顾客有责任即时通知本公司。
																			</p>

																			<p align="justify">
																				协景酒行保留权利可绝对酌情决定拒绝向任何顾客送付货品。
																			</p>
																			<p align="justify">
																				本条款将按照中华人民共和国香港特别行政区(「香港」)法律诠释及受香港法律所管辖。而本条款每个部分的应用仅限于该法律所允许的程度。若本条款内有任何部分在任何司法管辖区被禁止或无法执行，只有被禁止或无法执行的部分在该司法管辖区宣告无效，但不会使本条款其余部分无效或该部分在其他司法管辖区的有效性、合法性或可执行性均不会受到影响。协景酒行保留权利，可按其绝对酌情权修改本条款及条件。如有任何争议，协景酒行保留最终的决定权。本中文译本仅供参考之用，倘若中、英文本之文义有异，应以英文原文为准。
																			</p>
							
							
						</div>
						<div class="clear"></div>
					</div>
					<!--end of left content-->

					<div class="right_content">
						<div class="languages_box">
							<span class="red">语言:</span>
							<a href="#" class="selected"><img src="${pageContext.request.contextPath}/book_store/images/gb.gif" alt=""
									title="" border="0" />
							</a>
							<a href="#"><img src="${pageContext.request.contextPath}/book_store/images/fr.gif" alt="" title=""
									border="0" />
							</a>
							<a href="#"><img src="${pageContext.request.contextPath}/book_store/images/de.gif" alt="" title=""
									border="0" />
							</a>
						</div>
						<div class="currency">
							<span class="red">货币: </span>
							<a href="#">GBP</a>
							<a href="#">EUR</a>
							<a href="#" class="selected">USD</a>
						</div>

						<div class="cart">
							<c:if test="${(empty sessionScope.user)}">
								<form action="${pageContext.request.contextPath}/LoginServlet.action"
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
											<a href="${pageContext.request.contextPath}/LogoutServlet.action">注销</a>
										</td>
									</tr>
								</table>
								<table>
									<tr>
										<td>
											<span class="title_icon"><img src="${pageContext.request.contextPath}/book_store/images/cart.gif"
													alt="" title="" />
											</span>购物车
										</td>
										<td>
											${sessionScope.cart.items} x items |
											<span class="red">TOTAL: ${sessionScope.cart.money}$</span>
										</td>
										<td>
											<a href="${pageContext.request.contextPath}/book_store/cart.jsp" class="view_cart">进入购物车</a>
										</td>
									</tr>
								</table>

							</c:if>

						</div>

						<div class="title">
							<span class="title_icon"><img src="${pageContext.request.contextPath}/book_store/images/bullet3.gif"
									alt="" title="" />
							</span>书城介绍 Story
						</div>
						<div class="about">
							<p>
								<img src="${pageContext.request.contextPath}/book_store/images/about.gif" alt="" title="" class="right" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我们书店是全较最大的综合性中文网上购物商城，由国内著名出版机构科文公司、美国
								当当网 老虎基金、美国IDG集团、卢森堡剑桥集团、亚洲创业投资基金（原名软银中国创业基金）共同投资成立。
							</p>

						</div>

    <div class="right_box">
             
             	<div class="title"><span class="title_icon"><img src="${pageContext.request.contextPath}/book_store/images/bullet4.gif" alt="" title="" /></span>促销</div> 
                    <div class="new_prod_box">
                        <a href="${pageContext.request.contextPath}/FindProductDetails.action?productId=${sessionScope.all_product1.productId}">${all_product1.name}</a>
                        <div class="new_prod_bg">
                        <span class="new_icon"><img src="${pageContext.request.contextPath}/book_store/images/promo_icon.gif" alt="" title="" /></span>
                        <a href="${pageContext.request.contextPath}/FindProductDetails.action?productId=${sessionScope.all_product1.productId}"><img src="${pageContext.request.contextPath}/book_store/${sessionScope.all_product1.images}" alt="" title="" class="thumb" border="0" /></a>
                        </div>           
                    </div>
                    
                    <div class="new_prod_box">
                        <a href="${pageContext.request.contextPath}/FindProductDetails.action?productId=${sessionScope.all_product2.productId}">${all_product2.name}</a>
                        <div class="new_prod_bg">
                        <span class="new_icon"><img src="${pageContext.request.contextPath}/book_store/images/promo_icon.gif" alt="" title="" /></span>
                        <a href="${pageContext.request.contextPath}/FindProductDetails.action?productId=${sessionScope.all_product2.productId}"><img src="${pageContext.request.contextPath}/book_store/${sessionScope.all_product2.images}" alt="" title="" class="thumb" border="0" /></a>
                        </div>           
                    </div>                   
                    
                    <div class="new_prod_box">
                        <a href="${pageContext.request.contextPath}/FindProductDetails.action?productId=${sessionScope.all_product3.productId}">${all_product3.name}</a>
                        <div class="new_prod_bg">
                        <span class="new_icon"><img src="${pageContext.request.contextPath}/book_store/images/promo_icon.gif" alt="" title="" /></span>
                        <a href="${pageContext.request.contextPath}/FindProductDetails.action?productId=${sessionScope.all_product3.productId}"><img src="${pageContext.request.contextPath}/book_store/${sessionScope.all_product3.images}" alt="" title="" class="thumb" border="0" /></a>
                        </div>           
                    </div>             
             
             </div>

						<div class="right_box">

							<div class="title">
								<span class="title_icon"><img src="${pageContext.request.contextPath}/book_store/images/bullet5.gif"
										alt="" title="" />
								</span>类别
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
								<span class="title_icon"><img src="${pageContext.request.contextPath}/book_store/images/bullet6.gif"
										alt="" title="" />
								</span>合伙人
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
						<img src="${pageContext.request.contextPath}/book_store/images/footer_logo.gif" alt="" title="" />
						<br />
						<a href="http://csscreme.com"><img src="${pageContext.request.contextPath}/book_store/images/csscreme.gif"
								alt="by csscreme.com" title="by csscreme.com" border="0" />
						</a>
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