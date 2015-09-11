package tianen;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MultiSearcher;
import org.apache.lucene.search.TermQuery;

public class Searcher {
	public static void main(String[] args) throws IOException {
		Document doc = null;
		Field field = null;
		String id = null;
		String text = null;

		IndexSearcher searcher1 = new IndexSearcher("index1");
		IndexSearcher searcher2 = new IndexSearcher("index2");

		IndexSearcher[] searchers = { searcher1, searcher2 };

		MultiSearcher searcher = new MultiSearcher(searchers);

		Term t = new Term("text", "i");

		TermQuery q = new TermQuery(t);

		Hits hs = searcher.search(q);

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < hs.length(); i++) {
			// ��ȡdocument
			doc = hs.doc(i);
			// ��ȡfield id
			id = doc.getField("id").stringValue();
			sb.append("id: " + id);
			// ��ȡfield txt
			text = doc.getField("text").stringValue();

			sb.append(", text: " + text + "\n");
		}

		searcher.close();
		System.out.println(sb.toString());
	}
}
