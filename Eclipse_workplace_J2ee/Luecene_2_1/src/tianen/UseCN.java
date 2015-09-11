package tianen;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.cjk.CJKTokenizer;
import org.apache.lucene.analysis.cn.ChineseTokenizer;

public class UseCN {
	public static void main(String[] args) throws IOException {
		String s="�Ұ���ΰ����ϰ�����,�Ұ���׳�����л�!";
		StringReader sr=new StringReader(s);
		ChineseTokenizer cn=new ChineseTokenizer(sr);
		Token t=null;
		
		while((t=cn.next())!=null){
			System.out.print(t.termText()+"|");
		}
	}
}
