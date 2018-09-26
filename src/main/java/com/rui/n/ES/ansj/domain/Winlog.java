package com.rui.n.ES.ansj.domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Arrays;

import com.google.gson.Gson;

public class Winlog {

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
	 * 日志名称
	 */
	String logname;
	/**
	 * 日志来源
	 */
	String logsource;
	/**
	 * 日志产生时间
	 */
	String logtime;
	/**
	 * 事件ID
	 */
	String eventid;
	/**
	 * 任务类别
	 */
	String tasktype;
	/**
	 * 日志级别
	 */
	String log_level;
	/**
	 * 关键字
	 */
	String keywords;
	/**
	 * 
	 */
	String opcode;
	/**
	 * 进程ID
	 */
	String process_id;
	/**
	 * 进程GUID
	 */
	String provider_guid;
	/**
	 * 记录号
	 */
	String record_number;
	/**
	 * 线程ID
	 */
	String thread_id;
	/**
	 * 用户信息
	 */
	String user_domain;
	String user_identifier;
	String user_name;
	String user_type;
	/**
	 * 计算机名
	 */
	String hostname;
	/**
	 * 日志描述
	 */
	String log_des;


	public String getId() {
		return id;
	}


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


	public String getLogName() {
		return logname;
	}


	public void setLogName(String logName) {
		logname = logName;
	}


	public String getLogSource() {
		return logsource;
	}


	public void setLogSource(String logsource) {
		this.logsource = logsource;
	}


	public String getLogTime() {
		return logtime;
	}


	public void setLogTime(String logtime) {
		this.logtime = logtime;
	}


	public String getEventId() {
		return eventid;
	}


	public void setEventId(String eventid) {
		this.eventid = eventid;
	}


	public String getTaskType() {
		return tasktype;
	}


	public void setTaskType(String tasktype) {
		this.tasktype = tasktype;
	}


	public String getLog_level() {
		return log_level;
	}


	public void setLog_level(String log_level) {
		this.log_level = log_level;
	}


