package tool.extractors.ExtractorHTML;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.beans.LinkBean;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.TextExtractingVisitor;

/*
 * 用于抓取网页中的普通超链接
 */
public class LinkSimple {
	public static void main(String[] args) throws ParserException, IOException {
		String file = "html/td.htm";
		String s = LinkSimple.getText(file);
		System.out.println(s);
	}

	public static String getText(String f) throws IOException, ParserException {
		StringBuffer sb = new StringBuffer();
		LinkBean lb=new LinkBean();
		
		lb.setURL(f);
		
		URL[] urls=lb.getLinks();
		for (int i = 0; i < urls.length; i++) {
			sb.append(urls[i]+"\n");
		}

		return sb.toString();
	}
}
