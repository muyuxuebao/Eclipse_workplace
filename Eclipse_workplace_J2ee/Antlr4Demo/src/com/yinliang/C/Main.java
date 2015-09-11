package com.yinliang.C;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import com.yinliang.C.CMinusParser.ProgramContext;

public class Main {
	public static void main(String[] args) throws IOException {
		ANTLRFileStream input = new ANTLRFileStream("aaa.c");
		CMinusLexer lexer = new CMinusLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CMinusParser parser = new CMinusParser(tokens);
		ProgramContext context = parser.program();

		List<ParseTree> list = context.children;

		for (ParseTree parseTree : list) {
			for (int i = 0; i < parseTree.getChildCount(); i++) {
				System.out.println(parseTree.getChild(i));
				System.out.println("---");
			}
			System.out.println("****************************************");
		}
	}

	private static Object newANTLRInputStream(InputStream in) {
		return null;
	}
}
