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
	String RakeParamCheckimg = "";
	String RakeParamCheckbgcolor = "";
	String RakeParamCheckgraphlink = "";
	String rakeParamRate1 = DataQuery.getSingleton().showUTdata(
			DcmUtil.getNowDate(), "DCM_CP_RakeParamCheck_UT_NQST");
	String rakeParamRate2 = DataQuery.getSingleton().showUTdata(
			DcmUtil.getyesterday(), "DCM_CP_RakeParamCheck_UT_NQST");

	int rakeParamint1 = DcmUtil.checkString2decimal(rakeParamRate1);
	if (rakeParamint1 == 0) {
		rakeParamint1 = 100;
		rakeParamRate1 = "100";
	}

	int rakeParamint2 = DcmUtil.checkString2decimal(rakeParamRate2);
	if (rakeParamint2 == 0) {
		rakeParamint2 = 100;
	}

	if (rakeParamint1 > rakeParamint2) {
		RakeParamCheckimg = "img/uparrow.png";
	} else if (rakeParamint1 < rakeParamint2) {
		RakeParamCheckimg = "img/downarrow.png";
	} else if (rakeParamint1 == rakeParamint2) {
		RakeParamCheckimg = "img/horizontalarrow.png";
	}
	if (rakeParamint1 < 70) {
		RakeParamCheckbgcolor = "#FF0000";
	} else if (rakeParamint1 >= 95) {
		RakeParamCheckbgcolor = "#7FFF00";
	} else {
		RakeParamCheckbgcolor = "#FFD700";
	}
	RakeParamCheckgraphlink = request.getContextPath()
			+ "/showut3.jsp";

	String SharedSymbolimg = "";
	String SharedSymbolbgcolor = "";
	String SharedSymbolgraphlink = "";
	String sharedSymbolRate1 = DataQuery.getSingleton()
			.showUTdata(DcmUtil.getNowDate(),
					"DCM_CP_SharedSymbol_UT_NQST");
	String sharedSymbolRate2 = DataQuery.getSingleton()
			.showUTdata(DcmUtil.getyesterday(),
					"DCM_CP_SharedSymbol_UT_NQST");

	int sharedSymbolint1 = DcmUtil
			.checkString2decimal(sharedSymbolRate1);
	if (sharedSymbolint1 == 0) {
		sharedSymbolint1 = 100;
		sharedSymbolRate1 = "100";
	}

	int sharedSymbolint2 = DcmUtil
			.checkString2decimal(sharedSymbolRate2);
	if (sharedSymbolint2 == 0) {
		sharedSymbolint2 = 100;
	}

	if (sharedSymbolint1 > sharedSymbolint2) {
		SharedSymbolimg = "img/uparrow.png";
	} else if (sharedSymbolint1 < sharedSymbolint2) {
		SharedSymbolimg = "img/downarrow.png";
	} else if (sharedSymbolint1 == sharedSymbolint2) {
		SharedSymbolimg = "img/horizontalarrow.png";
	}
	if (sharedSymbolint1 < 70) {
		SharedSymbolbgcolor = "#FF0000";
	} else if (sharedSymbolint1 >= 95) {
		SharedSymbolbgcolor = "#7FFF00";
	} else {
		SharedSymbolbgcolor = "#FFD700";
	}
	SharedSymbolgraphlink = request.getContextPath() + "/showut4.jsp";
%>
	<table width="800" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan=2 style="color: #000079; font-size: 25px">RakeParamCheck_UT</td>
			<td width="15"></td>
			<td colspan=2 style="color: #000079; font-size: 25px">SharedSymbol_UT</td>
		</tr>
		<tr>
			<td height="146" width="200" bgcolor=<%=RakeParamCheckbgcolor%>>
				<a href=<%=RakeParamCheckgraphlink%> target="_blank"><img
					width="200" src=<%=RakeParamCheckimg%>></img></a>
			</td>
			<td height="146" width="80" bgcolor=<%=RakeParamCheckbgcolor%>>
				<Font style="color: #FFFFFF; font-size: 40px"><%=rakeParamint1%>%</Font>
			</td>
			
			<td width="15"></td>
			
			<td height="146" width="200" bgcolor=<%=SharedSymbolbgcolor%>>
				<a href=<%=SharedSymbolgraphlink%> target="_blank"><img
					width="200" src=<%=SharedSymbolimg%>></img></a>
			</td>
			<td height="146" width="80" bgcolor=<%=SharedSymbolbgcolor%>>
				<Font style="color: #FFFFFF; font-size: 40px"><%=sharedSymbolint1%>%</Font>
			</td>
		</tr>
	</table>
</body>
</html>