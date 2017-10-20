package org.shuerlink.controller;

import javax.annotation.Resource;

import org.shuerlink.serviceImpl.SearchServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author VATALU
 * @version 0.1
 */

@Controller
public class SearchController {
	@Resource
	private SearchServiceImpl searchService;

	@RequestMapping(value = "search", produces = "application/json; charset=utf-8")
	public @ResponseBody String search(String content) {
		Long start = System.currentTimeMillis();
		String result = searchService.search(content);
		System.out.println(System.currentTimeMillis()-start);
		return result;
		//return searchService.search(content);
	}

}
