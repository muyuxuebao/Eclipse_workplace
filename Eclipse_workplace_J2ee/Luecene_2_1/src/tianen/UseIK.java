package tianen;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKTokenizer;
import org.mira.lucene.analysis.IK_CAnalyzer;

public class UseIK {
	public static void main(String[] args) throws IOException {
		String s="�Ұ���ΰ����ϰ�����,�Ұ���׳�����л�!";
		StringReader sr=new StringReader(s);
		IK_CAnalyzer i=new IK_CAnalyzer();
		
		TokenStream ts=i.tokenStream("*", sr);
		Token t=null;
		
		while((t=ts.next())!=null){
			System.out.print(t.termText()+"|");
		}
		
	}
}
