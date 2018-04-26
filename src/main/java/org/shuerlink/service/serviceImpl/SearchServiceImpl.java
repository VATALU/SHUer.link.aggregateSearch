package org.shuerlink.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.shuerlink.crawler.CallableSpider;
import org.shuerlink.crawler.crawlerImpl.ImageCrawlerImpl.BaiduImageCallablePageProcessor;
import org.shuerlink.crawler.crawlerImpl.ImageCrawlerImpl.BingImageCallablePageProcessor;
import org.shuerlink.crawler.crawlerImpl.ImageCrawlerImpl.GoogleImageCallablePageProcessor;
import org.shuerlink.crawler.crawlerImpl.ShareCrawlerImpl.WeixinShareCallablePageProcessor;
import org.shuerlink.crawler.crawlerImpl.ShareCrawlerImpl.ZhihuShareCallablePageProcessor;
import org.shuerlink.crawler.crawlerImpl.VideoCrawlerImpl.*;
import org.shuerlink.crawler.crawlerImpl.WebpageCrawlerImpl.BaiduWebpageCallablePageProcessor;
import org.shuerlink.crawler.crawlerImpl.WebpageCrawlerImpl.BingWebpageCallablePageProcessor;
import org.shuerlink.crawler.crawlerImpl.WebpageCrawlerImpl.GoogleWebpageCallablePageProcessor;
import org.shuerlink.model.Result.ImageResult;
import org.shuerlink.model.Result.ShareResult;
import org.shuerlink.model.Result.VideoResult;
import org.shuerlink.model.Result.WebPageResult;
import org.shuerlink.service.SearchService;
import org.shuerlink.util.AssessScore;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * @author VATALU
 * @version 0.1
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Resource
    private ThreadPoolTaskExecutor taskExecutor;
    @Resource
    private BaiduImageCallablePageProcessor baiduImageCallablePageProcessor;
    @Resource
    private BaiduWebpageCallablePageProcessor baiduWebpageCallablePageProcessor;
    @Resource
    private BaiduVideoCallablePageProcessor baiduBaiduVideoCallablePageProcessor;
    @Resource
    private BingImageCallablePageProcessor bingImageCallablePageProcessor;
    @Resource
    private BingWebpageCallablePageProcessor bingWebpageCallablePageProcessor;
    @Resource
    private BingVideoCallablePageProcessor bingVideoCallablePageProcessor;
    @Resource
    private GoogleImageCallablePageProcessor googleImageCallablePageProcessor;
    @Resource
    private GoogleWebpageCallablePageProcessor googleWebpageCallablePageProcessor;
    @Resource
    private YoukuVideoCallablePageProcessor youkuVideoCallablePageProcessor;
    @Resource
    private IQIYIVideoCallablePageProcessor iqiyiVideoCallablePageProcessor;
    @Resource
    private BilibiliVideoCallablePageProcessor bilibiliVideoCallablePageProcessor;
    @Resource
    private ZhihuShareCallablePageProcessor zhihuShareCallablePageProcessor;
    @Resource
    private WeixinShareCallablePageProcessor weixinShareCallablePageProcessor;

    /*
     * 搜索网页
     * */
    @Override
    public List<WebPageResult> getWebpage(String keyword, int start, int num) {
        CallableSpider callableSpider = CallableSpider.newInstance(keyword, start, num,
                baiduWebpageCallablePageProcessor,
//              googleWebpageCallablePageProcessor,
                bingWebpageCallablePageProcessor
        ).setThreadPoolTask(taskExecutor);

        List<WebPageResult> webPageResults = callableSpider.call();
        /*
         * 排序
         * */
        webPageResults.forEach((webPageResult -> {
            AssessScore.assessWebPageBySimilarity(keyword, webPageResult);
        }));
        webPageResults.sort((t1, t2) -> (t1.compareTo(t2)));
        return webPageResults;
    }

    /*
     * 搜索照片
     * */
    @Override
    public List<ImageResult> getImage(String keyword, int start, int num) {

        CallableSpider callableSpider = CallableSpider.newInstance(keyword, start, num,
                baiduImageCallablePageProcessor,
//                googleImageCallablePageProcessor,
                bingImageCallablePageProcessor).setThreadPoolTask(taskExecutor);
        List<ImageResult> imageResults = callableSpider.call();
        /*
         *url排序
         */
        imageResults.sort((t1, t2) -> (t1.compareTo(t2)));

        return imageResults;
    }

    /*
     * 搜索视频
     * */
    @Override
    public List<VideoResult> getVideo(String keyword, int start, int num) {
        CallableSpider callableSpider = CallableSpider.newInstance(keyword, start, num,
                baiduBaiduVideoCallablePageProcessor, youkuVideoCallablePageProcessor,
//                bilibiliVideoCallablePageProcessor,
                iqiyiVideoCallablePageProcessor,
                bingVideoCallablePageProcessor).setThreadPoolTask(taskExecutor);
        List<VideoResult> videoResults = callableSpider.call();

        videoResults.sort((t1, t2) -> (t1.compareTo(t2)));

        return videoResults;
    }

    @Override
    public List<ShareResult> getShare(String keyword, int start, int num) {
        CallableSpider callableSpider = CallableSpider.newInstance(keyword, start, num,
                zhihuShareCallablePageProcessor,
                weixinShareCallablePageProcessor).setThreadPoolTask(taskExecutor);
        List<ShareResult> shareResults = callableSpider.call();

        shareResults.sort((t1, t2) -> (t1.compareTo(t2)));

        return shareResults;
    }

    @Override
    public List<WebPageResult> getSchool(String keyword, int start, int num) {
        List<WebPageResult> results = null;
        return results;
    }

}
