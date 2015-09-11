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
			
			//调用PDFTextStripper来提取文本
			PDFTextStripper stripper=new PDFTextStripper();
			
			PrintWriter pw=new PrintWriter(new FileWriter("pdfboxb.txt"));
			
			stripper.writeText(document, pw);
			
			pw.close();
			
			System.out.println("文本已经成功写入!!!");
			
			
			
		} catch (IOException e) {
			System.out.println("文件读写错误!!!");
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
