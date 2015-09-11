//ExtractorAll.java
package tool.extractors;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

import tool.extractors.ExtractorHTML.BeanNormal;


public class ExtractorAll
{
	public static String getText(String doc)
	{
		String text = "";
		try
		{
			// 根据扩展名判断文件类型
			String ext = doc.substring(doc.lastIndexOf(".")+1);
			
			if(ext.equalsIgnoreCase("htm") || ext.equalsIgnoreCase("html") || ext.equalsIgnoreCase("shtml"))
			{
				//处理HTML文件
				text = BeanNormal.getText(doc);
			}
			else if(ext.equalsIgnoreCase("doc"))
			{
				//处理Word文件
				text = ExtractorWord.getText(doc);
			}
			else if(ext.equalsIgnoreCase("xls"))
			{
				//处理Excel文件
				text = ExtractorExcel.getText(doc);
			}
			else if(ext.equalsIgnoreCase("pdf"))
			{
				//处理PDF文件
				text = ExtractorPDF.getText(doc);
			}
			else if(ext.equalsIgnoreCase("xml"))
			{
				//处理XML文件
				text = ExtractorXML.getText(doc);
			}
			else
			{
				//对于其他扩展名的文件都假设文件是文本文件
				text = ExtractorTXT.getText(doc);
			}
		}
		catch(Exception e)
		{
			text = "";
		}
		
		return text;
	}
	
	public static void toTextFile(String doc,String txt) throws Exception 
	{	
		// 提取文本
		String text = getText(doc);
		
		//写入文本文件
		PrintWriter pw=new PrintWriter(new FileWriter(new File(txt)));
		pw.write(text);
		pw.flush();
		pw.close();
		
		System.out.println("成功写入文本文件 " + txt);
	}

	public static void main(String[] args) throws Exception
	{
		String text = getText("齐天大圣孙悟空.xml");
		System.out.println(text);
		toTextFile("齐天大圣孙悟空.xml","齐天大圣孙悟空.txt");
	}
}