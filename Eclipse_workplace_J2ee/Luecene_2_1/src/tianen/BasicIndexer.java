package tianen;

import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;





public class BasicIndexer {
	public static void main(String[] args) throws IOException {
		String indexPath = "index";
		IndexWriter writer = new IndexWriter(indexPath, new StandardAnalyzer());
		
		String d=writer.getDirectory().toString();
		System.out.println(d);
		
		double t=writer.getDefaultWriteLockTimeout();
		System.out.println(t);
		
		int n=writer.numRamDocs();
		System.out.println(n);
		
		int  c=writer.docCount();
		System.out.println(c);
		
		writer.close();
	}
}
