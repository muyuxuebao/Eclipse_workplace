//ExtractorTXT.java
package tool.extractors;

import java.io.*;

public class ExtractorTXT
{

	public static String getText(String doc) 
	{
		StringBuffer sb=new StringBuffer("");
		try
		{
			FileReader fr=new FileReader(doc);
			BufferedReader br=new BufferedReader(fr);
			
			String s=br.readLine();
			while(s!=null)
			{
				sb.append(s);
				s=br.readLine();
			}
			br.close();
		}
		catch(Exception e)
		{
			sb.append("");
		}
		
		return sb.toString();
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
		String text = getText("齐天大圣孙悟空.xiaoyue");
		System.out.println(text);
		toTextFile("齐天大圣孙悟空.xiaoyue","txt.txt");
	}
}