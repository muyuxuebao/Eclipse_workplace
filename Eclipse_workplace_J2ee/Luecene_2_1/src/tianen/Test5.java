//Test4.java
package tianen;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.document.*;
import org.apache.lucene.store.RAMDirectory;

public class Test5 {
	public static void main(String[] args) throws java.io.IOException {
		// create index
		RAMDirectory rd = new RAMDirectory();
		IndexWriter writer = new IndexWriter(rd, new StandardAnalyzer());

		Document doc = null;
		Field field = null;
		String id = null;
		String text = null;

		// doc 0
		doc = new Document();
		id = "0";
		field = new Field("id", id, Field.Store.YES, Field.Index.UN_TOKENIZED);
		doc.add(field);
		text = "i love you, my mother land! ";
		field = new Field("text", text, Field.Store.YES, Field.Index.TOKENIZED);
		doc.add(field);
		writer.addDocument(doc);

		// doc 1
		doc = new Document();
		id = "1";
		field = new Field("id", id, Field.Store.YES, Field.Index.UN_TOKENIZED);
		doc.add(field);
		text = "i love you, xiaoyue,honey! ";
		field = new Field("text", text, Field.Store.YES, Field.Index.TOKENIZED);
		doc.add(field);
		writer.addDocument(doc);

		// doc 2
		doc = new Document();
		id = "2";
		field = new Field("id", id, Field.Store.YES, Field.Index.UN_TOKENIZED);
		doc.add(field);
		text = "People love Genius! ";
		field = new Field("text", text, Field.Store.YES, Field.Index.TOKENIZED);
		doc.add(field);
		writer.addDocument(doc);

		// close
		writer.close();

		/**********************************************************/

		// IndexSearcher
		IndexSearcher searcher = new IndexSearcher(rd);

		// Term & Query
		String searchField = "text";
		String searchPhrase = "love";
		Term t = new Term(searchField, searchPhrase);
		TermQuery q = new TermQuery(t);

		// Filter
		Term tb = new Term("id", "0");

		Term te = new Term("id", "2");
		RangeQuery rq = new RangeQuery(tb, te, false);
		QueryFilter filter = new QueryFilter(rq);

		// Hits
		Hits hs = searcher.search(q, filter);

		int num = hs.length();

		StringBuffer sb = new StringBuffer("");

		// view details
		for (int i = 0; i < num; i++) {
			// get document
			doc = hs.doc(i);

			// fields
			id = doc.getField("id").stringValue();

			text = doc.getField("text").stringValue();

			// document id
			int did = hs.id(i);

			// boost
			float boost = doc.getBoost();

			// score
			float score = hs.score(i);

			sb.append("document " + did + ":" + doc + "\n");
			sb.append("boost:" + boost + "\n");
			sb.append("score:" + score + "\n");
			sb.append("id:" + id + "\n");
			sb.append("text:" + text + "\n");

			sb.append("------------------- " + "\n\n");
		}

		searcher.close();

		System.out.print(sb);
	}
}