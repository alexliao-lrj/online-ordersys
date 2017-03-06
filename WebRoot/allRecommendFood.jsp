<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="ajax.js"></script>
<script type="text/javascript">
	function loadData(page) {
		//alert("succeed"+page);
		xmlAjaxRequest("showRecommendFood?page=" + page + "&time=" + Math.random(),
				"get", true, null, showData, null, null);

	}
	function showResult(responseTxt, obj) {
	    // body...
	    alert("succeed");
	}

	function removeRecommendFood(id,pageIndex) {
		// body...
		var se=confirm("确定删除吗?");
		if (se == true) {
			xmlAjaxRequest(
				"removeRecommendFoodServlet?id=" + id + "&pageIndex=" + pageIndex + "&time=" + Math.random(),
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

		var head = "";

		head += "<tr>";
		head += "<td>菜品编号</td>";
		head += "<td>菜品名称</td>";
		head += "<td>菜品介绍</td>";
		head += "<td>菜品种类</td>";
		head += "<td>菜品价格</td>";
		head += "<td>菜品图片</td>";
		
		
		head += "</tr>";

		body.innerHTML = head;
		
		//alert(body.innerHTML);
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

			var html = "<tr>";
			html += "<td>" + id + "</td>";
			html += "<td>" + fname + "</td>";
			html += "<td>" + fdescription + "</td>";
			html += "<td>" + flag + "</td>";
			html += "<td>" + fprice + "</td>";
			html += "<td>" + "<img src = " + bpic + ">"   + "</td>";
			html += "<td>" + "<button onclick=\"removeRecommendFood(" + id +"," + pageIndex + ")\">删除</button>";
			html += "</tr>";
			
			body.innerHTML += html;

		}


		preLink.href = "javascript:loadData(" + (pageIndex - 1) + ")";
		nextLink.href = "javascript:loadData(" + (pageIndex + 1) + ")";

	}
</script>
<title>Insert title here</title>
</head>
<body onload="loadData(1)">
	<%@page import="ordersys.db.RecommendInfo"%><table>

		<tbody id="listbody">

		</tbody>




	</table>

	<br>

	<a id="pre">上一页</a> &nbsp;&nbsp;&nbsp;
	<a id="next">下一页</a>

</body>
</html>