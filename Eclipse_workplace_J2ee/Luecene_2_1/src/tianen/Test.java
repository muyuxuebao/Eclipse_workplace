package tianen;

import java.io.IOException;
import java.util.Iterator;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermPositionVector;
import org.apache.lucene.index.TermPositions;
import org.apache.lucene.search.Hit;
import org.apache.lucene.search.HitIterator;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.TokenSources;
import org.apache.lucene.store.RAMDirectory;

public class Test {
	public static void main(String[] args) throws IOException {
		RAMDirectory rd = new RAMDirectory();
		IndexWriter writer = new IndexWriter(rd, new StandardAnalyzer());

		Document doc = null;
		Field field = null;
		String id = null;
		String text = null;
		String time = null;

		// doc0
		doc = new Document();
		id = "0";
		field = new Field("id", id, Field.Store.YES, Field.Index.UN_TOKENIZED);
		doc.add(field);

		text = "i love you, my mother land!i love you, my mother land!i love you, my mother land!";
		field = new Field("text", text, Field.Store.YES, Field.Index.TOKENIZED,
				Field.TermVector.WITH_POSITIONS_OFFSETS);// Field的新的构造方法,这样构造的Field对象,就可以将词条位置信息记录进去
		doc.add(field);

		time = "2007-05-28";
		field = new Field("time", time, Field.Store.YES,
				Field.Index.UN_TOKENIZED);
		doc.add(field);

		writer.addDocument(doc);

		// doc1
		doc = new Document();
		id = "1";
		field = new Field("id", id, Field.Store.YES, Field.Index.UN_TOKENIZED);
		doc.add(field);

		text = "i love you, xiaoyun,honey!i love you, xiaoyun,honey!i love you, xiaoyun,honey!";
		field = new Field("text", text, Field.Store.YES, Field.Index.TOKENIZED,
				Field.TermVector.WITH_POSITIONS_OFFSETS);// Field的新的构造方法,这样构造的Field对象,就可以将词条位置信息记录进去
		doc.add(field);

		time = "2007-05-29";
		field = new Field("time", time, Field.Store.YES,
				Field.Index.UN_TOKENIZED);
		doc.add(field);
		writer.addDocument(doc);

		// doc2
		doc = new Document();
		id = "2";
		field = new Field("id", id, Field.Store.YES, Field.Index.UN_TOKENIZED);
		doc.add(field);

		text = "People love Genius!People love Genius!People love Genius!";
		field = new Field("text", text, Field.Store.YES, Field.Index.TOKENIZED,
				Field.TermVector.WITH_POSITIONS_OFFSETS);// Field的新的构造方法,这样构造的Field对象,就可以将词条位置信息记录进去
		doc.add(field);

		time = "2007-06-01";
		field = new Field("time", time, Field.Store.YES,
				Field.Index.UN_TOKENIZED);
		doc.add(field);

		writer.addDocument(doc);

		writer.close();

		/******************************************************************/

		// 创建IndexSearcher
		IndexSearcher searcher = new IndexSearcher(rd);
		String searchField = "text";
		String searchPhrase = "love";

		Term t = new Term(searchField, searchPhrase);
		TermQuery q = new TermQuery(t);

		// Hits
		Hits hs = searcher.search(q, Sort.RELEVANCE);

		/*
		 * 文本的高亮显示
		 */

		// 第一步,构造Highlighter对象
		Highlighter highlighter = new Highlighter(new QueryScorer(q));
		// 第二步,设定文本的分块
		SimpleFragmenter sf = new SimpleFragmenter(10);
		highlighter.setTextFragmenter(sf);

		int num = hs.length();

		// view details
		for (int i = 0; i < num; i++) {
			// get document
			doc = hs.doc(i);

			// fields
			text = doc.getField("text").stringValue();
			

			// 第三步,建立TermPositionVector对象
			TermPositionVector tpv = (TermPositionVector) searcher
					.getIndexReader().getTermFreqVector(hs.id(i), "text");
			TokenStream tokenStream = TokenSources.getTokenStream(tpv);

			// 第四步,设置高亮显示块的分割符号,分块的最多数量
			int maxNumFragmentsRequired = 10;// 分块的最多数量
			String fragmentSeparator = "...";// 分割符号
			
			
			
			//第五步,获取高亮处理后的结果
			String result = highlighter.getBestFragments(tokenStream, text,
					maxNumFragmentsRequired, fragmentSeparator);

			System.out.println(result);

			// document id
			int did = hs.id(i);

			// boost
			float boost = doc.getBoost();

			// score
			float score = hs.score(i);

			String explaination = searcher.explain(q, i).toString();

		}

		searcher.close();

	}
}
