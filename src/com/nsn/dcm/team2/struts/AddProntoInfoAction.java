package com.nsn.dcm.team2.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;
/**
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright:</p>
 * <p>Company:</p>
 * @author Reggie(Li Gong)
 * @version 1.00
 *
 */
public class AddProntoInfoAction extends MgrBaseAction {
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DynaValidatorForm form = (DynaValidatorForm) actionForm;

		String prid = (String) form.get("prId");
		String prTitle = (String) form.get("prTitle");
		String prTopimportance = (String) form.get("prTopimportance");
		String prSeverity = (String) form.get("prSeverity");
		String prPriority = (String) form.get("prPriority");
		String prState = (String) form.get("prState");
		String prUpdate = (String) form.get("prUpdate");
		String prRespperson = (String) form.get("prRespperson");
		String prAuthor = (String) form.get("prAuthor");
		String prAuthorgrp = (String) form.get("prAuthorgrp");
		String prReportdate = (String) form.get("prReportdate");

		mgr.addPronto(prid, prTitle, prTopimportance, prSeverity, prPriority,
				prState, prUpdate, prRespperson, prAuthor, prAuthorgrp,
				prReportdate);
		request.setAttribute("result", "add one pronto record succ");
		return actionMapping.findForward("success");

	}
}