package tool.extractors.ExtractorHTML;

import java.io.IOException;
import java.util.Iterator;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.TextExtractingVisitor;

/*
 * 用于抓取网页中的所有超链接
 */
public class LinkFull {
	public static void main(String[] args) throws ParserException, IOException {
		String file = "html/td.htm";
		String s = LinkFull.getText(file);
		System.out.println(s);
	}

	public static String getText(String f) throws IOException, ParserException {
		StringBuffer sb = new StringBuffer();
		Parser parser = new Parser(f);

		NodeFilter filter = new NodeClassFilter(LinkTag.class);

		NodeList links = new NodeList();

		for (NodeIterator e = parser.elements(); e.hasMoreNodes();) {
			e.nextNode().collectInto(links, filter);
		}

		for (int i = 0; i < links.size(); i++) {
			LinkTag linkTag = (LinkTag) links.elementAt(i);
			sb.append("\"" + linkTag.getLinkText() + "\"=>");

			sb.append(linkTag.getLink());

			sb.append("\n");

		}

		return sb.toString();
	}
}
