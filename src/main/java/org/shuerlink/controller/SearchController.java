package org.shuerlink.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.shuerlink.model.ImageResult;
import org.shuerlink.model.ShareResult;
import org.shuerlink.model.VedioResult;
import org.shuerlink.model.WebPageResult;
import org.shuerlink.serviceImpl.SearchServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author VATAL
 * @version 0.1
 */

@Controller
@RequestMapping(value = "search")
public class SearchController {
    private static Logger logger = Logger.getLogger(SearchController.class.getName());
    @Resource
    private SearchServiceImpl searchService;

    @RequestMapping(value = "/webpage", produces = "application/json; charset=utf-8")
    public @ResponseBody
    List<WebPageResult> searchWebPage(String keyword,
                                      @RequestParam(defaultValue = "0") int start,
                                      @RequestParam(defaultValue = "10") int num) {
        logger.info(keyword);
        Long startTime = System.currentTimeMillis();
        List<WebPageResult> result = null;
        result = searchService.getWebpage(keyword, start, num);
        System.out.println(System.currentTimeMillis() - startTime);
        return result;
    }

    @RequestMapping(value = "/image", produces = "application/json; charset=utf-8")
    public @ResponseBody
    List<ImageResult> searchImage(String keyword,
                                  @RequestParam(defaultValue = "0") int start,
                                  @RequestParam(defaultValue = "10") int num) {
        logger.info(keyword);
        Long startTime = System.currentTimeMillis();
        List<ImageResult> result = null;
        result = searchService.getImage(keyword, start, num);
        System.out.println(System.currentTimeMillis() - startTime);
        return result;
    }

    @RequestMapping(value = "/vedio", produces = "application/json; charset=utf-8")
    public @ResponseBody
    List<VedioResult> searchVedio(String keyword,
                                  @RequestParam(defaultValue = "0") int start,
                                  @RequestParam(defaultValue = "10") int num) {
        logger.info(keyword);
        Long startTime = System.currentTimeMillis();
        List<VedioResult> result = null;
        result = searchService.getVedio(keyword, start, num);
        System.out.println(System.currentTimeMillis() - startTime);
        return result;
    }

    @RequestMapping(value = "/article", produces = "application/json; charset=utf-8")
    public @ResponseBody
    List<ShareResult> searchArticle(String keyword,
                                    @RequestParam(defaultValue = "0") int start,
                                    @RequestParam(defaultValue = "10") int num) {
        logger.info(keyword);
        Long startTime = System.currentTimeMillis();
        List<ShareResult> result = null;
        result = searchService.getShare(keyword, start, num);
        System.out.println(System.currentTimeMillis() - startTime);
        return result;
    }

    @RequestMapping(value = "/school", produces = "application/json; charset=utf-8")
    public @ResponseBody
    List<WebPageResult> searchSchool(String keyword,
                                     @RequestParam(defaultValue = "0") int start,
                                     @RequestParam(defaultValue = "10") int num) {
        logger.info(keyword);
        Long startTime = System.currentTimeMillis();
        List<WebPageResult> result = null;
        result = searchService.getSchool(keyword, start, num);
        System.out.println(System.currentTimeMillis() - startTime);
        return result;
    }

}
