package test.org.shuerlink.crawler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.shuerlink.crawler.BingCrawler;
import org.shuerlink.crawler.ZhiwangCrawler;

public class ZhiwangCrawlerTest {
	@Test
	public void testBaiduCrawler() throws UnsupportedEncodingException {

	System.out.println("Start:");
	try {
		System.out.println(new ZhiwangCrawler().start("Íõ±¦Ç¿"));
	} catch (IOException e) {
		e.printStackTrace();
		System.out.println("connect failer");
	}
	System.out.println("End~");
}
}
