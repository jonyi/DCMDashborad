<%@ page import="java.util.*"%>
<%@ page import="com.nsn.dcm.team2.handler.*"%>
<%@ page import="com.nsn.dcm.team2.utility.*"%>
<html>
<head>

<!-- saved from url=(0013)about:internet -->
<title>DCM DashBoard</title>
</head>
<body>
<%
	String Controlimg = "";
	String Controlbgcolor = "";
	String Controlgraphlink = "";
	String controlRate1 = DataQuery.getSingleton().showUTdata(
			DcmUtil.getNowDate(), "DCM_CP_Control_UT_NQST");
	String controlRate2 = DataQuery.getSingleton().showUTdata(
			DcmUtil.getyesterday(), "DCM_CP_Control_UT_NQST");

	int controlint1 = DcmUtil.checkString2decimal(controlRate1);
	if (controlint1 == 0) {
		controlint1 = 100;
		controlRate1 = "100";
	}

	int controlint2 = DcmUtil.checkString2decimal(controlRate2);
	if (controlint2 == 0) {
		controlint2 = 100;
	}

	if (controlint1 > controlint2) {
		Controlimg = "img/uparrow.png";
	} else if (controlint1 < controlint2) {
		Controlimg = "img/downarrow.png";
	} else if (controlint1 == controlint2) {
		Controlimg = "img/horizontalarrow.png";
	}
	if (controlint1 < 70) {
		Controlbgcolor = "#FF0000";
	} else if (controlint1 >= 95) {
		Controlbgcolor = "#7FFF00";
	} else {
		Controlbgcolor = "#FFD700";
	}
	Controlgraphlink = request.getContextPath() + "/showut1.jsp";

	String Databaseimg = "";
	String Databasebgcolor = "";
	String Databasegraphlink = "";
	String databaseRate1 = DataQuery.getSingleton()
			.showUTdata(DcmUtil.getNowDate(),
					"DCM_CP_Database_UT_NQST");
	String databaseRate2 = DataQuery.getSingleton()
			.showUTdata(DcmUtil.getyesterday(),
					"DCM_CP_Database_UT_NQST");

	int databaseint1 = DcmUtil
			.checkString2decimal(databaseRate1);
	if (databaseint1 == 0) {
		databaseint1 = 100;
		databaseRate1 = "100";
	}

	int databaseint2 = DcmUtil
			.checkString2decimal(databaseRate2);
	if (databaseint2 == 0) {
		databaseint2 = 100;
	}

	if (databaseint1 > databaseint2) {
		Databaseimg = "img/uparrow.png";
	} else if (databaseint1 < databaseint2) {
		Databaseimg = "img/downarrow.png";
	} else if (databaseint1 == databaseint2) {
		Databaseimg = "img/horizontalarrow.png";
	}
	if (databaseint1 < 70) {
		Databasebgcolor = "#FF0000";
	} else if (databaseint1 >= 95) {
		Databasebgcolor = "#7FFF00";
	} else {
		Databasebgcolor = "#FFD700";
	}
	Databasegraphlink = request.getContextPath() + "/showut2.jsp";
%>

	<table width="800" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan=2 style="color: #000079; font-size: 25px">Control_UT</td>
			<td width="15"></td>
			<td colspan=2 style="color: #000079; font-size: 25px">Database_UT</td>
		</tr>
		<tr>
			<td height="146" width="200" bgcolor=<%=Controlbgcolor%>>
				<a href=<%=Controlgraphlink%> target="_blank"><img
					width="200" src=<%=Controlimg%>></img></a>
			</td>
			<td height="146" width="80" bgcolor=<%=Controlbgcolor%>>
				<Font style="color: #FFFFFF; font-size: 40px"><%=controlint1%>%</Font>
			</td>
			
			<td width="15"></td>
			
			<td height="146" width="200" bgcolor=<%=Databasebgcolor%>>
				<a href=<%=Databasegraphlink%> target="_blank"><img
					width="200" src=<%=Databaseimg%>></img></a>
			</td>
			<td height="146" width="80" bgcolor=<%=Databasebgcolor%>>
				<Font style="color: #FFFFFF; font-size: 40px"><%=databaseint1%>%</Font>
			</td>
		</tr>
	</table>
</body>
</html>