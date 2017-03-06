<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Login</title>
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
</head>

<body>
	<!-- 主导航栏 -->
	<!-- 登陆注册选项 -->
	<div class="header">
		<div class="container">
			<div class="top-header">
				<div class="login-section">
					<ul>
						<li><a href="login.jsp">Login</a></li> |
						<li><a href="reg.jsp">Register</a></li>
					</ul>
				</div>
				<!-- 搜索栏 -->
				<div class="search-box">
					<div id="sb-search" class="sb-search">
						<form>
							<input class="sb-search-input"
								placeholder="（未实现）Enter your search item..." type="search"
								name="search" id="search"> <input
								class="sb-search-submit" type="submit" value=""> <span
								class="sb-icon-search"> </span>
						</form>
					</div>
				</div>

				<!-- 搜索栏特效 -->
				<script src="js/classie.js"></script>
				<script src="js/uisearch.js"></script>
				<script>
					new UISearch(document.getElementById('sb-search'));
				</script>

				<!-- 购物车记账 -->
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
							<li><a href="index.jsp">HOME</a></li>
							<li><a href="about.jsp">ABOUT</a></li>
							<li><a href="notice.jsp">ALL DISHES</a></li>
							<li><a href="notice.jsp">TODAY'S SPECIAL</a>
							<li><a href="contact.jsp">CONTACT</a></li>
							<li><a href="">(未实现)MORE</a></li>
							<div class="clearfix"></div>
						</ul>
					</div>
					<!-- logo图标 -->
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
				</div>
			</div>
		</div>
	</div>


	<!-- 登陆页面 -->
	<div class="login">
		<div class="container">
			<div class="login-grids">
				<div class="col-md-6 log">
					<h3>LOGIN</h3>
					<div class="strip"></div>
					<p>Welcome, please enter the following to continue.</p>
					<p>
						If you have previously Login with us, <a href="#">click here</a>
					</p>
					<%
						String token = UUID.randomUUID().toString();
						session.setAttribute("LOGINTOKEN", token);
					%>
					<form method="post" action="login">
						<h5>User Name:</h5>
						<input type="text" name="account">
						<h5>Password:</h5>
						<input type="password" name="password"> <input
							type="submit" value="Log in"> <input type="hidden" name="requestToken"
							value="<%=token%>">
					</form>
					
					<a href="#">(未实现)Forgot Password ?</a>
				</div>
				
				<div class="col-md-6 login-right">
					<h3>NEW REGISTRATION</h3>
					<div class="strip"></div>
					<p>By creating an account with our store, you will be able to
						move through the checkout process faster, store multiple shipping
						addresses, view and track your orders in your account and more.</p>
					<a href="reg.jsp" class="hvr-shutter-in-horizontal button">CREATE
						AN ACCOUNT</a>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>


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