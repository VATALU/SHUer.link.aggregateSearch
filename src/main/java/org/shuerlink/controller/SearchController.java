package org.shuerlink.controller;

import java.util.LinkedList;
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
@RequestMapping(value="search")
public class SearchController {
    private static Logger logger = Logger.getLogger(SearchController.class.getName());
    @Resource
    private SearchServiceImpl searchService;

    @RequestMapping(value = "/webpage", produces = "application/json; charset=utf-8")
    public @ResponseBody
    LinkedList<?> searchWebPage(String keyword,
                         @RequestParam(defaultValue = "0") int start,
                         @RequestParam(defaultValue = "10") int num) {
        logger.info(keyword);
        Long startTime = System.currentTimeMillis();
        LinkedList<?> result = null;
        result = searchService.getWebpage(keyword,start,num);
        System.out.println(System.currentTimeMillis() - startTime);
        return result;
    }

    @RequestMapping(value = "/image", produces = "application/json; charset=utf-8")
    public @ResponseBody
    LinkedList<?> searchImage(String keyword,
                         @RequestParam(defaultValue = "0") int start) {
        logger.info(keyword);
        Long startTime = System.currentTimeMillis();
        LinkedList<?> result = null;
        result = searchService.getImage(keyword,start);
        System.out.println(System.currentTimeMillis() - startTime);
        return result;
    }

    @RequestMapping(value = "/vedio", produces = "application/json; charset=utf-8")
    public @ResponseBody
    LinkedList<?> searchVedio(String keyword,
                         @RequestParam(defaultValue = "0") int start,
                         @RequestParam(defaultValue = "10") int num) {
        logger.info(keyword);
        Long startTime = System.currentTimeMillis();
        LinkedList<?> result = null;
        result = searchService.getVedio(keyword,start,num);
        System.out.println(System.currentTimeMillis() - startTime);
        return result;
    }

    @RequestMapping(value = "/paper", produces = "application/json; charset=utf-8")
    public @ResponseBody
    LinkedList<?> searchPaper(String keyword,
                         @RequestParam(defaultValue = "0") int start,
                         @RequestParam(defaultValue = "10") int num) {
        logger.info(keyword);
        Long startTime = System.currentTimeMillis();
        LinkedList<?> result = null;
        result = searchService.getPaper(keyword,start,num);
        System.out.println(System.currentTimeMillis() - startTime);
        return result;
    }

    @RequestMapping(value = "/music", produces = "application/json; charset=utf-8")
    public @ResponseBody
    LinkedList<?> searchMusic(String keyword,
                              @RequestParam(defaultValue = "0") int start,
                              @RequestParam(defaultValue = "10") int num) {
        logger.info(keyword);
        Long startTime = System.currentTimeMillis();
        LinkedList<?> result = null;
        result = searchService.getMusic(keyword,start,num);
        System.out.println(System.currentTimeMillis() - startTime);
        return result;
    }

    @RequestMapping(value = "/book", produces = "application/json; charset=utf-8")
    public @ResponseBody
    LinkedList<?> searchBook(String keyword,
                              @RequestParam(defaultValue = "0") int start,
                              @RequestParam(defaultValue = "10") int num) {
        logger.info(keyword);
        Long startTime = System.currentTimeMillis();
        LinkedList<?> result = null;
        result = searchService.getBook(keyword,start,num);
        System.out.println(System.currentTimeMillis() - startTime);
        return result;
    }
}
