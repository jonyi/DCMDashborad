<%@ page language="java" contentType="text/html; charset=utf8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nsn.dcm.team2.handler.*"%>
<%@ page import="com.nsn.dcm.team2.utility.*"%>
<%@ page import="com.nsn.dcm.team2.model.*"%>
<html>
<%@include file="header.jsp"%>
<title>DCM SW2 CI Detail Info</title>
<link rel="stylesheet" href="css/style.css" />
	<%
		TreeMap<String, CIDetailInfo> treehs = DataQuery.getSingleton().getCIDTLdata();
		Iterator<String> sortiter = treehs.keySet().iterator();
	%>
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
			<th><h3>Tester</h3></th>
			<th><h3>Total Cases</h3></th>
			<th><h3>Failed Cases</h3></th>
			<th><h3>FailureTestCaseName</h3></th>
			<th><h3>LastFailureVersion</h3></th>
			<th><h3>LastFailureDate</h3></th>
			<th><h3>Date</h3></th>
		</tr>
		</thead>
		<tbody>
		<%
		while (sortiter.hasNext())
		{
			String classkey = (String) sortiter.next();
			CIDetailInfo cidtlinfo = (CIDetailInfo) treehs.get(classkey);
			if (cidtlinfo != null) 
			{
		%>
		<tr>
			<td><%=cidtlinfo.getCidtltester()%></td>
			<td><%=cidtlinfo.getCidtltotalcase()%></td>
			<td><%=cidtlinfo.getCidtlfailedcase()%></td>
		<%	
			if("".equals(cidtlinfo.getCidtlfailedcasename()))
			{ %>
			<td>N/A</td>
			<td>N/A</td>
			<td>N/A</td>
		<%	}
			else
			{ %>
			<td><%=cidtlinfo.getCidtlfailedcasename()%></td>
			<td><%=cidtlinfo.getCidtlfailedversion()%></td>
			<td><%=cidtlinfo.getCidtlfaileddate()%></td>
		<%	}
		%>
			<td><%=cidtlinfo.getCidtlreportdate()%></td>
		</tr>
		<%
			}
		}
		%>
		</tbody>
	</table>
	<div id="tablefooter">
         <div id="tablenav">
            	<div>
                    <img src="img/first.gif" width="16" height="16" alt="First Page" onclick="sorter.move(-1,true)" />
                    <img src="img/previous.gif" width="16" height="16" alt="Previous Page" onclick="sorter.move(-1)" />
                    <img src="img/next.gif" width="16" height="16" alt="Next Page" onclick="sorter.move(1)" />
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

	<script type="text/javascript" src="script.js"></script>
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
		size:7,
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