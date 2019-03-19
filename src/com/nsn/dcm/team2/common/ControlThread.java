package com.nsn.dcm.team2.common;

import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright:</p>
 * <p>Company:</p>
 * @author Reggie(Li Gong)
 * @version 1.00
 *
 */
public class ControlThread {

	private static ControlThread control = null;
	private static final Object mutex = new Object();
	private static Timer timer = new Timer();
	private static SynchroTimerTask uttask = new SynchroTimerTask(0);

	private ControlThread() {

	}

	public static ControlThread getInstance() {
		if (control != null) {
			return control;
		}
		synchronized (mutex) {
			if (control == null) {
				control = new ControlThread();
				control.start();
			}
			return control;
		}
	}

	private void start() {
		//update data every 60 mins
		timer.schedule(uttask, 0, 60 * 60000);
	}

}
