package tianen;

import java.io.IOException;

import org.apache.lucene.index.IndexReader;

public class UnDeleteIndexer1 {
	public static void main(String[] args) throws IOException {
		String indexPath = "cjkindex";

		// ����IndexReader
		IndexReader ir = IndexReader.open(indexPath);
		
		ir.undeleteAll();
		
		ir.close();
	}
}
