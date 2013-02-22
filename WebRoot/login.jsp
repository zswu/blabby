<%@page contentType="text/html;charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Login</title>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<link href="css/cardtips.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div id="wrap">
			<div class="head">
				<!--
		<div class="logo"></div>
		-->
				<div class="logotxt">
					login
				</div>
				<div class="headlink">
					<a href="#">Home</a> |
					<a href="#">Help</a>
				</div>
				<div class="clearit"></div>
			</div>
			<div class="main">
				<form method="post" id="vForm" name="vForm"
					action="${pageContext.request.contextPath }/LoginServlet">
					<div class="logoicon"></div>
					<div class="main_top">
						<div class="title_note1 red link" id="login_err"></div>
					</div>
					<div class="main_cen">
						<br />
						<br />
						<p class="title1">
							Login   
							<center><span style="font-size: 10pt;color: red">${msg }</span></center>
						</p>
						<ul class="mainlogin">
							<li>
								<div class="ml_l f14">
									Name:
								</div>
								<div class="ml_r">
									<div class="inputbox">
										<span class="input"> <cite> <input id="email"
													name="email" tabindex="0" maxlength="64" type="text" /> </cite> </span>
									</div>
									<span class="inputacc link inputacc_signin">
										<a href="${pageContext.request.contextPath }/regist.jsp">Register</a>
									</span>
									<span class="red_ero link inputacc_signin" id="usernametip"></span>
								</div>
							</li>

							<li>
								<div class="ml_l f14">
									password：
								</div>
								<div class="ml_r">
									<div class="inputbox">
										<span class="input"><cite> <input id="password"
													name="password" tabindex="1" maxlength="32" value=""
													type="password" /> </cite> </span>
									</div>
									<span class="inputacc link inputacc_signin"><a href="#">retrieve password</a>
									</span>
									<span class="red_ero link inputacc_signin" id="passwordtip"></span>
								</div>
							</li>
							<li>
								<div class="ml_l"></div>
								<div class="ml_r">
									<div class="inputacc">
										<input id="remLoginName" tabindex="3" name="remLoginName"
											type="checkbox" />
										<label for="remLoginName">
											remember password
										</label>
										<input id="safe_login" tabindex="4" name="safe_login"
											value="1" checked="checked" style="margin-left: 12px;"
											type="checkbox" />
										<label for="safe_login">
											safe login
										</label>
									</div>
								</div>
							</li>
							<li>
								<div class="ml_l"></div>
								<div class="ml_r">
									<input tabindex="5" class="btn_submit_m" value="login"
										type="submit" />
								</div>
								<div class="clearit"></div>
							</li>
						</ul>
					</div>
					<div class="main_bottom"></div>
				</form>
			</div>
			
		</div>

		<div style="position: absolute; display: none; z-index: 9999;"
			id="livemargins_control">
			<img src="signin.php_files/monitor-background-horizontal.png"
				style="position: absolute; left: -77px; top: -5px;" height="5"
				width="77" />
			<img src="signin.php_files/monitor-background-vertical.png"
				style="position: absolute; left: 0pt; top: -5px;" />
			<img id="monitor-play-button"
				src="signin.php_files/monitor-play-button.png"
				onmouseover="this.style.opacity=1"
				onmouseout="this.style.opacity=0.5"
				style="position: absolute; left: 1px; top: 0pt; opacity: 0.5; cursor: pointer;" />
		</div>
		<div>
			<ul class="passCard" id="sramNote" style="display:none;"></ul>
		</div>
	</body>
</html>
