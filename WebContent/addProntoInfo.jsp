<%@ page contentType="text/html; charset=utf8" language="java" import="java.sql.*" errorPage="../error.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="taglibs.jsp"%>

<html>
<head>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>DCM DashBoard</title>
</head>
<body>
<%@include file="header.jsp"%> 
<table align="center" width="800" border="0" cellpadding="0" cellspacing="1" bgcolor="#FFFFFF">
<tr>
  <TD valign=TOP>&nbsp; </td>
</tr>
<tr>
<html:form action="/processAdd.do" onsubmit="return validateAddForm(this);">
<br>
<table align="center" width="800" border="0" cellpadding="0" cellspacing="1" bgcolor="#FFFFFF">
  <tr>
    <td width="15%" class="formLeft">Pronto ID:</td>
    <td class="formRight">
      <input type="text" name="prId">
    </td>
  </tr>
  <tr>
    <td width="15%" class="formLeft">Pronto Title:</td>
    <td class="formRight">
    <textarea name="prTitle" rows="3" cols="80"></textarea>
    </td>
  </tr>
   <tr>
    <td width="15%" class="formLeft">Pronto Topimportance:</td>
    <td class="formRight">
      <input type="text" name="prTopimportance">
    </td>
  </tr>
    <tr>
    <td width="15%" class="formLeft">Pronto Severity:</td>
    <td class="formRight">
      <input type="text" name="prSeverity">
    </td>
  </tr>
   <tr>
    <td width="15%" class="formLeft">Pronto Priority:</td>
    <td class="formRight">
      <input type="text" name="prPriority">
    </td>
   </tr>
  
    <tr>
    <td width="15%" class="formLeft">Pronto State:</td>
    <td class="formRight">
      <input type="text" name="prState">
    </td>
    </tr>
    <tr>
    <td width="15%" class="formLeft">Pronto Update:</td>
    <td class="formRight">
    <textarea name="prUpdate" rows="10" cols="80"></textarea>
    </td>
    </tr>
    <tr>
    <td width="15%" class="formLeft">Pronto Respperson:</td>
    <td class="formRight">
      <input  type="text" name="prRespperson">
    </td>
    </tr>
   <tr>
    <td width="15%" class="formLeft">Pronto Author:</td>
    <td class="formRight">
      <input  type="text" name="prAuthor">
    </td>
  </tr>
  <tr>
    <td width="15%" class="formLeft">Pronto Authorgrp:</td>
    <td class="formRight">
      <input  type="text" name="prAuthorgrp">
    </td>
  </tr>
     <tr>
    <td width="15%" class="formLeft">Pronto Reportdate:</td>
    <td class="formRight">
      <input type="text" name="prReportdate">
    </td>
  </tr>
  </table>
    <input type="submit" value="Submit" name="Submit">
    <input type="reset" name="Submit2" value="Reset">
</html:form>
<html:javascript formName="addForm"/>
</table>

<%@include file="footer.jsp"%> 

</body>
</html>
