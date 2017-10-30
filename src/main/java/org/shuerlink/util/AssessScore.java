package org.shuerlink.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class AssessScore {
	private static Map<String, Integer> searchEngineCoefficientMap;
	static {
		Properties props = new Properties();
		searchEngineCoefficientMap = new HashMap<String, Integer>();
		InputStream in = null;
		try {
			in = AssessScore.class.getClassLoader().getResourceAsStream("searchEngineCoefficient.properties");
			props.load(in);
			Enumeration<?> en = props.propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				String property = props.getProperty(key);
				searchEngineCoefficientMap.put(key, Integer.valueOf(property));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 暂行评分算法
	 * @param index result's index
	 * @param searchEngine searchEngine's name
	 * @param title result's title
	 * @param keyword search keyword
	 * @return score
	 */
	public static int assess(int index,String searchEngine,String title,String keyword) {
		int searchEngineCoefficient = searchEngineCoefficientMap.get(searchEngine).intValue();
		int score = index * searchEngineCoefficient;
		
		return score;
	}
}
