package com.tigytech.SmartMiner;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tigytech.SmartMiner.util.FileUtil;

public class SmartMiner {
	
	public static final String APP_DIR = new File("").getAbsolutePath();
	
	public SmartMiner() {
	}
	
	public void scan(URL baseURL, int scanLevels) {
	}
	
	private static ArrayList<URL> parseURL(URL baseURL) throws Exception {
		ArrayList<URL> urlList = new ArrayList<URL>();
		
		String data = FileUtil.getSource(baseURL.toString());
		Document doc = Jsoup.parse(data);
		
		Elements linkList = doc.getElementsByTag("a");
		for (Element e : linkList) {
			String link = e.attr("href").toString();
			
			try {
				if (!urlList.contains(new URL(link))) {
					if (e.attr("href").contains(baseURL.toString()))
						urlList.add(new URL(e.attr("href")));
					else if (e.attr("href").startsWith("/"))
						urlList.add(new URL(baseURL.toString() + e.attr("href").substring(1)));
				}
			} catch (Exception ex) {}
		}
		return urlList;
	}
	
	public static void main(String[] args) {
		try {
			/*
			ArrayList<URL> url = parseURL(new URL("http://www.620wtmj.com/"));
			for (URL u : url) System.out.println(u.toString());
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}