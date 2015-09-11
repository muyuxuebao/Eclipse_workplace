package tianen;

import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;

public class UpdateIndex2 {
	public static void main(String[] args) throws IOException {
		// ½¨Á¢Ë÷Òý
		String indexPath = "ui";
		IndexWriter writer = new IndexWriter(indexPath, new StandardAnalyzer());
		Document doc = new Document();
		doc.add(new Field("id", "1", Field.Store.YES, Field.Index.UN_TOKENIZED));
		doc.add(new Field("body", "aa", Field.Store.YES, Field.Index.TOKENIZED));

		writer.addDocument(doc);

		doc = new Document();
		doc.add(new Field("id", "2", Field.Store.YES, Field.Index.UN_TOKENIZED));
		doc.add(new Field("body", "bb", Field.Store.YES, Field.Index.TOKENIZED));

		writer.addDocument(doc);

		writer.close();
		IndexReader ir=IndexReader.open(indexPath);
		System.out.println("Before updatinging: "+" total "+ir.numDocs());
		ir.close();

		/**********************************************************************/
		ir=IndexReader.open(indexPath);
		
		//delete
		ir.deleteDocuments(new Term("body", "aa"));
		
		System.out.println("After deleting: "+" total "+ir.numDocs());
		ir.close();
		/**********************************************************************/
		
		
		writer = new IndexWriter(indexPath, new StandardAnalyzer());

		doc = new Document();
		doc.add(new Field("id", "1", Field.Store.YES, Field.Index.UN_TOKENIZED));
		doc.add(new Field("body", "up", Field.Store.YES, Field.Index.TOKENIZED));
		writer.addDocument(doc);
		
		writer.optimize();
		writer.close();
		
	}
}
