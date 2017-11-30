package org.shuerlink.service;

import java.util.LinkedList;

import org.shuerlink.model.*;


public interface SearchService {
	public LinkedList<WebPageResult> getWebpage(String keyword, int start, int num);
	public LinkedList<ImageResult> getImage(String keyword,int start);
	public LinkedList<VedioResult> getVedio(String keyword, int start, int num);
}
