package tianen;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;

public class CNSearcher {
	public static void main(String[] args) throws IOException {
		String indexPath = "cnindex";

		String searchField = "content";
		String searchPhrase = "¥Û”Ì";

		StringBuffer sb = new StringBuffer();

		IndexSearcher searcher = new IndexSearcher(indexPath);

		Term t = new Term(searchField, searchPhrase);
		Query q = new TermQuery(t);

		Hits hs = searcher.search(q);

		int num = hs.length();

		for (int i = 0; i < num; i++) {
			Document doc = hs.doc(i);

			Field fname = doc.getField("name");

			sb.append("name: " + "\n");
			sb.append(fname.stringValue() + "\n");

			Field fcontent = doc.getField("content");
			sb.append("content: " + "\n");
			sb.append(fcontent.stringValue().substring(0, 50) + "\n");

			sb.append("-----------------------------------" + "\n");

		}
		searcher.close();
		System.out.println(sb.toString());

	}
}
