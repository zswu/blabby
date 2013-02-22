<%@page contentType="text/html;charset=utf-8"%>
<div id="WB_box_1310109129935" class="tsina_gnbarea">
	<div class="bg_gnbarea">
		&nbsp;
	</div>
	<div class="tsina_gnb" id="WB_weiboTop_1310109129935">
		<ul class="gnb_l">
			<li>
				<a href="">message</a>
			</li>
			<li>
				<a class="nohover" id="WB_square_1310109129935" href="">discovery<span
					class="arr_d"><em class="b1">&nbsp;</em><em class="b2">&nbsp;</em><em
						class="b3">&nbsp;</em> </span> </a>
			</li>
			<li>
				<a href="">group</a>
			</li>
			<li>
				<a class="nohover" id="WB_application_1310109129935" href="#">application<span
					class="arr_d"><em class="b1">&nbsp;</em><em class="b2">&nbsp;</em><em
						class="b3">&nbsp;</em> </span> </a>
			</li>
			<li>
				<a href="#">telephone</a>
			</li>
			<li>
				<a href="#">search</a>
			</li>
		</ul>
		<ul class="gnb_r">
			<li>
				<a id="WB_name_span_1310109129935" href="#">${userInfo.nickName }</a>
			</li>
			<li>
				<a href="#">tool</a>
			</li>
			<li>
				<a href="#">messages</a>
			</li>
			<li>
				<a href="#">inform</a>
			</li>
			<li>
				<a href="#">account</a>
			</li>
			<li class="line">
				|
			</li>
			<li>
				<a href="#" id="WB_logout_1310109129935">exit</a>
			</li>
		</ul>
	</div>
</div>


<!-- 第二段 -->
<div class="header">
	<div class="logo">
		<p>
			&nbsp;
		</p>
		<p>
			&nbsp;
		</p>
		<span style="font-style: italic;font-size: 30px;color: #BBD783"><b>Blabby</b>
		</span>
	</div>

	<div class="head_menu">
		<span class="menu_l">&nbsp;</span>
		<div class="menu_c">
			<div class="bg_menu_c">
				&nbsp;
			</div>
			<ul>
				<li class="cur">
					<a href="#">Home</a>
				</li>
				<li class="line">
					|
				</li>
				<li>
					<a href="/microBlog/MyBlogServlet?userId=${userInfo.id }">Message</a>
				</li>
				<li class="line">
					|
				</li>
				<li>
					<a href="#">friend</a>
				</li>
				<li class="line">
					|
				</li>
			</ul>
			<div id="m_search" class="search">
				<input type="text" title="search" id="m_keyword" />
				<a id="m_submit" href="#" class="submit">search</a>
			</div>
		</div>
		<span class="menu_r">&nbsp;</span>
	</div>
</div>
