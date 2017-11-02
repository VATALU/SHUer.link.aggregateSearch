package org.shuerlink.service;

import java.util.LinkedList;

import org.shuerlink.model.*;


public interface SearchService {
	public LinkedList<WebPageResult> searchWebPage(String keyword);
	public LinkedList<ImageResult> searchImage(String keyword);
	public LinkedList<VedioResult> searchVedio(String keyword);
	public LinkedList<MusicResult> searchMusic(String keyword);
	public LinkedList<BookResult> searchBook(String keyword);
	public LinkedList<PaperResult> searchPaper(String keyword);
}