	public String getKeywords() {
		return keywords;
	}


	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}


	public String getOpcode() {
		return opcode;
	}


	public void setOpcode(String opcode) {
		this.opcode = opcode;
	}


	public String getProcess_id() {
		return process_id;
	}


	public void setProcess_id(String process_id) {
		this.process_id = process_id;
	}


	public String getProvider_guid() {
		return provider_guid;
	}


	public void setProvider_guid(String provider_guid) {
		this.provider_guid = provider_guid;
	}


	public String getRecord_number() {
		return record_number;
	}


	public void setRecord_number(String record_number) {
		this.record_number = record_number;
	}


	public String getThread_id() {
		return thread_id;
	}


	public void setThread_id(String thread_id) {
		this.thread_id = thread_id;
	}


	public String getUser_domain() {
		return user_domain;
	}


	public void setUser_domain(String user_domain) {
		this.user_domain = user_domain;
	}
	
	public String getUser_identifier() {
		return user_identifier;
	}


	public void setUser_identifier(String user_identifier) {
		this.user_identifier = user_identifier;
	}
	
	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public String getUser_type() {
		return user_type;
	}


	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}


	public String getHostName() {
		return hostname;
	}


	public void setHostName(String hostname) {
		this.hostname = hostname;
	}


	public String getLog_des() {
		return log_des;
	}


	public void setLog_des(String log_des) {
		this.log_des = log_des;
	}

	
	class WinlogJson{
		// 对应logtime
		private String timestamp;
		
		private Beat beat;
		
		public class Beat{
			String hostname;
			String name;
			String version;
		}
		// 对应计算机名
		String computer_name;
		
		private Event_data event_data;
		
		public class Event_data{
			String Binary;
			String param1;
			String param2;
			String String;
			String StringCount;
			
			public String getBinary() {
				return Binary;
			}
			public void setBinary(String binary) {
				Binary = binary;
			}
			public String getParam1() {
				return param1;
			}
			public void setParam1(String param1) {
				this.param1 = param1;
			}
			public String getParam2() {
				return param2;
			}
			public void setParam2(String param2) {
				this.param2 = param2;
			}
			
		}
		// 对应事件id
		String event_id;
		// 对应关键字
		String [] keywords;
		// 对应日志级别
		String level;
		// 对应日志名称
		String log_name;
		// 对应日志描述
		String message;
		//
		String opcode;
		// 进程ID
		String process_id;
		// 进程GUID
		String provider_guid;
		// 记录号
		String record_number;
		// 日志来源
		String source_name;
		// 线程ID
		String thread_id;
		// 任务类型
		String type;
		
		private User user;
		
		public class User{
			String domain;
			String identifier;
			String name;
			String type;
			public String getDomain() {
				return domain;
			}
			public void setDomain(String domain) {
				this.domain = domain;
			}
			public String getIdentifier() {
				return identifier;
			}
			public void setIdentifier(String identifier) {
				this.identifier = identifier;
			}
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public String getType() {
				return type;
			}
			public void setType(String type) {
				this.type = type;
			}
			
		}

		public String getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}

		public Beat getBeat() {
			return beat;
		}

		public void setBeat(Beat beat) {
			this.beat = beat;
		}

		public String getComputer_name() {
			return computer_name;
		}

		public void setComputer_name(String computer_name) {
			this.computer_name = computer_name;
		}

		public Event_data getEvent_data() {
			return event_data;
		}

		public void setEvent_data(Event_data event_data) {
			this.event_data = event_data;
		}

		public String getEvent_id() {
			return event_id;
		}

		public void setEvent_id(String event_id) {
			this.event_id = event_id;
		}

		public String[] getKeywords() {
			return keywords;
		}

		public void setKeywords(String[] keywords) {
			this.keywords = keywords;
		}

		public String getLevel() {
			return level;
		}

		public void setLevel(String level) {
			this.level = level;
		}

		public String getLog_name() {
			return log_name;
		}

		public void setLog_name(String log_name) {
			this.log_name = log_name;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getProcess_id() {
			return process_id;
		}

		public void setProcess_id(String process_id) {
			this.process_id = process_id;
		}

		public String getProvider_guid() {
			return provider_guid;
		}

		public void setProvider_guid(String provider_guid) {
			this.provider_guid = provider_guid;
		}

		public String getRecord_number() {
			return record_number;
		}

		public void setRecord_number(String record_number) {
			this.record_number = record_number;
		}

		public String getSource_name() {
			return source_name;
		}

		public void setSource_name(String source_name) {
			this.source_name = source_name;
		}

		public String getThread_id() {
			return thread_id;
		}

		public void setThread_id(String thread_id) {
			this.thread_id = thread_id;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getOpcode() {
			return opcode;
		}

		public void setOpcode(String opcode) {
			this.opcode = opcode;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}
		
		
	}
	
	public Winlog() {
		
	}
	
	public Winlog(String log) {
		log = log.replace("@", "");
		
		Gson gson = new Gson();
		
		WinlogJson winlog = gson.fromJson(log, WinlogJson.class);
		
		// 事件id
		this.eventid = winlog.getEvent_id();
		// 日志名称
		this.logname = winlog.getLog_name();
		// 计算机名
		this.hostname = winlog.getComputer_name();
		// 日志产生时间
		this.logtime = winlog.getTimestamp().replace("T", " ").replace("Z", "");
		// 关键字
		if (winlog.getKeywords() != null) {
			int a = winlog.getKeywords().length;
			StringBuilder tmp = new StringBuilder();
			for(String keyword : winlog.getKeywords()){
				tmp.append(keyword);
				if (a>1) {
					tmp.append(",");
					a--;
				}
			}
			this.keywords = tmp.toString();
		}
		
		// 日志描述
		this.log_des = winlog.getMessage();
		// 日志级别
		this.log_level = winlog.getLevel();
		// 日志来源
		this.logsource = winlog.getSource_name();
		// 任务类型
		this.tasktype = winlog.getType();
		// 进程ID
		this.process_id = winlog.getProcess_id();
		// 进程GUID
		this.provider_guid = winlog.getProcess_id();
		// 记录号
		this.record_number = winlog.getRecord_number();
		// 线程ID
		this.thread_id = winlog.getThread_id();
		
		if (winlog.getOpcode() != null) {
			this.opcode = winlog.getOpcode();
		}
		if (winlog.getUser() != null) {
			this.user_domain = winlog.getUser().getDomain();
			
			this.user_identifier = winlog.getUser().getIdentifier();
			
			this.user_name = winlog.getUser().getName();
			
			this.user_type = winlog.getUser().getType();
		}
		
	}

	public String ToMapping() {
		 String template = "{\n" 
                +"\t\t\"properties\":{\n"
                        + "\t\t{#}\n" 
                + "\t\t\t\t}"
            +"}";
		String fieldString =  getClassMapping(new Winlog());
		template = template.replace("{#}",fieldString);
		return template;
	}

	public <T> String getClassMapping(T classes) {
		
		StringBuilder fieldstring = new StringBuilder();
		
		String [] fielddata = {"userid","deptid","equipmentid","logtime","logname","logsource","eventid","tasktype","log_level","keywords","hostname"};
     Field[] fields = classes.getClass().getDeclaredFields();
     for (int i = 0; i < fields.length; i++) {
          fieldstring.append("\t\t\t\t\"" + fields[i].getName().toLowerCase() + "\": {\n");
          fieldstring.append("\t\t\t\t\t\t\"type\": \""
                     + GetElasticSearchMappingType(fields[i].getType().getSimpleName(),fields[i].getName()) + "\n");
          if (fields[i].getName().equals("id")) {
         	 fieldstring.append("\t\t\t\t\t\t,\"index\": \""
                      + "false\"" + "\n");
			}
          if (Arrays.asList(fielddata).contains(fields[i].getName())) {
				fieldstring.append("\t\t\t\t\t\t,\"fielddata\": "
                     + "true" + "\n");
			}
          if (fields[i].getName().equals("log_des")||fields[i].getName().equals("keywords")) {
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
	
	public static void main(String[] args) throws IOException {

		// TODO Auto-generated method stub
		
		/*String log = "{\"@timestamp\":\"2017-09-29T01:42:29.601Z\",\"beat\":{\"hostname\":\"aaa-PC\",\"name\":\"aaa-PC\",\"version\":\"5.5.2\"},\"computer_name\":\"aaa-PC\",\"event_data\":{\"String\":\"C:\\\\Windows\\\\system32\\\\nvinitx.dll\",\"StringCount\":\"1\"},\"event_id\":11,\"level\":\"警告\",\"log_name\":\"System\",\"message\":\"正在为每个应用程序加载自定义动态链接库。系统管理员应该复查库列表以确保它们与受信任的应用程序相关。\",\"opcode\":\"信息\",\"process_id\":692,\"provider_guid\":\"{206F6DEA-D3C5-4D10-BC72-989F03C8B84B}\",\"record_number\":\"207722\",\"source_name\":\"Microsoft-Windows-Wininit\",\"thread_id\":724,\"type\":\"wineventlog\",\"user\":{\"domain\":\"NT AUTHORITY\",\"identifier\":\"S-1-5-18\",\"name\":\"SYSTEM\",\"type\":\"User\"}}";
				//"{\"@timestamp\":\"2017-09-29T01:42:22.315Z\",\"beat\":{\"hostname\":\"aaa-PC\",\"name\":\"aaa-PC\",\"version\":\"5.5.2\"},\"computer_name\":\"aaa-PC\",\"event_data\":{\"Binary\":\"4200460045002F0034000000\",\"param1\":\"Base Filtering Engine\",\"param2\":\"正在运行\"},\"event_id\":7036,\"keywords\":[\"经典\",\"经典\",\"经典\"],\"level\":\"信息\",\"log_name\":\"System\",\"message\":\"Base Filtering Engine 服务处于 正在运行 状态。\",\"process_id\":748,\"provider_guid\":\"{555908d1-a6d7-4695-8e1e-26931d2012f4}\",\"record_number\":\"207704\",\"source_name\":\"Service Control Manager\",\"thread_id\":880,\"type\":\"wineventlog\"}";

		Winlog wmi = new Winlog(log);*/
		//System.out.println(new Winlog().ToMapping());

		File filelog = new File("D:\\tmp\\九州\\winlog日志.txt");
		
		
		FileInputStream fis=new FileInputStream("D:\\tmp\\九州\\winlog日志.txt");
        InputStreamReader isr=new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        while ((line=br.readLine())!=null) {
        	System.out.println(line);
        	
        	Winlog winlog = new Winlog(line);
        	
        	System.out.println(winlog.getEventId());
        }
	}



}
