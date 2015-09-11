package tool.extractors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

import jxl.demo.XML;

public class ExtractorXML {
	public static String getText(String doc) {
		StringBuffer sb = new StringBuffer();
		try {
			FileReader fr = new FileReader(new File(doc));
			BufferedReader br = new BufferedReader(fr);

			String s = br.readLine();

			while (s != null) {
				s = s.replaceAll("<[a-zA-z0-9]*[^<>]+>", "");

				s = s.trim();

				sb.append(s);

				s = br.readLine();
			}
			br.close();

		} catch (Exception e) {
			// TODO: handle exception
			sb.append("");
		}
		return sb.toString();
	}

	public static void toTextFile(String doc, String txt)
			throws FileNotFoundException {
		String text = getText(doc);

		PrintWriter pw = new PrintWriter(new File(txt));

		pw.write(text);

		pw.flush();

		pw.close();
	}
	public static void main(String[] args) throws FileNotFoundException {
		String text=ExtractorXML.getText("xml/∆ÎÃÏ¥Û •ÀÔè≈ø’.xml");
		
		System.out.println(text);
		
		ExtractorXML.toTextFile("xml/∆ÎÃÏ¥Û •ÀÔè≈ø’.xml", "xml.txt");
	}
}
