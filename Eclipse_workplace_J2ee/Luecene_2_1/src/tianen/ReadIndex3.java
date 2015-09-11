package tianen;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermDocs;
import org.apache.lucene.index.TermEnum;

public class ReadIndex3 {
	public static void main(String[] args) throws IOException {
		String indexPath = "cnindex";

		// 建立IndexReader
		IndexReader ir = IndexReader.open(indexPath);

		// terms()获取索引中的所有词条
		TermEnum te = ir.terms();
		int i = 0;
		while (te.next()) {
			i++;
			Term t = te.term();

			// System.out.println(t.text());
		}
		System.out.println("词条总数: " + i);
		te.close();

		System.out
				.println("***************************************************************************************************************");
		i = 0;
		// 符合"content:大"的词条
		Term t = new Term("content", "大");
		te = null;
		te = ir.terms(t);
		while (te.next()) {
			i++;
			Term ti = te.term();

			System.out.println(ti);
		}
		// 符合"content:大"的词条的数目
		System.out.println(ir.docFreq(t));

		// 获取所有符合"content:大" Document对象
		TermDocs td = ir.termDocs(t);
		while (td.next()) {
			int id = td.doc();
			Document doc = ir.document(id);
			Field field = doc.getField("name");
			System.out.println(field.stringValue());
		}

		ir.close();
	}
}
