package tianen;

import java.io.IOException;

import org.apache.lucene.index.IndexReader;

public class TestIndex1 {
	public static void main(String[] args) throws IOException {
		String indexPath = "cjkindex";

		// ����IndexReader
		IndexReader ir = IndexReader.open(indexPath);
		
		System.out.println(ir.numDocs());
		
		ir.close();
	}
}
