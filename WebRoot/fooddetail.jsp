<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="ordersys.db.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>FOOD DETAIL</title>
<!--fonts-->
<link
	href='http://fonts.useso.com/css?family=Lato:100,300,400,700,900,100italic,300italic,400italic,700italic,900italic'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Slabo+27px'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.useso.com/css?family=Roboto+Slab:400,100,300,700'
	rel='stylesheet' type='text/css'>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<!--//fonts-->
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/stylewaiter.css" rel="stylesheet" type="text/css" media="all" />
<!-- for-mobile-apps -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Favorites Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
		Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- //for-mobile-apps -->
<!-- js -->
<script type="text/javascript" src="js/jquery.min.js"></script>
<!-- js -->
<!-- cart -->
<script src="js/simpleCart.min.js">
	
</script>
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
<script type="text/javascript" src="ajax.js"></script>
<script type="text/javascript">
	function begin() {
		txtAjaxRequest("getbeicai?messageTitle=beicai&time=" + Math.random(),
				"get", true, null, showChef, null, null);
		txtAjaxRequest("getall?messageTitle=all&time=" + Math.random(),
				"get", true, null, showAdmin, null, null);
	}
	function showChef(responseTxt, obj) {
		var chatdiv = document.getElementById("msj");
		chatdiv.innerHTML = "";
		chatdiv.innerHTML += "<br>" + responseTxt;
		txtAjaxRequest("getbeicai?messageTitle=beicai&time=" + Math.random(),
				"get", true, null, showChef, null, null);
	}
	function showAdmin(responseTxt, obj) {
		var chatdiv = document.getElementById("msj");
		chatdiv.innerHTML = "";
		chatdiv.innerHTML += "<br>" + responseTxt;
		txtAjaxRequest("getall?messageTitle=all&time=" + Math.random(),
				"get", true, null, showAdmin, null, null);
	}
</script>
</head>
<body onload="begin()">
	<%
		String account = session.getAttribute("account").toString();
		String pic = session.getAttribute("pic").toString();
	%>
	<%
		request.setAttribute("page", new Integer(1));
	%>
	<!-- header -->
	<div class="header" style="height:90px;">
		<div class="container" style="height:90px;">
			<div class="top-header" style="height:90px;">
				<div class="header-left"></div>
				<div class="login-section">
					<ul>
						<li><%=account%></li> |
						<li><img alt="img" src="<%=pic%>" id="bpic"
							style="width=10px;height=10px;"></li>
					</ul>
				</div>
		<div id="msj" style="position:relative; left:300px; background:#FFFFFF; font-size:20px; width:700px;height:50px;text-align:center;line-height:16px;overflow:hidden;"></div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>

	<!-- //header -->
	<!-- banner -->
	<div class="banner-slider">
		<div class="banner-pos">
			<div class="banner one page-head">
				<div class="container">
					<div class="navigation text-center">
						<span class="menu"><img src="images/menu.png" alt="" /> </span>
						
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
										<span><img src="images/logo.png" alt="" /> </span>
									</div>
								</div>
							</div>
							<h1>FAVORITES</h1> </a>
					</div>
					<!-- //point burst circle -->

				</div>
			</div>
		</div>
	</div>

	<!-- //banner -->
	<!-- menu-page -->
	<div class="menu">
		<div class="container">
			<div class="menu-info">
				<h3>DISHES DETAIL HERE</h3>
				<div class="strip-line"></div>
			</div>
			<div class="our-menu-grids">
				<%
					FoodInfo f = (FoodInfo) session.getAttribute("thisfood");
					if (f != null) {
				%>

				<div class="order-top">
					<li class="im-g"><a href="#"><img src="<%=f.getBpic()%>"
							class="img-responsive" alt="no picture"> </a>
					</li>
					<li class="data">
						<h3>
							Price:<%=f.getPrice()%></h3>
						<h4>
							Name:<%=f.getName()%></h4> <%
 	}
 %>
						<form action="adddish" , method="post">
							<h4>
								Quantity :  <input type="text" name="dishnum"
									class="hvr-shutter-in-horizontal button">
							</h4>
							<h4>
								Table ID :  <input type="text" name="tableid"
									class="hvr-shutter-in-horizontal button">
							</h4>
					</li>
					<li class="bt-nn">
						<h4>
							<Button class="label label-danger" type="submit" name="button">ADD
								TO MENU</Button>
							</form>
						</h4>
					</li>
					<div class="clearfix"></div>
				</div>

			</div>
		</div>
	</div>
	
	<!-- //menu-page -->
	<!-- footer-top -->
	<div class="footer-top">
		<div class="container">
			<div class="col-md-3 footer-grid">
				<h3>SUMMER'S SECRETS</h3>
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
					<li class="list-two"><a href="mailto:SummerSecrets@163.com">(未实现)SummerSecrets@163.com</a>
					</li>
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
			<div class="footer-left"></div>
			<div class="footer-right">
				<ul>
					<li><a href="#" class="twitter"> </a>
					</li>
					<li><a href="#" class="facebook"> </a>
					</li>
					<li><a href="#" class="chrome"> </a>
					</li>
					<li><a href="#" class="pinterest"> </a>
					</li>
					<li><a href="#" class="linkedin"> </a>
					</li>
					<li><a href="#" class="dribbble"> </a>
					</li>
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
		id="toTopHover" style="opacity: 1;"> </span> </a>
	<!-- //smooth scrolling -->

</body>
</html>
