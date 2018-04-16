package org.shuerlink.nlp;

import org.shuerlink.nlp.similarity.phrase.PhraseSimilarity;
import org.shuerlink.nlp.similarity.sentence.editdistance.EditDistance;
import org.shuerlink.nlp.similarity.sentence.editdistance.GregorEditDistanceSimilarity;
import org.shuerlink.nlp.similarity.sentence.editdistance.NewEditDistanceSimilarity;
import org.shuerlink.nlp.similarity.sentence.editdistance.StandardEditDistanceSimilarity;
import org.shuerlink.nlp.similarity.sentence.morphology.MorphoSimilarity;
import org.shuerlink.nlp.similarity.word.CharBasedSimilarity;
import org.shuerlink.nlp.similarity.word.clin.CilinSimilarity;
import org.shuerlink.nlp.similarity.word.hownet.concept.ConceptSimilarity;

/**
 * Similarity 相似度计算工具包
 *
 * @author xuming
 */
public class Similarity {

    public static final class Config {
        /**
         * 词林编码路径
         */
        public static String CilinPath = "cilin.db.gz";
        /**
         * 拼音词典路径
         */
        public static String PinyinPath = "F02-GB2312-to-PuTongHua-PinYin.txt";
        /**
         * concept路径
         */
        public static String ConceptPath = "concept.dat";
        /**
         * concept.xml.gz路径
         */
        public static String ConceptXmlPath = "concept.xml.gz";
        /**
         * 义原关系的路径
         */
        public static String SememePath = "sememe.dat";
        /**
         * 义原数据路径
         */
        public static String SememeXmlPath = "sememe.xml.gz";
        /**
         * 词频统计输出路径
         */
        public static String StatisticsResultPath = "data/WordFrequencyStatistics-Result.txt";

    }

    private Similarity() {
    }


    /**
     * 词语相似度
     * 计算词林编码相似度
     *
     * @param word1
     * @param word2
     * @return
     */
    public static double cilinSimilarity(String word1, String word2) {
        return CilinSimilarity.getInstance().getSimilarity(word1, word2);
    }

    /**
     * 词语相似度
     * 计算拼音相似度
     *
     * @param word1
     * @param word2
     * @return
     */
    public static double pinyinSimilarity(String word1, String word2) {
        return PhraseSimilarity.getInstance().getSimilarity(word1, word2);
    }

    /**
     * 词语相似度
     * 计算字面相似度
     *
     * @param word1
     * @param word2
     * @return
     */
    public static double charBasedSimilarity(String word1, String word2) {
        return CharBasedSimilarity.getInstance().getSimilarity(word1, word2);
    }

    /**
     * 词语相似度
     * 计算语义概念相似度
     *
     * @param word1
     * @param word2
     * @return
     */
    public static double conceptSimilarity(String word1, String word2) {
        return ConceptSimilarity.getInstance().getSimilarity(word1, word2);
    }

    /**
     * 短语相似度
     *
     * @param pharse1
     * @param pharse2
     * @return
     */
    public static double phraseSimilarity(String pharse1, String pharse2) {
        return PhraseSimilarity.getInstance().getSimilarity(pharse1, pharse2);
    }

    /**
     * 句子相似度
     * 词形和词序结合法
     *
     * @param sentence1
     * @param sentence2
     * @return
     */
    public static double morphoSimilarity(String sentence1, String sentence2) {
        return MorphoSimilarity.getInstance().getSimilarity(sentence1, sentence2);
    }

    /**
     * 句子相似度
     * 夏天编辑距离法
     *
     * @param sentence1
     * @param sentence2
     * @return
     */
    public static double editDistanceSimilarity(String sentence1, String sentence2) {
        EditDistance ed = new NewEditDistanceSimilarity();
        return ed.getSimilarity(sentence1, sentence2);
    }

    /**
     * 句子相似度
     * Gregor编辑距离算法
     *
     * @param sentence1
     * @param sentence2
     * @return
     */
    public static double gregorEditDistanceSimilarity(String sentence1, String sentence2) {
        EditDistance ed = new GregorEditDistanceSimilarity();
        return  ed.getSimilarity(sentence1, sentence2);
    }

    /**
     * 句子相似度
     * 标准编辑距离算法
     *
     * @param sentence1
     * @param sentence2
     * @return
     */
    public static double standardEditDistanceSimilarity(String sentence1, String sentence2) {
        EditDistance ed = new StandardEditDistanceSimilarity();
        return  ed.getSimilarity(sentence1, sentence2);
    }

}

