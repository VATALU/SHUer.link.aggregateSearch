package test.org.shuerlink.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import ucar.unidata.util.Urlencoded;

import java.io.IOException;
import java.net.URLEncoder;

public class BilibiliCallablePageProcessorTest {
    @Test
    public void testBilibili(){
        try {
            String keyword = URLEncoder.encode("可达鸭");
            Document doc= Jsoup.connect("https://search.bilibili.com/all?keyword="+keyword).userAgent("Mozilla").get();
            Elements movieList=doc.select(".ajax-render li").select("a.title");
            for (Element element:movieList){
                String nameElement=element.attr("title");
                String urlElement=element.attr("href").substring(2);
                System.out.println(nameElement+" "+urlElement);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
