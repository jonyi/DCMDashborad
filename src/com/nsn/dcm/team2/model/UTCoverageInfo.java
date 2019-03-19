package com.nsn.dcm.team2.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import com.nsn.dcm.team2.common.ConnectToMySql;
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
public class UTCoverageInfo {

	private String modulename; // Rake
	private String teamname; // Tieto Team1 Team2 Misc
	private String coveredline; //
	private String totalline;
	private String linecoverage;
	private String coveredbranch;
	private String totalbranch;
	private String branchcoverage;

	private String reportdate;
	private String reportpath;

	public void printCoverageInfo(UTCoverageInfo covInfo) {
		System.out.println(covInfo.getModulename());
		System.out.println(covInfo.getTeamname());
		System.out.println(covInfo.getCoveredline());
		System.out.println(covInfo.getTotalline());
		System.out.println(covInfo.getLinecoverage());
		System.out.println(covInfo.getCoveredbranch());
		System.out.println(covInfo.getTotalbranch());
		System.out.println(covInfo.getBranchcoverage());
	}

	public String getCoverageKey(String reportdate, String teamname,
			String modulename) {
		return reportdate + ":" + teamname + ":" + modulename;
	}

	public int countUTInfo(String date, String teamname, String modulename) {
		String countUTInfo = "";
		String countSql = "select count(*) from info_utcoverage where ut_reportdate = '"
				+ date + "' and ut_teamname = '"
				+ teamname + "' and ut_modulename = '"
				+ modulename +"'";
		Connection conn = ConnectToMySql.getSingleton().getDBPoolService();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(countSql);
			ResultSet rs = null;
			rs = pst.executeQuery(countSql);
			rs.next();
			countUTInfo = rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		int countnum = DcmUtil.checkString2int(countUTInfo, 0);
		return countnum;
	}

	public void insertCoverageInfo(UTCoverageInfo coverageInfo) {
		String insertSql = "insert into info_utcoverage values(?, ?, ?,?,?,?,?,?,?,?)";
		Connection conn = ConnectToMySql.getSingleton().getDBPoolService();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(insertSql);
			pst.setString(1, coverageInfo.getModulename());
			pst.setString(2, coverageInfo.getTeamname());
			pst.setString(3, coverageInfo.getCoveredline());
			pst.setString(4, coverageInfo.getTotalline());
			pst.setString(5, coverageInfo.getLinecoverage());
			pst.setString(6, coverageInfo.getCoveredbranch());
			pst.setString(7, coverageInfo.getTotalbranch());
			pst.setString(8, coverageInfo.getBranchcoverage());
			pst.setString(9, coverageInfo.getReportdate());
			pst.setString(10, coverageInfo.getReportpath());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
	}

	public void delCoverageInfo(UTCoverageInfo coverageInfo) {
		String delSql = "delete from info_utcoverage where ut_reportdate = ?";

		Connection conn = ConnectToMySql.getSingleton().getDBPoolService();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(delSql);
			pst.setString(1, coverageInfo.getReportdate());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
	}

	public void updateCoverageInfo(UTCoverageInfo coverageInfo) {
		String updateSql = "update info_utcoverage set ut_coveredline = ?, ut_totalline = ?, ut_linecoverage = ?, ut_coveredbranch = ?, ut_totalbranch = ?, ut_branchcoverage = ?, ut_reportpath = ? where ut_modulename = ? and ut_teamname = ? and ut_reportdate = ?";

		Connection conn = ConnectToMySql.getSingleton().getDBPoolService();
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(updateSql);

			pst.setString(1, coverageInfo.getCoveredline());
			pst.setString(2, coverageInfo.getTotalline());
			pst.setString(3, coverageInfo.getLinecoverage());
			pst.setString(4, coverageInfo.getCoveredbranch());
			pst.setString(5, coverageInfo.getTotalbranch());
			pst.setString(6, coverageInfo.getBranchcoverage());
			pst.setString(7, coverageInfo.getReportpath());
			pst.setString(8, coverageInfo.getModulename());
			pst.setString(9, coverageInfo.getTeamname());
			pst.setString(10, coverageInfo.getReportdate());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
	}

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
	 * @return the teamname
	 */
	public String getTeamname() {
		return teamname;
	}

	/**
	 * @param teamname
	 *            the teamname to set
	 */
	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	/**
	 * @return the coveredline
	 */
	public String getCoveredline() {
		return coveredline;
	}

	/**
	 * @param coveredline
	 *            the coveredline to set
	 */
	public void setCoveredline(String coveredline) {
		this.coveredline = coveredline;
	}

	/**
	 * @return the totalline
	 */
	public String getTotalline() {
		return totalline;
	}

	/**
	 * @param totalline
	 *            the totalline to set
	 */
	public void setTotalline(String totalline) {
		this.totalline = totalline;
	}

	/**
	 * @return the linecoverage
	 */
	public String getLinecoverage() {
		return linecoverage;
	}

	/**
	 * @param linecoverage
	 *            the linecoverage to set
	 */
	public void setLinecoverage(String linecoverage) {
		this.linecoverage = linecoverage;
	}

	/**
	 * @return the coveredbranch
	 */
	public String getCoveredbranch() {
		return coveredbranch;
	}

	/**
	 * @param coveredbranch
	 *            the coveredbranch to set
	 */
	public void setCoveredbranch(String coveredbranch) {
		this.coveredbranch = coveredbranch;
	}

	/**
	 * @return the totalbranch
	 */
	public String getTotalbranch() {
		return totalbranch;
	}

	/**
	 * @param totalbranch
	 *            the totalbranch to set
	 */
	public void setTotalbranch(String totalbranch) {
		this.totalbranch = totalbranch;
	}

	/**
	 * @return the branchcoverage
	 */
	public String getBranchcoverage() {
		return branchcoverage;
	}

	/**
	 * @param branchcoverage
	 *            the branchcoverage to set
	 */
	public void setBranchcoverage(String branchcoverage) {
		this.branchcoverage = branchcoverage;
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

	/**
	 * @return the reportpath
	 */
	public String getReportpath() {
		return reportpath;
	}

	/**
	 * @param reportpath
	 *            the reportpath to set
	 */
	public void setReportpath(String reportpath) {
		this.reportpath = reportpath;
	}

	
}
