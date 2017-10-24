package test.org.shuerlink.util;

import org.junit.Test;
import org.shuerlink.util.AssessScore;

public class AssessScoreTest {
	@Test
	public void testAssessScore() {
		System.out.println(AssessScore.assess(3, "百度", "", ""));
		System.out.println(AssessScore.assess(1, "必应", "", ""));
	}
}
