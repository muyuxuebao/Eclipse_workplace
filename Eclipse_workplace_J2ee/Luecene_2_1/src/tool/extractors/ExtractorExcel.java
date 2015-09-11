package tool.extractors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class ExtractorExcel {
	public static String getText(String file) {
		StringBuffer sb = new StringBuffer();

		try {
			Workbook book = Workbook.getWorkbook(new File(file));

			int numSheet = book.getNumberOfSheets();

			for (int i = 0; i < numSheet; i++) {
				// 获得当前工作簿对象
				Sheet sheet = book.getSheet(i);

				// 行数
				int numRow = sheet.getRows();

				// 列数
				int numCol = sheet.getColumns();

				for (int j = 0; j < numRow; j++) {
					for (int k = 0; k < numCol; k++) {
						Cell c = sheet.getCell(k, j);
						sb.append(c.getContents());
					}
				}
			}

		} catch (Exception e) {
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
		String s = ExtractorExcel.getText("excel/齐天大圣孙徟空.xls");

		System.out.println(s);

		ExtractorExcel.toTextFile("excel/齐天大圣孙徟空.xls", "excel.txt");
	}
}
