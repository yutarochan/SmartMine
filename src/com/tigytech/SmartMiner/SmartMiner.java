package com.tigytech.SmartMiner;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SmartMiner {
	
	public static final String APP_DIR = new File("").getAbsolutePath();
	
	public SmartMiner() {
	}
	
	public void scan(URL baseURL, int scanLevels) {
	}
	
	private static ArrayList<URL> parseURL(URL baseURL) throws Exception {
		ArrayList<URL> urlList = new ArrayList<URL>();
		
		String data = getPage(baseURL.toString());
		Document doc = Jsoup.parse(data);
		
		Elements linkList = doc.getElementsByTag("a");
		for (Element e : linkList) {
			if (e.attr("href").contains(baseURL.toString()))
				urlList.add(new URL(e.attr("href")));
			else if (e.attr("href").startsWith("/"))
				urlList.add(new URL(baseURL.toString() + e.attr("href").substring(1)));
		}
		return urlList;
	}
	
	private static String getPage(String url) throws Exception {
		String cmd = "wget -O " + "data.txt" + " " + url;
		Process process = Runtime.getRuntime().exec(cmd);
		
		BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String data = "";
		while ((data = r.readLine()) != null) System.out.println(data + "\n");
		
		process.waitFor();
		
		String text_data = "";
		File f = new File(APP_DIR + "/data.txt");
		Scanner sc = new Scanner(f);
		while (sc.hasNextLine()) text_data += sc.nextLine();
		f.delete();
		
		return text_data;
	}
	
	public static void main(String[] args) {
		try {
			ArrayList<URL> url = parseURL(new URL(""));
			for (URL u : url) System.out.println(u.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}