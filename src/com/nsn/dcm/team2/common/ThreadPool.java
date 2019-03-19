package com.nsn.dcm.team2.common;

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
public class ThreadPool {

	private static ThreadPool pool = null;
	private static final Object mutex = new Object();

	private ThreadPool() {
	}

	public static ThreadPool newIntance() {
		if (pool != null) {
			return pool;
		}
		synchronized (mutex) {
			if (pool == null) {
				pool = new ThreadPool();
			}
			return pool;
		}
	}

	public void synchroUTInfo() {
		synchronized (mutex) {
			new SynchroThread(0).start();
		}
	}

	class SynchroThread extends Thread {
		private int tag;

		public SynchroThread(int tag) {
			this.tag = tag;
		}

		public void run() {
			if (tag == 0) {
				try {
					DataCacheManage.getSingleton().updateAllDataInfo();
				} catch (Exception ex) {

				}
			}
		}
	}
}
