package tianen;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKTokenizer;
import org.mira.lucene.analysis.IK_CAnalyzer;
import org.mira.lucene.analysis.MIK_CAnalyzer;

public class UseMIK {
	public static void main(String[] args) throws IOException {
		String s="我爱我伟大的老爸老妈,我爱我壮丽的中华!";
		StringReader sr=new StringReader(s);
		MIK_CAnalyzer i=new MIK_CAnalyzer();
		
		TokenStream ts=i.tokenStream("*", sr);
		Token t=null;
		
		while((t=ts.next())!=null){
			System.out.print(t.termText()+"|");
		}
		
	}
}
