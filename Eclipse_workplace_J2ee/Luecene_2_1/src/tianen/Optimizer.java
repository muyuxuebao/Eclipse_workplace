package tianen;

import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.FSDirectory;

public class Optimizer {
	public static void main(String[] args) throws IOException {
		FSDirectory fd=FSDirectory.getDirectory("index");
		IndexWriter writer=new IndexWriter(fd, new StandardAnalyzer());
		
		writer.optimize();
		
		writer.close();
		
	}
}
