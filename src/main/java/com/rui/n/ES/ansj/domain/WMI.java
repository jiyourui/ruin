package com.rui.n.ES.ansj.domain;

import java.io.IOException;
import com.google.gson.Gson;

public class WMI {

	/**
	 * id
	 */
	String id;
	/**
	 * 日志名称
	 */
	String LogName;
	/**
	 * 日志来源
	 */
	String logSource;
	/**
	 * 日志产生时间
	 */
	String logTime;
	/**
	 * 事件ID
	 */
	String eventId;
	/**
	 * 任务类别
	 */
	String taskType;
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
	String hostName;
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


	public String getLogName() {
		return LogName;
	}


	public void setLogName(String logName) {
		LogName = logName;
	}


	public String getLogSource() {
		return logSource;
	}


	public void setLogSource(String logSource) {
		this.logSource = logSource;
	}


	public String getLogTime() {
		return logTime;
	}


	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}


	public String getEventId() {
		return eventId;
	}


	public void setEventId(String eventId) {
		this.eventId = eventId;
	}


	public String getTaskType() {
		return taskType;
	}


	public void setTaskType(String taskType) {
		this.taskType = taskType;
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
		return hostName;
	}


	public void setHostName(String hostName) {
		this.hostName = hostName;
	}


	public String getLog_des() {
		return log_des;
	}


	public void setLog_des(String log_des) {
		this.log_des = log_des;
	}

	
	class Winlog{
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
	
	public WMI() {
		
	}
	
	public WMI(String log) {
		log = log.replace("@", "");
		
		Gson gson = new Gson();
		
		Winlog winlog = gson.fromJson(log, Winlog.class);
		
		// 事件id
		this.eventId = winlog.getEvent_id();
		// 日志名称
		this.LogName = winlog.getLog_name();
		// 计算机名
		this.hostName = winlog.getComputer_name();
		// 日志产生时间
		this.logTime = winlog.getTimestamp().replace("T", " ").replace("Z", "");
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
		this.logSource = winlog.getSource_name();
		// 任务类型
		this.taskType = winlog.getType();
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

	public static void main(String[] args) throws IOException {

		// TODO Auto-generated method stub
		
		String log = "{\"@timestamp\":\"2017-09-29T01:42:29.601Z\",\"beat\":{\"hostname\":\"aaa-PC\",\"name\":\"aaa-PC\",\"version\":\"5.5.2\"},\"computer_name\":\"aaa-PC\",\"event_data\":{\"String\":\"C:\\\\Windows\\\\system32\\\\nvinitx.dll\",\"StringCount\":\"1\"},\"event_id\":11,\"level\":\"警告\",\"log_name\":\"System\",\"message\":\"正在为每个应用程序加载自定义动态链接库。系统管理员应该复查库列表以确保它们与受信任的应用程序相关。\",\"opcode\":\"信息\",\"process_id\":692,\"provider_guid\":\"{206F6DEA-D3C5-4D10-BC72-989F03C8B84B}\",\"record_number\":\"207722\",\"source_name\":\"Microsoft-Windows-Wininit\",\"thread_id\":724,\"type\":\"wineventlog\",\"user\":{\"domain\":\"NT AUTHORITY\",\"identifier\":\"S-1-5-18\",\"name\":\"SYSTEM\",\"type\":\"User\"}}";
				//"{\"@timestamp\":\"2017-09-29T01:42:22.315Z\",\"beat\":{\"hostname\":\"aaa-PC\",\"name\":\"aaa-PC\",\"version\":\"5.5.2\"},\"computer_name\":\"aaa-PC\",\"event_data\":{\"Binary\":\"4200460045002F0034000000\",\"param1\":\"Base Filtering Engine\",\"param2\":\"正在运行\"},\"event_id\":7036,\"keywords\":[\"经典\",\"经典\",\"经典\"],\"level\":\"信息\",\"log_name\":\"System\",\"message\":\"Base Filtering Engine 服务处于 正在运行 状态。\",\"process_id\":748,\"provider_guid\":\"{555908d1-a6d7-4695-8e1e-26931d2012f4}\",\"record_number\":\"207704\",\"source_name\":\"Service Control Manager\",\"thread_id\":880,\"type\":\"wineventlog\"}";

		WMI wmi = new WMI(log);

	}



}
