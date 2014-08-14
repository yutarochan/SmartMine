package com.tigytech.SmartMiner.util;

import java.io.File;

public class FilePath {
	
	public static final String APP_DIR = new File("").getAbsolutePath();
	public static final String USR_TMP = System.getProperty("java.io.tmpdir");
	public static final String USR_ROOT = System.getProperty("user.home");
	public static String APP_TMP;
	
	public static boolean appTempDirPresent() {
		return APP_TMP != null;
	}
	
	public static void setAppTempDir() {
		File app_temp = new File(USR_TMP + "/SmartMine");
		app_temp.mkdir();
	}
	
	public static String getAppTempDir() {
		if (APP_TMP == null) 
			setAppTempDir();
		
		return APP_TMP;
	}
	
}