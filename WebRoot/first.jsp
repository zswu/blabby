<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Blabby</title>
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
		<!-- 第三段 -->
		<div class="MIB_blogbody">
			<div class="MIB_mblogbgr">
				<div class="MIB_mblogbgl">
					<div id="indexMainL" class="mainL MIB_600 MIB_txtal MIB_linkal">
						<div class="MIB_mbloghead">
							<div class="MIB_mblogbox">
								<div class="postWrap">
									<div class="pngBg">
										<div class="postOptionBg">
										</div>
										<div class="pngGuidBg">
										</div>
										<div class="pngAreaBg">
										</div>
									</div>
									<div class="connBg">
										<div id="publisher_info" class="wordNumBg">
											you can input
											<span class="pipsLim">140</span>words
										</div>
										<div class="postOptionBg">
											<div id="publisher_faces" class="OnTit">
												<img title="表情" class="small_icon faceicon"
													src="http://img.t.sramjs.cn/t35/style/images/common/transparent.gif" />
												<a tabindex="2" href="javascript:void(0)">face</a>
											</div>
											<div id="pop_{$smarty.const.POP_WARN_PICSET}" class="OnPic">
												<span poptype="1" pop="true" id="publisher_image"> <img
														title="picture" class="small_icon picnormal"
														src="http://img.t.sinajs.cn/t35/style/images/index/fbqimgclose.gif" /><a
													tabindex="3" href="####">picture</a> </span>
											</div>
											<div id="publisher_video" class="OnTit">
												<img class="small_icon videoicon" title="video"
													src="http://img.t.sinajs.cn/t35/style/images/common/transparent.gif" />
												<a tabindex="4" href="javascript:void(0);">video</a>
											</div>
											<div id="publisher_music" class="OnTit">
												<img class="small_icon musicico" title="music"
													src="http://img.t.sinajs.cn/t35/style/images/common/transparent.gif" />
												<a tabindex="5" href="javascript:void(0);">music</a>
											</div>
											<div id="publisher_topic" class="OnTit">
												<img class="small_icon askico" title="topic"
													src="http://img.t.sramjs.cn/t35/style/images/common/transparent.gif" />
												<a tabindex="6" href="javascript:void(0);">topic</a>
											</div>
											<div class="OnTit" id="publisher_vote">
												<img transparent.gif"="" common="" images=""
													src="http://img.t.sramjs.cn/t35/style"
													class="small_icon voteicon" title="vote" />
												<a tabindex="7" href="javascript:void(0);">vote</a>
											</div>
										</div>
										<div class="postBtnBg bgColorA_No">
											<a title="" tabindex="8" id="publisher_submit"
												href="${pageContext.request.contextPath }/PublishBlogServlet?content="
												onclick="getContent();"><span class="bgColorA"><span
													class="BackG"></span><span class="BackG1"></span><span
													class="BackG2"></span> </span><span class="bgColorB"></span> </a>
										</div>
										<div style="display:block" class="inputarea">
											<label style="display:none;" for="publish_editor">
												Message:
											</label>
											<textarea title="" id="content" rows="" cols=""
												name="content" accesskey="1" tabindex="1"
												style="font-family: Tahoma,宋体; border-style: solid; border-width: 0px; word-wrap: break-word; font-size: 14px; line-height: 18px; overflow-x: hidden;"
												range="0&amp;0"></textarea>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- 第四段 -->
						<div class="MIB_mbloglist">
							<div id="MIB_newFilter" class="newFilter MIB_txtal">
								<div class="nfTagB nfTagOn">
									<ul>
										<li class="current">
											<span _link="#">group</span>
										</li>
									</ul>
									<a href="#" title="收起" class="nftagOpen"><em>&lt;</em> </a>
									<div class="clear"></div>
								</div>
								<div style="" class="nfBox">
									<div id="filter_key_panel" class="nfBoxSim">
										<div class="left">
										</div>
										<div class="right">
											<div class="lf searchbg">
												<input type="text" class="input" value="search"
													id="filter_mkey_input" name="filter_search"
													style="color: rgb(153, 153, 153);" />
												<a href="#" class="searcheck" id="filter_mkey_btn">search</a>
											</div>
										</div>
										<div class="clear"></div>
									</div>
								</div>
								<div style="display: none;" class="nfresult MIB_linedot2">
									<div class="lf">
										search
										<span class="spetxt">93</span>messages
									</div>
									<div class="rt">
										<a href="#"><em>&lt;&lt;</em>return</a>
									</div>
								</div>
							</div>

							<!--微博列表开始-->
							<ul id="feed_list" class="MIB_feed">
								<c:if test="${empty newerBlogs}" var="emptyList">
									<li id="mid_5624700956921545466">
										<center>
											<span style="color: red;font-size: 10pt">no dongtai</span>
										</center>
									</li>
								</c:if>
								<c:if test="${!emptyList}">
									<c:forEach items="${newerBlogs}" var="blog">
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
																			transmit to my blabby
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
						</div>
						<!-- 第四段结束 -->
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
