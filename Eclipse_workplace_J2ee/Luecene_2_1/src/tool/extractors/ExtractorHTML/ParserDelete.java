package tool.extractors.ExtractorHTML;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.TextExtractingVisitor;
import org.pdfbox.pdmodel.graphics.predictor.Paeth;

/*
 * 用于解析设定了错误编码的中文网页
 */
public class ParserDelete {
	public static void main(String[] args) throws ParserException, IOException {
		String file="html/tc.htm";
		String s=ParserDelete.getText(file);
		System.out.println(s);
	}
	public static String getText(String f) throws IOException, ParserException {
		FileReader fr=new FileReader(new File(f));
		
		BufferedReader br=new BufferedReader(fr);
		
		String s=br.readLine();
		
		StringBuffer sb=new StringBuffer();
		
		while(s!=null){
			sb.append(s);
			s=br.readLine();
		}
		
		br.close();
		
		//除去网页中原来设定的编码
		
		s=sb.toString().toLowerCase();
		
		s = s.replaceAll("<meta http-equive","<metaa");
		
		//重新解析原文件
		Parser parser=new Parser(f);
		
		parser.setEncoding("gbk");
		
		parser.setInputHTML(s);
		
		TextExtractingVisitor visitor=new TextExtractingVisitor();
		parser.visitAllNodesWith(visitor);
		
		s=visitor.getExtractedText();
		
		
		
		
		return s;
	}
}
