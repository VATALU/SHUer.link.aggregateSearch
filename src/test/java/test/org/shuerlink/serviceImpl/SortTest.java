package test.org.shuerlink.serviceImpl;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import org.junit.Test;
import org.shuerlink.model.TextResult;

public class SortTest {
	@Test
	public void testSort() {
		LinkedList<TextResult> results = new LinkedList<TextResult>();
		TextResult sr1 = new TextResult();
		TextResult sr2 = new TextResult();
		TextResult sr3 = new TextResult();
		TextResult sr4 = new TextResult();
		
		sr1.setGrade(120);
		sr2.setGrade(120);
		sr3.setGrade(110);
		sr4.setGrade(130);
		
		sr1.setTitleURL("a");
		sr2.setTitleURL("asdaf");
		sr3.setTitleURL("asfsfss");
		sr4.setTitleURL("a");

		sr1.setTitle("1");
		sr2.setTitle("2");
		sr3.setTitle("3");
		sr4.setTitle("4");
		
		results.add(sr1);
		results.add(sr2);
		results.add(sr3);
		results.add(sr4);
		results = new LinkedList<TextResult>(new LinkedHashSet<TextResult>(results));
		// 排序
		Collections.sort(results, new Comparator<TextResult>() {
			@Override
			public int compare(TextResult o1, TextResult o2) {
				return o1.compareTo(o2);
			}
		});
		
		for(int i=0;i<results.size();i++) {
			TextResult sr = results.get(i);
			System.out.println(sr.getTitle() + " " + sr.getGrade());
		}
		
	}
}
