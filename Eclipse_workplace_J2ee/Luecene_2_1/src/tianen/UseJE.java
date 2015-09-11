package tianen;

import java.io.IOException;
import java.io.StringReader;

import jeasy.analysis.MMAnalyzer;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.cjk.CJKTokenizer;
import org.apache.lucene.analysis.cn.ChineseTokenizer;
import org.apache.lucene.analysis.ngram.NGramTokenizer;

public class UseJE {
	public static void main(String[] args) throws IOException {
		String s="�Ұ���ΰ����ϰ�����,�Ұ���׳�����л�!";
		MMAnalyzer mm=new MMAnalyzer();
		System.out.println(mm.segment(s, "|"));
		
		System.out.println(mm.size());
	}
}
