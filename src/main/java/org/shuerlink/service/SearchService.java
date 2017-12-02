package org.shuerlink.service;

import java.util.LinkedList;
import java.util.List;

import org.shuerlink.model.*;


public interface SearchService {
	public List<WebPageResult> getWebpage(String keyword, int start, int num);
	public List<ImageResult> getImage(String keyword, int start,int num);
	public List<VedioResult> getVedio(String keyword, int start, int num);
}
