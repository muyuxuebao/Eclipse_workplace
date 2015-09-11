//FileSearcher.java
package tianen;

import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.document.*;

public class FileSearcher {
	public static void main(String[] args) throws java.io.IOException {
		String indexPath = "file";
		String searchField = "title";
		String searchPhrase = "ÌìµÀ";
		StringBuffer sb = new StringBuffer("");

		// IndexSearcher
		IndexSearcher searcher = new IndexSearcher(indexPath);

		/********************** search title ************************/
		sb.append("search field:  " + searchField + "\t" + "search phrase:  "
				+ searchPhrase + "\n");

		// Term & Query
		Term t = new Term(searchField, searchPhrase);
		Query q = new TermQuery(t);

		// Hits
		Hits hs = searcher.search(q);

		int num = hs.length();

		// view details
		for (int i = 0; i < num; i++) {

			// get document
			Document doc = hs.doc(i);

			// field title
			Field title = doc.getField("title");
			sb.append("title:" + "\n");
			sb.append(title.stringValue() + "\n");

			// field abs
			Field abs = doc.getField("abs");
			sb.append("abstract:" + "\n");
			sb.append(abs.stringValue() + "\n");

			// field time
			Field time = doc.getField("time");
			sb.append("time:" + "\n");
			sb.append(time.stringValue() + "\n");

			sb.append("------------------- " + "\n");
		}

		/********************** search abs ************************/
		searchField = "abs";
		searchPhrase = "girlfriend";
		sb.append("search field:  " + searchField + "\t" + "search phrase:  "
				+ searchPhrase + "\n");

		// Term & Query
		t = new Term(searchField, searchPhrase);
		q = new TermQuery(t);

		// Hits
		hs = searcher.search(q);

		num = hs.length();

		// view details
		for (int i = 0; i < num; i++) {

			// get document
			Document doc = hs.doc(i);

			// field title
			Field title = doc.getField("title");
			sb.append("title:" + "\n");
			sb.append(title.stringValue() + "\n");

			// field abs
			Field abs = doc.getField("abs");
			sb.append("abstract:" + "\n");
			sb.append(abs.stringValue() + "\n");

			// field time
			Field time = doc.getField("time");
			sb.append("time:" + "\n");
			sb.append(time.stringValue() + "\n");

			sb.append("------------------- " + "\n");
		}

		searcher.close();

		System.out.print(sb);
	}
}