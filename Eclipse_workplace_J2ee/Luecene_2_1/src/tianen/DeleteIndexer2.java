package tianen;

import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;

public class DeleteIndexer2 {
	public static void main(String[] args) throws IOException {
		String indexPath = "cjkindex";
		// ����IndexReader
		IndexReader ir = IndexReader.open(indexPath);
		System.out.println("1: " + ir.numDocs());

		// ���
		ir.deleteDocument(0);
		System.out.println("2: " + ir.numDocs());

		// �ָ�
		ir.undeleteAll();
		System.out.println("3: " + ir.numDocs());

		// ���
		ir.deleteDocument(0);

		// �ر�ir
		ir.close();

		IndexWriter writer = new IndexWriter(indexPath, new StandardAnalyzer());
		writer.optimize();
		writer.close();
		ir = IndexReader.open(indexPath);
		System.out.println("4: " + ir.numDocs());

		// �ָ�
		ir.undeleteAll();
		System.out.println("5: " + ir.numDocs());
		ir.close();

	}
}
