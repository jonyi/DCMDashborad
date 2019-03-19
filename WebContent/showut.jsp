<%@ page contentType="text/html; charset=utf8" language="java"%>
<html>
<head>
<%@include file="header.jsp"%>
<!-- saved from url=(0013)about:internet -->
<script type="text/javascript" language="JavaScript" src="FusionCharts.js"></script> 
<title>DCM SW Team2 UT Coverage</title> 
</head> 
<%
String utjob = request.getParameter("utjob");
String url = "FusionCharts/Data/"+utjob+".xml";
System.out.println(url);
%>
<body> 
	<table align='center' border="1">    
		<tr>
			<td> 
				<div id='Line' align="center">  
				<script type="text/javascript"> 
					var chart = new FusionCharts('FusionCharts/Charts/Column2D.swf', "ChartId", "600", "450");  
					chart.setDataURL('<%=url%>');       
					chart.render('Line'); 
				</script> 
				</div> 
			</td>  
		</tr>    
	</table> 
	<%@include file="footer.jsp"%>
</body>  
</html>  