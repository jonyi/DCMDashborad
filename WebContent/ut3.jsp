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
	String SlaveSymbolimg = "";
	String SlaveSymbolbgcolor = "";
	String SlaveSymbolgraphlink = "";
	String slaveSymbolRate1 = DataQuery.getSingleton().showUTdata(
			DcmUtil.getNowDate(), "DCM_CP_SlaveSymbol_UT_NQST");
	String slaveSymbolRate2 = DataQuery.getSingleton().showUTdata(
			DcmUtil.getyesterday(), "DCM_CP_SlaveSymbol_UT_NQST");

	int slaveSymbolint1 = DcmUtil.checkString2decimal(slaveSymbolRate1);
	if (slaveSymbolint1 == 0) {
		slaveSymbolint1 = 100;
		slaveSymbolRate1 = "100";
	}

	int slaveSymbolint2 = DcmUtil.checkString2decimal(slaveSymbolRate2);
	if (slaveSymbolint2 == 0) {
		slaveSymbolint2 = 100;
	}

	if (slaveSymbolint1 > slaveSymbolint2) {
		SlaveSymbolimg = "img/uparrow.png";
	} else if (slaveSymbolint1 < slaveSymbolint2) {
		SlaveSymbolimg = "img/downarrow.png";
	} else if (slaveSymbolint1 == slaveSymbolint2) {
		SlaveSymbolimg = "img/horizontalarrow.png";
	}
	if (slaveSymbolint1 < 70) {
		SlaveSymbolbgcolor = "#FF0000";
	} else if (slaveSymbolint1 >= 95) {
		SlaveSymbolbgcolor = "#7FFF00";
	} else {
		SlaveSymbolbgcolor = "#FFD700";
	}
	SlaveSymbolgraphlink = request.getContextPath() + "/showut5.jsp";

	String PowerControlimg = "";
	String PowerControlbgcolor = "";
	String PowerControlgraphlink = "";
	String powerControlRate1 = DataQuery.getSingleton()
			.showUTdata(DcmUtil.getNowDate(),
					"DCM_CP_PowerControl_UT_NQST");
	String powerControlRate2 = DataQuery.getSingleton()
			.showUTdata(DcmUtil.getyesterday(),
					"DCM_CP_PowerControl_UT_NQST");

	int powerControlint1 = DcmUtil
			.checkString2decimal(powerControlRate1);
	if (powerControlint1 == 0) {
		powerControlint1 = 100;
		powerControlRate1 = "100";
	}

	int powerControlint2 = DcmUtil
			.checkString2decimal(powerControlRate2);
	if (powerControlint2 == 0) {
		powerControlint2 = 100;
	}

	if (powerControlint1 > powerControlint2) {
		PowerControlimg = "img/uparrow.png";
	} else if (powerControlint1 < powerControlint2) {
		PowerControlimg = "img/downarrow.png";
	} else if (powerControlint1 == powerControlint2) {
		PowerControlimg = "img/horizontalarrow.png";
	}
	if (powerControlint1 < 70) {
		PowerControlbgcolor = "#FF0000";
	} else if (powerControlint1 >= 95) {
		PowerControlbgcolor = "#7FFF00";
	} else {
		PowerControlbgcolor = "#FFD700";
	}
	PowerControlgraphlink = request.getContextPath() + "/showut.jsp";
%>
	<table width="800" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan=2 style="color: #000079; font-size: 25px">SlaveSymbol_UT</td>
			<td width="15"></td>
			<td colspan=2 style="color: #000079; font-size: 25px">PowerControl_UT</td>
		</tr>
		<tr>
			<td height="146" width="200" bgcolor=<%=SlaveSymbolbgcolor%>>
				<a href=<%=SlaveSymbolgraphlink%> target="_blank"><img
					width="200" src=<%=SlaveSymbolimg%>></img></a>
			</td>
			<td height="146" width="80" bgcolor=<%=SlaveSymbolbgcolor%>>
				<Font style="color: #FFFFFF; font-size: 40px"><%=slaveSymbolint1%>%</Font>
			</td>
			
			<td width="15"></td>
			
			<td height="146" width="200" bgcolor=<%=PowerControlbgcolor%>>
				<a href=<%=PowerControlgraphlink%> target="_blank"><img
					width="200" src=<%=PowerControlimg%>></img></a>
			</td>
			<td height="146" width="80" bgcolor=<%=PowerControlbgcolor%>>
				<Font style="color: #FFFFFF; font-size: 40px"><%=powerControlint1%>%</Font>
			</td>
		</tr>
	</table>
</body>

</html>