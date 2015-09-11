package tianen;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;

public class PDFBoxB {
	public void writeText(String file) throws IOException{
		String pdfFile=file;
		PDDocument document=null;
		
		try {
			document=PDDocument.load(pdfFile);
			
			//����PDFTextStripper����ȡ�ı�
			PDFTextStripper stripper=new PDFTextStripper();
			
			PrintWriter pw=new PrintWriter(new FileWriter("pdfboxb.txt"));
			
			stripper.writeText(document, pw);
			
			pw.close();
			
			System.out.println("�ı��Ѿ��ɹ�д��!!!");
			
			
			
		} catch (IOException e) {
			System.out.println("�ļ���д����!!!");
		}finally{
			if(document!=null){
				document.close();
			}
		}
		
	}
	public static void main(String[] args) {
		PDFBoxB a=new PDFBoxB();
		try {
			a.writeText("pdf/Only you.pdf");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}
}
