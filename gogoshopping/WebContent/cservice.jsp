<%@page import="org.apache.jasper.tagplugins.jstl.core.Import"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>智慧客服</title>
	<link rel="icon" href="logo/logo.ico">
	<link rel="stylesheet" type="text/css" href="css/cover.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	<script src="https://cdn.botframework.com/botframework-webchat/latest/webchat.js"></script>
	<style>
        #webchat {
			height:60%;
			width: 100%;
			margin: auto;
			margin-top: 13em;
			border: 2px solid rgba(0,99,177);
		}
  	</style>
</head>
<body class="text-center">
    <div class="container col-lg-12 col-md-12 col-sm-12">
        <header class="masthead my-5">
            <div class="inner">
            	<img src="logo/logo.png" style="float:left;width:70px;height:70px">
                <nav class="nav nav-masthead justify-content-center">
                    <a class="nav-link" href="index.jsp">首頁</a>
                    <a class="nav-link" href="aboutus.jsp">關於我們</a>
                    <a class="nav-link active" href="cservice.jsp">智慧客服</a>
                </nav>
            </div>
        </header>
  		
	  	<div id="webchat"></div>
	  	<br><br>
	  	<button onclick="location.href='javascript:history.back();'"class="btn btn-primary btn-danger btn-lg btn-left" type="button">取消</button>
	  	<a href="#" class="btn btn-lg btn-primary btn-success btn-right">呼叫現場人員</a>
	
	   <script>   
		   const styleOptions = {
		       botAvatarInitials: 'Bot',
		       bubbleBackground: 'rgba(255,255,255)',
		       bubbleFromUserBackground: 'rgba(0,99,177,0.5)',
		   };
		   window.WebChat.renderWebChat({
		   		directLine: window.WebChat.createDirectLine({
		   			secret: 'KyBznN2fmoM.dlkE98Sf8X38-K6YE37taMKWZVDPgdV7Meoaai2hogU'
		   		}),
		   		styleOptions
		   	}, document.getElementById('webchat'));
	   </script>
	</div>  	
</body>
</html>