package test.org.shuerlink.serviceImpl;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import org.junit.Test;
import org.shuerlink.model.Result.WebPageResult;

public class SortTest {
	@Test
	public void testSort() {
		LinkedList<WebPageResult> results = new LinkedList<WebPageResult>();
		WebPageResult sr1 = new WebPageResult();
		WebPageResult sr2 = new WebPageResult();
		WebPageResult sr3 = new WebPageResult();
		WebPageResult sr4 = new WebPageResult();
		
		sr1.setScore(120);
		sr2.setScore(120);
		sr3.setScore(110);
		sr4.setScore(130);
		
		sr1.setUrl("a");
		sr2.setUrl("asdaf");
		sr3.setUrl("asfsfss");
		sr4.setUrl("a");

		sr1.setTitle("1");
		sr2.setTitle("2");
		sr3.setTitle("3");
		sr4.setTitle("4");
		
		results.add(sr1);
		results.add(sr2);
		results.add(sr3);
		results.add(sr4);
		results = new LinkedList<WebPageResult>(new LinkedHashSet<WebPageResult>(results));
		// 排序
		Collections.sort(results, new Comparator<WebPageResult>() {
			@Override
			public int compare(WebPageResult o1, WebPageResult o2) {
				return o1.compareTo(o2);
			}
		});
		
		for(int i=0;i<results.size();i++) {
			WebPageResult sr = results.get(i);
			System.out.println(sr.getTitle() + " " + sr.getScore());
		}
		
	}
}
