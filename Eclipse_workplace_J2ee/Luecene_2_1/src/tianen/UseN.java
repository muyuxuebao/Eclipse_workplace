package tianen;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.cjk.CJKTokenizer;
import org.apache.lucene.analysis.cn.ChineseTokenizer;
import org.apache.lucene.analysis.ngram.NGramTokenizer;

public class UseN {
	public static void main(String[] args) throws IOException {
		String s="我爱我伟大的老爸老妈,我爱我壮丽的中华!";
		StringReader sr=new StringReader(s);
		NGramTokenizer cn=new NGramTokenizer(sr);
		Token t=null;
		
		while((t=cn.next())!=null){
			System.out.print(t.termText()+"|");
		}
	}
}
