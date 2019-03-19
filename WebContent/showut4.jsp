<%@ page contentType="text/html; charset=utf8" language="java"%>
<html>
<head>
<%@include file="header.jsp"%>
<!-- saved from url=(0013)about:internet -->
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="JavaScript" src="FusionCharts.js"></script> 
<title>Rake UT Coverage - DCM_CP_SharedSymbol_UT_NQST</title> 
</head> 

<body> 
	<table width="800" align='center' border="1">    
		<tr>
			<td> 
				<div id='Line' align="center">  
				<script type="text/javascript"> 
					var chart = new FusionCharts('FusionCharts/Charts/Column2D.swf', "ChartId", "600", "450");  
					chart.setDataURL("FusionCharts/Data/DCM_CP_SharedSymbol_UT_NQST.xml");       
					chart.render('Line'); 
				</script> 
				</div> 
			</td>  
		</tr>    
	</table> 
	<%@include file="footer.jsp"%>
</body>  
</html>  