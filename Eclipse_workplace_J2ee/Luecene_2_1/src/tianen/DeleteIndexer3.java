package tianen;

import java.awt.List;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermDocs;

public class DeleteIndexer3 {
	public static void main(String[] args) throws IOException {
		String indexPath = "index";
		// 建立IndexReader
		IndexReader ir = IndexReader.open(indexPath);

		for (int i = 0; i < ir.numDocs(); i++) {
			Document doc = ir.document(i);

			System.out.println();
			Field fname = doc.getField("filename");
			System.out.println(fname.stringValue());
		}

		System.out.println("1: " + ir.numDocs());

		Term t = new Term("filename", "lucene");

		int i = ir.deleteDocuments(t);

		System.out.println("删除了 " + i + " 个Document对象");

		System.out.println("2: " + ir.numDocs());

		ir.undeleteAll();

		System.out.println("3: " + ir.numDocs());

		ir.close();
	}
}
