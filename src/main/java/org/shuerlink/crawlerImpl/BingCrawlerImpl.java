package org.shuerlink.crawlerImpl;

import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.shuerlink.crawler.ImageCrawler;
import org.shuerlink.crawler.VedioCrawler;
import org.shuerlink.crawler.WebPageCrawler;
import org.shuerlink.model.ImageResult;
import org.shuerlink.model.VedioResult;
import org.shuerlink.model.WebPageResult;
import org.shuerlink.util.AssessScore;
import org.springframework.stereotype.Repository;

@Repository
public class BingCrawlerImpl implements WebPageCrawler, ImageCrawler, VedioCrawler {
    private static String bing = "https://www.bing.com/search?q=";
    private static String image = "https://cn.bing.com/images/search?q=";

    @Override
    public LinkedList<WebPageResult> getWebPageResult(String keyword, int start, int num) {
        LinkedList<WebPageResult> resultList = null;
        try {
            resultList = new LinkedList<>();
            keyword = URLEncoder.encode(keyword, "UTF-8");
            Document doc = Jsoup.connect(bing + keyword + "&first=" + start).userAgent("Mozilla").timeout(3000).get();
            Elements results = doc.select(".b_algo");
            int i = 1;
            for (Element result : results) {
                Elements s = result.select("h2");
                WebPageResult webPageResult = new WebPageResult();
                webPageResult.setSearchEngine("必应搜索");
                webPageResult.setTitle(s.text());
                webPageResult.setUrl(s.select("a[href]").attr("href"));
                webPageResult.setDiscription(result.select("p").text());
                webPageResult.setScore(AssessScore.assess(i++, "bing"));
                resultList.add(webPageResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }


    @Override
    public LinkedList<VedioResult> getVedioResult(String keyword, int start, int num) {
        return null;
    }

    @Override
    public LinkedList<ImageResult> getImageResult(String keyword, int start, int num) {
        LinkedList<ImageResult> resultList = null;
        try{
            resultList = new LinkedList<>();
            keyword = URLEncoder.encode(keyword, "UTF-8");
            DesiredCapabilities dcaps = new DesiredCapabilities();
            dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "D:\\driver\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
            PhantomJSDriver driver = new PhantomJSDriver(dcaps);
            driver.manage().window().maximize();
            driver.get(image+keyword);
            WebDriverWait webDriverWait = new WebDriverWait(driver,3);
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".iusc")));
            int i = 1;
            List<WebElement> elements = driver.findElements(By.cssSelector(".iusc"));
            for (WebElement element : elements) {
                System.out.println(i);
                ImageResult imageResult = new ImageResult();
                //设置搜索引擎
                imageResult.setSearchEngine("必应搜索");
                //设置url
                String url = element.getAttribute("m");
                imageResult.setUrl(url);
                WebElement subElement = element.findElement(By.cssSelector(".b_dataList"));
                List<WebElement> li = subElement.findElements(By.tagName("li"));
                String title = li.get(2).getText();
                imageResult.setTitle(title);
                String width_height_extension = li.get(1).getText();
                String[] imageInfo = width_height_extension.split(" ");
                imageResult.setWidth(Integer.valueOf(imageInfo[0]));
                imageResult.setHeight(Integer.valueOf(imageInfo[2]));
                imageResult.setExtension(imageInfo[4]);
                imageResult.setScore(AssessScore.assess(i++,"bing"));
                resultList.add(imageResult);
            }
            driver.quit();
        }catch (Exception e){

        }
        return resultList;

    }

}
