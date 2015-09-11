//ParserPage.java
package tool.extractors.ExtractorHTML;

import org.htmlparser.*;
import org.htmlparser.visitors.*;
import org.htmlparser.util.*;

public class ParserPage
{
    public static void main (String[] args) throws ParserException
    {
        Parser parser = new Parser ("html/te.htm");
        //parser.setEncoding("gbk");
        
        HtmlPage hp = new HtmlPage(parser);
        
        parser.visitAllNodesWith(hp);
        
        //title
        String title = hp.getTitle();
        System.out.println("title:" + title);
        
        //body--1
        NodeList body = hp.getBody();
        String b = body.asString();
        System.out.println("body:" + b);
    }
}























