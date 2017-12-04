package org.shuerlink.crawlerImpl.VedioCrawlerImpl;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.crawler.VedioCallablePageProcessor;
import org.shuerlink.model.VedioResult;
import org.shuerlink.util.AssessScore;
import org.springframework.stereotype.Repository;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;

import java.util.LinkedList;
import java.util.List;

@Repository
public class QIYVedioCallablePageProcessor extends VedioCallablePageProcessor {
    private static final String url = "http://so.iqiyi.com/so/q_";
    @Override

    public LinkedList<VedioResult> getResults(Page page) {
        return process(page.getHtml().getDocument());
    }
    public LinkedList<VedioResult> process(Document document) {
        LinkedList<VedioResult> resultLinkedList = new LinkedList<>();
        Elements elements = document.select("li.list_item");
        int i = 1;
        for (Element element : elements) {
            VedioResult vedioResult = new VedioResult();
            //设置搜索引擎
            vedioResult.setSearchEngine("爱奇艺搜索");
            //设置score
            vedioResult.setScore(AssessScore.assess(i++,"QIY"));
            //设置time
            String time = element.select("span.icon-vInfo").text();
            vedioResult.setTime(time);
            //设置imageUrl
            String imageUrl = element.select("img").attr("src");
            vedioResult.setImageUrl(imageUrl);
            //设置url
            String url = element.select("a").attr("href");
            vedioResult.setUrl(url);
            //设置title
            String title = element.select("img").attr("alt");
            vedioResult.setTitle(title);
            //设置publisher
            String publisher = element.select("div.result_info_cont result_info_cont-half").select("a").text();
            vedioResult.setPublisher(publisher);
            resultLinkedList.add(vedioResult);
        }
        return resultLinkedList;
    }
    @Override
    public String getUrl(String keyword, int start, int num) {
        return url + keyword+"_ctg__t_0_page_1_p_1_qc_1_rd__site__m_1_bitrate_?source=correct";
    }
}