package com.rui.n.ES.ansj.domain;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import com.google.gson.Gson;

public class ZtsUser {
	private _links _links;
	private String chgPassFlag;
	private String datasource;
	private String email;
	private String empStatus;
	private String employeeNumber;
	private String gender;
	// private String id;
	private String jobCode;
	private String jobName;
	private String jobStatus;
	private String name;
	private String orgs;
	private List<PartOrgs> partOrgs;
	private String phoneNo;
	private List<Properties> properties;
	private String status;
	private String type;
	private String userName;
	private String workPlace;
	private String officePhone;
	private String jobGroupCode;
	private String jobGroupName;
	private String phoneShortNo;
	private String officeShortNo;
	private List<UserMaps> userMaps;
	private String uid;


	public _links get_links() {
		return _links;
	}

	public void set_links(_links _links) {
		this._links = _links;
	}

	public String getChgPassFlag() {
		return chgPassFlag;
	}

	public void setChgPassFlag(String chgPassFlag) {
		this.chgPassFlag = chgPassFlag;
	}

	public String getDatasource() {
		return datasource;
	}

	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrgs() {
		return orgs;
	}

	public void setOrgs(String orgs) {
		this.orgs = orgs;
	}

	public List<PartOrgs> getPartOrgs() {
		return partOrgs;
	}

