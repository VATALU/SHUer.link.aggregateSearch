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
public class SearchController {
    private static Logger logger = Logger.getLogger(SearchController.class.getName());
    @Resource
    private SearchServiceImpl searchService;

    @RequestMapping(value = "search", produces = "application/json; charset=utf-8")
    public @ResponseBody
    LinkedList<?> search(String keyword,
                         String form,
                         @RequestParam(defaultValue = "0") int start,
                         @RequestParam(defaultValue = "10") int num) {
        logger.info(keyword);
        Long startTime = System.currentTimeMillis();
        LinkedList<?> result = null;
        switch (form.toLowerCase()) {
            case "webpage":
                result = searchService.searchWebPage(keyword, start, num);
                break;
            case "image":
                result = searchService.searchImage(keyword, start, num);
                break;
            case "music":
                result = searchService.searchMusic(keyword, start, num);
                break;
            case "vedio":
                result = searchService.searchVedio(keyword, start, num);
                break;
            case "book":
                result = searchService.searchBook(keyword, start, num);
                break;
            case "paper":
                result = searchService.searchPaper(keyword, start, num);
                break;
        }
        System.out.println(System.currentTimeMillis() - startTime);
        return result;
    }


}
