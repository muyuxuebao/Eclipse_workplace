//LuceneIndexer.java
package tianen;

import java.util.Iterator;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import javax.swing.*;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

import jeasy.analysis.MMAnalyzer;

import tool.*;

public class LuceneIndexer
{
	private JTextField jtfa;
	private JButton jba;
	private JTextField jtfb;
	private JButton jbb;
	private JButton jbc;
	private static JTextArea jta;
	
	private void createAndShowGUI()
	{
		
		// ���ÿ�ƽ̨��۸о�
		//String lf=UIManager.getCrossPlatformLookAndFeelClassName();
		
		//GTK
		//String lf="com.sun.java.swing.plaf.gtk.GTKLookAndFeel";

		//System
		//String lf=UIManager.getSystemLookAndFeelClassName();
		
		//windows
		//String lf="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		
		//metal
		//String lf="javax.swing.plaf.metal.MetalLookAndFeel";
		/**common use
		try
		{
			UIManager.setLookAndFeel(lf);
		}
		catch(Exception ce)
		{
			JOptionPane.showMessageDialog(null,"�޷��趨��۸о���");
		}
		**/
		
		//Java�о�
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		JFrame frame=new JFrame("Tianen Indexer! yutianen@163.com");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final JFileChooser fc=new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		Container con= frame.getContentPane();	
		con.setLayout(new BorderLayout());
		
		JPanel jpup=new JPanel();
		jpup.setLayout(new GridLayout(3,2));
		jtfa=new JTextField(30);
		jba=new JButton("ѡ���������ļ����·��");
		jba.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					int r=fc.showOpenDialog(null);
					if(r==JFileChooser.APPROVE_OPTION)
					{
						jtfa.setText(fc.getSelectedFile().getPath());
						jbc.setEnabled(true);
					}
				}	
			}
		);
		jtfb=new JTextField(30);
		JButton jbb=new JButton("ѡ�������Ĵ��·��");
		jbb.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					int r=fc.showOpenDialog(null);
					if(r==JFileChooser.APPROVE_OPTION)
					{
						jtfb.setText(fc.getSelectedFile().getPath());
						jbc.setEnabled(true);
					}
				}	
			}
		);
		JLabel jl=new JLabel("");
		jbc=new JButton("��������");
		jbc.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					try
					{
						LuceneIndexerTool.index(jtfa.getText(),jtfb.getText());
						//jbc.setEnabled(false);
					}
					catch(Exception ee)
					{
						ee.printStackTrace();
						jbc.setEnabled(true);
						JOptionPane.showMessageDialog(null,"��������ʧ�ܣ�");
						System.out.println(ee.getMessage());
					}
				}	
			}
		);
		jpup.add(jtfa);
		jpup.add(jba);
		jpup.add(jtfb);
		jpup.add(jbb);
		jpup.add(jl);
		jpup.add(jbc);
		
		jta=new JTextArea(10,60);
		JScrollPane jsp=new JScrollPane(jta);
		
		con.add(jpup,BorderLayout.NORTH);
		con.add(jsp,BorderLayout.CENTER);

		frame.setSize(200,100);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater
		(
			new Runnable()
			{
				public void run()
				{
					try
					{
						new LuceneIndexer().createAndShowGUI();
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null,"�������ʧ�ܣ�");
					}
				}
			}
		);
	}	
	
	//ʹ���ڲ���LuceneIndexerTool��ʵ����������
	//�������Ϳ��԰����������������ӳ���ı���������
	static class LuceneIndexerTool
	{
		//�������� -- ���������ļ���·����������·��
		public static void index(String filesPath,String indexPath) throws IOException 
		{
			//����������
			IndexWriter writer = new IndexWriter(indexPath,new MMAnalyzer());
			
			//�ڴ����ĵ������Ϊ50
			writer.setMaxBufferedDocs(50);
			
			//�ڴ��д洢50���ĵ�ʱд�ɴ����е�һ����
			writer.setMergeFactor(50);
			
			//�ݹ�����ļ�Ŀ¼����������
			String s[] = FileList.getFiles(filesPath);
			int len = s.length;
			for(int i=0;i<len;i++)
			{
				File f = new File(s[i]);
				String ext = getExt(f);
				if(ext.equalsIgnoreCase("htm") || ext.equalsIgnoreCase("html"))
				{
					/********************
					 *
					 *	filename
					 *	uri
					 *	cdate
					 *	size
					 *	text
					 *	digest
					 *
					 *********************/
					Document doc = new Document();
					
					//filename
					String filename = f.getName();
					Field field = new Field("filename", filename ,Field.Store.YES, Field.Index.TOKENIZED);
					doc.add(field);
					
					//uri local
					String uri = f.getPath();
					field = new Field("uri", uri ,Field.Store.YES, Field.Index.NO);
					doc.add(field);
					
					//cdate
					Date dt=new Date(f.lastModified());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd E "); 
					String cdate = sdf.format(dt);
					field = new Field("cdate", cdate ,Field.Store.YES, Field.Index.NO);
					doc.add(field);
					
					//size
					double si = f.length();
					String size = "";
					
					if(si>1024)
					{
						size = String.valueOf(Math.floor(si/1024)) + " K";
					}
					else
					{
						size = String.valueOf(si) + " Bytes";
					}
					field = new Field("size", size ,Field.Store.YES, Field.Index.NO);
					doc.add(field);

					//text
					String text = FileText.getText(f);
					field = new Field("text", text ,Field.Store.COMPRESS, Field.Index.TOKENIZED);
					doc.add(field);
					
					//digest
					String digest = "";
					if(text.length()>200)
					{
						digest=text.substring(0,200);
					}
					else
					{
						digest=text;
					}
					field = new Field("digest", digest ,Field.Store.YES, Field.Index.TOKENIZED);
					doc.add(field);
					
					writer.addDocument(doc);

					jta.setText(jta.getText() + "�Ѿ����������� " + f + "\n");
				}
			}
	
			// �ر�������
			writer.close();
				
			JOptionPane.showMessageDialog(null,"�����������","�����ʾ",JOptionPane.INFORMATION_MESSAGE);
		}
		
		public static String getExt(File f)
		{
			String s = f.getName();
			try
			{
				s = s.substring(s.lastIndexOf(".")+1);
			}
			catch(Exception e)
			{
				s= "";
			}
			return s;
		}
	}
}
