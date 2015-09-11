package tool.extractors;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;

public class ExtractorPDF {
	public static String getText(String file) throws IOException{
		String pdfFile=file;
		PDDocument document=null;
		
		String s="";
		try {
			document=PDDocument.load(pdfFile);
			
			//����PDFTextStripper����ȡ�ı�
			PDFTextStripper stripper=new PDFTextStripper();
			
			s=stripper.getText(document);
		} catch (Exception e) {
			s="";
		}finally{
			if(document!=null){
				document.close();
			}
		}
		
		return s;
	}
	public static void toTextFile(String doc,String txt) throws IOException{
		String pdfFile=doc;
		PDDocument document=PDDocument.load(pdfFile);
		
		PDFTextStripper stripper=new PDFTextStripper();
		
		PrintWriter pw=new PrintWriter(new FileWriter(txt));
		
		stripper.writeText(document, pw);
		
		pw.close();
		
		System.out.println("�ɹ�д���ı��ļ� "+txt);
		
	}
	public static void main(String[] args) throws IOException {
		String s=ExtractorPDF.getText("pdf/�����ʥ��ſ�.pdf");
		System.out.println(s);
		
		ExtractorPDF.toTextFile("pdf/�����ʥ��ſ�.pdf", "pdf.txt");
	}
	
	
	
}
