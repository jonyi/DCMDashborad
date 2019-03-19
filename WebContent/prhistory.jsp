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
	String prHisimg = "";
	String prHisbgcolor1 = "";
	String prHisbgcolor2 = "";
	String prHisbgcolor3 = "";
	String prHisgraphlink = "prhistory.jsp";
	int prHisRate1 = DataQuery.getSingleton().countPRHisInfo("A");
	int prHisRate2 = DataQuery.getSingleton().countPRHisInfo("B");
	int prHisRate3 = DataQuery.getSingleton().countPRHisInfo("C");
		
	prHisimg = "img/horizontalarrow.png";

	
	
	
	String acorrectionTime = ExcelParser.getInstance().getPRCorrectionTime("A");
	int acorrectionTime1 = DcmUtil.checkString2int(acorrectionTime, 0);
	if(acorrectionTime1 >= 4){
		prHisbgcolor1 = "#FF0000";
	}else{
		prHisbgcolor1 = "#7FFF00";
	}
	
	
	String bcorrectionTime = ExcelParser.getInstance().getPRCorrectionTime("B");
	int bcorrectionTime1 = DcmUtil.checkString2int(bcorrectionTime, 0);
	if(bcorrectionTime1 >= 10){
		prHisbgcolor2 = "#FF0000";
	}else{
		prHisbgcolor2 = "#7FFF00";
	}
	
	String ccorrectionTime = ExcelParser.getInstance().getPRCorrectionTime("C");
	int ccorrectionTime1 = DcmUtil.checkString2int(ccorrectionTime, 0);
	if(ccorrectionTime1 >= 21){
		prHisbgcolor3 = "#FF0000";
	}else{
		prHisbgcolor3 = "#7FFF00";
	}
	
%>
	<table width="800" align="center" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan=2 style="color: #000079; font-size: 25px">PR A</td>
			<td width="10"></td>
			<td colspan=2 style="color: #000079; font-size: 25px">PR B</td>
			<td width="10"></td>
			<td colspan=2 style="color: #000079; font-size: 25px">PR C</td>
		</tr>
		<tr>
			<td height="146" width="180" bgcolor=<%=prHisbgcolor1%>>
				<a href=<%=prHisgraphlink%> target="_blank"><img
					width="200" src=<%=prHisimg%>></img></a>
			</td>
			<td height="146" width="80" bgcolor=<%=prHisbgcolor1%>>
				<Font style="color: #FFFFFF; font-size: 40px"><%=acorrectionTime%></Font>
			</td>
			
			<td width="10"></td>
			
			<td height="146" width="180" bgcolor=<%=prHisbgcolor2%>>
				<a href=<%=prHisgraphlink%> target="_blank"><img
					width="200" src=<%=prHisimg%>></img></a>
			</td>
			<td height="146" width="80" bgcolor=<%=prHisbgcolor2%>>
				<Font style="color: #FFFFFF; font-size: 40px"><%=bcorrectionTime%></Font>
			</td>
			
			<td width="10"></td>
			
			<td height="146" width="180" bgcolor=<%=prHisbgcolor3%>>
				<a href=<%=prHisgraphlink%> target="_blank"><img
					width="200" src=<%=prHisimg%>></img></a>
			</td>
			<td height="146" width="80" bgcolor=<%=prHisbgcolor3%>>
				<Font style="color: #FFFFFF; font-size: 40px"><%=ccorrectionTime%></Font>
			</td>
		</tr>
	</table>
</body>
</html>