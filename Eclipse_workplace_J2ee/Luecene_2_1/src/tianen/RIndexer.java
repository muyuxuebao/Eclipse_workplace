//RIndexer.java
package tianen;

import java.io.PrintStream;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.document.*;

import org.apache.lucene.store.RAMDirectory;

public class RIndexer {
	public static void main(String[] args) throws java.io.IOException {
		RAMDirectory rd = new RAMDirectory();
		IndexWriter writer = new IndexWriter(rd, new StandardAnalyzer());

		
		PrintStream ps=new PrintStream("log.txt");
		writer.setInfoStream(ps);
		
		// Document
		Document doc = new Document();

		// Field -title
		String title = "i love china";
		Field field = new Field("title", title, Field.Store.YES,
				Field.Index.TOKENIZED);
		doc.add(field);

		// Field -content
		String content = "i love you, my mother land! ";
		field = new Field("content", content, Field.Store.YES,
				Field.Index.TOKENIZED);
		doc.add(field);

		// add document
		writer.addDocument(doc);

		// close IndexWriter
		writer.close();

		// message
		System.out.println("Index Created!");

		/********************* search ******************************/

		String searchField = "content";
		String searchPhrase = "mother";
		StringBuffer sb = new StringBuffer("");

		// IndexSearcher
		IndexSearcher searcher = new IndexSearcher(rd);

		// Term & Query
		Term t = new Term(searchField, searchPhrase);
		Query q = new TermQuery(t);

		// Hits
		Hits hs = searcher.search(q);

		int num = hs.length();

		// view details
		for (int i = 0; i < num; i++) {

			// get document
			doc = hs.doc(i);

			// field title
			title = doc.getField("title").stringValue();
			sb.append("title:" + "\n");
			sb.append(title + "\n");

			// field content
			content = doc.getField("content").stringValue();
			sb.append("content:" + "\n");
			sb.append(content + "\n");

			sb.append("------------------- " + "\n");
		}

		searcher.close();

		System.out.print(sb);

	}
}
