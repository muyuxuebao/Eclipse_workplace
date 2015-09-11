package tianen;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;

import tool.FileList;
import tool.FileText;

public class CJKIndexer {
	public static void main(String[] args) throws IOException {
		String indexPath="cjkindex";
		
		IndexWriter writer=new IndexWriter(indexPath, new CJKAnalyzer());
		
		String files[]=FileList.getFiles("doc");
		
		int num=files.length;
		
		for (int i = 0; i < num; i++) {
			Document doc = new Document();
			File f = new File(files[i]);
			String name = f.getName();

			Field field = new Field("name", name, Field.Store.YES,
					Field.Index.TOKENIZED);
			doc.add(field);
			String content = FileText.getText(f);
			field = new Field("content", content, Field.Store.YES,
					Field.Index.TOKENIZED);
			doc.add(field);
			String path = f.getPath();
			field = new Field("path", path, Field.Store.YES, Field.Index.NO);
			doc.add(field);
			
			System.out.println("File: "+name+" Indexed!");
			writer.addDocument(doc);
		}
		
		writer.close();
		
		System.out.println("CJK Indexer Created!");
	}
}
