package com.rui.n.ES.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.google.gson.Gson;

import net.sf.cglib.beans.BeanMap; 

public class MapUtils {

	
	public static <T> Map<String, Object> beanToMap(T bean) {  
	    Map<String, Object> map = new HashMap<String, Object>();  
	    if (bean != null) {  
	        BeanMap beanMap = BeanMap.create(bean);  
	        for (Object key : beanMap.keySet()) {  
	            map.put(key+"", beanMap.get(key));  
	        }             
	    }  
	    return map;  
	}  
	
	public static <T> T mapToBean(Map<String, Object> map,T bean) {  
	    BeanMap beanMap = BeanMap.create(bean);  
	    beanMap.putAll(map);  
	    return bean;  
	} 
	
	
	class Firewall{
		private String devid;
		private String date;
		private String dname;
		public String getDevid() {
			return devid;
		}
		public void setDevid(String devid) {
			this.devid = devid;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getDname() {
			return dname;
		}
		public void setDname(String dname) {
			this.dname = dname;
		}
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*Map<String, String> map = {"devid":0 "date":"2013/05/21 09:53:17" "dname":"themis" "logtype":"9" "pri":"6" "ver":"0.3.0"};
		
		MapUtils.beanToMap(bean);*/
		Map<String, String> map = new HashMap<>();
		map.put("devid", "0");
		map.put("date", "2013/05/21 09:53:17");
		map.put("dname", "themis");
		//map = {devid=0, date=2013/05/21 09:53:17, dname=themis};
		//String dd = "devid=0 date='2012/07/19 00:14:09' dname=Themis logtype=16 pri=4 ver=0.3.0 user='administrator' mod='ips' eventtype=IPS eventname='UserDefine sina_alert' severity=1 dsp_msg='Detect attack: sina_alert; type: UserDefine ' protocol=TCP srcaddr=10.1.5.200 srcport =56085 destaddr=119.84.71.211 destport=80 repeated=25 eventdetails='/1245758104/50/1300244013/0' action='drop' if='市场部' fwlog=0";
		
		String dd = "devid=0 date='2012/07/19 00:14:09' dname=Themis";
		dd =dd.replace("=", ":");
		dd = dd.replace(" ", ",");
		dd = "{"+dd+"}";
		
		Gson gson = new Gson();
		
		Firewall firewall = gson.fromJson(dd, Firewall.class);
		//System.out.println("{"+dd+"}");
	}

}
