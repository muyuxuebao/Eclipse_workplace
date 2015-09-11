package tool.extractors.ExtractorHTML;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;

/*
 * 用于抓取编码错误的中文网页的E-mail链接
 */
public class EmailConvert {
	public static void main(String[] args) throws Exception {
		String s = getText("html/ta.htm");
		System.out.println(s);
	}

	public static String getText(String f) throws Exception {
		Parser parser = new Parser(f);
		NodeFilter filter = new NodeFilter() {
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
		return new String(sb.toString().getBytes("iso-8859-1"));
	}
}
