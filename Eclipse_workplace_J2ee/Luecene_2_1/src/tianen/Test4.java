package tianen;

import java.io.IOException;
import java.util.Iterator;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Hit;
import org.apache.lucene.search.HitIterator;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.RAMDirectory;

public class Test4 {
	public static void main(String[] args) throws IOException {
		RAMDirectory rd = new RAMDirectory();
		IndexWriter writer = new IndexWriter(rd, new StandardAnalyzer());

		Document doc = null;
		Field field = null;
		String id = null;
		String text = null;
		String age = null;

		doc = new Document();
		id = "1";
		field = new Field("id", id, Field.Store.YES, Field.Index.UN_TOKENIZED);
		doc.add(field);
		text = "i love you, my mother land!";
		field = new Field("text", text, Field.Store.YES, Field.Index.TOKENIZED);
		doc.add(field);
		age = "6";
		field = new Field("age", age, Field.Store.YES, Field.Index.UN_TOKENIZED);
		doc.add(field);
		writer.addDocument(doc);

		doc = new Document();
		id = "2";
		field = new Field("id", id, Field.Store.YES, Field.Index.UN_TOKENIZED);
		doc.add(field);
		text = "i love you, xiaoyun,honey!";
		field = new Field("text", text, Field.Store.YES, Field.Index.TOKENIZED);
		doc.add(field);
		age = "4";
		field = new Field("age", age, Field.Store.YES, Field.Index.UN_TOKENIZED);
		doc.add(field);
		writer.addDocument(doc);

		doc = new Document();
		id = "2";
		field = new Field("id", id, Field.Store.YES, Field.Index.UN_TOKENIZED);
		doc.add(field);
		text = "People all love Genius!";
		field = new Field("text", text, Field.Store.YES, Field.Index.TOKENIZED);
		doc.add(field);
		age = "5";
		field = new Field("age", age, Field.Store.YES, Field.Index.UN_TOKENIZED);
		doc.add(field);
		writer.addDocument(doc);

		doc = new Document();
		id = "3";
		field = new Field("id", id, Field.Store.YES, Field.Index.UN_TOKENIZED);
		doc.add(field);
		text = "People love me!";
		field = new Field("text", text, Field.Store.YES, Field.Index.TOKENIZED);
		doc.add(field);
		age = "2";
		field = new Field("age", age, Field.Store.YES, Field.Index.UN_TOKENIZED);
		doc.add(field);
		writer.addDocument(doc);

		writer.close();

		/******************************************************************/

		// ´´½¨IndexSearcher
		IndexSearcher searcher = new IndexSearcher(rd);
		String searchField = "text";
		String searchPhrase = "love";

		Term t = new Term(searchField, searchPhrase);
		TermQuery q = new TermQuery(t);

		// Hits
		SortField sf1 = new SortField("id", false);
		SortField sf2 = new SortField("age", true);
		SortField fields[] = { sf1, sf2 };
		Sort sort = new Sort(fields);
		Hits hs = searcher.search(q, sort);

		int num = hs.length();

		StringBuffer sb = new StringBuffer("");

		// view details
		for (int i = 0; i < num; i++) {
			// get document
			doc = hs.doc(i);

			// fields
			id = doc.getField("id").stringValue();
			text = doc.getField("text").stringValue();
			age = doc.getField("age").stringValue();

			// document id
			int did = hs.id(i);

			// boost
			float boost = doc.getBoost();

			// score
			float score = hs.score(i);

			String explaination = searcher.explain(q, i).toString();

			sb.append("document " + did + ":" + doc + "\n");
			sb.append("boost:" + boost + "\n");
			sb.append("score:" + score + "\n");
			sb.append("id:" + id + "\n");
			sb.append("text:" + text + "\n");
			sb.append("age:" + age + "\n");

			sb.append("------------------- " + "\n\n");
		}

		searcher.close();

		System.out.print(sb);

	}
}
