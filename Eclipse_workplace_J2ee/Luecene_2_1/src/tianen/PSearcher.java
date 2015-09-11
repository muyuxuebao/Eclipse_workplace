package tianen;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ParallelMultiSearcher;
import org.apache.lucene.search.TermQuery;

public class PSearcher {
	public static void main(String[] args) throws IOException {
		Document doc = null;
		Field field = null;
		String id = null;
		String text = null;

		IndexSearcher searcher1 = new IndexSearcher("index1");
		IndexSearcher searcher2 = new IndexSearcher("index2");

		IndexSearcher[] searchers = { searcher1, searcher2 };

		ParallelMultiSearcher searcher = new ParallelMultiSearcher(searchers);

		Term t = new Term("text", "i");

		TermQuery q = new TermQuery(t);

		Hits hs = searcher.search(q);

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < hs.length(); i++) {
			// 获取document
			doc = hs.doc(i);
			// 获取field id
			id = doc.getField("id").stringValue();
			sb.append("id: " + id);
			// 获取field txt
			text = doc.getField("text").stringValue();

			sb.append(", text: " + text + "\n");
		}

		searcher.close();
		System.out.println(sb.toString());
	}
}
