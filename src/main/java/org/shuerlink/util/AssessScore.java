package org.shuerlink.util;

import org.shuerlink.model.Result.Result;
import org.shuerlink.model.Result.WebPageResult;
import org.shuerlink.nlp.similarity.text.TextSimilarity;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class AssessScore {
    private static Map<String, Integer> searchEngineCoefficientMap;

    static {
        Properties props = new Properties();
        searchEngineCoefficientMap = new HashMap<String, Integer>();
        InputStream in = null;
        try {
            in = AssessScore.class.getClassLoader().getResourceAsStream("searchEngineCoefficient.properties");
            props.load(in);
            Enumeration<?> en = props.propertyNames();
            while (en.hasMoreElements()) {
                String key = (String) en.nextElement();
                String property = props.getProperty(key);
                searchEngineCoefficientMap.put(key, Integer.valueOf(property));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 暂行评分算法
     *
     * @param index        result's index
     * @param searchEngine searchEngine's name
     * @return score
     */
    public static int assessBySearchEngine(int index, String searchEngine) {
        int searchEngineCoefficient = searchEngineCoefficientMap.get(searchEngine).intValue();
        int score = index * searchEngineCoefficient;
        return score;
    }

    public static double assessWebPageBySimilarity(String keyword, WebPageResult webPageResult) {
        double score= webPageResult.getScore()*assessByTextWithText(keyword,webPageResult.getTitle()+webPageResult.getDescription());
        webPageResult.setScore(score);
        return score;
    }

    private static double assessByTextWithText(String t1, String t2) {
        TextSimilarity similarity = new FrequentSimilarity();
        return similarity.getSimilarity(t1, t2);
    }
}
