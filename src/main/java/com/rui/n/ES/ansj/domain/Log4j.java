package com.rui.n.ES.ansj.domain;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author jiyourui
 *  log4j日志字段模型
 */
public class Log4j {
	
	/**
	 * id
	 */
	String id;
	/**
	 * userid
	 */
	String userid;
	/**
	 * deptid
	 */
	String deptid;
	/**
	 * equipmentid
	 */
	String equipmentid;
	/**
	 * 日志时间
	 */
	String logtime;
	/**
	 * 操作类型
	 */
	String operation_level;
	/**
	 * ip地址
	 */
	String ip;
	/**
	 * 操作描述
	 */
	String operation_des;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getEquipmentid() {
		return equipmentid;
	}
	public void setEquipmentid(String equipmentid) {
		this.equipmentid = equipmentid;
	}
	/**
	 * @return the logtime
	 */
	public String getLogtime() {
		return logtime;
	}
	/**
	 * @param logtime the logtime to set
	 */
	public void setLogtime(String logtime) {
		this.logtime = logtime;
	}
	/**
	 * @return the operation_level
	 */
	public String getOperation_level() {
		return operation_level;
	}
	/**
	 * @param operation_level the operation_level to set
	 */
	public void setOperation_level(String operation_level) {
		this.operation_level = operation_level;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * @return the operation_des
	 */
	public String getOperation_des() {
		return operation_des;
	}
	/**
	 * @param operation_des the operation_des to set
	 */
	public void setOperation_des(String operation_des) {
		this.operation_des = operation_des;
	}
	
	public Log4j(){
		
	}
	/**
	 * 初始化	String2bean
	 * @param log
	 */
	public Log4j(String log) {
		
		Pattern facility_pattern = Pattern.compile("local3:");
		Matcher facility_matcher = facility_pattern.matcher(log);
		
        
        if (facility_matcher.find()) {
			String logleft = log.substring(0, log.indexOf(facility_matcher.group(0))+facility_matcher.group(0).length());
			System.out.println(logleft);
			String logright = log.substring(log.indexOf(facility_matcher.group(0))+facility_matcher.group(0).length());
			System.out.println(logright);
			
			// 截取日志生产ip
	        Pattern ipPattern = Pattern.compile("(\\d+\\.\\d+\\.\\d+\\.\\d+)\\s");  
	        Matcher ipmatcher = ipPattern.matcher(logleft);
			// 截取时间
			Pattern datePattern = Pattern.compile("[0-9]{4}[-][0-9]{1,2}[-][0-9]{1,2}[ ][0-9]{1,2}[:][0-9]{1,2}[:][0-9]{1,2}[,][0-9]{2,3}");  
	        Matcher datematcher = datePattern.matcher(logright);
	        // 截取日志级别
	        Pattern logLevelpattern = Pattern.compile("debug|info|error|warn",Pattern.CASE_INSENSITIVE);
	        Matcher logLevelmatcher = logLevelpattern.matcher(logright);
	        
	        if(datematcher.find()){ 
	        	//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,S");
	        	datematcher.group(0).replaceAll("/,", "/.");
	        	System.out.println(datematcher.group(0).replaceAll(",", "."));
	        	// 获取 时间
				this.logtime = datematcher.group(0).replaceAll(",", ".");
	        }
	        if (logLevelmatcher.find()) {
	        	// 获取 日志级别
	        	this.operation_level = logLevelmatcher.group(0);
			}
	        if (ipmatcher.find()) {
	        	// 获取 日志地址
				this.ip = ipmatcher.group(0);
			}
	        
	        /*if(ip!=null) {
	        	int c = log.indexOf(ip)+ip.length();
	            // 获取日志操作
	            this.operation_des = log.substring(c, log.length());
	        }else*/ if (operation_level!=null){
	        	int c = log.indexOf(operation_level)+operation_level.length()+1;
	            // 获取日志操作
	            this.operation_des = log.substring(c, log.length());
			}else {
				this.operation_des = log;
			}
		}
        
	}
	
	public String ToMapping() {
		 String template = "{\n" 
                   +"\t\t\"properties\":{\n"
                           + "\t\t{#}\n" 
                   + "\t\t\t\t}"
               +"}";
		String fieldString =  getClassMapping(new Log4j());
		template = template.replace("{#}",fieldString);
		return template;
	}

	public <T> String getClassMapping(T classes) {
		
		StringBuilder fieldstring = new StringBuilder();
		
        Field[] fields = classes.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
             fieldstring.append("\t\t\t\t\"" + fields[i].getName().toLowerCase() + "\": {\n");
             fieldstring.append("\t\t\t\t\t\t\"type\": \""
                        + GetElasticSearchMappingType(fields[i].getType().getSimpleName(),fields[i].getName()) + "\n");
             if (fields[i].getName().equals("id")) {
            	 fieldstring.append("\t\t\t\t\t\t,\"index\": \""
                         + "false\"" + "\n");
			}
             if (!fields[i].getName().equals("operation_des")&&!fields[i].getName().equals("id")) {
				fieldstring.append("\t\t\t\t\t\t,\"fielddata\": "
                        + "true" + "\n");
			}
             if (fields[i].getName().equals("operation_des")||fields[i].getName().equals("ip")) {
            	 fieldstring.append("\t\t\t\t\t\t,\"analyzer\": \""
            	 + "index_ansj\"" + "\n");
            	 fieldstring.append("\t\t\t\t\t\t,\"search_analyzer\": \""
            	 + "query_ansj\"" + "\n");
			}
             if (i == fields.length-1) {
                    fieldstring.append("\t\t\t\t\t}\n");
                } else {
                    fieldstring.append("\t\t\t\t\t},\n");
                }
        }
        return fieldstring.toString();
	}

	private static String GetElasticSearchMappingType(String varType,String name) {
        String es = "text";
        switch (varType) {
        case "Date":
            es = "date\"\n"+"\t\t\t\t\t\t,\"format\":\"yyyy-MM-dd HH:mm:ss,S\"\n"+"\t\t\t\t\t\t";
            break;
        case "Double":
            es = "double\"\n"+"\t\t\t\t\t\t,\"null_value\":\"NaN\"";
            break;
        case "Long":
            es = "long\"";
            break;
        default:
        	if (name.equals("id")) {
        		es = "keyword\"";
			}else {
				es = "text\"";
			}
            
            break;
        }
        return es;
    }
	
	public static void main(String [] args) {
		System.out.println(new Log4j().ToMapping());
		
		/*String log = "192.168.4.148  local3: 2017-10-26 11:18:02,013 [ERROR]  [##test 好1508987882013]  [java.lang.Class] main()[11] [12010]";
		Log4j log4j = new Log4j(log);*/
		
	}
}
