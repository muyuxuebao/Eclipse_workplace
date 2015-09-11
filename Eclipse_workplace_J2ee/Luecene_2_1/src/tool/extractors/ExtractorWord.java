package tool.extractors;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.textmining.text.extraction.WordExtractor;

public class ExtractorWord {
	public static String getText(String doc) throws Exception {
		FileInputStream in = new FileInputStream(new File(doc));

		WordExtractor extractor = new WordExtractor();

		String text = extractor.extractText(in);

		return text;
	}

	public static void toTextFile(String doc, String txt) throws Exception {
		FileInputStream in = new FileInputStream(new File(doc));

		WordExtractor extractor = new WordExtractor();

		String text = extractor.extractText(in);

		PrintWriter pw = new PrintWriter(new File(txt));
		pw.write(text);
		pw.flush();
		pw.close();

		System.out.println("�ı�д��ɹ�!!!");
	}

	public static void main(String[] args) {
		try {
			String text = ExtractorWord.getText("word/�����ʥ��ſ�.doc");
			System.out.println(text);

			ExtractorWord.toTextFile("word/�����ʥ��ſ�.doc", "text.txt");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
