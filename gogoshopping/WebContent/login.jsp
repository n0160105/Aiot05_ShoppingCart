<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>登入</title>
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
                <nav class="nav nav-masthead justify-content-center">
                    <a class="nav-link" href="index.jsp">首頁</a>
                    <a class="nav-link" href="aboutus.jsp">關於我們</a>
                    <a class="nav-link" href="cservice.jsp">智慧客服</a>
                </nav>
            </div>
        </header>
       	<br><br><br><br><br><br><br>
        <img class="logo" src="logo/logo.png" alt="" >
	    <form action="LoginServlet" method="post">
	        <br>
		    <div class="form-group">
		      	<input class="form-control" placeholder="帳號" type="email" name="email" required />
		    </div>
		    <div class="form-group">
		      	<input class="form-control" placeholder="密碼" type="password" name="password" required />
		    </div>
		    <div class="form-group">
		      	<button class="btn btn-lg btn-block btn-primary btn-success" name="login" type="submit">登入</button>
		    </div>
		    <nav class="nav nav-masthead">
		    	<a href="forgot.jsp">忘記密碼</a>
		    </nav>
	    </form>  
	    
	</div>  
</body>
</html>