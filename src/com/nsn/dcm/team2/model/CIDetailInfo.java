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
public class CIDetailInfo extends BaseBlockObject{

	private String cidtltester;
	private String cidtltotalcase;
	private String cidtlfailedcase;
	private String cidtlreportdate;
	private String cidtlfailedversion;
	private String cidtlfailedcasename;
	private String cidtlfaileddate;
	
	
	public void printCIDetailInfo(CIDetailInfo cidtlinfo){
//		System.out.println("cidtltester=== "+cidtlinfo.getCidtltester());
//		System.out.println("cidtltotalcase=== "+cidtlinfo.getCidtltotalcase());
//		System.out.println("cidtlfailedcase=== "+cidtlinfo.getCidtlfailedcase());
//		System.out.println("cidtlreportdate=== "+cidtlinfo.getCidtlreportdate());
//		System.out.println("cidtlfailedversion=== "+cidtlinfo.getCidtlfailedversion());
//		System.out.println("cidtlfailedcasename=== "+cidtlinfo.getCidtlfailedcasename());
//		System.out.println("cidtlfaileddate=== "+cidtlinfo.getCidtlfaileddate());
		
	}
	
	public int countCIDTLInfo(String tester, String date) {
		int countCIInfo = 0;
		String countSql = "select count(*) from info_cidetail where cidetail_tester = '"
				+ tester + "' and cidetail_reportdate = '"
				+ date +"'";
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

	public void insertCIDTLInfo(CIDetailInfo ciInfo) {
		String insertSql = "insert into info_cidetail values(?, ?,?,?,?,?,?)";
		Connection conn = ConnectToMySql.getSingleton().getDBPoolService();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(insertSql);
			pst.setString(1, ciInfo.getCidtltester());
			pst.setString(2, ciInfo.getCidtltotalcase());
			pst.setString(3, ciInfo.getCidtlfailedcase());
			pst.setString(4, ciInfo.getCidtlfailedversion());
			pst.setString(5, ciInfo.getCidtlfailedcasename());
			pst.setString(6, ciInfo.getCidtlfaileddate());
			pst.setString(7, ciInfo.getCidtlreportdate());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
	}
	

	public void updateCIDTLInfo(CIDetailInfo ciInfo) {
		String updateSql = "update info_cidetail set cidetail_totalcase=?, cidetail_failedcase=?,cidetail_failedversion=?,cidetail_failedcasename=?,cidetail_faileddate=? where cidetail_tester = '"
				+ ciInfo.getCidtltester() + "' and cidetail_reportdate = '"
				+ ciInfo.getCidtlreportdate() +"'";
		Connection conn = ConnectToMySql.getSingleton().getDBPoolService();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(updateSql);
			pst.setString(1, ciInfo.getCidtltotalcase());
			pst.setString(2, ciInfo.getCidtlfailedcase());
			pst.setString(3, ciInfo.getCidtlfailedversion());
			pst.setString(4, ciInfo.getCidtlfailedcasename());
			pst.setString(5, ciInfo.getCidtlfaileddate());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
	}
	
	public String getCidtltester() {
		return cidtltester;
	}
	public void setCidtltester(String cidtltester) {
		this.cidtltester = cidtltester;
	}
	public String getCidtltotalcase() {
		return cidtltotalcase;
	}
	public void setCidtltotalcase(String cidtltotalcase) {
		this.cidtltotalcase = cidtltotalcase;
	}
	public String getCidtlfailedcase() {
		return cidtlfailedcase;
	}
	public void setCidtlfailedcase(String cidtlfailedcase) {
		this.cidtlfailedcase = cidtlfailedcase;
	}
	public String getCidtlreportdate() {
		return cidtlreportdate;
	}
	public void setCidtlreportdate(String cidtlreportdate) {
		this.cidtlreportdate = cidtlreportdate;
	}

	public String getCidtlfailedversion() {
		return cidtlfailedversion;
	}

	public void setCidtlfailedversion(String cidtlfailedversion) {
		this.cidtlfailedversion = cidtlfailedversion;
	}

	public String getCidtlfailedcasename() {
		return cidtlfailedcasename;
	}

	public void setCidtlfailedcasename(String cidtlfailedcasename) {
		this.cidtlfailedcasename = cidtlfailedcasename;
	}

	public String getCidtlfaileddate() {
		return cidtlfaileddate;
	}

	public void setCidtlfaileddate(String cidtlfaileddate) {
		this.cidtlfaileddate = cidtlfaileddate;
	}
	
	
	
}
