package tool.extractors.ExtractorHTML;

import java.io.UnsupportedEncodingException;

import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.TextExtractingVisitor;

/*
 * 用于解析未设定编码的中文网页
 */
public class ParserConvert {
	public static void main(String[] args) throws UnsupportedEncodingException, ParserException {
		String file="html/ta.htm";
		String s=ParserConvert.getText(file);
		System.out.println(s);
	}
	public static String getText(String f) throws ParserException, UnsupportedEncodingException{
		Parser parser=new Parser(f);
		
		TextExtractingVisitor visitor=new TextExtractingVisitor();
		
		parser.visitAllNodesWith(visitor);
		
		String s=visitor.getExtractedText();
		
		s=new String(s.getBytes("iso-8859-1"));
		
		return s;
	}
}
