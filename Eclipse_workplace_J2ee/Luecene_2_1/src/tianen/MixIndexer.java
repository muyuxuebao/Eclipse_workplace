//MixIndexer.java
package tianen;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.document.*;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.store.FSDirectory;

public class MixIndexer {
	public static void main(String[] args) throws java.io.IOException {
		/***************** ram ****************/
		RAMDirectory rd = new RAMDirectory();
		IndexWriter iw = new IndexWriter(rd, new StandardAnalyzer());

		// Document
		Document doc = new Document();

		// Field -title
		String title = "i love china";
		Field field = new Field("title", title, Field.Store.YES,
				Field.Index.TOKENIZED);
		doc.add(field);

		// Field -content
		String content = "i love you, my mother land! ";
		field = new Field("content", content, Field.Store.YES,
				Field.Index.TOKENIZED);
		doc.add(field);

		// add document
		iw.addDocument(doc);

		// close IndexWriter
		iw.close();

		/***************** fs ****************/

		FSDirectory fd = FSDirectory.getDirectory("mix");

		// 将内存索引并入文件系统索引
		IndexWriter writer = new IndexWriter(fd, new StandardAnalyzer());
		writer.addIndexes(new Directory[] { rd });
		writer.close();

		// message
		System.out.println("Index Created!");
	}
}
