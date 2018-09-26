package com.rui.n.ES.elasticsearch.utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.URLDecoder;
import java.util.Date;

import org.productivity.java.syslog4j.Syslog;
import org.productivity.java.syslog4j.SyslogIF;
import org.productivity.java.syslog4j.server.SyslogServer;
import org.productivity.java.syslog4j.server.SyslogServerConfigIF;
import org.productivity.java.syslog4j.server.SyslogServerEventHandlerIF;
import org.productivity.java.syslog4j.server.SyslogServerIF;
import org.productivity.java.syslog4j.server.impl.event.printstream.PrintStreamSyslogServerEventHandler;

public class UdpServerSocket {

	 /*public static void main(String[] args){  
	        try {  
	            //获取syslog的操作类，使用udp协议。syslog支持"udp", "tcp", "unix_syslog", "unix_socket"协议  
	            SyslogIF syslog = Syslog.getInstance("udp");   
	            //设置syslog服务器端地址  
	            syslog.getConfig().setHost("192.168.126.129");  
	            //设置syslog接收端口，默认514  
	            syslog.getConfig().setPort(514);  
	            //拼接syslog日志，这个日志是自己定义的，通常我们定义成符合公司规范的格式就行，方便查询。例如 操作时间：2014年8月1日  操作者ID:张三 等。信息就是一个字符串。  
	            StringBuffer buffer = new StringBuffer();  
	            buffer.append("操作时间：" + new Date().toString().substring(4, 20) + ";");  
	            buffer.append("操作者ID:" + "张三" + ";");  
	            buffer.append("操作时间:" + new Date()+ ";");  
	            buffer.append("日志类别:" + "22"+ ";");  
	            buffer.append("执行动作:" + "动作" + ";");  
	            buffer.append("备注:" + "备注");  
	              发送信息到服务器，2表示日志级别 范围为0~7的数字编码，表示了事件的严重程度。0最高，7最低 
	             *  syslog为每个事件赋予几个不同的优先级： 
	                LOG_EMERG：紧急情况，需要立即通知技术人员。 
	                LOG_ALERT：应该被立即改正的问题，如系统数据库被破坏，ISP连接丢失。 
	                LOG_CRIT：重要情况，如硬盘错误，备用连接丢失。 
	                LOG_ERR：错误，不是非常紧急，在一定时间内修复即可。 
	                LOG_WARNING：警告信息，不是错误，比如系统磁盘使用了85%等。 
	                LOG_NOTICE：不是错误情况，也不需要立即处理。 
	                LOG_INFO：情报信息，正常的系统消息，比如骚扰报告，带宽数据等，不需要处理。 
	                LOG_DEBUG：包含详细的开发情报的信息，通常只在调试一个程序时使用。 
	               
	            syslog.log(0, URLDecoder.decode(buffer.toString(),"utf-8"));  
	        } catch (Exception e) {  
	        }  
	    }  */
		private byte[] buffer = new byte[1024];
	    
	    private DatagramSocket ds = null;
	
	    private DatagramPacket packet = null;
	
	    private InetSocketAddress socketAddress = null;
	
	    private String orgIp;

	    /**
	     * 构造函数，绑定主机和端口.
	     * @param host 主机
	     * @param port 端口
	     * @throws Exception
	     */
	    public UdpServerSocket(String host, int port) throws Exception {
	        socketAddress = new InetSocketAddress(host, port);
	        ds = new DatagramSocket(socketAddress);
	        System.out.println("--------------service start----------------");
	    }
	    
	    public final String getOrgIp() {
	        return orgIp;
	    }

	    /**
	     * 设置超时时间，该方法必须在bind方法之后使用.
	     * @param timeout 超时时间
	     * @throws Exception
	     */
	    public final void setSoTimeout(int timeout) throws Exception {
	        ds.setSoTimeout(timeout);
	    }

	    /**
	     * 获得超时时间.
	     * @return 返回超时时间.
	     * @throws Exception
	     
	      - 下午10:34:36
	     */
	    public final int getSoTimeout() throws Exception {
	        return ds.getSoTimeout();
	    }

	    /**
	     * 绑定监听地址和端口.
	     * @param host 主机IP
	     * @param port 端口
	     * @throws SocketException
	     
	      - 下午10:36:17
	     */
	    public final void bind(String host, int port) throws SocketException {
	        socketAddress = new InetSocketAddress(host, port);
	        ds = new DatagramSocket(socketAddress);
	    }


	    /**
	     * 接收数据包，该方法会造成线程阻塞.
	     * @return 返回接收的数据串信息
	     * @throws IOException
	     
	      - 下午10:38:24
	     */
	    public final String receive() throws IOException {
	        packet = new DatagramPacket(buffer, buffer.length);
	        ds.receive(packet);
	        orgIp = packet.getAddress().getHostAddress();
	        String info = new String(packet.getData(), 0, packet.getLength());
	        System.out.println(info);
	        //System.out.println("CONTENT="+info+":SOURCE_IP="+packet.getAddress().getHostAddress()+"SOURCE_PORT:"+packet.getPort());
	        return info;
	    }

	    /**
	     * 将响应包发送给请求端.
	     * @param bytes 回应报文
	     * @throws IOException
	     
	      - 下午11:05:31
	     */
	    public final void response(String info) throws IOException {
	        System.out.println("Client IP" + packet.getAddress().getHostAddress()
	                + ",Port：" + packet.getPort());
	        DatagramPacket dp = new DatagramPacket(buffer, buffer.length, packet
	                .getAddress(), packet.getPort());
	        dp.setData(info.getBytes());
	        ds.send(dp);
	    }

	    /**
	     * 设置报文的缓冲长度.
	     * @param bufsize 缓冲长度
	     
	      - 下午10:47:49
	     */
	    public final void setLength(int bufsize) {
	        packet.setLength(bufsize);
	    }

	    /**
	     * 获得发送回应的IP地址.
	     * @return 返回回应的IP地址
	     
	      - 下午10:48:27
	     */
	    public final InetAddress getResponseAddress() {
	        return packet.getAddress();
	    }

	    /**
	     * 获得回应的主机的端口.
	     * @return 返回回应的主机的端口.
	     
	      - 下午10:48:56
	     */
	    public final int getResponsePort() {
	        return packet.getPort();
	    }

	    /**
	     * 关闭udp监听口.
	     
	      - 下午10:49:23
	     */
	    public final void close() {
	        try {
	            ds.close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }

	    /**
	     * 测试方法.
	     * @param args
	     * @throws Exception

	      - 下午10:49:50
	     */
	    public static void main(String[] args) throws Exception {
	        //这里的IP是你本机的IP也就是syslog服务器的IP
	    	//InetAddress locIP = InetAddress.getByName("192.168.0.109");
	      /*  String serverHost = "192.168.0.109";
	        int serverPort = 514;
	        UdpServerSocket udpServerSocket = new UdpServerSocket(serverHost, serverPort);
	        while (true) {
	            udpServerSocket.receive();
	        }*/
	    	
	    	SyslogServerEventHandlerIF eventHandler = new PrintStreamSyslogServerEventHandler(
					System.out);
			SyslogServerIF serverIF = SyslogServer.getInstance("udp");
			SyslogServerConfigIF config = serverIF.getConfig();
			config.setHost("192.168.0.109");
			config.setPort(514);
			config.addEventHandler(eventHandler);
			serverIF.initialize("udp",config);
			serverIF.run();
	   
	    }
}
