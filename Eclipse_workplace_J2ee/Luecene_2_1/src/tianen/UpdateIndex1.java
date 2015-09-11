package tianen;

import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;

public class UpdateIndex1 {
	public static void main(String[] args) throws IOException {
		// 建立索引
		String indexPath = "di";
		IndexWriter writer = new IndexWriter(indexPath, new StandardAnalyzer());

		Document doc = new Document();
		doc.add(new Field("id", "1", Field.Store.YES, Field.Index.UN_TOKENIZED));
		doc.add(new Field("body", "aa", Field.Store.YES, Field.Index.TOKENIZED));
		
		writer.addDocument(doc);
		
		
		doc=new Document();
		doc.add(new Field("id", "2", Field.Store.YES, Field.Index.UN_TOKENIZED));
		doc.add(new Field("body", "bb", Field.Store.YES, Field.Index.TOKENIZED));
		
		writer.addDocument(doc);
		
		
		writer.close();
		
		/**********************************************************************/
		
		
		//删除第一个文档
		IndexReader ir=IndexReader.open(indexPath);
		System.out.print("Before updatinging: "+" total "+ir.numDocs());
		
		doc=ir.document(0);
		System.out.print(", document(0).body: "+doc.getField("body").stringValue());
		doc=ir.document(1);
		System.out.println(", document(1).body: "+doc.getField("body").stringValue());
		
		//delete
		ir.deleteDocument(0);
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
		
		//查看更新结果
		ir=IndexReader.open(indexPath);
		System.out.print("After updatinging: "+" total "+ir.numDocs());
		
		doc=ir.document(0);
		System.out.print(", document(0).body: "+doc.getField("body").stringValue());
		doc=ir.document(1);
		System.out.println(", document(1).body: "+doc.getField("body").stringValue());
		
		

	}
}
