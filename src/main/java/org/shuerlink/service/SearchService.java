package org.shuerlink.service;

import java.util.LinkedList;

import org.shuerlink.model.*;


public interface SearchService {
	public LinkedList<Result> getWebpage(String keyword, int start, int num);
	public LinkedList<Result> getImage(String keyword,int start);
	public LinkedList<Result> getVedio(String keyword, int start, int num);
	public LinkedList<Result> getMusic(String keyword, int start, int num);
	public LinkedList<Result> getBook(String keyword, int start, int num);
}
