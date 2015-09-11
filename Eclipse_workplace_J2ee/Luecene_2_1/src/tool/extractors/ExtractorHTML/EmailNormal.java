package tool.extractors.ExtractorHTML;

import java.io.UnsupportedEncodingException;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

/*
 * 用于抓取编码正确的中文网页的E-mail链接
 */
public class EmailNormal {
	public static void main(String[] args) throws UnsupportedEncodingException,
			ParserException {
		String file = "html/tb.htm";
		String s = EmailNormal.getText(file);
		System.out.println(s);
	}

	public static String getText(String f) throws ParserException {
		Parser parser = new Parser(f);
		NodeFilter filter = new NodeFilter() {

			@Override
			public boolean accept(Node node) {
				return (LinkTag.class.isAssignableFrom(node.getClass()) && ((LinkTag) node)
						.isMailLink());
			}
		};

		NodeList links = new NodeList();
		for (NodeIterator e = parser.elements(); e.hasMoreNodes();) {
			e.nextNode().collectInto(links, filter);
		}

		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < links.size(); i++) {
			LinkTag linkTag = (LinkTag) links.elementAt(i);
			sb.append("\"" + linkTag.getLinkText() + "\" => ");
			sb.append(linkTag.getLink());
			sb.append("\n");
		}
		return sb.toString();
	}
}
