package com.nsn.dcm.team2.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.nsn.dcm.team2.common.ConnectToMySql;




public class PrHistoryInfo {
	private String prid;
	private String prtitle;
	private String prseverity;
	private String prrespperson;
	private String prrootcause;
	private String prreportdate;
	private String propendate;
	private String prclosedate;
	


	
	public void insertProntoHisInfo(PrHistoryInfo pr) {
		String insertSql = "insert into info_prontohistory values(?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = ConnectToMySql.getSingleton().getDBPoolService();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(insertSql);
			pst.setString(1, pr.getPrid());
			pst.setString(2, pr.getPrtitle());
			pst.setString(3, pr.getPrseverity());
			pst.setString(4, pr.getPrrespperson());
			pst.setString(5, pr.getPrrootcause());
			pst.setString(6, pr.getPrreportdate());
			pst.setString(7, pr.getPropendate());
			pst.setString(8, pr.getPrclosedate());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
	}
	
public void truncateProntoHisInfo() {
		
		String trunSql = "truncate table info_prontohistory";
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

	
	
	public String getPrid() {
		return prid;
	}
	public void setPrid(String prid) {
		this.prid = prid;
	}
	public String getPrtitle() {
		return prtitle;
	}
	public void setPrtitle(String prtitle) {
		this.prtitle = prtitle;
	}
	public String getPrseverity() {
		return prseverity;
	}
	public void setPrseverity(String prseverity) {
		this.prseverity = prseverity;
	}
	public String getPrrespperson() {
		return prrespperson;
	}
	public void setPrrespperson(String prrespperson) {
		this.prrespperson = prrespperson;
	}
	public String getPrrootcause() {
		return prrootcause;
	}
	public void setPrrootcause(String prrootcause) {
		this.prrootcause = prrootcause;
	}
	public String getPrreportdate() {
		return prreportdate;
	}
	public void setPrreportdate(String prreportdate) {
		this.prreportdate = prreportdate;
	}
	public String getPropendate() {
		return propendate;
	}
	public void setPropendate(String propendate) {
		this.propendate = propendate;
	}
	public String getPrclosedate() {
		return prclosedate;
	}
	public void setPrclosedate(String prclosedate) {
		this.prclosedate = prclosedate;
	}
	
	

}
