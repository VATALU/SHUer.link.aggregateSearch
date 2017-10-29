package org.shuerlink.crawler;

import java.io.IOException;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.shuerlink.model.TextResult;
import org.shuerlink.util.AssessScore;

public class BaiduCrawler {
    private static String baidu = "http://www.baidu.com/s?wd=";

    public LinkedList<TextResult> start(String keyword) throws IOException {
        LinkedList<TextResult> resultList = new LinkedList<TextResult>();
        Long getConnect = System.currentTimeMillis();
        Document doc = Jsoup.connect(baidu + keyword).userAgent("Mozilla").timeout(3000).get();
        System.out.println("baidu获取链接" + (System.currentTimeMillis() - getConnect));

        //result-op.c-container.xpath-log
        Elements result_op_c_container_xpath_log = doc.select("div.result-op.c-container.xpath-log");
        for (Element result : result_op_c_container_xpath_log) {
            TextResult textResult = new TextResult();
            textResult.setSearchEngine("百度搜索");
            Elements title = result.select("h3");
            textResult.setTitle(title.text());
            textResult.setTitleURL(title.select("a[href]").attr("href"));
            Elements baike_abstract = result.select("div.c-span18.c-span-last");
            if (!baike_abstract.html().equals("")) {
                Element p = baike_abstract.select("p").first();
                textResult.setDiscription(p.text());
                textResult.setGrade(AssessScore.assess(Integer.valueOf(result.attr("id")), "baidu", textResult.getTitle(), textResult.getTitleURL()));
                resultList.add(textResult);
            }

//            Elements fanyi_abstract = result.select("div.op_dict_content");
//            if (fanyi_abstract != null) {
//                textResult.setDiscription(fanyi_abstract.select("div.op_dict3_readline.c-clearfix").text() + "\n" +
//                        fanyi_abstract.select("div.op_dict3_english_result_table").text() + "\n" +
//                        fanyi_abstract.select("div.op_dict3_lineone_result.c-clearfix").text() + "\n" +
//                        fanyi_abstract.select("div.op_dict3_linetwo_result").text() + "\n" +
//                        fanyi_abstract.select("div.op_dict3_english_result_table.op_dict3_else").text()
//                );
//            }

            Elements tieba_abstract = result.select("div.op-tieba-general-main-col.op-tieba-general-main-con");
            if (!tieba_abstract.html().equals("")) {
                textResult.setDiscription(
                        tieba_abstract.select("p").text());
                textResult.setGrade(AssessScore.assess(Integer.valueOf(result.attr("id")), "baidu", textResult.getTitle(), textResult.getTitleURL()));
                resultList.add(textResult);
            }
        }
        //result-op.c-container

        //result.c-container
        Elements results_c_container = doc.select("div.result.c-container");
        for (Element result : results_c_container) {
            TextResult searchResult = new TextResult();
            searchResult.setSearchEngine("百度搜索");
            Elements title = result.select("h3");
            searchResult.setTitle(title.text());
            searchResult.setTitleURL(title.select("a[href]").attr("href"));
            searchResult.setDiscription(result.select(".c-abstract").text());
            searchResult.setGrade(AssessScore.assess(Integer.valueOf(result.attr("id")), "baidu", searchResult.getTitle(), searchResult.getTitleURL()));
            resultList.add(searchResult);
        }
        return resultList;
    }
}
