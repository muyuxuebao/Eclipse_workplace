package tianen;

import java.io.IOException;
import java.util.Date;

import org.apache.lucene.index.IndexReader;

public class ReadIndex1 {
	public static void main(String[] args) throws IOException {
		String indexPath="cjkindex";
		
		//建立IndexReader
		IndexReader ir=IndexReader.open(indexPath);
		
		//indexExists方法
		if(ir.indexExists(indexPath)){
			System.out.println("该索引存在");
		}else {
			System.out.println("该索引不存在");
		}
		
		//lastModified方法
		Date d=new Date(ir.lastModified(indexPath));
		System.out.println(d);
		
		//isCurrent方法
		System.out.println(ir.isCurrent());
		
		//isOptimized方法
		System.out.println(ir.isOptimized());
		
		//directory方法
		System.out.println(ir.directory());
		
		// numDocs 索引中的文档数目
		System.out.println(ir.numDocs());
		
		//关闭ir
		ir.close();
		
		
	}
}
