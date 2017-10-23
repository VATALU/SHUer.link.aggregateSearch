package org.shuerlink.service;

import java.util.LinkedList;

import org.shuerlink.model.SearchResult;

public interface SearchService {
	public LinkedList<SearchResult> search(String content);
}
