package com.nsn.dcm.team2.common;

import java.util.TimerTask;

import com.nsn.dcm.team2.handler.DataCacheManage;

/**
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright:</p>
 * <p>Company:</p>
 * @author Reggie(Li Gong)
 * @version 1.00
 *
 */
public class SynchroTimerTask extends TimerTask {
	private int taskid = 0;

	public SynchroTimerTask(int taskid) {
		this.taskid = taskid;
	}

	public void run() {
		if (this.taskid == 0) {
			try {
				DataCacheManage.getSingleton().updateAllDataInfo();
			} catch (Exception ex) {

			}
		}
	}

	public static void main(String[] args) {

	}

}
