package tianen;

import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;

public class Indexer {
	public static void main(String[] args) throws IOException {
		String indexPath = null;
		Document doc = null;
		Field field = null;
		String id = null;
		String text = null;
		IndexWriter writer = null;

		/*****************************************************************************************************************/

		// 建立第一个索引
		indexPath = "index1";
		writer = new IndexWriter(indexPath, new StandardAnalyzer());

		// doc0
		doc = new Document();
		id = "genius";
		field = new Field("id", id, Field.Store.YES, Field.Index.UN_TOKENIZED);
		doc.add(field);

		text = "i love you, my mother land!";
		field = new Field("text", text, Field.Store.YES, Field.Index.TOKENIZED);
		doc.add(field);
		writer.addDocument(doc);

		// doc1
		doc = new Document();
		id = "test";
		field = new Field("id", id, Field.Store.YES, Field.Index.UN_TOKENIZED);
		doc.add(field);

		text = "nothing is impossible";
		field = new Field("text", text, Field.Store.YES, Field.Index.TOKENIZED);
		doc.add(field);
		writer.addDocument(doc);

		// doc2
		doc = new Document();
		id = "love";
		field = new Field("id", id, Field.Store.YES, Field.Index.UN_TOKENIZED);
		doc.add(field);

		text = "i am Genius!                                                                                                                                                                                ";
		field = new Field("text", text, Field.Store.YES, Field.Index.TOKENIZED);
		doc.add(field);
		writer.addDocument(doc);

		writer.close();

		/*****************************************************************************************************************/

		// 建立第二个索引
		indexPath = "index2";
		writer = new IndexWriter(indexPath, new StandardAnalyzer());

		// doc0
		doc = new Document();
		id = "genius";
		field = new Field("id", id, Field.Store.YES, Field.Index.UN_TOKENIZED);
		doc.add(field);

		text = "i love you, my mother land!";
		field = new Field("text", text, Field.Store.YES, Field.Index.TOKENIZED);
		doc.add(field);
		writer.addDocument(doc);

		// doc1
		doc = new Document();
		id = "test";
		field = new Field("id", id, Field.Store.YES, Field.Index.UN_TOKENIZED);
		doc.add(field);

		text = "nothing is impossible";
		field = new Field("text", text, Field.Store.YES, Field.Index.TOKENIZED);
		doc.add(field);
		writer.addDocument(doc);

		// doc2
		doc = new Document();
		id = "love";
		field = new Field("id", id, Field.Store.YES, Field.Index.UN_TOKENIZED);
		doc.add(field);

		text = "i am Genius!                                                                                                                                                                                ";
		field = new Field("text", text, Field.Store.YES, Field.Index.TOKENIZED);
		doc.add(field);
		writer.addDocument(doc);

		writer.close();
		
		System.out.println("Indexes Created!!!");

	}
}
