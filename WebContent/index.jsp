<%@ page contentType="text/html; charset=utf8" language="java" %>
<%@ page import="java.util.*"%>
<%@ page import="com.nsn.dcm.team2.handler.*"%>
<%@ page import="com.nsn.dcm.team2.utility.*"%>
<%@ include file="taglibs.jsp"%>

<html>
<head>
<link href="css/main.css" rel="stylesheet" type="text/css">
<meta http-equiv="refresh" content="1800; url=index.jsp">
<!-- saved from url=(0013)about:internet -->
<title>DCM DashBoard</title>
</head>
<body>
<%
   
	String Allutimg = "";
	String Allutbgcolor = "";
	String Allutgraphlink = "";
	String allutRate1 = DataQuery.getSingleton().getAvgdata(
			DcmUtil.getNowDate());
	String allutRate2 = DataQuery.getSingleton().getAvgdata(
			DcmUtil.getyesterday());

	int allutint1 = DcmUtil.checkString2decimal(allutRate1);
	if (allutint1 == 0) {
		allutint1 = 100;
	}

	int allutint2 = DcmUtil.checkString2decimal(allutRate2);
	if (allutint2 == 0) {
		allutint2 = 100;
	}

	if (allutint1 > allutint2) {
		Allutimg = "img/uparrow.png";
	} else if (allutint1 < allutint2) {
		Allutimg = "img/downarrow.png";
	} else if (allutint1 == allutint2) {
		Allutimg = "img/horizontalarrow.png";
	}
	if (allutint1 < 70) {
		Allutbgcolor = "#FF0000";
	} else if (allutint1 >= 95) {
		Allutbgcolor = "#7FFF00";
	} else {
		Allutbgcolor = "#FFD700";
	}
	Allutgraphlink = request.getContextPath() + "/showallutdata.jsp";

	String Ciimg = "";
	String Cibgcolor = "";
	String Cigraphlink = "";
	String ciRate1 = DataQuery.getSingleton().showCIdata(
			DcmUtil.getNowDate());
	String ciRate2 = DataQuery.getSingleton().showCIdata(
			DcmUtil.getyesterday());

	int ciint1 = DcmUtil.checkString2decimal(ciRate1);
	if (ciint1 == 0) {
		ciint1 = 100;
	}

	int ciint2 = DcmUtil.checkString2decimal(ciRate2);
	if (ciint2 == 0) {
		ciint2 = 100;
	}

	if (ciint1 > ciint2) {
		Ciimg = "img/uparrow.png";
	} else if (ciint1 < ciint2) {
		Ciimg = "img/downarrow.png";
	} else if (ciint1 == ciint2) {
		Ciimg = "img/horizontalarrow.png";
	}
	if (ciint1 < 90) {
		Cibgcolor = "#FF0000";
	} else if (ciint1 > 95) {
		Cibgcolor = "#7FFF00";
	} else {
		Cibgcolor = "#FFD700";
	}
	Cigraphlink = request.getContextPath() + "/showci.jsp";

	String Mtimg = "";
	String Mtbgcolor = "";
	String Mtgraphlink = "";
	String mtRate1 = DataQuery.getSingleton().showMTdata(
			DcmUtil.getNowDate());
	String mtRate2 = DataQuery.getSingleton().showMTdata(
			DcmUtil.getyesterday());

	int mtint1 = DcmUtil.checkString2decimal(mtRate1);
	if (mtint1 == 0) {
		mtint1 = 100;
	}

	int mtint2 = DcmUtil.checkString2decimal(mtRate2);
	if (mtint2 == 0) {
		mtint2 = 100;
	}

	if (mtint1 > mtint2) {
		Mtimg = "img/uparrow.png";
	} else if (mtint1 < mtint2) {
		Mtimg = "img/downarrow.png";
	} else if (mtint1 == mtint2) {
		Mtimg = "img/horizontalarrow.png";
	}
	if (mtint1 < 90) {
		Mtbgcolor = "#FF0000";
	} else if (mtint1 == 100) {
		Mtbgcolor = "#7FFF00";
	} else {
		Mtbgcolor = "#FFD700";
	}
	Mtgraphlink = request.getContextPath() + "/showmt.jsp";
	
	
	String Primg = "";
	String Prbgcolor = "";
	String Prgraphlink = "";
	String prRate1 = DataQuery.getSingleton().showProntodata(
			DcmUtil.getNowDate());
	String prRate2 = DataQuery.getSingleton().showProntodata(
			DcmUtil.getyesterday());

	int print1 = DcmUtil.checkString2decimal(prRate1);

	int print2 = DcmUtil.checkString2decimal(prRate2);
	

	if (print1 > print2) {
		Primg = "img/uparrow.png";
	} else if (print1 < print2) {
		Primg = "img/downarrow.png";
	} else if (print1 == print2) {
		Primg = "img/horizontalarrow.png";
	}
	if (print1 > 5) {
		Prbgcolor = "#FF0000";
	} else if (print1 < 3) {
		Prbgcolor = "#7FFF00";
	} else {
		Prbgcolor = "#FFD700";
	}
	Prgraphlink = request.getContextPath() + "/showpr.jsp";
%>

	<%@include file="header.jsp"%>
	<table width="800" align="center" border="0" cellpadding="0"
		cellspacing="0">

		<tr>
			<td colspan=2 style="color: #000079; font-size: 25px">UT Line Coverage</td>
					<td width="15"></td>		
			<td colspan=2 style="color: #000079; font-size: 25px">SCT CI Passrate</td>
		</tr>
		<tr>
			<td height="146" width="200" bgcolor=<%=Allutbgcolor%>><a
				href=<%=Allutgraphlink%> target="_blank"><img width="200"
					src=<%=Allutimg%>></img></a></td>
			<td height="146" width="80" bgcolor=<%=Allutbgcolor%>><Font
				style="color: #FFFFFF; font-size: 40px"><%=allutint1%>%</Font></td>
			<td width="15"></td>

			<td height="146" width="200" bgcolor=<%=Cibgcolor%>><a
				href=<%=Cigraphlink%> target="_blank"><img width="200"
					src=<%=Ciimg%>></img></a></td>
			<td height="146" width="80" bgcolor=<%=Cibgcolor%>><Font
				style="color: #FFFFFF; font-size: 40px"><%=ciint1%>%</Font></td>
		</tr>

		<tr>
			<td colspan=2 style="color: #000079; font-size: 25px">Team2 MT Passrate</td>
					<td width="15"></td>		
			<td colspan=2 style="color: #000079; font-size: 25px">Team2 Pronto Info</td>

		</tr>
		<tr>
			<td height="146" width="200" bgcolor=<%=Mtbgcolor%>><a
				href=<%=Mtgraphlink%> target="_blank"><img width="200"
					src=<%=Mtimg%>></img></a></td>
			<td height="146" width="80" bgcolor=<%=Mtbgcolor%>><Font
				style="color: #FFFFFF; font-size: 40px"><%=mtint1%>%</Font></td>
			<td width="15"></td>	
				
			<td height="146" width="200" bgcolor=<%=Prbgcolor%>><a
				href=<%=Prgraphlink%> target="_blank"><img width="200"
					src=<%=Primg%>></img></a></td>
			<td height="146" width="80" bgcolor=<%=Prbgcolor%>><Font
				style="color: #FFFFFF; font-size: 40px"><%=print1%></Font></td>
		</tr>
	</table>
	<%@include file="prhistory.jsp"%>
	<%@include file="footer.jsp"%>
</body>
</html>
