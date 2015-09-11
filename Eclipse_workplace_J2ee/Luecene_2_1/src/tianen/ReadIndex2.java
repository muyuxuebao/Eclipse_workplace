package tianen;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;

public class ReadIndex2 {
	public static void main(String[] args) throws IOException {
		String indexPath = "cjkindex";

		// 建立IndexReader
		IndexReader ir = IndexReader.open(indexPath);

		// numDocs 索引中的文档数目
		System.out.println(ir.numDocs());
		
		Document doc=ir.document(3);
		
		System.out.println(doc);
		
		
		for (int i = 0; i < ir.numDocs(); i++) {
			doc=ir.document(i);
			Field fname=doc.getField("name");
			System.out.println(fname.stringValue());
		}
		
		ir.close();
	}
}
