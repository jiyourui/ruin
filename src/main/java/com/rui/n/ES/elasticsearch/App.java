package com.rui.n.ES.elasticsearch;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
    	
    	Token token = null;;
    	Analyzer analyzer = new StandardAnalyzer();
    	TokenStream stream=  analyzer.tokenStream("", "at com.jz.log.test.Log4jTest.main(Log4jTest.java:37)");
    	System.out.println(stream.toString());
    	
    }
}
