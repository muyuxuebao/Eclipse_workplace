package tianen;

import java.io.IOException;

import org.apache.lucene.index.IndexReader;

public class DeleteIndexer1 {
	public static void main(String[] args) throws IOException {
		String indexPath = "cjkindex";

		// ½¨Á¢IndexReader
		IndexReader ir = IndexReader.open(indexPath);
		
		ir.deleteDocument(0);
		
		ir.close();
	}
}
