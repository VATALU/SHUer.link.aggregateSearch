package test.org.shuerlink.crawler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Test;
import org.shuerlink.crawler.BaiduCrawler;
import org.shuerlink.crawler.BingCrawler;

public class BingCrawlerTest {
	@Test
	public void testBaiduCrawler() throws UnsupportedEncodingException {
		// System.out.println("Start:");
		// try {
		// System.out.println(new BingCrawler().start("%E5%8F%AF%E8%BE%BE%E9%B8%AD"));
		// } catch (IOException e) {
		// e.printStackTrace();
		// System.out.println("connect failer");
		// }
		// System.out.println("End~");
		String strPart = "�ɴ�Ѽ";
		String urlStr = URLEncoder.encode(strPart,"UTF-8");
		System.out.println(urlStr);
	}

}