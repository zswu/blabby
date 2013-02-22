<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>My message</title>
		<link rel="stylesheet" type="text/css" href="css/index.css" />
		<link rel="stylesheet" type="text/css" href="css/skin.css" />
		<script type="text/javascript" src="js/jquery-1.5.js"></script>
		<script type="text/javascript">
			function getContent(){
				var area=document.getElementById("content");
				var content=area.innerHTML;
				var hot=document.getElementById("publisher_submit");
				hot.href+=content;
				return true;
			}
			
			function displayComments(blogId){
				//显示评论列表
				var commentsListDiv=document.getElementById('commentsList_'+blogId);
				if(commentsListDiv.style.display=='none'){
					//使用ajax去后台，查询该围脖信息的评论列表
					commentsListDiv.style.display='block';
				}else{
					commentsListDiv.style.display='none';
				}
			}
			
			function loadComments(blogId){
				var url="/microBlog/FindCommentsList?blogId="+blogId+"&now="+new Date().getTime();
				$('#commentsList1_'+blogId).load(url);
				displayComments(blogId);
			}
			
			function publishComment(blogId){
				var url="/microBlog/PublishComment";
				var content=$('#commentArea_'+blogId).val();
				$('#commentArea_'+blogId).html('');
				$('#commentsList1_'+blogId).load(url,{'content':content,'blogId':blogId});
				
			}
		</script>
	</head>
	<body>
		<%@include file="common/head.jsp"%>
		<!-- 主题部分 -->
		<div id="profileMain" class="MIB_blogbody">
			<div class="MIB_mblogbgr ">
				<div class="MIB_mblogbgl">
					<!--右栏内容 -->
					<div class="mainR MIB_200 MIB_txtar MIB_linkar">
					</div>
					<!--/右栏内容 -->
					<!--左栏内容 -->
					<div id="profileL" class="mainL MIB_600 MIB_txtal MIB_linkal">
						<!-- 个人信息-->
						<div id="profileHead" class="MIB_mbloghead">
							<div id="profileHeadBg" class="MIB_mbloglist">
								<div class="other_headpic">
									<a href="#"><img title="jack_rose66" src="#" /> </a>
								</div>
								<div class="other_info">
									<div class="name MIB_txtal">
										<span class="lf">${user.nickName }</span>
									</div>
									<p>
										<a href="#"> <c:if test="${empty user.accessAddress}"
												var="emp">
												<span style="font-size: 10pt;color:red">address</span>
											</c:if> <c:if test="${!emp}">
												${user.accessAddress }
											</c:if> </a>
									</p>
									<div class="ad MIB_txtbl">
										<c:if test="${!empty user.address.province}">
											${user.address.province },${user.address.city }
										</c:if>
									</div>
									<p class="blog MIB_txtbl">
										message：
										<a target="_blank" href="#">http://blog.sram.com.cn/nickName</a>
									</p>
									<p class="MIB_txtbl">
										come on
										<a href="#">introduce yourself</a>！
									</p>
								</div>
							</div>
						</div>
						<!--/发布区 -->

						<!--内容区 -->
						<div id="profileFeed" class="MIB_mbloglist">
							<div class="newFilter MIB_txtal newFilterMb0">
								<div class="nfTagB nfTagOff">
									<ul>
										<li class="current">
											<span>message</span>
										</li>
										<li>
											<a href="#1">profile</a>
										</li>
									</ul>
									<div class="clear"></div>
								</div>
							</div>
							<!--筛选-->
							<div class="nfBox" style="">
								<div cashindex="1" class="nfBoxSim" id="filter_key_panel">
									<div class="right">
										<div class="lf searchbg">
											<form method="get" action="#" id="filter_key_form">
												<span><input type="text" value="搜索自己说的话"
														class="swidth" id="filter_key_input" name="filter_search"
														style="color: rgb(153, 153, 153);" /><a class="searcheck"
													href="#" id="filter_key_btn"><em>search</em> </a> </span>
											</form>
										</div>
									</div>
									<div class="clear"></div>
								</div>
								<!--/筛选-->
								<!--列表-->
								<ul id="feed_list" class="MIB_feed">
									<c:if test="${empty blogs}" var="emptyList">
										<li id="mid_5624700956921545466">
											<center>
												<span style="color: red;font-size: 10pt">no dongtai</span>
											</center>
										</li>
									</c:if>
									<c:if test="${!emptyList}">
										<c:forEach items="${blogs}" var="blog">
											<li id="mid_5624700956921545466" class="MIB_linedot_l">
												<!-- 显示图像 -->
												<div class="head_pic">
													<a href="#"> <img src="" /> </a>
												</div>
												<div class="MIB_feed_c">
													<!-- p标签显示微博内容 -->
													<p class="sms">
														<a href="#">${blog.user.nickName }</a>&nbsp;：${blog.content
														}
													</p>
													<div class="feed_att MIB_linkbl MIB_txtbl">
														<div class="lf">
															<cite><a href="#">${blog.publishTime }</a> </cite>
															<strong lang="CL1006">from</strong><cite><a
																target="_blank" href="#">blabby</a> </cite>
														</div>
														<div class="rt">
															<a href="#">transmit </a>
															<span class="MIB_line_l">|</span>
															<a href="#">collect </a>
															<span class="MIB_line_l">|</span>
															<a href="#"
																id="_comment_count_miniblog2_5624700956921545466"
																onclick="loadComments('${blog.id }');">comment(${blog.commentNum
																})</a>
														</div>
													</div>
													<!-- 评论列表 -->
													<div id="_comment_list_miniblog2_5624700956921545466">
														<div class="MIB_assign rt" id="commentsList_${blog.id }"
															style="display:none">
															<div class="MIB_asarrow_r"></div>
															<div class="MIB_assign_t"></div>
															<div class="MIB_assign_c MIB_txtbl">
																<div class="logininput new_position">
																	<textarea class="lf"
																		style="overflow: hidden; height: 18px; font-family: Tahoma,宋体; border-style: solid; border-width: 1px; word-wrap: break-word; font-size: 12px; line-height: 18px;"
																		id="commentArea_${blog.id }"></textarea>
																	<a href="javascript:void(0);"
																		onclick="publishComment('${blog.id }')"
																		id="_comment_post_miniblog2_5626984732938098378"
																		class="btn_normal"><em>comment</em> </a>
																	<div class="margin_b MIB_txtbl ml35">
																		<p>
																			<input type="checkbox" id="agree_5626984732938098378" />
																			<label for="agree_5626984732938098378">
																				transmit my blabby
																			</label>
																		</p>
																	</div>
																</div>
																<div id="commentsList1_${blog.id }">
																	<!-- 加载围脖评论 -->
																</div>
																<div class="MIB_assign_b"></div>
															</div>
														</div>
													</div>
											</li>
										</c:forEach>
									</c:if>
								</ul>
								<!--/列表-->
								<div style="" class="MIB_bobar">
									<!--翻页-->
									<form name="form" method="get" action="">
										<div id="feed_foot" class="fanye MIB_txtbl rt">
											<a id="paging_popup" href="#" class="btn_num btn_numWidth"><em>page 1</em>
											</a>
											<!--翻页浮层-->
											<!--/翻页浮层-->
											<a href="#" class="btn_num btn_numWidth"><em>next</em> </a>
										</div>
									</form>
									<!--翻页-->
								</div>
								<!--/内容区 -->
							</div>
							<!--/左栏内容 -->
						</div>
					</div>
				</div>
				<!--/正文-->
			</div>
	</body>
</html>
