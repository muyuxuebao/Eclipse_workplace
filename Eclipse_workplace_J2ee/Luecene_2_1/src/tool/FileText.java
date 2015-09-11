package tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileText {
	public static String getText(File file) {
		StringBuffer sb = new StringBuffer();

		FileReader fileReader;
		try {
			fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String s = bufferedReader.readLine();

			while (s != null) {
				sb.append(s);
				s=bufferedReader.readLine();
			}
			bufferedReader.close();
		} catch (Exception e) {
			sb.append("");
		}
		

		return sb.toString();
	}
	
	public static String getText(String s) {
		String t="";
		try {
			File file=new File(s);
			t=getText(file);
		} catch (Exception e) {
			t="";
		}
		
		return t;
	}
	public static void main(String[] args) {
		String s=FileText.getText("test.htm");
		System.out.println(s);
	}
}
