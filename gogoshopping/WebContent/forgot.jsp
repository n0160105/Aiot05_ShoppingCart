<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>忘記密碼</title>
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
                    <a class="nav-link" href="index.jsp">首頁</a>
                    <a class="nav-link" href="aboutus.jsp">關於我們</a>
                    <a class="nav-link" href="#">智慧客服</a>
                </nav>
            </div>
        </header>
       	<br><br><br><br><br><br><br>
       	<div class="container">
            <div class="col-md-12 order-md-1 ">
                <h2>忘記密碼</h2>
                <br>
				<form action="ForgotServlet" method="post">
	                <div class="row ">
	                    <div class="col-md-12 mb-3">
	                      	<input type="text" class="form-control form-control-lg" name="name" placeholder="請輸入您的註冊帳號, 我們將發送連結到您的信箱" required>
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