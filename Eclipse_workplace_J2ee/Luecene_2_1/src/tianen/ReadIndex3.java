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

		// ����IndexReader
		IndexReader ir = IndexReader.open(indexPath);

		// terms()��ȡ�����е����д���
		TermEnum te = ir.terms();
		int i = 0;
		while (te.next()) {
			i++;
			Term t = te.term();

			// System.out.println(t.text());
		}
		System.out.println("��������: " + i);
		te.close();

		System.out
				.println("***************************************************************************************************************");
		i = 0;
		// ����"content:��"�Ĵ���
		Term t = new Term("content", "��");
		te = null;
		te = ir.terms(t);
		while (te.next()) {
			i++;
			Term ti = te.term();

			System.out.println(ti);
		}
		// ����"content:��"�Ĵ�������Ŀ
		System.out.println(ir.docFreq(t));

		// ��ȡ���з���"content:��" Document����
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
