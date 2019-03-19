package com.nsn.dcm.team2.handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.TreeMap;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.nsn.dcm.team2.model.CIInfo;
import com.nsn.dcm.team2.model.MTInfo;
import com.nsn.dcm.team2.model.PrCountInfo;
import com.nsn.dcm.team2.model.UTCoverageInfo;
/**
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright:</p>
 * <p>Company:</p>
 * @author Reggie(Li Gong)
 * @version 1.00
 *
 */
public class PersistToChartData {
	private static Object lockO = new Object();
	private static PersistToChartData instance = null;
	public static PersistToChartData getSingleton() {
		if (null != instance) {
			return instance;
		} else {
			synchronized (lockO) {
				if (instance == null) {
					instance = new PersistToChartData();
				}
			}
		}
		return instance;
	}
	
	
	public void persistCIInfoToXML(TreeMap<String, CIInfo> cimap) {

		String fileName = getDataXMLPath() + "cidata.xml";
		File filea = new File(fileName);
		if (filea.exists()) {
			filea.delete();
			filea = new File(fileName);
		}

		Element root = null;
		Document doc = null;
		root = new Element("graph");
		doc = new Document(root);
		root.setAttribute("baseFont", "Sunsim");
		root.setAttribute("baseFontSize", "20");
		root.setAttribute("caption", "CI Pass Rate");
		root.setAttribute("subcaption", "");
		root.setAttribute("yAxisMinValue", "0");
		root.setAttribute("yAxisMaxValue", "100");
		root.setAttribute("xaxisname", "date");
		root.setAttribute("yaxisname", "%");
		root.setAttribute("hovercapbg", "FFECAA");
		root.setAttribute("hovercapborder", "F47E00");
		root.setAttribute("formatNumberScale", "0");
		root.setAttribute("decimalPrecision", "0");
		root.setAttribute("showvalues", "1");
		root.setAttribute("numdivlines", "10");
		root.setAttribute("numVdivlines", "0");
		root.setAttribute("shownames", "1");
		root.setAttribute("rotateNames", "1");
		Iterator<String> sortiter = cimap.keySet().iterator();
		int i = 0;
		while (sortiter.hasNext()) {
			String classkey = (String) sortiter.next();
			CIInfo ciinfo = (CIInfo) cimap.get(classkey);
			Element setElement = new Element("set");
			setElement.setAttribute("name", classkey);
			setElement.setAttribute("value", ciinfo.getPassrate());
			setElement.setAttribute("color", "8BBA00");
			root.addContent(setElement);
			
		}
		try {
			// synchronized (utmutex) {
		
			XMLOutputter outputter = new XMLOutputter();
			OutputStreamWriter writer = new OutputStreamWriter(
					new FileOutputStream(filea), "UTF-8");
			Format format = Format.getPrettyFormat();
			format.setEncoding("UTF-8");
			format.setLineSeparator("\r\n");
			outputter.setFormat(format);
			outputter.output(doc, writer);
			writer.close();
		
			// }
		} catch (Exception e) {
			System.out.println("file failed:" + fileName);
			System.out.println(" exception is :" + e);

		}
	}

	public void persistMTInfoToXML(TreeMap<String, MTInfo> mtmap) {

		String fileName = getDataXMLPath() + "mtdata.xml";
		File fileb = new File(fileName);
		if (fileb.exists()) {
			fileb.delete();
			fileb = new File(fileName);
		}

		Element root = null;
		Document doc = null;

		root = new Element("graph");
		doc = new Document(root);

		root.setAttribute("baseFont", "Sunsim");
		root.setAttribute("baseFontSize", "20");
		root.setAttribute("caption", "Rake MT Pass Rate");
		root.setAttribute("subcaption", "");
		root.setAttribute("yAxisMinValue", "0");
		root.setAttribute("yAxisMaxValue", "100");
		root.setAttribute("xaxisname", "date");
		root.setAttribute("yaxisname", "%");
		root.setAttribute("hovercapbg", "FFECAA");
		root.setAttribute("hovercapborder", "F47E00");
		root.setAttribute("formatNumberScale", "0");
		root.setAttribute("decimalPrecision", "0");
		root.setAttribute("showvalues", "1");
		root.setAttribute("numdivlines", "10");
		root.setAttribute("numVdivlines", "0");
		root.setAttribute("shownames", "1");
		root.setAttribute("rotateNames", "1");

		Iterator<String> sortiter = mtmap.keySet().iterator();
		int i = 0;

		while (sortiter.hasNext()) {
			String classkey = (String) sortiter.next();
			MTInfo mtinfo = (MTInfo) mtmap.get(classkey);
			Element setElement = new Element("set");
			setElement.setAttribute("name", classkey);
			setElement.setAttribute("value", mtinfo.getPassrate());
			setElement.setAttribute("color", "8BBA00");
			root.addContent(setElement);
		
		}
		try {
			// synchronized (utmutex) {
			
			XMLOutputter outputter = new XMLOutputter();
			OutputStreamWriter writer = new OutputStreamWriter(
					new FileOutputStream(fileb), "UTF-8");

			Format format = Format.getPrettyFormat();
			format.setEncoding("UTF-8");
			format.setLineSeparator("\r\n");
			outputter.setFormat(format);
		
			outputter.output(doc, writer);
			writer.close();

			// }
		} catch (Exception e) {
			System.out.println("file failed:" + fileName);
			System.out.println(" exception is :" + e);

		}
	}

