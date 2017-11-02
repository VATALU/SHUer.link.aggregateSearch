package org.shuerlink.service;

import java.util.LinkedList;

import org.shuerlink.model.*;


public interface SearchService {
	public LinkedList<WebPageResult> searchWebPage(String keyword, int start, int num);
	public LinkedList<ImageResult> searchImage(String keyword, int start, int num);
	public LinkedList<VedioResult> searchVedio(String keyword, int start, int num);
	public LinkedList<MusicResult> searchMusic(String keyword, int start, int num);
	public LinkedList<BookResult> searchBook(String keyword, int start, int num);
	public LinkedList<PaperResult> searchPaper(String keyword, int start, int num);
}
