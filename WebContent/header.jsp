<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page contentType="text/html; charset=utf8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nsn.dcm.team2.utility.*"%>
<head>
</head>
<link rel="stylesheet" href="css/main.css" />
<body>
	<%
		String nowdate1 = DcmUtil.getNowDate();
	%>
	<p align="center">
		<img width="150" src="img/logo.jpg"> <img width="550"
			src="img/indextop.jpg"><Font
				style="width: 100; color: #221166; font-size: 20px"><%=nowdate1%></Font>
	</p>
	<table align="center" width="800" border="0" cellpadding="0" cellspacing="0">
		<div id="navigation" class="smartmenu">
			<ul>
				<li><a href="/DCMDashboard/index.jsp" title="Home"><img
						src="img/home.png" alt="Home" /></a></li>
			</ul>
		</div>
	</table>
	<hr width="800" align="center" />
	<!--  
	<table width="100%" border="0" cellpadding="0" cellspacing="1"
		bgcolor="#FFFFFF">
		<div id="navigation" class="smartmenu">
			<ul>
				<li><a href="/index.jsp">Home
						Page</a></li>
				<li><a href="showGraph.do" target="_blank">Show UT Coverage
						Graph</a></li>
				<li><a href="http://10.140.21.13/" target="_blank">DCM CI
						Server</a></li>
				<li><a href="addPronto.do">Add New Pronto</a></li>
				<li><a href="viewPronto.do">View Pronto</a></li>
			</ul>
		</div>
	</table>
-->
</body>


