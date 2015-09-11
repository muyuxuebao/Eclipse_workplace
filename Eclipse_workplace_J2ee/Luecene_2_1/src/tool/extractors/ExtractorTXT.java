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
		String text = getText("�����ʥ�����.xiaoyue");
		System.out.println(text);
		toTextFile("�����ʥ�����.xiaoyue","txt.txt");
	}
}