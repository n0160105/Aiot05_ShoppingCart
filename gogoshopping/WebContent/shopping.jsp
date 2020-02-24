<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>我的購物車</title>
	<link rel="icon" href="logo/logo.ico">
	<link rel="stylesheet" type="text/css" href="css/cover.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	<script src="javascript/shopping.js"></script>
</head>

<body class="text-center">
    <div class="container col-lg-12 col-md-12 col-sm-12">
        <header class="masthead my-5">
            <div class="inner">
            	<img src="logo/logo.png" style="float:left;width:70px;height:70px">
                <nav class="nav nav-masthead justify-content-center">
                	<a class="nav-link" href="#"><c:out value="${sessionScope.name}"/></a>
                    <a class="nav-link" href="index.jsp">首頁</a>
                    <a class="nav-link" href="aboutus.jsp">關於我們</a>
                    <a class="nav-link" href="cservice.jsp">智慧客服</a>
                </nav>
            </div>
        </header>
       	<br><br><br><br><br><br><br>
		<h4 class="d-flex justify-content-between align-items-center mb-3">
	     	<span>我的購物車</span>
	      	<span class="badge badge-secondary badge-pill" id="rownum">物件數量:0</span>
      	</h4>
       	<ul class="list-group mb-4" id="myul">
   			<c:forEach var="fruits" items="${selectFruits}">
		      	<li class="list-group-item d-flex justify-content-between lh-condensed">
			     	<div>
				       	<img src="/file/<c:out value='${fruits.name}'/>_<c:out value='${fruits.id}'/>.jpg " width=100 height=100  />
				   	</div>
					<div>
				      	<h6 class="my-0" id="fruitname"><c:out value="${fruits.name}"/></h6>
				     	<small>偵測概率:<c:out value="${fruits.prob}"/></small>
					</div>
				  	<span id="unitprice"><c:out value="${fruits.price}"/>元</span>
		 		</li>
	   		</c:forEach>
	   		<li class="list-group-item d-flex justify-content-between">
	      		<span>總價</span>
	         	<strong class="cart-total-price" id="totalprice"></strong>
	   		</li>
     	</ul>
		<div align="center">
			<a class="btn btn-primary btn-lg btn-block" type="button" href="RefreshServlet">開始偵測</a>
			<br>
			<hr>
			<a href="LogoutServlet" class="btn btn-primary btn-danger btn-lg btn-left" type="button">取消購買</a>
			<a href="CheckoutServlet" class="btn btn-primary btn-success btn-lg btn-right" type="button">確認結帳</a>
		</div>
  	</div>
</body>
</html>

