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
public class MTInfo {

	private String modulename; // Rake
	private String passrate;
	private String reportdate;

	/**
	 * @return the modulename
	 */
	public String getModulename() {
		return modulename;
	}

	/**
	 * @param modulename
	 *            the modulename to set
	 */
	public void setModulename(String modulename) {
		this.modulename = modulename;
	}

	/**
	 * @return the passrate
	 */
	public String getPassrate() {
		return passrate;
	}

	/**
	 * @param passrate
	 *            the passrate to set
	 */
	public void setPassrate(String passrate) {
		this.passrate = passrate;
	}

	/**
	 * @return the reportdate
	 */
	public String getReportdate() {
		return reportdate;
	}

	/**
	 * @param reportdate
	 *            the reportdate to set
	 */
	public void setReportdate(String reportdate) {
		this.reportdate = reportdate;
	}

	public int countMTInfo(String date) {
		int countMTInfo = 0;
		String countSql = "select count(*) from info_mt where mt_reportdate = '"
				+ date + "'";
		Connection conn = ConnectToMySql.getSingleton().getDBPoolService();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(countSql);
			ResultSet rs = null;
			rs = pst.executeQuery(countSql);
			rs.next();
			countMTInfo = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return countMTInfo;
	}

	public void insertMTInfo(MTInfo mtInfo) {
		String insertSql = "insert into info_mt values(?, ?, ?)";

		Connection conn = ConnectToMySql.getSingleton().getDBPoolService();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(insertSql);
			pst.setString(1, mtInfo.getModulename());
			pst.setString(2, mtInfo.getPassrate());
			pst.setString(3, mtInfo.getReportdate());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
	}

	public void delMTInfo(MTInfo mtInfo) {
		String delSql = "delete from info_mt where mt_reportdate = ?";

		Connection conn = ConnectToMySql.getSingleton().getDBPoolService();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(delSql);
			pst.setString(1, mtInfo.getReportdate());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
	}

	public void updateMTInfo(MTInfo mtInfo) {
		String updateSql = "update info_mt set mt_modulename = ?, mt_passrate = ? where mt_reportdate = ?";

		Connection conn = ConnectToMySql.getSingleton().getDBPoolService();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(updateSql);
			pst.setString(1, mtInfo.getModulename());
			pst.setString(2, mtInfo.getPassrate());
			pst.setString(3, mtInfo.getReportdate());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
	}

}
