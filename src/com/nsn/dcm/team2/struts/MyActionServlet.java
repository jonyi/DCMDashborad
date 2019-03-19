package com.nsn.dcm.team2.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright:</p>
 * <p>Company:</p>
 * @author Reggie(Li Gong)
 * @version 1.00
 *
 */  
public class MyActionServlet extends org.apache.struts.action.ActionServlet
{
	protected void process(HttpServletRequest request, HttpServletResponse response)
		throws java.io.IOException, javax.servlet.ServletException
	{
		request.setCharacterEncoding("utf8");
		super.process(request, response);
	}
}