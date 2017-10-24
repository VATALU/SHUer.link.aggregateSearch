package org.shuerlink.model;

/**
 * @author VATALU
 *
 */
public class SearchResult implements Comparable<SearchResult> {
	private String searchEngine;
	private String title;
	private String titleURL;
	private String _abstract;
	private int grade;

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getSearchEngine() {
		return searchEngine;
	}

	public void setSearchEngine(String searchEngine) {
		this.searchEngine = searchEngine;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleURL() {
		return titleURL;
	}

	public void setTitleURL(String titleURL) {
		this.titleURL = titleURL;
	}

	public String get_abstract() {
		return _abstract;
	}

	public void set_abstract(String _abstract) {
		this._abstract = _abstract;
	}

	@Override
	public int compareTo(SearchResult sr) {
		if (sr.grade < this.grade) {
			return -1;
		} else if (sr.grade > this.grade) {
			return 1;
		} else {
			return sr.title.compareTo(this.title);
		}
	}

	@Override
	public int hashCode() {
		return 0;
	}
	
	
	/* 
	 * URL去重
	 */
	@Override
	public boolean equals(Object obj) {
		SearchResult sr = (SearchResult) obj;
		if (this.titleURL.equals(sr.titleURL)) {
			return true;
		} else {
			return false;
		}
	}
}
