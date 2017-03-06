<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>About</title>
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

</head>
<body>
	<%
		//String account = session.getAttribute("account").toString();
		//String pic = session.getAttribute("pic").toString();
	%>
	<!-- header -->
	<div class="header">
		<div class="container">
			<div class="top-header">
				<div class="header-left"></div>
				<div class="login-section">
					<ul>
						<li>About Us</li>
					</ul>
				</div>
				<!-- start search-->
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
				<!-- search-scripts -->
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
								<span class="simpleCart_total"> $0.00 </span> (<span
									id="simpleCart_quantity" class="simpleCart_quantity"> 0
								</span> items)<img src="images/bag.png" alt="">
							</h3> </a>
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

								<div class="banner-info text-center">
									<p>Summer Secrets : Bon Appetit !</p>

								</div>
							</div>
						</div>
					</li>
					<li>
				</ul>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
	<!-- //banner -->
	<!-- about -->
	<div class="delicious">
		<div class="container">
			<div class="delicious-info">
				<h3>CoFounders</h3>
				<div class="strip-line"></div>
			</div>
			<div class="delicious-grids">
				<div class="col-md-3 delicious-grid">
					<h3>imcch</h3>
					<img src="images/mp.jpg" alt="" />
					<h3>Admin</h3>
					<br>
					<p>Name: 陈超豪</p>
					<p>ID: 2014141463004</p>

				</div>
				<div class="col-md-3 delicious-grid">
					<h3>imly</h3>
					<img src="images/mp.jpg" alt="" />
					<h3>Chef</h3>
					<br>
					<p>Name: 来旖</p>
					<p>ID: 2014141463077</p>

				</div>
				<div class="col-md-3 delicious-grid">
					<h3>imlrj</h3>
					<img src="images/mp.jpg" alt="" />
					<h3>Waiter</h3>
					<br>
					<p>Name: 廖若佳</p>
					<p>ID: 2014141463108</p>

				</div>
				<div class="col-md-3 delicious-grid">
					<h3>xpx</h3>
					<img src="images/mp.jpg" alt="" />
					<h3>UI Designer</h3>
					<br>
					<p>Name: 夏培萱</p>
					<p>ID: 2014141463218</p>

				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!-- //about -->
	<!-- welcome -->
	<div class="welcome">
		<div class="container">
			<div class="wel-info">
				<h3>WELCOME</h3>
				<div class="strip-line"></div>
				<p>Welcome the summer's secrets restaurant. Wish you a good
					Meal！</p>
			</div>
			<div class="welcome-grids">
				<div class="col-md-4 welcome-grid-img">
					<img src="images/pic10.jpg" alt="" />
					<div class="wel-pos">
						<h4>FRUIT SALAD</h4>
					</div>
				</div>
				<div class="col-md-4 welcome-grid">
					<div class="welcome-gd second">
						<h4>Supper Friut</h4>
						<p>Superfruit is a marketing term first used in the food and
							beverage industry in 2004. Europe or the United States by
							regulatory authorities such as the U.S. Food and Drug
							Administration or U.S. Department of Agriculture. The designation
							of a fruit as a superfruit</p>
					</div>
				</div>
				<div class="col-md-4 welcome-grid-img">
					<img src="images/pic11.jpg" alt="" />
					<div class="wel-pos">
						<h4>CARROT EGG</h4>
					</div>
				</div>
				<div class="col-md-4 welcome-grid">
					<div class="welcome-gd second">
						<h4>COBB SALAD</h4>
						<p>The Cobb salad is a main-dish American garden salad made
							from chopped salad greens (iceberg lettuce, watercress, endives
							and Romaine lettuce), tomato, crisp bacon, boiled, grilled or
							roasted (but not fried) chicken</p>
					</div>
				</div>
				<div class="col-md-4 welcome-grid-img">
					<img src="images/pic2.jpg" alt="" />
					<div class="wel-pos">
						<h4>SPECIAL PRAWNS</h4>
					</div>
				</div>
				<div class="col-md-4 welcome-grid">
					<div class="welcome-gd second">
						<h4>WEET-BIX</h4>
						<p>Weet-Bix, the breakfast cereal, was developed by Bennison
							Osborne in Sydney, Australia, in the mid-1920s. Osborne set out
							to make a product more palatable than "Granose," a biscuit that
							was marketed by the Sanitarium Health Food Company at that time</p>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!-- //welcome -->
	<!-- good -->
	<div class="good">
		<div class="container">
			<div class="good-info">
				<h3>FINE DESERTS</h3>
				<div class="strip-line"></div>
			</div>
			<div class="good-grids">
				<div class="col-md-5 good-right">
					<img src="images/pic12.jpg" alt="" />
					<div class="desc">
						<p>DESERT</p>
					</div>
				</div>
				<div class="col-md-7 good-left">
					<h3>GOOD FOOD KEEPS YOU HEALTHY</h3>
					<div class="strip"></div>
					<p>
						The word "dessert" originated from the French word desservir,
						meaning "to clear the table." Its first known use was in 1600, in
						a health education manual entitled Naturall and artificial
						Directions for Health, which was written by William Vaughan. </span>Sweet
						desserts usually contain cane sugar, palm sugar, honey.
					</p>
					<p>The proportions of these ingredients, along with the
						preparation methods, play a major part in the consistency.</p>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!-- //good -->
	<!-- delicious -->
	<div class="delicious">
		<div class="container">
			<div class="delicious-info">
				<h3>DELICIOUS FOOD FOR ALL TASTES</h3>
				<div class="strip-line"></div>
			</div>
			<div class="delicious-grids">
				<div class="col-md-3 delicious-grid">
					<h3>PASTA SPECIAL</h3>
					<img src="images/g3.jpg" alt="" />
					<p>Dessert consist of variations of flavors, textures, and
						appearances. Desserts can be defined as a usually sweeter course
						that concludes a meal</p>
					<a href="#">(x)MORE</a>
				</div>
				<div class="col-md-3 delicious-grid">
					<h3>FRIED CHICKEN</h3>
					<img src="images/g6.jpg" alt="" />
					<p>Dessert consist of variations of flavors, textures, and
						appearances. Desserts can be defined as a usually sweeter course
						that concludes a meal</p>
					<a href="#">(x)MORE</a>
				</div>
				<div class="col-md-3 delicious-grid">
					<h3>SAUSAGES</h3>
					<img src="images/g5.jpg" alt="" />
					<p>Dessert consist of variations of flavors, textures, and
						appearances. Desserts can be defined as a usually sweeter course
						that concludes a meal</p>
					<a href="#">(x)MORE</a>
				</div>
				<div class="col-md-3 delicious-grid">
					<h3>BREAD SLICE</h3>
					<img src="images/g1.jpg" alt="" />
					<p>Dessert consist of variations of flavors, textures, and
						appearances. Desserts can be defined as a usually sweeter course
						that concludes a meal</p>
					<a href="#">(x)MORE</a>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!-- //delicious -->
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
