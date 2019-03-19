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
public class PrCountInfo {

	private String prcountnum;
	private String prcountdate;

	/**
	 * @return the prcountnum
	 */
	public String getPrcountnum() {
		return prcountnum;
	}

	/**
	 * @param prcountnum
	 *            the prcountnum to set
	 */
	public void setPrcountnum(String prcountnum) {
		this.prcountnum = prcountnum;
	}

	/**
	 * @return the prcountdate
	 */
	public String getPrcountdate() {
		return prcountdate;
	}

	/**
	 * @param prcountdate
	 *            the prcountdate to set
	 */
	public void setPrcountdate(String prcountdate) {
		this.prcountdate = prcountdate;
	}

	public void insertPRCountInfo(PrCountInfo prinfo) {
		String insertSql = "insert into info_prcount values(?, ?)";
		Connection conn = ConnectToMySql.getSingleton().getDBPoolService();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(insertSql);
			pst.setString(1, prinfo.getPrcountnum());
			pst.setString(2, prinfo.getPrcountdate());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
	}

	public int countPRCountInfo(String date) {
		int countPRInfo = 0;
		String countSql = "select count(*) from info_prcount where prcount_date = '"
				+ date + "'";
		Connection conn = ConnectToMySql.getSingleton().getDBPoolService();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(countSql);
			ResultSet rs = null;
			rs = pst.executeQuery(countSql);
			rs.next();
			countPRInfo = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return countPRInfo;
	}

	public void updatePRCountInfo(PrCountInfo prinfo) {
		String updateSql = "update info_prcount set prcount_num = ? where prcount_date = ?";

		Connection conn = ConnectToMySql.getSingleton().getDBPoolService();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(updateSql);
			pst.setString(1, prinfo.getPrcountnum());
			pst.setString(2, prinfo.getPrcountdate());

			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
	}

}
