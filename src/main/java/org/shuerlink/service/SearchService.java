package org.shuerlink.service;

import java.util.List;

import org.shuerlink.model.Result.ImageResult;
import org.shuerlink.model.Result.ShareResult;
import org.shuerlink.model.Result.VedioResult;
import org.shuerlink.model.Result.WebPageResult;


public interface SearchService {
    public List<WebPageResult> getWebpage(String keyword, int start, int num);

    public List<ImageResult> getImage(String keyword, int start, int num);

    public List<VedioResult> getVedio(String keyword, int start, int num);

    public List<ShareResult> getShare(String keyword, int start, int num);

    public List<WebPageResult> getSchool(String keyword, int start, int num);
}
