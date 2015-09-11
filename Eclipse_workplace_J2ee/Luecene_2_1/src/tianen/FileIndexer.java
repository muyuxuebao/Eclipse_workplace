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

		// 使用PerFieldAnalyzerWrapper
		PerFieldAnalyzerWrapper wr = new PerFieldAnalyzerWrapper(
				new StandardAnalyzer());
		wr.addAnalyzer("title", new MMAnalyzer());
		wr.addAnalyzer("author", new MMAnalyzer());
		wr.addAnalyzer("abs", new StandardAnalyzer());
		wr.addAnalyzer("time", new StandardAnalyzer());

		/************************************************************************/

		// 创建Document
		Document doc = new Document();
		File f = new File("txt/mix1.txt");

		// 获取text
		String text = FileText.getText(f);

		// 获取title
		int i = text.indexOf("标题");
		int j = text.indexOf("作者");
		String title = text.substring(i + 3, j);

		// 获取author
		text = text.substring(j);
		i = text.indexOf("作者");
		j = text.indexOf("Abstract");
		String author = text.substring(i + 3, j);

		// 获取abstract
		i = text.indexOf("Abstract");
		j = text.indexOf("时间");
		String abs = text.substring(i + 9, j);

		// 获取time
		i = text.indexOf("时间");
		String time = text.substring(i + 3);

		// 添加Field
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
		i = text.indexOf("标题");
		j = text.indexOf("作者");
		title = text.substring(i + 3, j);

		// author
		text = text.substring(j);
		i = text.indexOf("作者");
		j = text.indexOf("Abstract");
		author = text.substring(i + 3, j);

		// abstract
		text = text.substring(j);
		i = text.indexOf("Abstract");
		j = text.indexOf("时间");
		abs = text.substring(i + 9, j);

		// time
		text = text.substring(j);
		i = text.indexOf("时间");
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
