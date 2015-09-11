package tianen;

import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermEnum;

public class UpdateIndex3 {
	public static void main(String[] args) throws IOException {
		// ½¨Á¢Ë÷Òý
		String indexPath = "u3";
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

		IndexReader ir = IndexReader.open(indexPath);
		int num = ir.numDocs();
		for (int i = 0; i < num; i++) {
			System.out.print("doc " + i + " :");
			System.out.print(" id="
					+ ir.document(i).getField("id").stringValue());
			System.out.println(" body="
					+ ir.document(i).getField("body").stringValue());
		}
		System.out
				.println("---------------------------------------------------------");
		writer = new IndexWriter(indexPath, new StandardAnalyzer());
		doc = new Document();
		doc.add(new Field("id", "1", Field.Store.YES, Field.Index.UN_TOKENIZED));
		doc.add(new Field("body", "up", Field.Store.YES, Field.Index.TOKENIZED));
		writer.updateDocument(new Term("body", "aa"), doc);

		writer.addDocument(doc);

		writer.close();

		

	}
}
