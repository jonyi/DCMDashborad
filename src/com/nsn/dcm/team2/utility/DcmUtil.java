package com.nsn.dcm.team2.utility;

import java.io.*;
import java.math.BigDecimal;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright:</p>
 * <p>Company:</p>
 * @author Reggie(Li Gong)
 * @version 1.00
 *
 */
public class DcmUtil {

	protected DcmUtil() {
	}


	public static int checkString2int(String input, int defaultvalue) {
		if (null == input) {
			return defaultvalue;
		}
		try {
			int temp = Float.valueOf(input).intValue();
			return temp;
		} catch (NumberFormatException e) {
			return defaultvalue;
		}
	}

	
	public static int checkString2decimal(String input){
		int inputvalue = DcmUtil.checkString2int(input, 0);
		BigDecimal bigDecimal = DcmUtil.checkint2decimal(inputvalue);
		return bigDecimal.intValue();
		
	}
	
	public static BigDecimal checkint2decimal(int defaultvalue){
		BigDecimal bigDecimal = new BigDecimal(defaultvalue).setScale(1, BigDecimal.ROUND_HALF_UP);
		return bigDecimal;
	}
	
	
	public static int checkFloat2int(String input, int defaultvalue) {
		if (null == input) {
			return defaultvalue;
		}
		try {
			int temp = Float.valueOf(input).intValue();
			return temp;
		} catch (NumberFormatException e) {
			return defaultvalue;
		}
	}

	public static short checkString2short(String input, short defaultvalue) {
		if (null == input) {
			return defaultvalue;
		}
		try {
			short temp = Short.parseShort(input);
			return temp;
		} catch (NumberFormatException e) {
			return defaultvalue;
		}
	}

	public static Date str2date(String str, String dateformat) {
		SimpleDateFormat format = new SimpleDateFormat(dateformat);
		Date date = null;
		try {
			date = format.parse(str);

		} catch (ParseException ex) {
			System.err
					.println("bad date format: " + dateformat + " for " + str);
			return new Date();
		}
		return date;
	}

	public static String date2str(Date date, String dateformat) {
		SimpleDateFormat format = new SimpleDateFormat(dateformat);
		String datestr;
		datestr = format.format(date);

		return datestr;
	}

	public static String getNowDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		String datestr;
		datestr = format.format(new Date());
		return datestr;
	}
	
	public static String getNowMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM");
		String datestr;
		datestr = format.format(new Date());
		return datestr;
	}

	public static String getyesterday() {
		String yestedayDate = "";
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		yestedayDate = new SimpleDateFormat("yyyy.MM.dd").format(cal.getTime());
		return yestedayDate;

	}

	public static boolean dateBefore(String srcdatestr, String desdatestr) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm");
		Date srcdate = null;
		Date desdate = null;
		try {
			srcdate = format.parse(srcdatestr);
			desdate = format.parse(desdatestr);
		} catch (ParseException ex) {
			ex.printStackTrace();
			System.err.println("bad date format, should be yyyy.MM.dd HH:mm");
			return false;
		}
		if (srcdate.equals(desdate)) {
			return true;
		}
		return srcdate.before(desdate);

	}

	public static byte[] int2bytes(int namevalue, int size) {

		byte[] intbyte = new byte[size];
		for (int i = intbyte.length - 1; i > -1; i--) {
			intbyte[i] = new Integer(namevalue & 0xff).byteValue();
			namevalue = namevalue >> 8;
		}
		return intbyte;
	}

	public static byte[] String2bytes(String namevalue, int size) {
		String newStr = (namevalue == null ? "" : namevalue);

		if (getStringLength(newStr) > size) {
			newStr = getString(newStr, 0, size);
		}

		for (; getStringLength(newStr) < size;) {
			newStr = newStr + "\0";
		}

		return newStr.getBytes();
	}

	public static int bytes2int(byte[] b) {
		int result = 0;
		for (int i = 0; i < b.length; i++) {
			result = result << 8;
			result = result + ((new Byte(b[i]).intValue() & 0xff));

		}
		return result;
	}

	public static String byte2String(byte[] bytes) {
		int start = 0;
		int end = bytes.length;

		if (bytes == null || bytes.length <= start || end <= start) {
			return "";
		}

		if (bytes.length < end) {
			end = bytes.length;

		}
		byte tempS[] = new byte[end - start];
		for (int i = start; i < end; i++) {
			tempS[i - start] = bytes[i];
			if (bytes[i] == (byte) 0) {
				break;
			}
		}
		return new String(tempS).trim();
	}

	public static int getStringLength(String str) {
		if (str == null) {
			return 0;
		}
		byte[] bytes = str.getBytes();
		return bytes.length;
	}

	public static String getString(String str, int start, int end) {

		if (str == null || getStringLength(str) <= start) {
			return "";
		}
		if (getStringLength(str) < end) {
			end = getStringLength(str);

		}
		return (new String(str.getBytes(), start, end - start)).trim();
	}

	public static boolean checkIP(String ipaddr) {
		if (ipaddr == null) {
			return false;
		}
		try {
			String[] iparray = ipaddr.split("\\.");
			if (iparray.length != 4) {
				return false;
			} else {
				for (int i = 0; i < 4; i++) {
					String ip_part = iparray[i];
					int ipvalue = DcmUtil.checkString2int(ip_part, -1);
					if (ipvalue < 0 || ipvalue > 255) {

						return false;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static void main(String[] argv){

	}
}
