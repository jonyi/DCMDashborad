package com.nsn.dcm.team2.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.nsn.dcm.team2.utility.DashboardConstants;

/**
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright:</p>
 * <p>Company:</p>
 * @author Reggie(Li Gong)
 * @version 1.00
 *
 */
public class ConnectToMySql {
	private static Connection conn;
	private static Object lockO = new Object();
	private static ConnectToMySql instance = null;

	public static ConnectToMySql getSingleton() {
		if (null != instance) {
			return instance;
		} else {
			synchronized (lockO) {
				if (instance == null) {
					instance = new ConnectToMySql();
				}
			}
		}
		return instance;
	}

	public Connection getDBPoolService() {
		if (null == conn) {
			try {
				try {
					Class.forName(DashboardConstants.MYSQLDRIVER);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				String url = DashboardConstants.MYSQLURL;
				try {
					conn = DriverManager.getConnection(url, DashboardConstants.MYSQLNAME, DashboardConstants.MYSQLPWD);
					System.out.println("Database connected!");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (Exception ex) {
				return null;
			}
		}
		return conn;
	}

	public static void main(String[] args) {

	}
}
