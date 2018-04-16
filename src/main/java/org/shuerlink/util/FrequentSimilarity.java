package org.shuerlink.util;

import org.shuerlink.nlp.similarity.text.TextSimilarity;
import org.shuerlink.nlp.similarity.util.AtomicFloat;
import org.shuerlink.nlp.tokenizer.Word;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FrequentSimilarity extends TextSimilarity {

    @Override
    protected double getSimilarityImpl(List<Word> words1, List<Word> words2) {
        // 词频标注词的权重
        taggingWeightByFrequency(words1, words2);
        // 权重容器
        Map<String, Float> weightMap1 = getFastSearchMap(words1);
        Map<String, Float> weightMap2 = getFastSearchMap(words2);
        Set<Word> words = new HashSet<>();
        words.addAll(words1);
        words.addAll(words2);
        AtomicFloat ab = new AtomicFloat();// a.b
        AtomicFloat aa = new AtomicFloat();// |a|的平方
        AtomicFloat bb = new AtomicFloat();// |b|的平方
        // 计算
        words.parallelStream()
                .forEach(word -> {
                    Float x1 = weightMap1.get(word.getName());
                    Float x2 = weightMap2.get(word.getName());
                    if (x1 != null && x2 != null) {
                        //x1x2
                        float oneOfTheDimension = x1 * x2;
                        //+
                        ab.addAndGet(oneOfTheDimension);
                    }
                    if (x1 != null) {
                        //(x1)^2
                        float oneOfTheDimension = x1 * x1;
                        //+
                        aa.addAndGet(oneOfTheDimension);
                    }
                    if (x2 != null) {
                        //(x2)^2
                        float oneOfTheDimension = x2 * x2;
                        //+
                        bb.addAndGet(oneOfTheDimension);
                    }
                });
        //|a|
        double aaa = Math.sqrt(aa.doubleValue());
        //|b|
//        double bbb = Math.sqrt(bb.doubleValue());
        //使用BigDecimal保证精确计算浮点数
        //|a|*|b|
        //double aabb = aaa * bbb;
//        BigDecimal aabb = BigDecimal.valueOf(aaa).multiply(BigDecimal.valueOf(bbb));
        BigDecimal aaaa=BigDecimal.valueOf(aaa);
        //similarity=a.b/|a|*|b|
        //double cos = ab.get() / aabb.doubleValue();
        double cos = BigDecimal.valueOf(ab.get()).divide(aaaa, 9, BigDecimal.ROUND_HALF_UP).doubleValue();
        return cos;
    }
}
