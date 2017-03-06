<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>Register</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="ajax.js"></script>
<script type="text/javascript">
	function choice() {
		var obj = document.getElementById("job");
		var index = obj.selectedIndex;
		var value = obj.options[index].index;
		document.getElementById("s").value = value;
	}
	function upload() {
		var tag = document.getElementById("imagetag");
		tag.src = "userhead/loading.gif";
		txtAjaxRequest(
			"getuploadmsg?messageTitle=upload&time=" + Math.random(),
			"get", true, null, showImg, null, null);
		imgform.submit();
	}

	function showImg(responseTxt, obj) {
		var tag = document.getElementById("imagetag");
		tag.src = "userhead/" + responseTxt;
		document.getElementById("f").value = "userhead/" + responseTxt;

	}
</script>

<!--fonts-->
<link
	href='http://fonts.useso.com/css?family=Lato:100,300,400,700,900,100italic,300italic,400italic,700italic,900italic'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Slabo+27px'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.useso.com/css?family=Roboto+Slab:400,100,300,700'
	rel='stylesheet' type='text/css'>

<!--//fonts-->
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- for-mobile-apps -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Favorites Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
		Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript">addEventListener("load", function() {
		setTimeout(hideURLbar, 0);
	}, false);
	function hideURLbar() {
		window.scrollTo(0, 1);
	}
</script>
<!-- //for-mobile-apps -->
<!-- js -->
<script type="text/javascript" src="js/jquery.min.js"></script>
<!-- js -->
<!-- cart -->
<script src="js/simpleCart.min.js"> </script>
<!-- cart -->
<!-- start-smoth-scrolling -->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 1000);
		});
	});
</script>
<!-- start-smoth-scrolling -->
<style  type="text/css">
#box {
 width: 50%;
 height: 30px;
 right: 10px;
 margin-top: -34px;
 display: compact;
 position: relative;
 float: right;
}

