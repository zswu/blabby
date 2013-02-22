<%@page contentType="text/html;charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Register</title>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
			function clearNickName(){
				var nickNameInput=document.getElementById("nickName");
				if(nickNameInput.value=='your nick name'){
					nickNameInput.value='';
				}
			}
			function displayNickName(){
				var nickNameInput=document.getElementById("nickName");	
				if(nickNameInput.value==''){
					nickNameInput.value='your nick name';
				}
			}
		</script>
	</head>

	<body>
		<div id="wrap">
			<div class="head">
				<!--
    <div class="logo"></div>
	-->
				<div class="logotxt">
					register
				</div>
				<div class="headlink">
					<a href="#">Home</a> |
					<a href="#">Help</a>
				</div>
				<div class="clearit"></div>
			</div>

			<div class="main">
				<form method="post" id="vForm" name="vForm"
					action="${pageContext.request.contextPath }/RegistServlet">
					<input id="act" name="act" value="1" type="hidden" />
					<input id="entry" name="entry" value="miniblog" type="hidden" />
					<input id="r" name="reference" value="" type="hidden" />
					<div class="logoicon"></div>
					<div class="main_top">
						<p class="notice link">
							<span class="fb">note：</span>already have an account
							<a href="${pageContext.request.contextPath }/login.jsp">login</a>
						</p>
						<p class="title_note red"></p>
					</div>
					<div class="main_cen">
						<p class="title">
							email
						</p>
						<ul class="maintable">
							<li>
								<div class="mt_l">
									<span class="red">*</span>email address：
								</div>
								<div class="mt_r">
									<div class="inputbox">
										<span class="input  inputGreen"><cite> <input
													id="email" name="email" maxlength="64" type="text" value="" />
										</cite> </span>
									</div>
									
									<span id="emailtip" style="display: none;"><span><span
											class="inputacc link"><span class="error"></span><span
												class="red">email address</span> </span> </span> </span>
									<div class="inputtxt zi_9">
										your email is your account 
									</div>
								</div>
							</li>
							<li>
								<div class="mt_l">
									<span class="red">*</span>Password：
								</div>
								<div class="mt_r">
									<div class="inputbox">
										<span class="input  inputGreen"> <cite> <input
													id="password" name="password" value="" type="password" />
										</cite> </span>
									</div>
									
									<span id="passwordtip" style="display: none;"><span><span
											class="inputacc link"><span class="error"></span><span
												class="red">password</span> </span> </span> </span>
									
								</div>
							</li>
							<li>
								<div class="mt_l">
									<span class="red">*</span>password：
								</div>
								<div class="mt_r">
									<div class="inputbox">
										<span class="input  inputGreen"><cite><input
													id="password2" name="password2" value="" type="password" />
										</cite> </span>
									</div>
									<span id="password2tip" style="display: none;"><span><span
											class="inputacc link"><span class="error"></span><span
												class="red">password again</span> </span> </span> </span>
								</div>
							</li>
							<!--add info 添加用户注册信息-->
							<!--add end-->
						</ul>
						<p class="title">
							nick name
						</p>
						<ul class="maintable">
							<li>
								<div class="mt_l">
									<span class="red">*</span>nick name：
								</div>
								<div class="mt_r">
									<div class="inputbox">
										<span class="input"><cite> <input
													style="color: rgb(153, 153, 153);" id="nickName"
													name="nickName" maxlength="10" value="nick name" type="text"
													onfocus="clearNickName();" onblur="displayNickName();" /> </cite>
										</span>
									</div>
									<span id="door_img" style="display:none;"> <span></span>
									</span>
									<span id="doortip"></span>
								</div>

							</li>
							
							<li>
								<div class="mt_l"></div>
								<div class="mt_r">
									<input class="btn_submit" value="submit" type="submit" />
								</div>
							</li>
						</ul>

					</div>
					<div class="main_bottom"></div>
				</form>
			</div>

			
		</div>




		<div style="position: absolute; display: none; z-index: 9999;"
			id="livemargins_control">
			<img src="signupmail.php_files/monitor-background-horizontal.png"
				style="position: absolute; left: -77px; top: -5px;" height="5"
				width="77" />
			<img src="signupmail.php_files/monitor-background-vertical.png"
				style="position: absolute; left: 0pt; top: -5px;" />
			<img id="monitor-play-button"
				src="signupmail.php_files/monitor-play-button.png"
				onmouseover="this.style.opacity=1"
				onmouseout="this.style.opacity=0.5"
				style="position: absolute; left: 1px; top: 0pt; opacity: 0.5; cursor: pointer;" />
		</div>
	</body>
</html>
