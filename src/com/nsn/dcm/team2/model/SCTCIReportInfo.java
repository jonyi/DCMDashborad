package com.nsn.dcm.team2.model;
/**
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright:</p>
 * <p>Company:</p>
 * @author Reggie(Li Gong)
 * @version 1.00
 *
 */
public class SCTCIReportInfo {

	private String index;
	private String casename;
	private String version1;
	private String version2;
	private String failuredate;
	private String caseowner;
	
	
	/**
	 * @return the index
	 */
	public String getIndex() {
		return index;
	}
	/**
	 * @param index the index to set
	 */
	public void setIndex(String index) {
		this.index = index;
	}
	/**
	 * @return the casename
	 */
	public String getCasename() {
		return casename;
	}
	/**
	 * @param casename the casename to set
	 */
	public void setCasename(String casename) {
		this.casename = casename;
	}
	/**
	 * @return the version1
	 */
	public String getVersion1() {
		return version1;
	}
	/**
	 * @param version1 the version1 to set
	 */
	public void setVersion1(String version1) {
		this.version1 = version1;
	}
	/**
	 * @return the version2
	 */
	public String getVersion2() {
		return version2;
	}
	/**
	 * @param version2 the version2 to set
	 */
	public void setVersion2(String version2) {
		this.version2 = version2;
	}
	/**
	 * @return the failuredate
	 */
	public String getFailuredate() {
		return failuredate;
	}
	/**
	 * @param failuredate the failuredate to set
	 */
	public void setFailuredate(String failuredate) {
		this.failuredate = failuredate;
	}
	/**
	 * @return the caseowner
	 */
	public String getCaseowner() {
		return caseowner;
	}
	/**
	 * @param caseowner the caseowner to set
	 */
	public void setCaseowner(String caseowner) {
		this.caseowner = caseowner;
	}
}
