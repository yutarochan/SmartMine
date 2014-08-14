package com.tigytech.SmartMiner.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FileUtil {
	
	public static File wget(String url, String filename) throws Exception {
		File data_file = new File(FilePath.getAppTempDir() + "/" + filename);
		
		String cmd = "wget -O " + data_file.getAbsolutePath() + " " + url;
		Process process = Runtime.getRuntime().exec(cmd);
		
		BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String data = "";
		while ((data = r.readLine()) != null) System.out.println(data + "\n");
		
		process.waitFor();
		
		return data_file;
	}
	
	public static String getSource(String url) {
		String source_data = "";
		
		try {
			File temp_file = wget(url.toString(), "data.txt");
			Scanner sc = new Scanner(temp_file);
			while (sc.hasNextLine())
				source_data += sc.nextLine();
			
			return source_data;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
