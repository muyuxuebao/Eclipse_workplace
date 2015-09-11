package tool.extractors.ExtractorHTML;

import java.io.UnsupportedEncodingException;

import org.htmlparser.Parser;
import org.htmlparser.beans.StringBean;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.TextExtractingVisitor;

/*
 * 用于解析设定了正确编码的中文网页
 */
public class ParserNormal {
	public static void main(String[] args) throws UnsupportedEncodingException, ParserException {
		String file="html/tb.htm";
		String s=ParserNormal.getText(file);
		System.out.println(s);
	}
	public static String getText(String f) throws ParserException{
		
Parser parser=new Parser(f);
		
		TextExtractingVisitor visitor=new TextExtractingVisitor();
		
		parser.visitAllNodesWith(visitor);
		
		String s=visitor.getExtractedText();
		
		return s;
	}
}