	public void setPartOrgs(List<PartOrgs> partOrgs) {
		this.partOrgs = partOrgs;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public List<Properties> getProperties() {
		return properties;
	}

	public void setProperties(List<Properties> properties) {
		this.properties = properties;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getJobGroupCode() {
		return jobGroupCode;
	}

	public void setJobGroupCode(String jobGroupCode) {
		this.jobGroupCode = jobGroupCode;
	}

	public String getJobGroupName() {
		return jobGroupName;
	}

	public void setJobGroupName(String jobGroupName) {
		this.jobGroupName = jobGroupName;
	}

	public String getPhoneShortNo() {
		return phoneShortNo;
	}

	public void setPhoneShortNo(String phoneShortNo) {
		this.phoneShortNo = phoneShortNo;
	}

	public String getOfficeShortNo() {
		return officeShortNo;
	}

	public void setOfficeShortNo(String officeShortNo) {
		this.officeShortNo = officeShortNo;
	}

	public List<UserMaps> getUserMaps() {
		return userMaps;
	}

	public void setUserMaps(List<UserMaps> userMaps) {
		this.userMaps = userMaps;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}



	class _links {
		private Self self;

		public Self getSelf() {
			return self;
		}

		public void setSelf(Self self) {
			this.self = self;
		}

	}

	class Self {
		private String href;

		public String getHref() {
			return href;
		}

		public void setHref(String href) {
			this.href = href;
		}

	}

	class PartOrgs {

	}

	class Properties {
		private String employeeNumber;
		private String id;
		private String userId;
		private String userKey;
		private String userValue;

		public String getEmployeeNumber() {
			return employeeNumber;
		}

		public void setEmployeeNumber(String employeeNumber) {
			this.employeeNumber = employeeNumber;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getUserKey() {
			return userKey;
		}

		public void setUserKey(String userKey) {
			this.userKey = userKey;
		}

		public String getUserValue() {
			return userValue;
		}

		public void setUserValue(String userValue) {
			this.userValue = userValue;
		}

	}

	class UserMaps {
		private String appCode;
		private String convertId;
		private String employeeNumber;
		private String id;
		private String status;
		private String userId;

		public String getAppCode() {
			return appCode;
		}

		public void setAppCode(String appCode) {
			this.appCode = appCode;
		}

		public String getConvertId() {
			return convertId;
		}

		public void setConvertId(String convertId) {
			this.convertId = convertId;
		}

		public String getEmployeeNumber() {
			return employeeNumber;
		}

		public void setEmployeeNumber(String employeeNumber) {
			this.employeeNumber = employeeNumber;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

	}

	public ZtsUser() {

	}

	public ZtsUser(String log) {
		
		Gson gson = new Gson();
		ZtsUser ztsUser = gson.fromJson(log, ZtsUser.class);

	}

	public String toMapping() {
		String template = "{\n" + "\t\t\"properties\":{\n" + "\t\t{#}\n" + "\t\t\t\t}" + "}";
		String fieldString = getClassMapping(new ZtsUser());
		template = template.replace("{#}", fieldString);
		return template;
	}

	public <T> String getClassMapping(T classes) {

		StringBuilder fieldstring = new StringBuilder();

		String[] fielddata = { "userid", "deptid", "equipmentid", "logtime", "ip", "hostname", "uid", "jobCode",
				"employeeNumber", "logtime_year", "logtime_month", "logtime_day", "logtime_hour", "logtime_minute",
				"equipmentname" };
		Field[] fields = classes.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fieldstring.append("\t\t\t\t\"" + fields[i].getName().toLowerCase() + "\": {\n");
			fieldstring.append("\t\t\t\t\t\t\"type\": \""
					+ getElasticSearchMappingType(fields[i].getType().getSimpleName(), fields[i].getName()) + "\n");
			if (fields[i].getName().equals("id")) {
				fieldstring.append("\t\t\t\t\t\t,\"index\": \"" + "false\"" + "\n");
			}
			if (Arrays.asList(fielddata).contains(fields[i].getName())) {
				fieldstring.append("\t\t\t\t\t\t,\"fielddata\": " + "true" + "\n");
			}
			if (fields[i].getName().equals("operation_des") || fields[i].getName().equals("ip")
					|| fields[i].getName().equals("employeeNumber") || fields[i].getName().equals("hostname")
					|| fields[i].getName().equals("equipmentname") || fields[i].getName().equals("uId")
					|| fields[i].getName().equals("userName")) {
				fieldstring.append("\t\t\t\t\t\t,\"analyzer\": \"" + "index_ansj\"" + "\n");
				fieldstring.append("\t\t\t\t\t\t,\"search_analyzer\": \"" + "query_ansj\"" + "\n");
			}

			if (i == fields.length - 1) {
				fieldstring.append("\t\t\t\t\t}\n");
			} else {
				fieldstring.append("\t\t\t\t\t},\n");
			}
		}
		return fieldstring.toString();
	}

	private static String getElasticSearchMappingType(String varType, String name) {
		String es = "text";
		switch (varType) {
		case "Date":
			es = "date\"\n" + "\t\t\t\t\t\t,\"format\":\"yyyy-MM-dd HH:mm:ss\"\n" + "\t\t\t\t\t\t";
			break;
		case "Double":
			es = "double\"\n" + "\t\t\t\t\t\t,\"null_value\":\"NaN\"";
			break;
		case "Long":
			es = "long\"";
			break;
		case "Integer":
			es = "integer\"";
			break;
		case "Boolean":
			es = "boolean\"";
			break;
		default:
			if (name.equals("id")) {
				es = "keyword\"";
			} else {
				es = "text\"";
			}

			break;
		}
		return es;
	}

	public static void main(String[] args) {
		//String log = "{"method":"getJSON","ip":"10.29.172.28","thread":"ForkJoinPool-1-worker-0","message":{\"userName\":\"wangyx03\",\"name\":\"ÍõâùÑ©\",\"email\":\"wangyx03@zts.com.cn\",\"phoneNo\":\"13954302163\",\"officePhone\":null,\"gender\":\"2\",\"status\":\"1\",\"chgPassFlag\":\"1\",\"orgs\":\"D3774:25\",\"employeeNumber\":\"91F1823868",\"userMaps\":[{\"id\":90250,\"userId\":17259,\"convertId\":\"91F1823868\",\"appCode\":\"M0001\",\"status\":\"1\",\"employeeNumber\":\"91F1823868\"},{\"id\":91126,\"userId\":17259,\"convertId\":\"wangyx03\",\"appCode\":\"M0025\",\"status\":\"1\",\"employeeNumber\":\"91F1823868\"},{\"id\":91127,\"userId\":17259,\"convertId\":\"wangyx03\",\"appCode\":\"M0028\",\"status\":\"1\",\"employeeNumber\":\"91F1823868\"}],\"type\":1,\"jobCode\":\"J14146\",\"jobName\":\"ÏîÄ¿³ÐÀ¿³Ð×ö¸Ú\",\"jobStatus\":\"2\",\"jobGroupCode\":null,\"jobGroupName\":null,\"empStatus\":\"1\",\"workPlace\":\"±±¾©\",\"phoneShortNo\":null,\"officeShortNo\":null,\"datasource\":\"HR\",\"partOrgs\":[],\"properties\":[{\"id\":339094,\"userId\":17259,\"userKey\":\"emptype\",\"userValue\":\"1\",\"employeeNumber\":\"91F1823868\"},{\"id\":339096,\"userId\":17259,\"userKey\":\"contract\",\"userValue\":\"ÊÇ\",\"employeeNumber\":\"91F1823868\"},{\"id\":339092,\"userId\":17259,\"userKey\":\"eid\",\"userValue\":\"19804\",\"employeeNumber\":\"91F1823868\"}],\"_links\":{\"self\":{\"href\":\"http://10.29.181.202:30080/v1/users/17259\"}},\"id\":17259},"priority":"DEBUG","type":"log4j","tags":[],"path":"com.foperate.oidc.op.util.RestClientBase","@timestamp":"2018-08-23T02:24:48.663Z","file":"RestClientBase.java:108","@version":"1","host":"10.29.172.28:38980","logger_name":"com.foperate.oidc.op.util.RestClientBase","class":"com.foperate.oidc.op.util.RestClientBase","timestamp":1534991087323}";
//		String log = \"{\"userName\":\"wangyx03\",\"name\":\"王怡雪\",\"email\":\"wangyx03@zts.com.cn\",\"phoneNo\":\"13954302163\",\"officePhone\":null,\"gender\":\"2\",\"status\":\"1\",\"chgPassFlag\":\"1\",\"orgs\":\"D3774:25\",\"employeeNumber\":\"91F1823868\",\"userMaps\":[{\"id\":90250,\"userId\":17259,\"convertId\":\"91F1823868\",\"appCode\":\"M0001\",\"status\":\"1\",\"employeeNumber\":\"91F1823868\"},{\"id\":91126,\"userId\":17259,\"convertId\":\"wangyx03\",\"appCode\":\"M0025\",\"status\":\"1\",\"employeeNumber\":\"91F1823868\"},{\"id\":91127,\"userId\":17259,\"convertId\":\"wangyx03\",\"appCode\":\"M0028\",\"status\":\"1\",\"employeeNumber\":\"91F1823868\"}],\"type\":1,\"jobCode\":\"J14146\",\"jobName\":\"项目承揽承做岗\",\"jobStatus\":\"2\",\"jobGroupCode\":null,\"jobGroupName\":null,\"empStatus\":\"1\",\"workPlace\":\"北京\",\"phoneShortNo\":null,\"officeShortNo\":null,\"datasource\":\"HR\",\"partOrgs\":[],\"properties\":[{\"id\":339094,\"userId\":17259,\"userKey\":\"emptype\",\"userValue\":\"1\",\"employeeNumber\":\"91F1823868\"},{\"id\":339096,\"userId\":17259,\"userKey\":\"contract\",\"userValue\":\"是\",\"employeeNumber\":\"91F1823868\"},{\"id\":339092,\"userId\":17259,\"userKey\":\"eid\",\"userValue\":\"19804\",\"employeeNumber\":\"91F1823868\"}],\"_links\":{\"self\":{\"href\":\"http://10.29.181.202:30080/v1/users/17259\"}},\"id\":17259}";
		String log="{\"method\":\"getJSON\",\"ip\":\"10.29.172.28\",\"thread\":\"ForkJoinPool-1-worker-0\",\"message\":11223344,\"priority\":\"DEBUG\",\"type\":\"log4j\",\"tags\":[],\"path\":\"com.foperate.oidc.op.util.RestClientBase\",\"@timestamp\":\"2018-08-23T02:24:48.663Z\",\"file\":\"RestClientBase.java:108\",\"@version\":\"1\",\"host\":\"10.29.172.28:38980\",\"logger_name\":\"com.foperate.oidc.op.util.RestClientBase\",\"class\":\"com.foperate.oidc.op.util.RestClientBase\",\"timestamp\":1534991087323}";
		
//		log =log.replace("11223344", "{\\\"userName\\\":\"wangyx03\",\"name\":\"王怡雪\",\"email\":\"wangyx03@zts.com.cn\",\"phoneNo\":\"13954302163\",\"officePhone\":null,\"gender\":\"2\",\"status\":\"1\",\"chgPassFlag\":\"1\",\"orgs\":\"D3774:25\",\"employeeNumber\":\"91F1823868\",\"userMaps\":[{\"id\":90250,\"userId\":17259,\"convertId\":\"91F1823868\",\"appCode\":\"M0001\",\"status\":\"1\",\"employeeNumber\":\"91F1823868\"},{\"id\":91126,\"userId\":17259,\"convertId\":\"wangyx03\",\"appCode\":\"M0025\",\"status\":\"1\",\"employeeNumber\":\"91F1823868\"},{\"id\":91127,\"userId\":17259,\"convertId\":\"wangyx03\",\"appCode\":\"M0028\",\"status\":\"1\",\"employeeNumber\":\"91F1823868\"}],\"type\":1,\"jobCode\":\"J14146\",\"jobName\":\"项目承揽承做岗\",\"jobStatus\":\"2\",\"jobGroupCode\":null,\"jobGroupName\":null,\"empStatus\":\"1\",\"workPlace\":\"北京\",\"phoneShortNo\":null,\"officeShortNo\":null,\"datasource\":\"HR\",\"partOrgs\":[],\"properties\":[{\"id\":339094,\"userId\":17259,\"userKey\":\"emptype\",\"userValue\":\"1\",\"employeeNumber\":\"91F1823868\"},{\"id\":339096,\"userId\":17259,\"userKey\":\"contract\",\"userValue\":\"是\",\"employeeNumber\":\"91F1823868\"},{\"id\":339092,\"userId\":17259,\"userKey\":\"eid\",\"userValue\":\"19804\",\"employeeNumber\":\"91F1823868\"}],\"_links\":{\"self\":{\"href\":\"http://10.29.181.202:30080/v1/users/17259\"}},\"id\":17259}");
		log=log.replace("11223344", "{\\\"userName\\\":\\\"wangyx03\\\",\\\"name\\\":\\\"王怡雪\\\",\\\"email\\\":\\\"wangyx03@zts.com.cn\\\",\\\"phoneNo\\\":\\\"13954302163\\\",\\\"officePhone\\\":null,\\\"gender\\\":\\\"2\\\",\\\"status\\\":\\\"1\\\",\\\"chgPassFlag\\\":\\\"1\\\",\\\"orgs\\\":\\\"D3774:25\\\",\\\"employeeNumber\\\":\\\"91F1823868\",\\\"userMaps\\\":[{\\\"id\\\":90250,\\\"userId\\\":17259,\\\"convertId\\\":\\\"91F1823868\\\",\\\"appCode\\\":\\\"M0001\\\",\\\"status\\\":\\\"1\\\",\\\"employeeNumber\\\":\\\"91F1823868\\\"},{\\\"id\\\":91126,\\\"userId\\\":17259,\\\"convertId\\\":\\\"wangyx03\\\",\\\"appCode\\\":\\\"M0025\\\",\\\"status\\\":\\\"1\\\",\\\"employeeNumber\\\":\\\"91F1823868\\\"},{\\\"id\\\":91127,\\\"userId\\\":17259,\\\"convertId\\\":\\\"wangyx03\\\",\\\"appCode\\\":\\\"M0028\\\",\\\"status\\\":\\\"1\\\",\\\"employeeNumber\\\":\\\"91F1823868\\\"}],\\\"type\\\":1,\\\"jobCode\\\":\\\"J14146\\\",\\\"jobName\\\":\\\"项目承揽承做岗\\\",\\\"jobStatus\\\":\\\"2\\\",\\\"jobGroupCode\\\":null,\\\"jobGroupName\\\":null,\\\"empStatus\\\":\\\"1\\\",\\\"workPlace\\\":\\\"北京\\\",\\\"phoneShortNo\\\":null,\\\"officeShortNo\\\":null,\\\"datasource\\\":\\\"HR\\\",\\\"partOrgs\\\":[],\\\"properties\\\":[{\\\"id\\\":339094,\\\"userId\\\":17259,\\\"userKey\\\":\\\"emptype\\\",\\\"userValue\\\":\\\"1\\\",\\\"employeeNumber\\\":\\\"91F1823868\\\"},{\\\"id\\\":339096,\\\"userId\\\":17259,\\\"userKey\\\":\\\"contract\\\",\\\"userValue\\\":\\\"是\\\",\\\"employeeNumber\\\":\\\"91F1823868\\\"},{\\\"id\\\":339092,\\\"userId\\\":17259,\\\"userKey\\\":\\\"eid\\\",\\\"userValue\\\":\\\"19804\\\",\\\"employeeNumber\\\":\\\"91F1823868\\\"}],\\\"_links\\\":{\\\"self\\\":{\\\"href\\\":\\\"http://10.29.181.202:30080/v1/users/17259\\\"}},\\\"id\\\":17259}");

		Gson gson = new Gson();
		ZtsUser ztsUser = gson.fromJson(log, ZtsUser.class);
	}

}
