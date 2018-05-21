package cn.edu.myself.study.test;

import java.io.IOException;
import java.io.StringReader;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;

public class PaoDingTest {
	public static void main(String[] args) {
		String str="分享到： 简介 白菜炖粉皮是北方冬季常吃的家常菜，但传统的做法吃的口腻了，做个鱼香味的白菜粉皮吧。小酸、小甜，微辣的鱼香味的白菜粉皮，这样的味道很讨喜。 用料 适量 白菜 适量 粉皮 做法 1 白菜择洗干净，用手撕成大块，粉皮用剪刀剪成需要的大小，用水泡软。 2 用陈醋、白糖、少许精盐、酱油调成料汁。 3 五花肉切薄片，码放入锅，小火煎制出油。 4 放入生姜片，炒香。 5 放入一汤匙郫县豆瓣酱、少许豆豉，炒香。 6 放入撕好的白菜块，撒少许精盐大火翻炒均匀后改小火炖5分钟。 7 放入泡好的粉皮，（可加少许清水），小火翻炒至熟。 8 倒入调好的料汁，大火翻炒均匀后即可出锅。 9 小酸、小甜，微辣的鱼香味的白菜粉皮，这样的味道很讨喜。 该菜谱来源于网络打印菜单";
        String str1 = "八达岭残长城";
		Analyzer analyzer = new PaodingAnalyzer();  
        
        //得到token序列的输出流  
        TokenStream tokens = analyzer.tokenStream(str1, new StringReader(str1));  
        try{  
            Token t;  
            while((t=tokens.next() ) !=null){  
                System.out.println(t);  
                //System.out.println(t.termText())  输出单个词  
            }  
        }catch(IOException e){  
            e.printStackTrace();  
        }  
	}
}
