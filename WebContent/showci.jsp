<%@ page contentType="text/html; charset=utf8" language="java"%>
<html>
<head>
<%@include file="header.jsp"%>
<!-- saved from url=(0013)about:internet -->
<link href="css/main.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<script type="text/javascript" language="JavaScript"
	src="FusionCharts.js"></script>
<title>DCM SW2 CI Pass Rate</title>
</head>
<body>

	<table width="800" align='center' border="1">
		<tr>
			<td>
				<div id='Line' align="center">
					<script type="text/javascript">
						var chart = new FusionCharts(
								'FusionCharts/Charts/Column2D.swf', "ChartId",
								"600", "450");
						chart.setDataURL("FusionCharts/Data/cidata.xml");
						chart.render('Line');
					</script>
				</div>
			</td>
		</tr>
	</table>
	
	
	<div>
		<ul>
			<li><a style="color: #000079; font-size: 30px"
				href="showcidetail.jsp" target="_blank">Show DCM SW2 CI Detail Info</a></li>
		</ul>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>
