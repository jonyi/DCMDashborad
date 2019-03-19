package com.nsn.dcm.team2.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nsn.dcm.team2.common.ConnectToMySql;
/**
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright:</p>
 * <p>Company:</p>
 * @author Reggie(Li Gong)
 * @version 1.00
 *
 */
public class CIInfo {

	private String passrate;
	private String reportdate;

	public String getReportdate() {
		return reportdate;
	}

	public void setReportdate(String reportdate) {
		this.reportdate = reportdate;
	}

	public String getPassrate() {
		return passrate;
	}

	public void setPassrate(String passrate) {
		this.passrate = passrate;
	}

	public int countCIInfo(String date) {
		int countCIInfo = 0;
		String countSql = "select count(*) from info_ci where ci_reportdate = '"
				+ date + "'";
		Connection conn = ConnectToMySql.getSingleton().getDBPoolService();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(countSql);
			ResultSet rs = null;
			rs = pst.executeQuery(countSql);
			rs.next();
			countCIInfo = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
		return countCIInfo;
	}

	public void insertCIInfo(CIInfo ciInfo) {
		String insertSql = "insert into info_ci values(?, ?)";
		Connection conn = ConnectToMySql.getSingleton().getDBPoolService();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(insertSql);
			pst.setString(1, ciInfo.getPassrate());
			pst.setString(2, ciInfo.getReportdate());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
	}

	public void delCIInfo(CIInfo ciInfo) {
		String delSql = "delete from info_ci where ci_reportdate = ?";
		Connection conn = ConnectToMySql.getSingleton().getDBPoolService();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(delSql);
			pst.setString(1, ciInfo.getReportdate());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
	}

	public void updateCIInfo(CIInfo ciInfo) {
		String updateSql = "update info_ci set ci_passrate=? where ci_reportdate = ?";
		Connection conn = ConnectToMySql.getSingleton().getDBPoolService();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(updateSql);
			pst.setString(1, ciInfo.getPassrate());
			pst.setString(2, ciInfo.getReportdate());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
	}

}
