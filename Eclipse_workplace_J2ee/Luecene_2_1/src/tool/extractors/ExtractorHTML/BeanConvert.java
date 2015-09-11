package tool.extractors.ExtractorHTML;

import java.io.UnsupportedEncodingException;

import org.htmlparser.beans.StringBean;

/*
 * 用于解析未设定编码的中文网页
 */

public class BeanConvert {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String file="html/ta.htm";
		String s=BeanConvert.getText(file);
		System.out.println(s);
	}

	public static String getText(String f) throws UnsupportedEncodingException {

		StringBean sb = new StringBean();

		sb.setLinks(false);

		sb.setReplaceNonBreakingSpaces(true);

		sb.setCollapse(true);

		sb.setURL(f);

		String s = sb.getStrings();

		s = new String(s.getBytes("iso-8859-1"));

		return s;
	}
}
