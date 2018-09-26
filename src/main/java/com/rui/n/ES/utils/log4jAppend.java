package com.rui.n.ES.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rui.n.ES.ansj.domain.Log4j;

public class log4jAppend {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String log0 = "<155>  18:42:12  192.168.4.148 192.168.4.148 local3: 2017-10-26 11:17:58,012 [INFO]  [##test 你1508987878012]  [java.lang.Class] main()[10] [8009]";
		String log1 = "<155>  18:42:12  192.168.4.148 192.168.4.148 local3:  org.eclipse.wst.server.c The error may involve com.jz.bigdata.common.user.dao.IUserDao.selectByNamePwd#015#012### The error occurred while executing a query#015#012### Cause: org.springframework.jdbc.CannotGetJdbcConnectionException: Could not get JDBC Connection; nested exception is org.apache.commons.dbcp.SQLNestedException: Cannot create PoolableConnectionFactory (Communications link failure#012#012The last packet sent successfully to the server was 0 milliseconds ago. The driver has not received any packets from the server.)#015#012";  
		String log2 = "<155>  18:42:12  192.168.4.148 192.168.4.148 local3:  ### The error occurred while executing a query";
		String log3 = "<155>  18:42:12  192.168.4.148 192.168.4.148 local3:      at org.apache.ibatis.binding.MapperMethod.executeForMany(MapperMethod.java:119)";
		String log4 = "<155>  18:42:12  192.168.4.148 192.168.4.148 local3:      at org.apache.ibatis.binding.MapperMethod.execute(MapperMethod.java:63)";
		String log5 = "<155>  18:42:12  192.168.4.148 192.168.4.148 local3: ### The error occurred while executing a query";
		String log6 = "<155>  18:42:12  192.168.4.148 192.168.4.148 local3:     at org.apache.ibatis.executor.BaseExecutor.getConnection(BaseExecutor.java:279)";
		String log7 = "<155>  18:42:12  192.168.4.148 192.168.4.148 local3:     at org.apache.ibatis.executor.SimpleExecutor.prepareStatement(SimpleExecutor.java:72)";
		String log8 = "<155>  18:42:12  192.168.4.148 192.168.4.148 local3:     at org.apache.ibatis.executor.SimpleExecutor.doQuery(SimpleExecutor.java:59)";
		String log9 = "<155>  18:42:12  192.168.4.148 192.168.4.148 local3:     at org.apache.ibatis.executor.BaseExecutor.queryFromDatabase(BaseExecutor.java:267)";
		String log10 ="<155>  18:42:12  192.168.4.148 192.168.4.148 local3:     at org.apache.ibatis.executor.BaseExecutor.query(BaseExecutor.java:137)";
		String log11 ="<155>  18:42:12  192.168.4.148 192.168.4.148 local3:     at org.apache.ibatis.executor.CachingExecutor.query(CachingExecutor.java:96)";
		String log12 ="<155>  18:42:12  192.168.4.148 192.168.4.148 local3:     at org.apache.ibatis.executor.CachingExecutor.query(CachingExecutor.java:77)";
		String log13 = "<155>  18:42:12  192.168.4.148 192.168.4.148 local3: 2017-10-26 11:17:58,012 [INFO]  [##test 你1508987878012]  [java.lang.Class] main()[10] [8009]";
		StringBuilder builder = new StringBuilder();
		Pattern pattern = Pattern.compile("\\s?\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2},\\d{3}");
		Pattern facility_pattern = Pattern.compile("local3:");
		
		String [] aa = {log0,log1,log2,log3,log4,log13};
		for(String log:aa){
//				修饰一个代码块，被修饰的代码块称为同步语句块，其作用的范围是大括号{}括起来的代码，作用的对象是调用这个代码块的对象；
				
				synchronized (log) {
					//multiline setting start
					Matcher m = pattern.matcher(log);
					//判断是否符合正则表达式 如果符合，表明这是一条开始数据
					if(m.find()) {
						//添加builder
						if (builder.length()!=0) {
							//进入范式化
							Log4j log4j = new Log4j(builder.toString());
							//清空数据
							builder.delete(0, builder.length());
						}
						builder.append(log);
					}else {
						Matcher facility_matcher = facility_pattern.matcher(log);
						if (facility_matcher.find()) {
							String logleft = log.substring(0, log.indexOf(facility_matcher.group(0))+facility_matcher.group(0).length());
							log = log.replace(logleft, "");
						}
//						添加数据（替换成换行符）
						builder.append(log);
					}
					//multiline setting end
				}
		}
		
		
	
	}

}
