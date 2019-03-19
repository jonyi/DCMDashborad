package com.nsn.dcm.team2.struts;

import java.util.*;

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
public interface MgrManager
{

	void addPronto(Pronto pr);
    void addPronto(String prId , String prTitle, String prTopimportance,String prSeverity,
    		String prPriority,String prState,String prUpdate,String prRespperson,String prAuthor,
    		String prAuthorgrp,String prReportdate)throws Exception;
   
    List<Pronto> getAllPronto()throws Exception;
}