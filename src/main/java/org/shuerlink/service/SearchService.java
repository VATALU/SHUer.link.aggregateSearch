package org.shuerlink.service;

import java.util.LinkedList;

import org.shuerlink.model.TextResult;

public interface SearchService {
	public LinkedList<TextResult> search(String content);
}
