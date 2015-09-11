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
			// ������չ���ж��ļ�����
			String ext = doc.substring(doc.lastIndexOf(".")+1);
			
			if(ext.equalsIgnoreCase("htm") || ext.equalsIgnoreCase("html") || ext.equalsIgnoreCase("shtml"))
			{
				//����HTML�ļ�
				text = BeanNormal.getText(doc);
			}
			else if(ext.equalsIgnoreCase("doc"))
			{
				//����Word�ļ�
				text = ExtractorWord.getText(doc);
			}
			else if(ext.equalsIgnoreCase("xls"))
			{
				//����Excel�ļ�
				text = ExtractorExcel.getText(doc);
			}
			else if(ext.equalsIgnoreCase("pdf"))
			{
				//����PDF�ļ�
				text = ExtractorPDF.getText(doc);
			}
			else if(ext.equalsIgnoreCase("xml"))
			{
				//����XML�ļ�
				text = ExtractorXML.getText(doc);
			}
			else
			{
				//����������չ�����ļ��������ļ����ı��ļ�
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
		// ��ȡ�ı�
		String text = getText(doc);
		
		//д���ı��ļ�
		PrintWriter pw=new PrintWriter(new FileWriter(new File(txt)));
		pw.write(text);
		pw.flush();
		pw.close();
		
		System.out.println("�ɹ�д���ı��ļ� " + txt);
	}

	public static void main(String[] args) throws Exception
	{
		String text = getText("�����ʥ�����.xml");
		System.out.println(text);
		toTextFile("�����ʥ�����.xml","�����ʥ�����.txt");
	}
}