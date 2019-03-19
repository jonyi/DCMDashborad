package com.nsn.dcm.team2.struts;

import java.text.SimpleDateFormat;
import java.util.List;

import com.nsn.dcm.team2.dao.ProntoDao;
import com.nsn.dcm.team2.model.Pronto;
/**
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright:</p>
 * <p>Company:</p>
 * @author Reggie(Li Gong)
 * @version 1.00
 *
 */
public class MgrManagerImpl implements MgrManager {
	private ProntoDao prontoDao;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

	public void setProntoDao(ProntoDao prontoDao) {
		this.prontoDao = prontoDao;
	}

	public ProntoDao getProntoDao() {
		return prontoDao;
	}

	public void addPronto(Pronto pr){
		prontoDao.save(pr);
	}
	public void addPronto(String prId, String prTitle, String prTopimportance,
			String prSeverity, String prPriority, String prState,
			String prUpdate, String prRespperson, String prAuthor,
			String prAuthorgrp, String prReportdate) throws Exception {
		Pronto pr = new Pronto();
		pr.setPrId(prId);
		pr.setPrTitle(prTitle);
		pr.setPrTopimportance(prTopimportance);
		pr.setPrSeverity(prSeverity);
		pr.setPrPriority(prPriority);
		pr.setPrState(prState);
		pr.setPrUpdate(prUpdate);
		pr.setPrRespperson(prRespperson);
		pr.setPrAuthor(prAuthor);
		pr.setPrAuthorgrp(prAuthorgrp);
		pr.setPrReportdate(prReportdate);

		prontoDao.save(pr);
	}

	public List<Pronto> getAllPronto() {
		return prontoDao.findAll();
	}

}
