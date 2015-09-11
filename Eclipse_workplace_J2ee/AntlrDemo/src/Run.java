import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

public class Run {
	public static void main(String[] args) throws IOException, RecognitionException {
		SimpleActionLexer lexer = new SimpleActionLexer(new ANTLRFileStream("code.txt"));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SimpleActionParser parser = new SimpleActionParser(tokens);
		parser.variable();
	}

	public static ANTLRFileStream getFormatInputStream(String filePath) {
		// replace all the string -2147483648 to 0x80000000 in file
		if ((filePath != null) && !filePath.equals("")) {
			try {
				
				
				return new ANTLRFileStream("code.txt");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
			}
		}
		return null;
	}
	
	void changeFile(){
		
	}
}
