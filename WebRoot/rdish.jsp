<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="ordersys.db.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>Waiter</title>
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
<link href="css/stylewaiter.css" rel="stylesheet" type="text/css" media="all" />
<!-- for-mobile-apps -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Favorites Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
		Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">-->
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
							style="width=10px;height=10px;">
						</li>
					</ul>
				</div>
				<div id="msj" style="position:relative; left:300px; background:#FFFFFF; font-size:20px; width:700px;height:50px;text-align:center;line-height:16px;overflow:hidden;"></div>
				
				<div class="clearfix"></div>
			</div>
		</div>
	</div>

	<!-- //header -->
	<div class="copyrights"></div>
	<!-- banner -->
	<div class="banner-slider">
		<div class="banner-pos">
			<!-- responsiveslides -->
			<script src="js/responsiveslides.min.js"></script>
			<script>
				// You can also use "$(window).load(function() {"
				$(function() {
					// Slideshow 4
					$("#slider3").responsiveSlides(
							{
								auto : true,
								pager : false,
								nav : false,
								speed : 500,
								namespace : "callbacks",
								before : function() {
									$('.events').append(
											"<li>before event fired.</li>");
								},
								after : function() {
									$('.events').append(
											"<li>after event fired.</li>");
								}
							});
				});
			</script>
			<!-- responsiveslides -->
			<div id="top" class="callbacks_container">
				<ul class="rslides" id="slider3">
					<li>
						<div class="banner one">
							<div class="container">
								<div class="navigation text-center">
									<span class="menu"><img src="images/menu.png" alt="" />
									</span>
								


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
										</a>
								</div>
								<!-- //point burst circle -->

<style>
     #alertify{
	width:313px;
	height:131px
	}
    #container{
        width: 75%;
        border-radius: 0.5ex;
        margin: 10px auto;
        padding-left: 20px;
    }
    fieldset{
       position:relative;
    }
    legend{
      padding-left:1%;
    }
    h2{
        display:inline;
        font-weight: bold;
    }
    #left{
        position:relative;
        float:left;
        width: 55%;
        height: 200px;
        border-radius: 0.5ex;
        margin-right: 10%;
    }
    #right{
    float:left;
    width:30%;
    height: 200px;
    border-radius:0.5ex;
    text-align:center;
    }
    table{
      position:absolute;
      left:5%;
      border-radius:0.5em;
    }
    td.gxt{
        border: 0px;
        text-align:center;
        background-color:#F8F8FF;
        border-radius:0.5ex;
        border:1px solid white;
        color:black;
        font-size:100%;
    }
    td.lcx{
        border: 0px;
        width:30%;
        text-align:center;
        background-color:#F1f1f1;
        border-radius:0.5ex;
        border:1px solid white;
        color:black;
        font-size:150%;
    }
    
     </style>

									<br><br>
									<div id="container">
									<legend><h2>Today's Special</h2></legend>
            <% 
            	ArrayList<FoodInfo> flist = (ArrayList<FoodInfo>)session.getAttribute("reclist");
    										if(flist != null){
    											for(FoodInfo f: flist){
            	
            %>
            <fieldset>
               
                <div id="left">
                    <table class="table table-hover">
    				<tr><td class="lcx">Special.ID</td>
       				 	<td class="gxt"><%=f.getId()%></td></tr>
    				<tr><td class="lcx">Special.Name</td>
    					<td class="gxt"><%=f.getName()%></td></tr>
    				<tr><td class="lcx">What's New</td>
        				<td class="gxt"><%=f.getDescription()%></td></tr>
    				</table>
                </div>
                
                <div id="right">
                <% if(f.getBpic()!=null && f.getBpic()!=""){ %>
                			<img src="<%=f.getBpic()%>" width="140px" height="140px"/><br/>
                		<%}else{ %>
                    		<img src="<%=request.getContextPath()%>/image/bookpic/bz.jpg" width="140px" height="140px"/><br/>
                		<%} %>
                </div>
                
   			</fieldset>
   <%}} %>
									
								
							</div>
						</div></li>
				</ul>
			</div>
		</div>
		<div class="clearfix"></div>
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
						<li class="list-two"><a href="mailto:SummerSecrets@163.com">SummerSecrets@163.com</a>
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
 
 <!--  
  <body>
    This is ur recommend. From Recdish servlet<br>
    <table>
    <thead>
    <th>recommend</th>
    </thead>
    <tbody>
    
    
    </tbody>
    </table>
  </body>
</html>
--> 
