package com.rui.n.ES.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



public class Configure {
	
	static Properties properties = new Properties();
	
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		Configure configure = new Configure();
		
		configure.init();
		
		//configure.showconf();
		
		System.out.println(Configure.getParam("elasticsearch.cluster.name"));
	}
	
	/**
	 * 初始化配置文件
	 */
	private void init() {
		
		String confpath = Thread.currentThread().getContextClassLoader ().getResource("").getPath().substring(1);
		
		File file = new File(confpath);
		
		String [] dir;
		
		dir = file.list();
		
		for(String pro : dir) {
			
			
			writePro(confpath, pro);
		}
	}
	
	
	public void init2() {
		String confpath = Thread.currentThread().getContextClassLoader ().getResource("").getPath().substring(1);
		
		File file = new File(confpath);
		
		String [] dir;
		
		dir = file.list();
		
		for(String pro : dir) {
			if (!pro.isEmpty()&&pro.endsWith("conf_.xml")) {
				pro = "/"+pro;
				System.out.println(pro);
				
				SAXReader saxReader = new SAXReader();
				Document document = null;
				try {
					document = saxReader.read(this.getClass().getResourceAsStream(pro));
					List<Element> list = document.getRootElement().elements();
					for(Element element : list) {
						System.out.println(element.attributeValue("key")+":"+element.getTextTrim());
						//properties.setProperty(element.attributeValue("name"), element.getTextTrim());
					}
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
			}
	}
	
	
	public void writePro(String path,String name) {
		File files = new File(path+File.separator+name);
		if (files.isDirectory()) {
			String [] file = files.list();
			for(String filename : file) {
				writePro(path, name+File.separator+filename);
			}
		}else if (!name.isEmpty()&&name.endsWith(".properties")) {
			name = "/"+name;
			System.out.println(name);
			try {
				properties.load(this.getClass().getResourceAsStream(name));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 遍历properties
	 */
	public void showconf() {
		Set<Object> keys = properties.keySet();
		
		for(Object key : keys) {
			System.out.println(key+":"+properties.get(key));
		}
	}
	
	
	/**
	 * @param key
	 * @return String value
	 * 获取配置文件内容
	 */
	public static String getParam(String key) {
		return (String) properties.get(key);
	}
	
	/**
	 * @param key
	 * @return String value
	 * 获取配置文件内容
	 */
	public static Integer getParamInt(String key) {
		if (getParam(key) == null) {
			return null;
		}
		return Integer.parseInt(getParam(key));
	}

}
