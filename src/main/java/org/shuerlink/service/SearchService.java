package org.shuerlink.service;

import java.util.LinkedList;

import org.shuerlink.model.ImageResult;
import org.shuerlink.model.MusicResult;
import org.shuerlink.model.WebPageResult;
import org.shuerlink.model.VedioResult;


public interface SearchService {
	public LinkedList<WebPageResult> searchWebPage(String keyword);
	public LinkedList<ImageResult> searchImage(String keyword);
	public LinkedList<VedioResult> searchVedio(String keyword);
	public LinkedList<MusicResult> searchMusic(String keyword);
}
