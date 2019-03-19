package com.nsn.dcm.team2.struts;


import org.apache.struts.action.Action;

/**
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright:</p>
 * <p>Company:</p>
 * @author Reggie(Li Gong)
 * @version 1.00
 *
 */
public class MgrBaseAction extends Action
{
    protected MgrManager mgr;

    public void setMgr(MgrManager mgr)
    {
        this.mgr = mgr;
    }
}