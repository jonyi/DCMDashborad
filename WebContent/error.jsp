<%@ page contentType="text/html; charset=utf8" language="java" import="java.sql.*" isErrorPage="true" %>
<!DOCTYPE HTML>
<html>
<head>
   <title>error</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf8"><style type="text/css">
</style>
<link href="images/css.css" rel="stylesheet" type="text/css">
</head>
<body text="#000000" bgcolor="#FFFFFF" link="#0000EE" vlink="#551A8B" alink="#FF0000">
<%@include file="header.jsp"%> 
<TABLE width="780" height="397" align="center" CELLSPACING=0 background="images/bodybg.jpg">
  <TR> 
  <td height="39" valign=top><br>
  <div align="center"><font color="#FF0000" size="+1"><b>error!</b></font></div>
  </td>
  </tr>

  <TR>

  </tr>
  <TR>
    <td height="315" valign=top><div align="center" class="error"><%=exception%></div></td>
  </tr>
  <TR>
    <td valign=top><div align="center"><font size="0" color="#333333">error</font></div><br></td>
  </tr>
</table>
<%@include file="footer.jsp"%> 
</body>
</html>

