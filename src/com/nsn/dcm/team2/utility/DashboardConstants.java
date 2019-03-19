package com.nsn.dcm.team2.utility;

/**
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright:</p>
 * <p>Company:</p>
 * @author Reggie(Li Gong)
 * @version 1.00
 *
 */
public class DashboardConstants {
	 
	//////////////////////////////Common////////////////////////////////////////////
	//Database
	public static final String MYSQLDRIVER = "com.mysql.jdbc.Driver";
	public static final String MYSQLURL = "jdbc:mysql://localhost:3306/dcmdashboard";
	public static final String MYSQLNAME = "root";
	public static final String MYSQLPWD = "root"; 
	////////////////////////////////////////////////////////////////////////////////
	
	///////////////////////////////KPI//////////////////////////////////////////////
	//UT
	public static final String DCM_CP_PowerControl_UT_NQST = "DCM_CP_PowerControl_UT_NQST";
	public static final String DCM_CP_Control_UT_NQST = "DCM_CP_Control_UT_NQST";
	public static final String DCM_CP_Database_UT_NQST = "DCM_CP_Database_UT_NQST";
	public static final String DCM_CP_RakeParamCheck_UT_NQST = "DCM_CP_RakeParamCheck_UT_NQST";
	public static final String DCM_CP_SharedSymbol_UT_NQST = "DCM_CP_SharedSymbol_UT_NQST";
	public static final String DCM_CP_SlaveSymbol_UT_NQST = "DCM_CP_SlaveSymbol_UT_NQST";
	
	public static String MacHs_UT = "MacHs-UT";
	public static String TUP_UT_GCC = "TUP-UT-GCC";
	public static String CP_CompressedModeService_UT = "CP_CompressedModeService_UT";
	public static String DCM_CP_DchAlloc_API_Meas_UT_NQST = "DCM_CP_DchAlloc_API_Meas_UT_NQST";
	public static String DCM_CP_DchControl_API_Meas_UT_NQST = "DCM_CP_DchControl_API_Meas_UT_NQST";
	public static String DCM_CP_SlaveBrowser_DCT_UT_NQST = "DCM_CP_SlaveBrowser_DCT_UT_NQST";
	public static String CP_EncHsdpaL1Lib_UT = "CP_EncHsdpaL1Lib_UT";
	public static String DCM_CP_CodecCommon_UT_NQST = "DCM_CP_CodecCommon_UT_NQST";
	public static String DCM_CP_CodecRM_UT_NQST = "DCM_CP_CodecRM_UT_NQST";
	public static String DCM_CP_Decoder_Measurement_UT_NQST = "DCM_CP_Decoder_Measurement_UT_NQST";
	public static String DCM_CP_EncBcpDch_UT_NQST = "DCM_CP_EncBcpDch_UT_NQST";
	public static String DCM_CP_EncBcpHsdpa_UT_NQST = "DCM_CP_EncBcpHsdpa_UT_NQST";
	public static String DCM_CP_Encoder_UT_NQST = "DCM_CP_Encoder_UT_NQST";
	public static String DCM_CP_L1Tra_UT_NQST = "DCM_CP_L1Tra_UT_NQST";
	public static String DCM_CP_LocalOamServer_UT_NQST = "DCM_CP_LocalOamServer_UT_NQST";
	public static String DCM_CP_LTCOM_UT_NQST = "DCM_CP_LTCOM_UT_NQST";
	public static String DCM_CP_Meas_Meas_UT_NQST = "DCM_CP_Meas_Meas_UT_NQST";
	public static String DCM_CP_W1SpTACCtrl_UT_NQST = "DCM_CP_W1SpTACCtrl_UT_NQST";
	public static String FP_UT = "FP-UT";

	public static String utremotepathpre = "http://10.140.21.13/view/DSP_UT/job/";
	public static String utremotepathsuf = "/ws/coverage_report.csv";

	
	public static String utlocalpathpre = "d:\\test\\";
	public static String utlocalpathsuf = "_coverage_report.csv";

	
	//MT
	public static final String mtremotepath = "http://10.140.21.13/view/DSP_MT/job/RAKE_MT_TEST/lastSuccessfulBuild/artifact/BuildRoot/C_Test/Robot/Reports/*zip*/Reports.zip";
	public static final String mtlocalpath = "d:\\test\\Reports.zip";
	public static final String mtlocalunzippath = "D:\\test\\RakeMT\\";
	
	//SCT CI
	public static final String JXG = "Jin, Xiaogang";
	public static final String PY = "Pan, Yi-Yee";
	public static final String PRK = "Pan, Runkai";
	public static final String ciremotepath = "http://10.140.21.13/job/DSP_SCBT_HSDPA_Test_DCM/Trunk_HSDPA_Report/*zip*/Trunk_HSDPA_Report.zip";
	public static final String ciremotepath1 = "http://10.140.21.13/job/DSP_SCBT_Test_DCM_L1/Trunk_Legacy&L1_Report/*zip*/Trunk_Legacy&L1_Report.zip";
	
	public static final String cilocalpath = "d:\\test\\Trunk_HSDPA_Report.zip";
	public static final String cilocalpath1 = "d:\\test\\Trunk_Legacy&L1_Report.zip";
	
	public static final String cilocalunzippath = "D:\\test\\CI\\";
	public static final String cilocalunzippath1 = "D:\\test\\CI1\\";
	public static final String cilocalfile = cilocalunzippath + "Trunk_HSDPA_Report\\index.html";
	public static final String cilocalfile1 = cilocalunzippath1 + "Trunk_Legacy&L1_Report\\index.html";

	//Pronto 
	public static final String PRONTOPATH = "d:/team2_ftp/DCM SW # Pronto list_template.xlsm";
	
	
}
