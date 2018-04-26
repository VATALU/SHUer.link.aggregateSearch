package org.shuerlink.util;

import com.hankcs.hanlp.mining.word2vec.DocVectorModel;
import com.hankcs.hanlp.mining.word2vec.WordVectorModel;
import org.shuerlink.model.Result.WebPageResult;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class AssessScore {

    private static WordVectorModel wordVectorModel;
    private static DocVectorModel docVectorModel;

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
            wordVectorModel = new WordVectorModel("/var/lib/model/hanlp-wiki-vec-zh.txt");
//            wordVectorModel = new WordVectorModel("E:\\github workplace\\SHUer.link.aggregateSearch\\src\\main\\resources\\data\\hanlp-wiki-vec-zh.txt");
            docVectorModel = new DocVectorModel(wordVectorModel);
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
        double score = assessByTextWithText(keyword, webPageResult.getTitle() + webPageResult.getDescription()) / webPageResult.getScore();
        webPageResult.setScore(score);
        return score;
    }

    private static double assessByTextWithText(String text1, String text2) {
        return docVectorModel.similarity(text1, text2);
    }

}