</style>
</head>
<body>
	<!-- 导航栏 -->
	<div class="header">
		<div class="container">
			<div class="top-header">
				<div class="header-left"></div>
				<div class="login-section">
					<ul>
						<li><a href="login.jsp">Login</a></li> |
						<li><a href="reg.jsp">Register</a></li>
					</ul>
				</div>
				<!-- 搜索栏-->
				<div class="search-box">
					<div id="sb-search" class="sb-search">
						<form>
							<input class="sb-search-input"
								placeholder="(未实现)Enter your search item..." type="search"
								name="search" id="search"> <input
								class="sb-search-submit" type="submit" value=""> <span
								class="sb-icon-search"> </span>
						</form>
					</div>
				</div>

				<!-- 搜索栏动画 -->
				<script src="js/classie.js"></script>
				<script src="js/uisearch.js"></script>
				<script>
					new UISearch(document.getElementById('sb-search'));
				</script>
				<!-- //search-scripts -->

				<div class="header-right">
					<div class="cart box_1">
						<a href="checkout.html">
							<h3>
								<span class="simpleCart_total"> (未实现)$0.00 </span> (<span
									id="simpleCart_quantity" class="simpleCart_quantity"> 0
								</span> items)<img src="images/bag.png" alt="">
							</h3>
						</a>
						<p>
							<a href="javascript:;" class="simpleCart_empty">Empty cart</a>
						</p>
						<div class="clearfix"></div>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>


	<!-- 副导航栏 -->
	<div class="banner-slider">
		<div class="banner-pos">
			<div class="banner one page-head">
				<div class="container">
					<div class="navigation text-center">
						<span class="menu"><img src="images/menu.png" alt="" /></span>
						<ul class="nav1">
							<li><a href="index.jsp">HOME</a>
										</li>
										<li><a href="about.jsp">ABOUT</a>
										</li>
										<li><a href="notice.jsp">ALL DISHES</a>
										</li>
										<li><a href="notice.jsp">TODAY'S SPECIAL</a>
										<li><a href="contact.jsp">CONTACT</a>
										</li>
										<li><a href="">(未实现)MORE</a>
										</li>
							<div class="clearfix"></div>
						</ul>
						<!-- script for menu -->
						<script>
							$("span.menu").click(function() {
								$("ul.nav1").slideToggle(300, function() {
									// Animation complete.
								});
							});
						</script>
						<!-- //script for menu -->

					</div>
					<!-- point burst circle -->
					<div class="logo">
						<a href="index.jsp">
							<div class="burst-36 ">
								<div>
									<div>
										<span><img src="images/logo.png" alt="" /></span>
									</div>
								</div>
							</div>
							<h1>FAVORITES</h1>
						</a>
					</div>
					<!-- //point burst circle -->


				</div>
			</div>
		</div>
	</div>

	<!-- 注册表单 -->
	<div class="registration-form">
		<div class="container">
			<h3>Registration</h3>
			<div class="strip"></div>
			<div class="registration-grids">
				<div class="reg-form">
					<div class="reg">
						<%
							String token = UUID.randomUUID().toString();
							session.setAttribute("TOKEN", token);
						%>
						<p>Welcome, please enter the following details to continue.</p>
						<p>
							If you have previously registered with us, <a href="#">click
								here</a>
						</p>
						<form target="targetFrame" enctype="multipart/form-data"
							method="post" action="upload" name="imgform">
							<ul>
								<li class="text-info">Photo:</li>
								<li><input type="file" name="pic"><input
									type="button" value="上传头像" onclick="upload()"></li>
							</ul>
						</form>

						<iframe name="targetFrame" width="0" height="0"
							style="display: none;"></iframe>
						<ul>
							<li class="text-info">Preview：</li>
							<li><img alt="img" src="" id="imagetag"></li>
						</ul>
						<form method="post" action="reg">
							<ul>
								<li class="text-info">Name:</li>
								<li><input type="text" name="account"></li>
							</ul>
							<ul>
								<li class="text-info">Password:</li>
								<li><input type="password" name="password"></li>
							</ul>
							<ul>
								<li class="text-info">Phone:</li>
								<li><input type="text" name="phone"></li>
							</ul>
							<ul>
								<li class="text-info">Job</li>
								<li><select id="job" onchange="choice()">
										<option value="empty"></option>
										<option value="admin" class="text-info">administrator</option>
										<option value="waiter" class="text-info">waiter or
											waitress</option>
										<option value="cheif" class="text-info">cook</option>
								</select></li>
							</ul>
							<ul >
								<li class="text-info">Verification code:</li>
								<li><input type="text" name="val">
								<img id="box" alt="img" src="random" ></li>
							</ul>
							<ul>
								
								<li><input type="submit" value="REGISTER NOW"></li>
							</ul>
							<input type="hidden" name="flag" id="s"> <input
								type="hidden" name="bpic" id="f"> <input type="hidden"
								name="requestToken" value="<%=token%>">
							<p class="click">
								By clicking this button, you are agree to my <a href="#">(没内容)Policy
									Terms and Conditions.</a>
							</p>
						</form>
					</div>
				</div>
				<div class="reg-right">
					<h3>Who Can Register An Account</h3>
					<div class="strip"></div>
					<p>You can register as an administrator, a waiter(waitress) or
						a cook.</p>
					<h3 class="lorem">Start With An Account</h3>
					<div class="strip"></div>
					<p>You can get the recommendation dishes.</p>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!-- registration-form -->

	<!-- footer-top -->
	<div class="footer-top">
		<div class="container">
			<div class="col-md-3 footer-grid">
				<h3>
					SUMMER'S SECRETS
				</h3>
			</div>
			<div class="col-md-3 footer-grid">
				<h4>BUFFET</h4>
				<p>
					MONDAY - THURSDAY<span>7 : 00 - 21 : 00</span>
				</p>
			</div>
			<div class="col-md-3 footer-grid">
				<h4>ORDERS</h4>
				<p>
					MONDAY - SUNDAY<span>7 : 00 - 21 : 00</span>
				</p>
			</div>
			<div class="col-md-3 footer-grid">
				<h4>ADDRESS</h4>
				<ul>
					<li class="list-one">二基楼A203</li>
					<li class="list-two"><a href="mailto:SummerSecrets@163.com">(未实现)SummerSecrets@163.com</a></li>
					<li class="list-three">028-800823823</li>
				</ul>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<!-- //footer-top -->
	<!-- footer -->
	<div class="footer">
		<div class="container">
			<div class="footer-left">
			</div>
			<div class="footer-right">
				<ul>
					<li><a href="#" class="twitter"> </a></li>
					<li><a href="#" class="facebook"> </a></li>
					<li><a href="#" class="chrome"> </a></li>
					<li><a href="#" class="pinterest"> </a></li>
					<li><a href="#" class="linkedin"> </a></li>
					<li><a href="#" class="dribbble"> </a></li>
				</ul>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<!-- //footer -->
	<!-- smooth scrolling -->
	<script type="text/javascript">
		$(document).ready(function() {
			/*
				var defaults = {
				containerID: 'toTop', // fading element id
				containerHoverID: 'toTopHover', // fading element hover id
				scrollSpeed: 1200,
				easingType: 'linear' 
				};
			*/
			$().UItoTop({
				easingType : 'easeOutQuart'
			});
		});
	</script>
	<a href="#" id="toTop" style="display: block;"> <span
		id="toTopHover" style="opacity: 1;"> </span></a>
	<!-- //smooth scrolling -->

</body>
</html>
