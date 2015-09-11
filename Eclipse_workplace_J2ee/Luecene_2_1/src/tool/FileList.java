package tool;

import java.io.File;
import java.io.IOException;

public class FileList {
	public static final String SEP="/;";
	public static StringBuffer sb=new StringBuffer("");
	public static String[] getFiles(File f) throws IOException{
		if(f.isDirectory()){
			File files[]=f.listFiles();
			for(int i=0;i<files.length;i++){
				getFiles(files[i]);
			}
		}else {
			sb.append(f.getPath()+SEP);
		}
		String s=sb.toString();
		String[] list=s.split(SEP);
		return list;
	}
	
	public static String[] getFiles(String t) throws IOException{
		File f=new File(t);
		
		if(f.isDirectory()){
			File files[]=f.listFiles();
			for(int i=0;i<files.length;i++){
				getFiles(files[i]);
			}
		}else {
			sb.append(f.getPath()+SEP);
		}
		String s=sb.toString();
		String[] list=s.split(SEP);
		return list;
	}
	public static void main(String[] args) throws IOException {
		String[] s=FileList.getFiles("D:/Program Files/Apache Software Foundation/Tomcat 7.0/bin");
		
		for (int i = 0; i < s.length; i++) {
			System.out.println(s[i]);
		}
	}
}
