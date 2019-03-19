package com.nsn.dcm.team2.handler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
/**
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright:</p>
 * <p>Company:</p>
 * @author Reggie(Li Gong)
 * @version 1.00
 *
 */
public class DownloadFiles {
	private static Object lockO = new Object();
	private static DownloadFiles downloadFiles = null;
	private static int BUFFER_SIZE = 8096;
	private Vector<String> vDownLoad = new Vector<String>();
	private Vector<String> vFileList = new Vector<String>();
	
	public static DownloadFiles getSingleton() {
		if (null != downloadFiles) {
			return downloadFiles;
		} else {
			synchronized (lockO) {
				if (downloadFiles == null) {
					downloadFiles = new DownloadFiles();

				}
			}
		}
		return downloadFiles;
	}
	private void resetList() {
		vDownLoad.clear();
		vFileList.clear();
	}

	public void addItem(String url, String filename) {
		vDownLoad.add(url);
		vFileList.add(filename);
	}

	public void downLoadByList() {
		String url = null;
		String filename = null;
		for (int i = 0; i < vDownLoad.size(); i++) {
			url = (String) vDownLoad.get(i);
			filename = (String) vFileList.get(i);
			try {
				saveToFile(url, filename);
			} catch (IOException err) {
				System.out.println("download [" + url + "] failed!!!");
			}
		}
		System.out.println("download succ!!!");
		this.resetList();
	}

	public void saveToFile(String destUrl, String fileName) throws IOException {
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		HttpURLConnection httpUrl = null;
		URL url = null;
		byte[] buf = new byte[BUFFER_SIZE];
		int size = 0;

		url = new URL(destUrl);
		httpUrl = (HttpURLConnection) url.openConnection();
		httpUrl.connect();
		bis = new BufferedInputStream(httpUrl.getInputStream());
		fos = new FileOutputStream(fileName);
		System.out.println("downloading from [" + destUrl
				+ "] ...and save to [" + fileName + "]");
		while ((size = bis.read(buf)) != -1)
			fos.write(buf, 0, size);
		fos.close();
		bis.close();
		httpUrl.disconnect();
	}
	
	public boolean unzipFile(String fileurl, String dir) throws Exception {
		int buffer = BUFFER_SIZE;
		boolean flag = false;

		File rootdir = new File(dir);
		if (!rootdir.exists()) {
			rootdir.mkdirs();
		}
		try {
			BufferedOutputStream dest = null;
			FileInputStream fis = new FileInputStream(fileurl);
			ZipInputStream zis = new ZipInputStream(
					new BufferedInputStream(fis));
			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null) {
				int count;
				byte data[] = new byte[buffer];
				// write the files to the disk
				if (entry.isDirectory()) {
					File subdir = new File(dir + entry.getName());
					if (!subdir.exists()) {
						subdir.mkdirs();

					}
				} else {
					FileOutputStream fos = new FileOutputStream(dir
							+ entry.getName());
					dest = new BufferedOutputStream(fos, buffer);
					while ((count = zis.read(data, 0, buffer)) != -1) {
						dest.write(data, 0, count);
					}
					dest.flush();
					dest.close();
				}

			}
			zis.close();
		} catch (Exception e) {

			throw new Exception(e);
		}
		return flag;
	}
}
