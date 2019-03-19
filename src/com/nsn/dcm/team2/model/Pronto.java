package com.nsn.dcm.team2.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
public class Pronto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String prId;
	private String prTitle;
	private String prTopimportance;
	private String prSeverity;
	private String prPriority;
	private String prState;
	private String prUpdate;
	private String prRespperson;
	private String prAuthor;
	private String prAuthorgrp;
	private String prReportdate;
	
	public Pronto() {
	}
	
	public void insertProntoInfo(Pronto pr) {
		String insertSql = "insert into info_pronto values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = ConnectToMySql.getSingleton().getDBPoolService();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(insertSql);
			pst.setString(1, pr.getPrId());
			pst.setString(2, pr.getPrTitle());
			pst.setString(3, pr.getPrTopimportance());
			pst.setString(4, pr.getPrSeverity());
			pst.setString(5, pr.getPrPriority());
			pst.setString(6, pr.getPrState());
			pst.setString(7, pr.getPrUpdate());
			pst.setString(8, pr.getPrRespperson());
			pst.setString(9, pr.getPrAuthor());
			pst.setString(10, pr.getPrAuthorgrp());
			pst.setString(11, pr.getPrReportdate());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
	}
	public Pronto(String prId) {
		this.prId = prId;
	}

	public int countPRInfo() {
		int countPRInfo = 0;
		String countSql = "select count(*) from info_pronto";
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

	public Pronto( String prId, String prTitle,
			String prTopimportance, String prSeverity, String prPriority,
			String prState, String prUpdate, String prRespperson,
			String prAuthor, String prAuthorgrp, String prReportdate, String prOpendate, String prClosedate) {
		this.prId = prId;
		this.prTitle = prTitle;
		this.prTopimportance = prTopimportance;
		this.prSeverity = prSeverity;
		this.prPriority = prPriority;
		this.prState = prState;
		this.prUpdate = prUpdate;
		this.prRespperson = prRespperson;
		this.prAuthor = prAuthor;
		this.prAuthorgrp = prAuthorgrp;
		this.prReportdate = prReportdate;
	}

	public int countProntoInfo() {
		int countPrInfo = 0;
		String countSql = "select count(*) from info_pronto";
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
			rs = stmt.executeQuery(countSql);
			rs.next();
			countPrInfo = rs.getInt(1);
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
		return countPrInfo;
	}
	
	public void truncateProntoInfo() {
		
		String trunSql = "truncate table info_pronto";
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
			rs = stmt.executeQuery(trunSql);
		
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
		
	}



	public String getPrId() {
		return this.prId;
	}

	public void setPrId(String prId) {
		this.prId = prId;
	}

	public String getPrTitle() {
		return this.prTitle;
	}

	public void setPrTitle(String prTitle) {
		this.prTitle = prTitle;
	}

	public String getPrTopimportance() {
		return this.prTopimportance;
	}

	public void setPrTopimportance(String prTopimportance) {
		this.prTopimportance = prTopimportance;
	}

	public String getPrSeverity() {
		return this.prSeverity;
	}

	public void setPrSeverity(String prSeverity) {
		this.prSeverity = prSeverity;
	}

	public String getPrPriority() {
		return this.prPriority;
	}

	public void setPrPriority(String prPriority) {
		this.prPriority = prPriority;
	}

	public String getPrState() {
		return this.prState;
	}

	public void setPrState(String prState) {
		this.prState = prState;
	}

	public String getPrUpdate() {
		return this.prUpdate;
	}

	public void setPrUpdate(String prUpdate) {
		this.prUpdate = prUpdate;
	}

	public String getPrRespperson() {
		return this.prRespperson;
	}

	public void setPrRespperson(String prRespperson) {
		this.prRespperson = prRespperson;
	}

	public String getPrAuthor() {
		return this.prAuthor;
	}

	public void setPrAuthor(String prAuthor) {
		this.prAuthor = prAuthor;
	}

	public String getPrAuthorgrp() {
		return this.prAuthorgrp;
	}

	public void setPrAuthorgrp(String prAuthorgrp) {
		this.prAuthorgrp = prAuthorgrp;
	}

	public String getPrReportdate() {
		return this.prReportdate;
	}

	public void setPrReportdate(String prReportdate) {
		this.prReportdate = prReportdate;
	}

	
}
