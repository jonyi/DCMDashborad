package com.nsn.dcm.team2.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;
/**
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright:</p>
 * <p>Company:</p>
 * @author Reggie(Li Gong)
 * @version 1.00
 *
 */
public class ViewProntoAction extends MgrBaseAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.setAttribute("pronto", mgr.getAllPronto());
		if (request.getAttribute("result") != null) {
			request.setAttribute("result", request.getAttribute("result"));
		}
		return actionMapping.findForward("viewPronto");
	}
}