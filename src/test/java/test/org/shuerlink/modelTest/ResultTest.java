package test.org.shuerlink.modelTest;

import org.junit.Test;
import org.shuerlink.model.Result;
import org.shuerlink.model.WebPageResult;

public class ResultTest {
    @Test
    public void testResult(){
        WebPageResult wpr = new WebPageResult();
        wpr.setUrl("adad.com");
        wpr.setTitle("xxx");
        Result r;
        r = wpr;
        System.out.println(r);
    }

}
