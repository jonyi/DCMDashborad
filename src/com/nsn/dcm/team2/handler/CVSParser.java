package com.nsn.dcm.team2.handler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;

import com.nsn.dcm.team2.model.UTCoverageInfo;
import com.nsn.dcm.team2.utility.DashboardConstants;
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
public class CVSParser {
	private String[] _columnHeadings = null;
	private static final String COMMENT_DELIM1 = ";";
	private static final String COMMENT_DELIM2 = "\";";
	private static CVSParser cvsParser = null;
	private static final Object mutex = new Object();
		
	private CVSParser() {

	}

	public static CVSParser getInstance() {
		if (cvsParser != null) {
			return cvsParser;
		}
		synchronized (mutex) {
			if (cvsParser == null) {
				cvsParser = new CVSParser();
				
			}
			return cvsParser;
		}
	}

	
	public void readCVSFile(String csvFileName) throws Exception {
		String fullFileName = DashboardConstants.utlocalpathpre + csvFileName + DashboardConstants.utlocalpathsuf;
		String nowtime = DcmUtil.date2str(new Date(), "yyyy.MM.dd");
		UTCoverageInfo coverageInfo = new UTCoverageInfo();
		BufferedReader reader = null;
		String inLine = "";
		FileReader fReader = null;
		try {

			fReader = new FileReader(fullFileName);
			reader = new BufferedReader(fReader);

			while ((inLine = reader.readLine()) != null) {
				if (inLine.contains("Team2")) {
					if (inLine.length() > 0) {
						String[] fields = inLine.split(",");
						coverageInfo.setTeamname(fields[0]);
						coverageInfo.setCoveredline(fields[1]);
						coverageInfo.setTotalline(fields[2]);
						int tmpcov = DcmUtil.checkString2int(fields[2], 100);
						if(tmpcov == 0){
							coverageInfo.setLinecoverage("100");
						}else{
							coverageInfo.setLinecoverage(fields[3]);
						}
						
						System.out.println(csvFileName + " covered line: " +fields[1]);
						System.out.println(csvFileName + " total line: " +fields[2]);
						System.out.println(csvFileName + " line cov: " +fields[3]);
						
						coverageInfo.setCoveredbranch(fields[4]);
						coverageInfo.setTotalbranch(fields[5]);
						coverageInfo.setBranchcoverage(fields[6]);
						coverageInfo.setReportdate(nowtime);
					}
				}
			}
		} finally {
			if (fReader != null) {
				try {
					fReader.close();
				} catch (IOException e) {
				}
			}
		}
		coverageInfo.setModulename(csvFileName);
		DataCacheManage.getSingleton().storeUTtoDB(coverageInfo);
	}

	public static void main(String[] args) {
		
	}

}