	public void persistPRInfoToXML(TreeMap<String, PrCountInfo> prmap) {

		String fileName = getDataXMLPath() + "prontodata.xml";
		File filec = new File(fileName);
		filec.deleteOnExit();

		Element root = null;
		Document doc = null;

		root = new Element("graph");
		doc = new Document(root);

		root.setAttribute("baseFont", "Sunsim");
		root.setAttribute("baseFontSize", "20");
		root.setAttribute("caption", "Team2 Pronto");
		root.setAttribute("subcaption", "");
		root.setAttribute("yAxisMinValue", "0");
		root.setAttribute("yAxisMaxValue", "20");
		root.setAttribute("xaxisname", "date");
		root.setAttribute("yaxisname", "Num");
		root.setAttribute("hovercapbg", "FFECAA");
		root.setAttribute("hovercapborder", "F47E00");
		root.setAttribute("formatNumberScale", "0");
		root.setAttribute("decimalPrecision", "0");
		root.setAttribute("showvalues", "1");
		root.setAttribute("numdivlines", "10");
		root.setAttribute("numVdivlines", "0");
		root.setAttribute("shownames", "1");
		root.setAttribute("rotateNames", "1");
		root.removeChildren("set");
		Iterator<String> sortiter = prmap.keySet().iterator();

		int i = 0;

		while (sortiter.hasNext()) {
			String classkey = (String) sortiter.next();
			PrCountInfo prinfo = (PrCountInfo) prmap.get(classkey);
			Element setElement = new Element("set");
			setElement.setAttribute("name", classkey);
			setElement.setAttribute("value", prinfo.getPrcountnum());
			setElement.setAttribute("color", "8BBA00");
			root.addContent(setElement);
//			i++;
//			if (i > color.length) {
//				i = 0;
//			}
		}
		try {
			// synchronized (utmutex) {
			
			XMLOutputter outputter = new XMLOutputter();
			Format format = Format.getPrettyFormat();
			format.setLineSeparator("\r\n");
			outputter.setFormat(format);
			OutputStreamWriter writer = new OutputStreamWriter(
					new FileOutputStream(filec), "UTF-8");
			outputter.output(doc, writer);
			
			writer.close();

			// }
		} catch (Exception e) {
			System.out.println("file failed:" + fileName);
			System.out.println(" exception is :" + e);

		}
	}
	
	public void persistUTInfoToXML(String modulename, TreeMap<String, UTCoverageInfo> treemap) throws IOException, JDOMException {
		try {
			String datapath = getDataXMLPath();
			String fileName = datapath + modulename+".xml";
			File filed = new File(fileName);
			filed.deleteOnExit();
	
			Element root = null;
			Document doc = null;

			root = new Element("graph");
			doc = new Document(root);

			root.setAttribute("baseFont", "Sunsim");
			root.setAttribute("baseFontSize", "20");
			root.setAttribute("caption", modulename);
			root.setAttribute("subcaption", "");
			root.setAttribute("yAxisMinValue", "0");
			root.setAttribute("yAxisMaxValue", "100");
			root.setAttribute("xaxisname", "date");
			root.setAttribute("yaxisname", "%");
			root.setAttribute("hovercapbg", "FFECAA");
			root.setAttribute("hovercapborder", "F47E00");
			root.setAttribute("formatNumberScale", "0");
			root.setAttribute("decimalPrecision", "0");
			root.setAttribute("showvalues", "1");
			root.setAttribute("numdivlines", "10");
			root.setAttribute("numVdivlines", "0");
			root.setAttribute("shownames", "1");
			root.setAttribute("rotateNames", "1");
	
			Iterator<String> sortiter = treemap.keySet().iterator();
			while (sortiter.hasNext()) {
				String classkey = (String) sortiter.next();
				UTCoverageInfo utinfo = (UTCoverageInfo) treemap.get(classkey);				
				if (utinfo != null) {
					Element setElement = new Element("set");
					setElement.setAttribute("name", classkey);				
					if(utinfo.getLinecoverage() == null || "".equals(utinfo.getLinecoverage())){
						setElement.setAttribute("value", "0");
					}else{
						setElement.setAttribute("value", utinfo.getLinecoverage());
					}
					setElement.setAttribute("color", "8BBA00");
					root.addContent(setElement);
				}else{
					System.out.println("utinfo====null ");
				}

			}
			XMLOutputter outputter = new XMLOutputter();
			OutputStreamWriter writer = new OutputStreamWriter(
					new FileOutputStream(filed), "UTF-8");
			Format format = Format.getPrettyFormat();
			format.setEncoding("UTF-8");
			format.setLineSeparator("\r\n");
			outputter.setFormat(format);
			outputter.output(doc, writer);
			writer.close();

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public String getDataXMLPath() {
		String dirpath = getCacheDirpath();
		String path = new File(dirpath).getParentFile().getParentFile()
				.getParentFile().getParentFile().getParent();
		String datapath = path + "\\FusionCharts\\Data\\";
		return datapath;
	}

	public String getPrjPath() {
		String dirpath = getCacheDirpath();
		String path = new File(dirpath).getParentFile().getParentFile()
				.getParentFile().getParent();
		return path;
	}

	public String getCacheDirpath() {
		String dirpath = this.getClass().getResource("/com/nsn/dcm").getPath();
		File dir = new File(dirpath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dirpath;
	}
}
