package com.nsn.dcm.team2.handler;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.TreeMap;
import java.util.Vector;
import org.jdom.JDOMException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import com.nsn.dcm.team2.common.ConnectToMySql;
import com.nsn.dcm.team2.model.CIDetailInfo;
import com.nsn.dcm.team2.model.CIInfo;
import com.nsn.dcm.team2.model.MTInfo;
import com.nsn.dcm.team2.model.PrCountInfo;
import com.nsn.dcm.team2.model.PrHistoryInfo;
import com.nsn.dcm.team2.model.Pronto;
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
public class DataCacheManage {

	private static Object lockO = new Object();
	private static DataCacheManage dataCacheManage = null;
	private static TreeMap<String, CIInfo> cimap = null;
	private static TreeMap<String, MTInfo> mtmap = null;
	private static TreeMap<String, PrCountInfo> prmap = null;
	private static final Object utmutex = new Object();	
	private static final String[] color = { "AFD8F8", "F6BD0F", "8BBA00", "FF8E46", "008E8E", "D64646" };

	private static Connection conn = null;




	private static final String[] utnames =
        {
		DashboardConstants.DCM_CP_PowerControl_UT_NQST,
		DashboardConstants.DCM_CP_Control_UT_NQST,
		DashboardConstants.DCM_CP_Database_UT_NQST,
		DashboardConstants.DCM_CP_RakeParamCheck_UT_NQST,
		DashboardConstants.DCM_CP_SharedSymbol_UT_NQST,
		DashboardConstants.DCM_CP_SlaveSymbol_UT_NQST,
		DashboardConstants.MacHs_UT,
		DashboardConstants.TUP_UT_GCC,
		DashboardConstants.CP_CompressedModeService_UT,
		DashboardConstants.DCM_CP_DchAlloc_API_Meas_UT_NQST,
		DashboardConstants.DCM_CP_DchControl_API_Meas_UT_NQST,
		DashboardConstants.DCM_CP_SlaveBrowser_DCT_UT_NQST,
		DashboardConstants.CP_EncHsdpaL1Lib_UT,
		DashboardConstants.DCM_CP_CodecCommon_UT_NQST,
		DashboardConstants.DCM_CP_CodecRM_UT_NQST,
		DashboardConstants.DCM_CP_Decoder_Measurement_UT_NQST,
		DashboardConstants.DCM_CP_EncBcpDch_UT_NQST,
		DashboardConstants.DCM_CP_EncBcpHsdpa_UT_NQST,
		DashboardConstants.DCM_CP_Encoder_UT_NQST,
		DashboardConstants.DCM_CP_L1Tra_UT_NQST,
		DashboardConstants.DCM_CP_LocalOamServer_UT_NQST,
		DashboardConstants.DCM_CP_LTCOM_UT_NQST,
		DashboardConstants.DCM_CP_Meas_Meas_UT_NQST,
		DashboardConstants.DCM_CP_W1SpTACCtrl_UT_NQST,
		DashboardConstants.FP_UT
    };
    
	public String[] getUtnames() {
		return utnames;
	}

	public static DataCacheManage getSingleton() {
		if (null != dataCacheManage) {
			return dataCacheManage;
		} else {
			synchronized (lockO) {
				if (dataCacheManage == null) {
					dataCacheManage = new DataCacheManage();
					conn = ConnectToMySql.getSingleton().getDBPoolService();
				}
			}
		}
		return dataCacheManage;
	}

