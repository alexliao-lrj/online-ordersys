<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="ordersys.db.FoodInfo"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" type="text/css" href="a.css">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="ajax.js"></script>
<title>AllFOOD</title>
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
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
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

<script type="text/javascript">
function loadData(page) {
		xmlAjaxRequest("showFood?page=" + page + "&time=" + Math.random(),
				"get", true, null, showData, null, null);

	}
	function showResult(responseTxt, obj) {
	    // body...
	    alert("succeed");
	}

	function removeFood(id,pageIndex) {
		// body...
		var se=confirm("确定删除吗?");
		if (se == true) {
			xmlAjaxRequest(
				"removeFoodServlet?id=" + id + "&pageIndex=" + pageIndex + "&time=" + Math.random(),
				"get", true, null, showData, null, null);
		}
	}
	
	function addAsRecommendFood(id) {
		var se = confirm("确定添加为今日推荐菜品吗?");
		if (se == true) {
			txtAjaxRequest(
					"addAsRecommendFoodServlet?id=" + id  + "&time=" + Math.random(),
					"get", true, null, showResult, null, null);
		}
	}
	
function showData(responseXml, obj) {

		var body = document.getElementById("listbody");

		var page = responseXml.getElementsByTagName("page");
		var pageIndex = parseInt(page[0].childNodes[0].nodeValue);

		var preLink = document.getElementById("pre");
		var nextLink = document.getElementById("next");
	
		var foods = responseXml.getElementsByTagName("food");
		for ( var i = 0; i < foods.length; i++) {

			var food = foods[i];
			var id;
			var fname;
			var fdescription;
			var flag;
			var fprice;
			var bpic;

			var child = food.childNodes;
			for ( var j = 0; j < child.length; j++) {
				var element = child[j];
				if (element.nodeName == "id") {
					id = element.childNodes[0].nodeValue;
				} else if (element.nodeName == "name") {
					fname = element.childNodes[0].nodeValue;
				} else if (element.nodeName == "description") {
					fdescription = element.childNodes[0].nodeValue;
				} else if (element.nodeName == "flag"){
					flag = element.childNodes[0].nodeValue;
				} else if (element.nodeName == "price") {
					fprice = element.childNodes[0].nodeValue;
				} else if (element.nodeName == "bpic") {
					bpic = element.childNodes[0].nodeValue;
				}
			}

			
			var html = "";
			html += "<div class=\"order-top\">";
			html +=	"<li class=\"im-g\">" + "<a href=\"#\">" + "<img src=" + bpic + " " +
							"class=\"img-responsive\" alt=\"no picture\">" + "</a>";
			html +=	"</li>";
			html +=	"<li class=\"data\">";
			html +=	"<h3>" + id + "</h3>";
			html +=	"<h4>" + fname +"</h4>";
			html +=	"<p>" + fdescription + "</p>";
			html +=	"</li>";
			html +=	"<li class=\"bt-nn\">"
			html += "<h4>" + "<Button onclick=\"removeFood(" + id +"," + pageIndex + ")\">DELETE</Button></h4>";
			html += "<h4>" + "<a href=\"changeFoodServlet?id=" + id + "\">MODIFY</a></h4>";
			html += "<h4>" + "<Button onclick=\"addAsRecommendFood(" + id + ")\">ADD TO SPECIAL</Button></h4>";
			html +=	"</li>";
			html +=	"<div class=\"clearfix\">" + "</div>";
			html +=	"</div>";
			
			
			body.innerHTML += html;

		}
		
		preLink.href = "javascript:loadData(" + (pageIndex - 1) + ")";
		nextLink.href = "javascript:loadData(" + (pageIndex + 1) + ")";

		txtAjaxRequest("getjiezhang?messageTitle=jiezhang&time="
		+ Math.random(), "get", true, null, showChat, null, null);
	}

</script>


<script type="text/javascript" src="ajax.js"></script>
<script type="text/javascript">
	function showChat(responseTxt, obj) {
		var chatdiv = document.getElementById("msj");
		chatdiv.innerHTML = "";
		chatdiv.innerHTML += "<br>" + responseTxt;
		txtAjaxRequest("getjiezhang?messageTitle=jiezhang&time="
				+ Math.random(), "get", true, null, showChat, null, null);
	}
</script>
</head>
<body onload="loadData(1)">
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
							style="width=10px;height=10px;">
						</li>
					</ul>
				</div>
			<div
					style="position:relative; left:300px;top:-13px;width:300px;height:50px">
					<h4>
					<form name="pushmsg" method="post" action="pushall" target="targetFrame">
					<input type="text" name="msj"
							style="height:50px; border-right:#ffffff 0px solid; border-top:#ffffff 0px solid;font-size:20px;border-left:#ffffff 0px solid;border-bottom:#c0c0c0 1px solid;background-color:#ffffff">
					<input type="image" name="submit" value="confirm" src="/userhead/u1.jpg" >
					</form>
					<iframe width="0" height="0" style="display: none" name="targetFrame"></iframe>
						
					</h4>
				</div>

				<div id="msj" style="position:relative; left:600px; top:-42px; background:#FFFFFF; font-size:20px; width:500px;height:50px;text-align:center;line-height:16px;overflow:hidden;"></div>
				

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

				<h3>ALL DISHES HERE</h3>
				<div class="strip-line"></div>
			</div>
			<div class="our-menu-grids" id = "listbody">
				
				
			</div>
		</div>
	</div>
	<div id="cen">
		<ul id="pagination-clean">
			<li class="previous-off"><a id="pre">&lt;&lt;PREVIOUS</a>
			</li>
			<li class="next"><a id="next">NEXT&gt;&gt;</a>
			</li>
		</ul>
	</div>
	<br>
	<div id="cen1">
			<a class="hvr-shutter-in-horizontal button" href="addFood.jsp">ADD</a>
	</div>
	<br>
	<br>
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
		id="toTopHover" style="opacity: 1;"> </span> </a>
	<!-- //smooth scrolling -->

</body>
</html>
