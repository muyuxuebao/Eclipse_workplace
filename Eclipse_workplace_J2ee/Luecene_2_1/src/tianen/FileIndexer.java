package tianen;

import java.io.File;
import java.io.IOException;

import jeasy.analysis.MMAnalyzer;

import org.apache.lucene.analysis.PerFieldAnalyzerWrapper;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;

import tool.FileText;

public class FileIndexer {
	public static void main(String[] args) throws IOException {
		String indexPath = "file";
		IndexWriter writer = new IndexWriter(indexPath, new StandardAnalyzer());
		writer.setMergeFactor(10);
		

		/************************************************************************/

		/*
		 * title author abs time
		 */

		// ʹ��PerFieldAnalyzerWrapper
		PerFieldAnalyzerWrapper wr = new PerFieldAnalyzerWrapper(
				new StandardAnalyzer());
		wr.addAnalyzer("title", new MMAnalyzer());
		wr.addAnalyzer("author", new MMAnalyzer());
		wr.addAnalyzer("abs", new StandardAnalyzer());
		wr.addAnalyzer("time", new StandardAnalyzer());

		/************************************************************************/

		// ����Document
		Document doc = new Document();
		File f = new File("txt/mix1.txt");

		// ��ȡtext
		String text = FileText.getText(f);

		// ��ȡtitle
		int i = text.indexOf("����");
		int j = text.indexOf("����");
		String title = text.substring(i + 3, j);

		// ��ȡauthor
		text = text.substring(j);
		i = text.indexOf("����");
		j = text.indexOf("Abstract");
		String author = text.substring(i + 3, j);

		// ��ȡabstract
		i = text.indexOf("Abstract");
		j = text.indexOf("ʱ��");
		String abs = text.substring(i + 9, j);

		// ��ȡtime
		i = text.indexOf("ʱ��");
		String time = text.substring(i + 3);

		// ���Field
		Field field = new Field("title", title, Field.Store.YES,
				Field.Index.TOKENIZED);
		doc.add(field);

		field = new Field("author", author, Field.Store.YES,
				Field.Index.UN_TOKENIZED);
		doc.add(field);

		field = new Field("abs", abs, Field.Store.YES, Field.Index.TOKENIZED);
		doc.add(field);

		field = new Field("time", time, Field.Store.YES, Field.Index.NO);
		doc.add(field);

		// add document
		writer.addDocument(doc, new MMAnalyzer());

		/***************************************************************/

		// Document
		doc = new Document();
		f = new File("txt/mix2.txt");

		// text
		text = FileText.getText(f);

		// title
		i = text.indexOf("����");
		j = text.indexOf("����");
		title = text.substring(i + 3, j);

		// author
		text = text.substring(j);
		i = text.indexOf("����");
		j = text.indexOf("Abstract");
		author = text.substring(i + 3, j);

		// abstract
		text = text.substring(j);
		i = text.indexOf("Abstract");
		j = text.indexOf("ʱ��");
		abs = text.substring(i + 9, j);

		// time
		text = text.substring(j);
		i = text.indexOf("ʱ��");
		time = text.substring(i + 3);

		// Fields
		field = new Field("title", title, Field.Store.YES,
				Field.Index.TOKENIZED);
		doc.add(field);

		field = new Field("author", author, Field.Store.YES,
				Field.Index.UN_TOKENIZED);
		doc.add(field);

		field = new Field("abs", abs, Field.Store.YES, Field.Index.TOKENIZED);
		doc.add(field);

		field = new Field("time", time, Field.Store.YES, Field.Index.NO);
		doc.add(field);

		// add document
		writer.addDocument(doc, new MMAnalyzer());

		/***************************************************************/

		// close IndexWriter
		writer.close();

		// message
		System.out.println("File Index Created!");

	}
}
