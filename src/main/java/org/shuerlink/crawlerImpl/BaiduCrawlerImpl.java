package org.shuerlink.crawlerImpl;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.shuerlink.crawler.ImageCrawler;
import org.shuerlink.crawler.MusicCrawler;
import org.shuerlink.crawler.VedioCrawler;
import org.shuerlink.crawler.WebPageCrawler;
import org.shuerlink.model.ImageResult;
import org.shuerlink.model.MusicResult;
import org.shuerlink.model.VedioResult;
import org.shuerlink.model.WebPageResult;
import org.shuerlink.util.AssessScore;
import org.springframework.stereotype.Repository;

@Repository
public class BaiduCrawlerImpl implements WebPageCrawler, MusicCrawler, ImageCrawler, VedioCrawler {
    private static Logger logger = Logger.getLogger(BaiduCrawlerImpl.class.getName());

    private static String baidu = "http://www.baidu.com/s?wd=";
    private static String image = "http://image.baidu.com/search/index?tn=baiduimage&word=";

    @Override
    public LinkedList<WebPageResult> getWebPageResult(String keyword, int start, int num) {
        LinkedList<WebPageResult> resultList = null;
        try {
            resultList = new LinkedList<WebPageResult>();
            Document doc = Jsoup.connect(baidu + keyword + "&pn=" + String.valueOf(start) + "&rn=" + String.valueOf(num)).userAgent("Mozilla").timeout(3000).get();

            //result-op.c-container.xpath-log
            Elements result_op_c_container_xpath_log = doc.select("div.result-op.c-container.xpath-log");
            for (Element result : result_op_c_container_xpath_log) {
                WebPageResult webPageResult = new WebPageResult();
                //设置搜索引擎
                webPageResult.setSearchEngine("百度搜索");
                //设置标题和标题链接
                Elements title = result.select("h3");
                webPageResult.setTitle(title.text());
                webPageResult.setUrl(title.select("a[href]").attr("href"));
                Elements baikeDiscription = result.select("div.c-span18.c-span-last");
                if (!baikeDiscription.html().equals("")) {
                    //设置摘要
                    Elements p = baikeDiscription.select("p:lt(2)");
                    webPageResult.setDiscription(p.text());
                    //设置评分
                    webPageResult.setScore(AssessScore.assess(Integer.valueOf(result.attr("id")), "baidu"));
                    resultList.add(webPageResult);
                }
                Elements tiebaDiscription = result.select("div.op-tieba-general-main-col.op-tieba-general-main-con");
                if (!tiebaDiscription.html().equals("")) {
                    //设置摘要
                    webPageResult.setDiscription(
                            tiebaDiscription.select("p").text());
                    //设置评分
                    webPageResult.setScore(AssessScore.assess(Integer.valueOf(result.attr("id")), "baidu"));
                    resultList.add(webPageResult);
                }
            }
            //result-op.c-container

            //result.c-container
            Elements results_c_container = doc.select("div.result.c-container");
            for (Element result : results_c_container) {
                WebPageResult webPageResult = new WebPageResult();
                //设置搜索引擎
                webPageResult.setSearchEngine("百度搜索");
                //设置标题和标题链接
                Elements title = result.select("h3");
                webPageResult.setTitle(title.text());
                webPageResult.setUrl(title.select("a[href]").attr("href"));
                //设置摘要
                webPageResult.setDiscription(result.select(".c-abstract").text());
                //设置评分
                webPageResult.setScore(AssessScore.assess(Integer.valueOf(result.attr("id")), "baidu"));
                resultList.add(webPageResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.warning("百度搜索" + "WebPage" + "搜索失败");
        }
        return resultList;
    }

    @Override
    public LinkedList<VedioResult> getVedioResult(String keyword, int start, int num) {
        return null;
    }

    /*
    * */
    @Override
    public LinkedList<ImageResult> getImageResult(String keyword, int start, int num) {
        LinkedList<ImageResult> resultList = null;
        try {
            keyword = URLEncoder.encode(keyword, "UTF-8");
            resultList = new LinkedList<>();
            DesiredCapabilities dcaps = new DesiredCapabilities();
            dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "D:\\driver\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
            PhantomJSDriver driver = new PhantomJSDriver(dcaps);
            driver.manage().window().maximize();
            driver.get(image+keyword);
            WebDriverWait webDriverWait = new WebDriverWait(driver,3);
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.className("imgitem")));
            List<WebElement> elements = driver.findElements(By.cssSelector(".imgitem"));
            for (int i = 0;i<elements.size();i++) {
                WebElement element = elements.get(i);
                ImageResult imageResult = new ImageResult();
                imageResult.setSearchEngine("百度搜索");
                String url = element.getAttribute("data-objurl");
                imageResult.setUrl(url);
                String hostUrl = element.getAttribute("data-fromurlhost");
                imageResult.setHostUrl(hostUrl);
                String title = element.getAttribute("data-title");
                imageResult.setTitle(title);
                String extension = element.getAttribute("data-ext");
                imageResult.setExtension(extension);
                String width = element.getAttribute("data-width");
                imageResult.setWidth(Integer.valueOf(width));
                String height = element.getAttribute("data-height");
                imageResult.setHeight(Integer.valueOf(height));
                imageResult.setScore(AssessScore.assess(i+1,"baidu"));
                resultList.add(imageResult);
            }
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public LinkedList<MusicResult> getMusicResult(String keyword, int start, int num) {
        return null;
    }


}