	public void updateAllDataInfo() {
		try {
			this.downloadAllFiles();
			this.parseAllFiles();
			this.retreAllData();
			this.persistAllToXML();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void downloadAllFiles() {
		try {
			String utremotepath = "";
			String utlocalpath = "";
			for(int i = 0; i < utnames.length; i++){
				utremotepath = DashboardConstants.utremotepathpre + utnames[i] + DashboardConstants.utremotepathsuf;
				utlocalpath = DashboardConstants.utlocalpathpre + utnames[i] + DashboardConstants.utlocalpathsuf;
				DownloadFiles.getSingleton().addItem(utremotepath, utlocalpath);
			}	
			
			DownloadFiles.getSingleton().addItem(DashboardConstants.ciremotepath, DashboardConstants.cilocalpath);
			DownloadFiles.getSingleton().addItem(DashboardConstants.ciremotepath1, DashboardConstants.cilocalpath1);
			DownloadFiles.getSingleton().addItem(DashboardConstants.mtremotepath, DashboardConstants.mtlocalpath);
			DownloadFiles.getSingleton().downLoadByList();
		} catch (Exception err) {
			System.out.println(err.getMessage());
		}
	}

	private void parseAllFiles() {
		String nowtime = DcmUtil.getNowDate();
		// 1. parse ut file
		try {		
			System.out.println("Parsing UT data....");
			for(int i = 0; i < utnames.length; i++){
				System.out.println(utnames[i]);
				CVSParser.getInstance().readCVSFile(utnames[i]);
			}	
			System.out.println("Complete parsing UT data!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 // 2. parse ci file
		try {
			System.out.println("Parsing CI data....");
			DownloadFiles.getSingleton().unzipFile(
					DashboardConstants.cilocalpath,
					DashboardConstants.cilocalunzippath);
			DownloadFiles.getSingleton().unzipFile(
					DashboardConstants.cilocalpath1,
					DashboardConstants.cilocalunzippath1);

			String passrate = this.getCIPassRate();
			CIInfo ciInfo = new CIInfo();
			ciInfo.setPassrate(passrate);
			ciInfo.setReportdate(nowtime);
			int countci = ciInfo.countCIInfo(nowtime);
			if (countci > 0) {
				ciInfo.updateCIInfo(ciInfo);
			} else {
				ciInfo.insertCIInfo(ciInfo);
			}
			System.out.println("Complete Parsing CI data!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 // 3. parse mt file
		try {
			System.out.println("Parsing MT data....");
			File mtfile = null;
			String passrate = "0";
			DownloadFiles.getSingleton().unzipFile(DashboardConstants.mtlocalpath, DashboardConstants.mtlocalunzippath);
			String rakemtfilepath = this.getMTFilePath();
			if(rakemtfilepath.contains("No Rake MT Report Today")){
				System.out.println("please check MT data! "+DashboardConstants.mtremotepath);
			}else{
				mtfile = new File(rakemtfilepath);
				passrate = this.getMTPassRate(mtfile);
			}
			
			MTInfo mtInfo = new MTInfo();
			mtInfo.setModulename("Rake");
			mtInfo.setPassrate(passrate);
			mtInfo.setReportdate(nowtime);
			int countmt = mtInfo.countMTInfo(nowtime);
			if (countmt > 0) {
				mtInfo.updateMTInfo(mtInfo);
			} else {
				mtInfo.insertMTInfo(mtInfo);
			}
			System.out.println("Complete Parsing MT data!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 // 4 update pr count num
		try {
			System.out.println("Parsing Pronto data....");
			Vector<Pronto> verpr = ExcelParser.getInstance().readProntoCSVFile();

			PrCountInfo prcountinfo = new PrCountInfo();
			Pronto pr = new Pronto();
			pr.truncateProntoInfo();
			for (int i = 0; i < verpr.size(); i++) {
				pr.insertProntoInfo(verpr.get(i));
			}
			prcountinfo.setPrcountdate(nowtime);
			prcountinfo.setPrcountnum(verpr.size() + "");
			int num = prcountinfo.countPRCountInfo(nowtime);
			if (num > 0) {
				prcountinfo.updatePRCountInfo(prcountinfo);
			} else {
				prcountinfo.insertPRCountInfo(prcountinfo);
			}
			System.out.println("Complete Parsing Pronto data!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//parse pronto history data		
		try {
			System.out.println("Parsing Pronto history data....");
			Vector<PrHistoryInfo> prontohisvec = ExcelParser.getInstance().readProntoHistoryFile();
			PrHistoryInfo prhis = new PrHistoryInfo();
			prhis.truncateProntoHisInfo();
			for (int i = 0; i < prontohisvec.size(); i++) {
				prhis.insertProntoHisInfo(prontohisvec.get(i));
			}
			System.out.println("Complete Parsing Pronto history data!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void storeUTtoDB(UTCoverageInfo utinfo) {
		int countut = utinfo.countUTInfo(utinfo.getReportdate(),
				utinfo.getTeamname(), utinfo.getModulename());
		if (countut > 0) {
			utinfo.updateCoverageInfo(utinfo);
		} else {
			utinfo.insertCoverageInfo(utinfo);
		}
	}
	
	private void retreAllData() {
		this.retreCoverageInfo();
		this.retreCIInfo();
		this.retreMTInfo();
		this.retrePRInfo();
	}
	private void persistAllToXML() {
		PersistToChartData.getSingleton().persistCIInfoToXML(cimap);
		PersistToChartData.getSingleton().persistMTInfoToXML(mtmap);
		PersistToChartData.getSingleton().persistPRInfoToXML(prmap);
	}
	
	private String getCIPassRate() {
		File cifile = new File(DashboardConstants.cilocalfile);
		File cifile1 = new File(DashboardConstants.cilocalfile1);
		String text = "";
		String passrate = "Get CI Pass Rate Error!";
		try {
			org.jsoup.nodes.Document doc = Jsoup.parse(cifile, "UTF-8");
			Elements trlink = doc.select("TR");
			for (org.jsoup.nodes.Element link1 : trlink) {
				text += link1.text() + "|";
			}
			
			org.jsoup.nodes.Document doc1 = Jsoup.parse(cifile1, "UTF-8");
			Elements trlink1 = doc1.select("TR");
			for (org.jsoup.nodes.Element link1 : trlink1) {
				text += link1.text() + "|";
			}
			
			String[] draftstr = text.split("\\|");
			passrate = this.calCIPassRate(draftstr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return passrate;
	}

	private String calCIPassRate(String[] draftstr) {
		String xgfailedcases = "";
		String pyfailedcases = "";
		String rkfailedcases = "";
		String failedversions = "";
		
		String xgfaileddate="";
		String pyfaileddate="";
		String rkfaileddate="";
		
		int xgtotal = 0;
		int xgfailed = 0;
		int pytotal = 0;
		int pyfailed = 0;
		int rktotal = 0;
		int rkfailed = 0;
		String nowtime = DcmUtil.getNowDate();
		String cipassrate = "";
		int totaltestedcase = 0;
		int totalfailed = 0;
		for (int i = 0; i < draftstr.length; i++) {
            if(draftstr[i].contains("LastFailureDate")){
            	String[] tmpheads = draftstr[i].split(" ");
            	failedversions = tmpheads[2];
            }
            
			if (draftstr[i].contains(DashboardConstants.JXG)
					&& draftstr[i].contains("Failed")) {
				if (!draftstr[i].contains("Passed")) {
					totalfailed++;
					xgfailed++;
					String[] tmpcontents = draftstr[i].split(" ");
					if(tmpcontents[3].contains("Failed") || tmpcontents[3].contains("Passed")){
						System.out.println(i+"  "+draftstr[i]);
					}else{
						xgfailedcases = xgfailedcases + tmpcontents[0] + " ";
						xgfaileddate = xgfaileddate + tmpcontents[3]+" ";
					}
					
				}else{
					int tempf = draftstr[i].indexOf("Failed");
					int tempp = draftstr[i].indexOf("Passed");
					if(tempf < tempp){
						totalfailed++;
						xgfailed++;
						String[] tmpcontents = draftstr[i].split(" ");
						if(tmpcontents[3].contains("Failed") || tmpcontents[3].contains("Passed")){
							System.out.println(i+"  "+draftstr[i]);
						}else{
							xgfailedcases = xgfailedcases + tmpcontents[0] + " ";
							xgfaileddate = xgfaileddate + tmpcontents[3]+" ";
						}
					}
				}

			} else if (draftstr[i].contains(DashboardConstants.PY)
					&& draftstr[i].contains("Failed")) {
				if (!draftstr[i].contains("Passed")) {
					totalfailed++;
					pyfailed++;
					String[] tmpcontents1 = draftstr[i].split(" ");
					if(tmpcontents1[3].contains("Failed") || tmpcontents1[3].contains("Passed")){
					}else{
					pyfailedcases = pyfailedcases + tmpcontents1[0] + " ";
					pyfaileddate = pyfaileddate + tmpcontents1[3]+" ";
					}
				}else{
					int tempf1 = draftstr[i].indexOf("Failed");
					int tempp1 = draftstr[i].indexOf("Passed");
					if(tempf1 < tempp1){
						totalfailed++;
						pyfailed++;
						String[] tmpcontents1 = draftstr[i].split(" ");
						if(tmpcontents1[3].contains("Failed") || tmpcontents1[3].contains("Passed")){
						}else{
						pyfailedcases = pyfailedcases + tmpcontents1[0] + " ";
						pyfaileddate = pyfaileddate + tmpcontents1[3]+" ";
						}
					}
				}
			} else if (draftstr[i].contains(DashboardConstants.PRK)
					&& draftstr[i].contains("Failed")) {
				if (!draftstr[i].contains("Passed")) {
					totalfailed++;
					rkfailed++;
					String[] tmpcontents2 = draftstr[i].split(" ");
					System.out.println(i+"  "+draftstr[i]);
					rkfailedcases = rkfailedcases + tmpcontents2[0] + " ";
					rkfaileddate = rkfaileddate + tmpcontents2[3]+" ";
				}else{
					int tempf2 = draftstr[i].indexOf("Failed");
					int tempp2 = draftstr[i].indexOf("Passed");
					if(tempf2 < tempp2){
						totalfailed++;
						rkfailed++;
						String[] tmpcontents2 = draftstr[i].split(" ");
						if(tmpcontents2[3].contains("Failed") || tmpcontents2[3].contains("Passed")){
							System.out.println(i+"  "+draftstr[i]);
						}else{
							System.out.println(i+"  "+draftstr[i]);
						rkfailedcases = rkfailedcases + tmpcontents2[0] + " ";
						rkfaileddate = rkfaileddate + tmpcontents2[3]+" ";}
					}
				}
			}

			if (draftstr[i].contains(DashboardConstants.JXG)) {
				totaltestedcase++;
				xgtotal++;
			} else if (draftstr[i].contains(DashboardConstants.PY)) {
				totaltestedcase++;
				pytotal++;
			} else if (draftstr[i].contains(DashboardConstants.PRK)) {
				totaltestedcase++;
				rktotal++;
			}

		}

		int realtotalfailed = totalfailed/2;
		int realtotaltestcase = totaltestedcase - realtotalfailed;
		double ratetmp = new Double((float)(realtotaltestcase - realtotalfailed) / realtotaltestcase);
		cipassrate = ratetmp * 100 + "";
		
		CIDetailInfo cidtlinfo = new CIDetailInfo();
		cidtlinfo.setCidtltester(DashboardConstants.JXG);
		
		int realxgfailed = xgfailed/2;
		int realxgtotal = xgtotal - realxgfailed;
		cidtlinfo.setCidtltotalcase(realxgtotal+"");
		cidtlinfo.setCidtlfailedcase(realxgfailed+"");
		cidtlinfo.setCidtlreportdate(nowtime);
		cidtlinfo.setCidtlfailedcasename(xgfailedcases);
		cidtlinfo.setCidtlfailedversion(failedversions);
		cidtlinfo.setCidtlfaileddate(xgfaileddate);
		
		if (cidtlinfo.countCIDTLInfo(DashboardConstants.JXG, nowtime) > 0) {
			cidtlinfo.updateCIDTLInfo(cidtlinfo);
		} else {
			cidtlinfo.insertCIDTLInfo(cidtlinfo);
		}
		cidtlinfo.printCIDetailInfo(cidtlinfo);
		
		
		int realpyfailed = pyfailed/2;
		int realpytotal = pytotal - realpyfailed;
		CIDetailInfo cidtlinfo1 = new CIDetailInfo();
		cidtlinfo1.setCidtltester(DashboardConstants.PY);
		cidtlinfo1.setCidtltotalcase(realpytotal+"");
		cidtlinfo1.setCidtlfailedcase(realpyfailed+"");
		cidtlinfo1.setCidtlreportdate(nowtime);
		cidtlinfo1.setCidtlfailedcasename(pyfailedcases);
		cidtlinfo1.setCidtlfailedversion(failedversions);
		cidtlinfo1.setCidtlfaileddate(pyfaileddate);
		
		if (cidtlinfo1.countCIDTLInfo(DashboardConstants.PY, nowtime) > 0) {
			cidtlinfo1.updateCIDTLInfo(cidtlinfo1);
		} else {
			cidtlinfo1.insertCIDTLInfo(cidtlinfo1);
		}
		
		cidtlinfo1.printCIDetailInfo(cidtlinfo1);
		
		int realrkfailed = rkfailed/2;
		int realrktotal = rktotal - realrkfailed;
		CIDetailInfo cidtlinfo2 = new CIDetailInfo();
		cidtlinfo2.setCidtltester(DashboardConstants.PRK);
		cidtlinfo2.setCidtltotalcase(realrktotal+"");
		cidtlinfo2.setCidtlfailedcase(realrkfailed+"");
		cidtlinfo2.setCidtlreportdate(nowtime);
		cidtlinfo2.setCidtlfailedcasename(rkfailedcases);
		cidtlinfo2.setCidtlfailedversion(failedversions);
		cidtlinfo2.setCidtlfaileddate(rkfaileddate);
		
		if (cidtlinfo2.countCIDTLInfo(DashboardConstants.PRK, nowtime) > 0) {
			cidtlinfo2.updateCIDTLInfo(cidtlinfo2);
		} else {
			cidtlinfo2.insertCIDTLInfo(cidtlinfo2);
		}
		
		cidtlinfo2.printCIDetailInfo(cidtlinfo2);
		return cipassrate;

	}

	private String getMTFilePath() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String datestr;
		datestr = format.format(new Date());

		String mtfilepath = "No Rake MT Report Today!";
		String filepath = DashboardConstants.mtlocalunzippath + "Reports";
		File dir = new File(filepath);
		if (dir.exists()) {
			File[] files = dir.listFiles();
			if (files == null || files.length == 0) {
				return mtfilepath;
			}
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()
						&& files[i].getName().startsWith(datestr)) {
					File[] htmlfiles = files[i].listFiles();
					for (int j = 0; j < htmlfiles.length; j++) {
						if (htmlfiles[j].getName().equalsIgnoreCase(
								"Tests_report.html")) {
							mtfilepath = htmlfiles[j].getPath();
						}
					}
				}
			}
		}
		return mtfilepath;

	}

	private String getMTPassRate(File html) {
		String passratestr = "Get MT Pass Rate Error!";
		try {
			org.jsoup.nodes.Document doc = Jsoup.parse(html, "UTF-8");
			Elements elms = doc.getElementsByTag("script");
			org.jsoup.nodes.Element draftstr = elms.get(3);

			String dstr = draftstr.toString();
			String str1 = dstr.substring(dstr.indexOf("fail"),
					dstr.indexOf("label"));
			str1 = str1.replaceAll("\"", "");
			str1 = str1.replaceAll(",", "");
			String[] failnum = str1.split(":");
			String num1 = failnum[1];

			String passstr = dstr.substring(dstr.indexOf("pass"),
					dstr.indexOf("},{"));
			passstr.replaceAll("\"", "");
			String[] passnum = passstr.split(":");
			String num2 = passnum[1];
			int rate1 = DcmUtil.checkString2int(num1, 0);
			int rate2 = DcmUtil.checkString2int(num2, 0);
			double ratetmp = new Double((float)rate2 / (rate1 + rate2));
			passratestr = ratetmp * 100 + "";

		} catch (IOException e) {
			e.printStackTrace();
		}
		return passratestr;
	}



	private void retreCoverageInfo() {
        String selectSql = "";
		for(int i = 0; i < utnames.length; i++){
			selectSql = "select * from info_utcoverage where ut_modulename='"
					+ utnames[i]+ "'" +
					" and ut_reportdate LIKE '%" + DcmUtil.getNowMonth() + "%' " ;
			System.out.println(selectSql);
			try {
				PersistToChartData.getSingleton().persistUTInfoToXML(utnames[i], this.getdata(selectSql));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JDOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	private TreeMap<String, UTCoverageInfo> getdata(String selectSql){
		TreeMap<String, UTCoverageInfo> treehs = new TreeMap<String, UTCoverageInfo>();
		UTCoverageInfo utinfo = null;
		
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
				utinfo = new UTCoverageInfo();
				utinfo.setModulename(rs.getString(1));
				utinfo.setTeamname(rs.getString(2));
				utinfo.setCoveredline(rs.getString(3));
				utinfo.setTotalline(rs.getString(4));
			
					utinfo.setLinecoverage(rs.getString(5));
				
//				utinfo.setCoveredbranch(rs.getString(6));
//				utinfo.setTotalbranch(rs.getString(7));
//				utinfo.setBranchcoverage(rs.getString(8));
				utinfo.setReportdate(rs.getString(9));
				//utinfo.setReportpath("");
				treehs.put(rs.getString(9), utinfo);
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

	private void retreCIInfo() {
		CIInfo ciinfo = null;
		cimap = new TreeMap<String, CIInfo>();
		
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String selectSql = "select * from info_ci" +
				" where ci_reportdate LIKE '%" + DcmUtil.getNowMonth() + "%' " ;
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(selectSql);
			while (rs.next()) {
				ciinfo = new CIInfo();
				ciinfo.setPassrate(rs.getString(1));
				ciinfo.setReportdate(rs.getString(2));
				cimap.put(rs.getString(2), ciinfo);
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
	}

	private void retreMTInfo() {
		MTInfo mtinfo = null;
		mtmap = new TreeMap<String, MTInfo>();
		
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String selectSql = "select * from info_mt" +
				" where mt_reportdate LIKE '%" + DcmUtil.getNowMonth() + "%' " ;
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(selectSql);
			while (rs.next()) {
				mtinfo = new MTInfo();
				mtinfo.setModulename(rs.getString(1));
				mtinfo.setPassrate(rs.getString(2));
				mtinfo.setReportdate(rs.getString(3));
				mtmap.put(rs.getString(3), mtinfo);
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
	}

	private void retrePRInfo() {
		PrCountInfo prinfo = null;
		prmap = new TreeMap<String, PrCountInfo>();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String selectSql = "select * from info_prcount" +
				" where prcount_date LIKE '%" + DcmUtil.getNowMonth() + "%' " ;
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(selectSql);
			while (rs.next()) {
				prinfo = new PrCountInfo();
				prinfo.setPrcountnum(rs.getString(1));
				prinfo.setPrcountdate(rs.getString(2));
				prmap.put(rs.getString(2), prinfo);
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
	}


	
	public static void main(String[] argv) {
		try {
			//DataCacheManage.getSingleton().updateAllDataInfo();
			for(int i = 0; i <20; i++){
				System.out.println(i%5);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
