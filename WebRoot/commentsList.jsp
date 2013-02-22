<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
	<ul class="PL_list oddline">
		<c:forEach items="${comments}" var="comment">
			<li class="MIB_linedot3">
				<!-- 头像显示 -->
				<img src="#" class="picborder_l lf" />
				<div class="txt">
					<!-- 评论显示 -->
					<div class="txtinfo">
						<a href="#">${comment.user.nickName } </a> ${comment.content }
						<span class="MIB_txtbl">(${comment.commentTime })</span>
					</div>
					<p class="MIB_more MIB_linkbl">
						<a class="lose" href="">reply</a>
					</p>
				</div>
				<span class="clear"></span>
			</li>
		</c:forEach>
	</ul>
</div>
