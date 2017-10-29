package org.shuerlink.controller;

import java.util.LinkedList;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.shuerlink.model.TextResult;
import org.shuerlink.serviceImpl.SearchServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public @ResponseBody LinkedList<TextResult> search(String keyword) {
		logger.info(keyword);
		Long start = System.currentTimeMillis();
		LinkedList<TextResult> result = searchService.search(keyword);
		System.out.println(System.currentTimeMillis()-start);
		return result;
		//return searchService.search(content);
	}

}
