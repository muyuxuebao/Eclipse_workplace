package tianen;

import java.io.IOException;

import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;

public class PDFBoxA {
	public String getText(String file) throws IOException{
		String pdfFile=file;
		PDDocument document=null;
		
		String s="";
		try {
			document=PDDocument.load(pdfFile);
			
			//调用PDFTextStripper来提取文本
			PDFTextStripper stripper=new PDFTextStripper();
			
			s=stripper.getText(document);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			if(document!=null){
				document.close();
			}
		}
		
		return s;
	}
	public static void main(String[] args) {
		PDFBoxA a=new PDFBoxA();
		try {
			String s=a.getText("pdf/Only you.pdf");
			System.out.println(s);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
