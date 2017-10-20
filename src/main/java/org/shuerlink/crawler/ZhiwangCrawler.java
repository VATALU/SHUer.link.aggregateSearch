package org.shuerlink.crawler;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ZhiwangCrawler {
	private static String zhiwang = "http://epub.cnki.net/kns/brief/"
			+ "default_result.aspx?"
			+ "txt_1_sel=FT%24%25%3D%7C&txt_1_special1=%25&txt_extension=&expertvalue=&cjfdcode=%C2%A4tid=txt_1_value1&dbJson=coreJson&dbPrefix=SCDB&db_opt=CJFQ%2CCJFN%2CCDFD%2CCMFD%2CCPFD%2CIPFD%2CCCND%2CCCJD%2CHBRD&db_value=&hidTabChange=&hidDivIDS=&singleDB=SCDB&db_codes=&singleDBName=&againConfigJson=false&action=scdbsearch&ua=1.11&txt_1_"
			+ "value1=";

	public String start(String content) throws IOException {
		StringBuffer sb = new StringBuffer("ÖªÍø\n");
		Document doc = Jsoup.connect(zhiwang + content).userAgent("Mozilla").timeout(3000).get();
//		Elements results = doc.select("ol#b_results li.b_algo");
//		for (Element result : results) {
//			Elements s = result.select("h2");
//			sb.append(s.text());
//			sb.append("\n");
//			sb.append(s.select("a[href]").attr("href"));
//			sb.append("\n");
//			sb.append(result.select("p").text());
//			sb.append("\n\n");
//		}
		return sb.toString();
	}
}
