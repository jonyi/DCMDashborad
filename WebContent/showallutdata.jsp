
<%@ page contentType="text/html; charset=utf8" language="java"
	import="java.sql.*" errorPage="../error.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nsn.dcm.team2.handler.*"%>
<%@ page import="com.nsn.dcm.team2.utility.*"%>
<%@include file="header.jsp"%>
<html>
<head>
<meta http-equiv="refresh" content="1800; url=showallutdata.jsp">
<!-- saved from url=(0013)about:internet -->
<title>DCM DashBoard</title>
</head>
<body>
<%
String[] utnames = DataCacheManage.getSingleton().getUtnames();
%>
<table width="800" align="center" border="0" cellpadding="0" cellspacing="0">
<tr>
<%
int j;
for(j =0; j <5; j++){
	
for(int i = 0; i < 5; i++)
{
	String utname1 = utnames[i+j*5].replaceAll("_UT_NQST", "").replaceAll("DCM_CP_", "").replaceAll("CP_", "").replaceAll("_UT", "");
	
%>
	<td colspan=2 style="color: #000079; font-size: 15px"><%=utname1%></td>
	<td width="5"></td>
<%}%>
</tr>
<tr>
<%
for(int i = 0; i < 5; i++)
{	
	
	String img = "";
	String bgcolor = "";
	String graphlink = "";
	String Rate1 = DataQuery.getSingleton().showUTdata(
			DcmUtil.getNowDate(), utnames[i+j*5]);
	String Rate2 = DataQuery.getSingleton().showUTdata(
			DcmUtil.getyesterday(), utnames[i+j*5]);

	int int1 = DcmUtil.checkString2decimal(Rate1);
	if (int1 == 0) {
		int1 = 100;
	}

	int int2 = DcmUtil.checkString2decimal(Rate2);
	if (int2 == 0) {
		int2 = 100;
	}

	if (int1 > int2) {
		img = "img/uparrow.png";
	} else if (int1 < int2) {
		img = "img/downarrow.png";
	} else if (int1 == int2) {
		img = "img/horizontalarrow.png";
	}
	if (int1 < 70) {
		bgcolor = "#FF0000";
	} else if (int1 >= 95) {
		bgcolor = "#7FFF00";
	} else {
		bgcolor = "#FFD700";
	}
	graphlink = request.getContextPath() + "/showut.jsp?utjob="+utnames[i+j*5];
	
 %>
			<td height="80" width="50" bgcolor=<%=bgcolor%>><a href=<%=graphlink%> target="_blank"><img
					width="50" src=<%=img%>></img></a>
			</td>
			<td height="80" width="40" bgcolor="<%=bgcolor%>">
				<Font style="color: #FFFFFF; font-size: 20px"><%=int1%>%</Font>
			</td>
			<td width="5"></td>
<%} %>			
</tr>
<%} %>	
</table>

<%@include file="footer.jsp"%>
</body>
</html>
