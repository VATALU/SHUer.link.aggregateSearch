package test.org.shuerlink.crawler;

import java.io.IOException;

import org.junit.Test;
import org.shuerlink.crawler.BaiduCrawler;

public class BaiduCrawlerTest {
	@Test
	public void testBaiduCrawler() {
		System.out.println("Start:");
		try {
			System.out.println(new BaiduCrawler().start("xxs"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("connect failer");
		}
		System.out.println("End~");
	}
}
