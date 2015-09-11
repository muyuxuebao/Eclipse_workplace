package tianen;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.cjk.CJKTokenizer;

public class UseCJK {
	public static void main(String[] args) throws IOException {
		String s="�Ұ���ΰ����ϰ�����,�Ұ���׳�����л�!";
		StringReader sr=new StringReader(s);
		CJKTokenizer cjk=new CJKTokenizer(sr);
		Token t=null;
		
		while((t=cjk.next())!=null){
			System.out.print(t.termText()+"|");
		}
	}
}
