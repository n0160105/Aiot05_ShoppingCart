<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>結帳</title>
	<link rel="icon" href="logo/logo.ico">
	<link rel="stylesheet" type="text/css" href="css/cover.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
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
        <div class="container">
            <div class="col-md-12 order-md-1 ">
                <h2>信用卡付款</h2>
                <br>
				<form action="CheckoutServlet" method="post">
	                <div class="row ">
	                    <div class="col-md-12 mb-3">
	                        <label for="cc-name ">信用卡持有人</label>
	                      	<input type="text" class="form-control " id="cc-name " placeholder="須與簽名欄一致" required>
	                    </div>
		                <div class="col-md-12 mb-3">
		                	<label for="cc-number ">信用卡卡號</label>
		                	<input type="text" class="form-control" id="cc-number" placeholder="oooo-xxxx-oooo-xxxx" required>
		          		</div>
	   				</div>
			        <div class="row">
			          	<div class="col-md-6 mb-3">
			              	<label for="cc-expiration">到期年月</label>
			                <input type="text" class="form-control " id="cc-expiration " placeholder="MM/YY" required>
			   			</div>
			          	<div class="col-md-6 mb-0">
			               	<label for="cc-cvv ">安全碼</label>
			               	<input type="text" class="form-control" id="cc-cvv " placeholder="請見簽名欄末端" required>
			   			</div>
			   		</div>
			   		<br><br>
			   		<div align="center">
			        	<button onclick="location.href='javascript:history.back();'"class="btn btn-primary btn-danger btn-lg btn-left" type="button">取消</button>
			        	<button class="btn btn-primary btn-lg btn-success btn-right" type="submit">確認</button>
			        </div>
	    		</form>
    		</div>
       	</div>
	</div>        
</body>
</html>