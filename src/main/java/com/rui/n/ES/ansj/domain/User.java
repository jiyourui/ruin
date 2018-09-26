package com.rui.n.ES.ansj.domain;

import java.lang.reflect.Field;

public class User {
	
	private String id;
	
	private String name;
	
	private String password;
	
	private Integer age;
	
	private Integer userid;
	
	private Integer deptid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getDeptid() {
		return deptid;
	}

	public void setDeptid(Integer deptid) {
		this.deptid = deptid;
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public String ToMapping() {
		 String template = "{\n" 
                  +"\t\"properties\":{\n"
                          + "{#}\n" 
                  + "\t}\n"
              +"}";
		String fieldString =  getClassMapping(new User());
		template = template.replace("{#}",fieldString);
		return template;
	}

	public <T> String getClassMapping(T classes) {
		
		StringBuilder fieldstring = new StringBuilder();
		
       Field[] fields = classes.getClass().getDeclaredFields();
       for (int i = 0; i < fields.length; i++) {
            fieldstring.append("\t\t\"" + fields[i].getName().toLowerCase() + "\": {\n");
            fieldstring.append("\t\t\t\"type\": \""
                       + GetElasticSearchMappingType(fields[i].getType().getSimpleName(),fields[i].getName()));
            if (fields[i].getName().equals("id")) {
           	 fieldstring.append(",\"index\": \""
                        + "false\"" + "\n");
			}
            if (!fields[i].getName().equals("operation_des")&&!fields[i].getName().equals("id")&&!fields[i].getType().getSimpleName().equals("Integer")) {
				fieldstring.append(",\"fielddata\": "
                       + "true" + "\n");
			}
            if (fields[i].getName().equals("operation_des")||fields[i].getName().equals("ip")) {
           	 fieldstring.append(",\"analyzer\": \""
           	 + "index_ansj\"" + "\n");
           	 fieldstring.append(",\"search_analyzer\": \""
           	 + "query_ansj\"" + "\n");
			}
            if (i == fields.length-1) {
                   fieldstring.append("\t\t\t}\n");
               } else {
                   fieldstring.append("\t\t\t},\n");
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
        case "Integer":
            es = "integer\"";
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
	
	public static void main(String [] agrs) {
		System.out.println(new User().ToMapping());
	}

}
