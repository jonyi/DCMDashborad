<%@ page contentType="text/html; charset=utf8" language="java"
	import="com.nsn.dcm.team2.model.*" errorPage="../error.jsp"%>
<%@include file="taglibs.jsp"%>
<link rel="stylesheet" href="css/style.css" />
<html>
	<%@include file="header.jsp"%>
		<div id="tablewrapper">
		<div id="tableheader">
        	<div class="search">
                <select id="columns" onchange="sorter.search('query')"></select>
                <input type="text" id="query" onkeyup="sorter.search('query')" />
            </div>
            <span class="details">
				<div>Records <span id="startrecord"></span>-<span id="endrecord"></span> of <span id="totalrecords"></span></div>
        		<div><a href="javascript:sorter.reset()">reset</a></div>
        	</span>
        </div>
	<table cellpadding="0" cellspacing="0" border="0" id="table" class="tinytable">
		<thead>
		<tr>
			<th><h3>PR ID</h3></th>
			<th><h3>Title</h3></th>
			<th><h3>Top Importance</h3></th>
			<th><h3>Severity</h3></th>
			<th><h3>Priority</h3></th>
			<th><h3>State</h3></th>
			<th><h3>Update</h3></th>
			<th><h3>Responsible Person</h3></th>
			<th><h3>Author</h3></th>
			<th><h3>Author Group</h3></th>
			<th><h3>Reported Date</h3></th>
		</tr>
		</thead>
		<tbody>
		<logic:iterate id="item" name="pronto" indexId="index">
			<tr>
				<td><bean:write name="item" property="prId" /></td>
				<td><bean:write name="item" property="prTitle" /></td>
				<td><bean:write name="item"
						property="prTopimportance" /></td>
				<td><bean:write name="item"
						property="prSeverity" /></td>
				<td><bean:write name="item"
						property="prPriority" /></td>
				<td><bean:write name="item" property="prState" /></td>
				<td><bean:write name="item" property="prUpdate" /></td>
				<td><bean:write name="item"
						property="prRespperson" /></td>
				<td><bean:write name="item" property="prAuthor" /></td>
				<td><bean:write name="item"
						property="prAuthorgrp" /></td>
				<td><bean:write name="item"
						property="prReportdate" /></td>
			</tr>
		</logic:iterate>
		</tbody>
	</table>
	
	 <div id="tablefooter">
          <div id="tablenav">
            	<div>
                    <img src="img/first.gif" width="16" height="16" alt="First Page" onclick="sorter.move(-1,true)" />
                    <img src="img/previous.gif" width="16" height="16" alt="First Page" onclick="sorter.move(-1)" />
                    <img src="img/next.gif" width="16" height="16" alt="First Page" onclick="sorter.move(1)" />
                    <img src="img/last.gif" width="16" height="16" alt="Last Page" onclick="sorter.move(1,true)" />
                </div>
                <div>
                	<select id="pagedropdown"></select>
				</div>
                <div>
                	<a href="javascript:sorter.showall()">view all</a>
                </div>
            </div>
			<div id="tablelocation">
            	<div>
                    <select onchange="sorter.size(this.value)">
                    <option value="5">5</option>
                        <option value="10" selected="selected">10</option>
                        <option value="20">20</option>
                        <option value="50">50</option>
                        <option value="100">100</option>
                    </select>
                    <span>Entries Per Page</span>
                </div>
                <div class="page">Page <span id="currentpage"></span> of <span id="totalpages"></span></div>
            </div>
        </div>
    </div>

	<script type="text/javascript" src="css/script.js"></script>
	<script type="text/javascript">
	var sorter = new TINY.table.sorter('sorter','table',{
		headclass:'head',
		ascclass:'asc',
		descclass:'desc',
		evenclass:'evenrow',
		oddclass:'oddrow',
		evenselclass:'evenselected',
		oddselclass:'oddselected',
		paginate:true,
		size:11,
		colddid:'columns',
		currentid:'currentpage',
		totalid:'totalpages',
		startingrecid:'startrecord',
		endingrecid:'endrecord',
		totalrecid:'totalrecords',
		hoverid:'selectedrow',
		pageddid:'pagedropdown',
		navid:'tablenav',
		sortcolumn:1,
		sortdir:1,
		init:true
	});
  </script>
	<%@include file="footer.jsp"%>
</html>