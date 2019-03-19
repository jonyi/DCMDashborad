package com.nsn.dcm.team2.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeMap;

import com.nsn.dcm.team2.common.ConnectToMySql;
import com.nsn.dcm.team2.model.CIDetailInfo;
import com.nsn.dcm.team2.utility.DcmUtil;
/**
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright:</p>
 * <p>Company:</p>
 * @author Reggie(Li Gong)
 * @version 1.00
 *
 */
public class DataQuery {
	
	private static Object lockO = new Object();
	private static DataQuery dataQuery = null;
	
	public static DataQuery getSingleton() {
		if (null != dataQuery) {
			return dataQuery;
		} else {
			synchronized (lockO) {
				if (dataQuery == null) {
					dataQuery = new DataQuery();

				}
			}
		}
		return dataQuery;
	}
	
	
	public String showUTdata(String date, String modulename) {
		String rate = "";
		String utsql = "select ut_linecoverage from info_utcoverage where ut_reportdate = '"
				+ date + "' and ut_modulename='"
				+ modulename+"'";
		rate = this.searchbydate(utsql, date);
		return rate;
	}

	public String showCIdata(String date) {
		String rate = "";
		String cisql = "select ci_passrate from info_ci where ci_reportdate = '"
				+ date + "'";
		rate = this.searchbydate(cisql, date);
		return rate;
	}

	public String showMTdata(String date) {
		String rate = "";
		String mtsql = "select mt_passrate from info_mt where mt_reportdate = '"
				+ date + "'";
		rate = this.searchbydate(mtsql, date);
		return rate;
	}

	public String showProntodata(String date) {
		String countnum = "";
		String prsql = "select prcount_num from info_prcount where prcount_date = '"
				+ date + "'";
		countnum = this.searchbydate(prsql, date);
		return countnum;
	}

	public String searchbydate(String selectSql, String date) {
		String passrate = "";
		Connection conn = ConnectToMySql.getSingleton().getDBPoolService();
		PreparedStatement pst;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(selectSql);
			rs = pst.executeQuery(selectSql);
			while (rs.next()) {
				if (selectSql.contains("info_utcoverage")) {
					passrate = (String) rs.getObject("ut_linecoverage");
				} else {
					passrate = rs.getString(1);
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				rs.close();
				// conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return passrate;
	}
	
	public String getAvgdata(String date){
		String returnavgdata = "";
		String selectSql = "select * from info_utcoverage where ut_reportdate='"
				+ date + "'";
		int totallines = 0;
		int totalcovlines = 0;
		Connection conn = ConnectToMySql.getSingleton().getDBPoolService();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(selectSql);
			while (rs.next()) {
				totalcovlines += DcmUtil.checkString2int(rs.getString(3), 0);
				totallines += DcmUtil.checkString2int(rs.getString(4), 0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				// conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	

		if (totallines > 0) {
			returnavgdata = new Double((float)totalcovlines*100 / totallines) + "";
		}
		System.out.println("total ut covlines=== " + totalcovlines);
		System.out.println("total ut lines=====  " + totallines);
		return returnavgdata;
	}
	
	
	public TreeMap<String, CIDetailInfo> getCIDTLdata(){
		String date = DcmUtil.getNowDate();
		TreeMap<String, CIDetailInfo> treehs = new TreeMap<String, CIDetailInfo>();
	    String selectSql = "select * from info_cidetail where cidetail_reportdate='"+date+"'";

		Connection conn = ConnectToMySql.getSingleton().getDBPoolService();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(selectSql);
			while (rs.next()) {
				CIDetailInfo cdtl = new CIDetailInfo();
				cdtl.setCidtltester(rs.getString(1));
				cdtl.setCidtltotalcase(rs.getString(2));
				cdtl.setCidtlfailedcase(rs.getString(3));
				cdtl.setCidtlfailedversion(rs.getString(4));
				cdtl.setCidtlfailedcasename(rs.getString(5));
				cdtl.setCidtlfaileddate(rs.getString(6));
				cdtl.setCidtlreportdate(rs.getString(7));
				treehs.put(rs.getString(1), cdtl);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				// conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	

		return treehs;
	}
	

	public int countPRHisInfo(String severity) {
		int countPRInfo = 0;
		String countSql = "select count(*) from info_prontohistory where pr_severity = '"
				+ severity +"'";
		Connection conn = ConnectToMySql.getSingleton().getDBPoolService();
		PreparedStatement pst;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(countSql);
			rs = pst.executeQuery(countSql);
			rs.next();
			countPRInfo = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return countPRInfo;
	}
}
