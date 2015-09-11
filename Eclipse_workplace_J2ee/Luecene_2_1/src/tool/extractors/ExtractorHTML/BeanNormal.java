package tool.extractors.ExtractorHTML;

import java.io.UnsupportedEncodingException;

import org.htmlparser.beans.StringBean;

/*
 * ���ڽ����趨����ȷ�����������ҳ
 */
public class BeanNormal {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String file="html/tb.htm";
		String s=BeanNormal.getText(file);
		System.out.println(s);
	}
	public static String getText(String f){
		StringBean sb = new StringBean();

		sb.setLinks(false);

		sb.setReplaceNonBreakingSpaces(true);

		sb.setCollapse(true);

		sb.setURL(f);

		String s = sb.getStrings();

		return s;
	}
}
