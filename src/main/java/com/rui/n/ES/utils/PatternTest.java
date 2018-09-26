package com.rui.n.ES.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PatternTest {
	
	
	

	public static void main(String[] args) {

		Map<String, Integer> smap = new HashMap<>();
        smap.put("登录", 1111111);
        smap.put("bbb", 2222222);
        smap.put("ccc", 3333333);

        String str = "aaassccc";
        
        List<Map.Entry<String, Integer>> list2 = new ArrayList<>();
        //map 转换成   List<Map.Entry<String, Integer>>结构
        list2.addAll(smap.entrySet());
        List<Map.Entry<String, Integer>> filterResult = list2.stream().filter(x-> str.indexOf(x.getKey())>=0 )//过滤
        		.collect(Collectors.toList());//重组 list
        
        System.out.println(filterResult.size());
	}

}
