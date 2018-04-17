package test.org.shuerlink.nlp;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.mining.word2vec.DocVectorModel;
import com.hankcs.hanlp.mining.word2vec.WordVectorModel;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import org.junit.Test;
import org.shuerlink.util.AssessScore;

import java.io.IOException;
import java.util.List;

public class AssessScoreTest {

    @Test
    public void testSegment() {
        List<Term> termList = StandardTokenizer.segment("李青(yang xiu zhu) ");
        System.out.println(termList);
    }

    @Test
    public void testNLPSegment() {
        System.out.println(NLPTokenizer.segment("杨秀珠(yang xiu zhu) ?"));
    }

    @Test
    public void testWord2Vec() throws IOException {
        WordVectorModel wordVectorModel = new WordVectorModel("E:\\github workplace\\SHUer.link.aggregateSearch\\src\\main\\resources\\data\\hanlp-wiki-vec-zh.txt");
        DocVectorModel docVectorModel = new DocVectorModel(wordVectorModel);
        String[] documents = new String[] {
                "山东苹果丰收",
                "上海大学学生毕业【课外】上海大学第二届“海星杯”英语跨文化能力大赛 社区学院 社区学院二楼活动室(暂定) 2018-05-11 16:00-2018-05-11 20:00 开始报名:2018-04-03 10:..",
                "奥运会女排夺冠",
                "世界锦标赛胜出",
                "中国足球失败"
        };

        System.out.println(docVectorModel.similarity(documents[0], documents[1]));
        System.out.println(docVectorModel.similarity(documents[0], documents[2]));
    }
}
