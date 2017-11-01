package test.org.shuerlink.crawler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.shuerlink.crawlerImpl.ZhiwangCrawlerImpl;

public class ZhiwangCrawlerImplTest {
	@Test
	public void testBaiduCrawler() throws UnsupportedEncodingException {

	System.out.println("Start:");
	try {
		System.out.println(new ZhiwangCrawlerImpl().start("王宝强"));
	} catch (IOException e) {
		e.printStackTrace();
		System.out.println("connect failer");
	}
	System.out.println("End~");
}
}
