package org.shuerlink.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;

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
    List<?> searchWebPage(String keyword,
                                @RequestParam(defaultValue = "0") int start,
                                @RequestParam(defaultValue = "10") int num) {
        logger.info(keyword);
        Long startTime = System.currentTimeMillis();
        List<?> result = null;
        result = searchService.getWebpage(keyword, start, num);
        System.out.println(System.currentTimeMillis() - startTime);
        return result;
    }

    @RequestMapping(value = "/image", produces = "application/json; charset=utf-8")
    public @ResponseBody
    List<?> searchImage(String keyword,
                              @RequestParam(defaultValue = "0") int start,
                                @RequestParam(defaultValue = "10") int num) {
        logger.info(keyword);
        Long startTime = System.currentTimeMillis();
        List<?> result = null;
        result = searchService.getImage(keyword, start,num);
        System.out.println(System.currentTimeMillis() - startTime);
        return result;
    }

    @RequestMapping(value = "/vedio", produces = "application/json; charset=utf-8")
    public @ResponseBody
    List<?> searchVedio(String keyword,
                              @RequestParam(defaultValue = "0") int start,
                              @RequestParam(defaultValue = "10") int num) {
        logger.info(keyword);
        Long startTime = System.currentTimeMillis();
        List<?> result = null;
        result = searchService.getVedio(keyword, start, num);
        System.out.println(System.currentTimeMillis() - startTime);
        return result;
    }

}
