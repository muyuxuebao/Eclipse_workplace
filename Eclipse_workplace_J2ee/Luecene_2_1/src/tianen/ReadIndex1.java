package tianen;

import java.io.IOException;
import java.util.Date;

import org.apache.lucene.index.IndexReader;

public class ReadIndex1 {
	public static void main(String[] args) throws IOException {
		String indexPath="cjkindex";
		
		//����IndexReader
		IndexReader ir=IndexReader.open(indexPath);
		
		//indexExists����
		if(ir.indexExists(indexPath)){
			System.out.println("����������");
		}else {
			System.out.println("������������");
		}
		
		//lastModified����
		Date d=new Date(ir.lastModified(indexPath));
		System.out.println(d);
		
		//isCurrent����
		System.out.println(ir.isCurrent());
		
		//isOptimized����
		System.out.println(ir.isOptimized());
		
		//directory����
		System.out.println(ir.directory());
		
		// numDocs �����е��ĵ���Ŀ
		System.out.println(ir.numDocs());
		
		//�ر�ir
		ir.close();
		
		
	}
}
