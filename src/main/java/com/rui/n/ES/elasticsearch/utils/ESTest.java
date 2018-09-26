package com.rui.n.ES.elasticsearch.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class ESTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ESTest esTest = new ESTest();
		
		esTest.deleteByIndex("estest2");
		
		esTest.closeclient();

	}

	private TransportClient client;
	
	
	/**
	 * 连接默认集群
	 */
	@SuppressWarnings("resource")
	public ESTest() {
		Settings settings = Settings.builder()
				// 设置es集群名称
		        //.put("cluster.name", "ruincluster").build();
				.put("cluster.name", "myElasticsearchCluster").build();
		        // 设置监听
				//.put("client.transport.sniff", true)
		try {
			/*client = new PreBuiltTransportClient(settings)
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("10.4.2.5"), 9300));*/
			client = new PreBuiltTransportClient(settings)
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("124.133.246.61"), 9300));
			/*client = new PreBuiltTransportClient(settings)
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.0.116"), 9300));*/
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @param clustername
	 * @param ip
	 * @author jiyourui
	 * 自定义连接ES集群
	 */
	public ESTest(String clustername,String ip) {
		Settings settings = Settings.builder()
				// 设置es集群名称
		        .put("cluster.name", clustername)
		        // 设置监听
				.put("client.transport.sniff", true).build();
		try {
			client = new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ip), 9300));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean deleteByIndex(String index) {
		boolean result = false;
		DeleteIndexResponse dResponse = client.admin().indices().prepareDelete(index)
                .execute().actionGet();
		result = dResponse.isAcknowledged();
		
		return result;
	}
	
	public void closeclient() {
		client.close();
	}
}
