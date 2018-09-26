package com.rui.n.ES.ansj.controller;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.BaseAnalysis;
import org.ansj.splitWord.analysis.DicAnalysis;
import org.ansj.splitWord.analysis.IndexAnalysis;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.ansj.splitWord.analysis.ToAnalysis;

public class ansjController {

	public static void main(String[] args) throws IOException  {
		// TODO Auto-generated method stub
		/*File filelog = new File("C:\\Users\\jiyourui\\Desktop\\log日志格式.txt");
		
		
		FileInputStream fis=new FileInputStream("C:\\Users\\jiyourui\\Desktop\\log日志格式.txt");
        InputStreamReader isr=new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);*/
        
        String line="中华人民共和国";
        Result result = null;
        long starttime = System.currentTimeMillis();
        //while ((line=br.readLine())!=null) {
       /* while (line!=null) {
        	// 各种分词结果展示
        	//result = BaseAnalysis.parse(line);
        	//result = ToAnalysis.parse(line);
        	//result = IndexAnalysis.parse(line);
        	//result =  NlpAnalysis.parse(line);
        	result = DicAnalysis.parse(line);
    		System.out.println(result.getTerms());
        }*/
     // 各种分词结果展示
        //result = BaseAnalysis.parse(line);
        //result = ToAnalysis.parse(line);
    	result = IndexAnalysis.parse(line);
    	//result =  NlpAnalysis.parse(line);
    	//result = DicAnalysis.parse(line);
		System.out.println(result.getTerms());
        long endtime = System.currentTimeMillis();
       /* br.close();
        isr.close();
        fis.close();*/
		
		long time = endtime - starttime;
		System.out.println("解析log4j耗时  ： " + time + " ms");
		
		
		
		/*List<Term> term = result.getTerms();
		
		for (int i = 0; i < term.size(); i++) {
			String word = term.get(i).getName();
			String natureStr = term.get(i).getNatureStr();
			System.out.println(word + " : " + natureStr);
		}*/

	}

}
