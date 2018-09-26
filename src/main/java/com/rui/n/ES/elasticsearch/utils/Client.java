package com.rui.n.ES.elasticsearch.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.core.util.Loader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.bulk.byscroll.BulkByScrollResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MoreLikeThisQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.reindex.DeleteByQueryAction;

import org.elasticsearch.index.reindex.DeleteByQueryRequestBuilder;
import org.elasticsearch.index.reindex.UpdateByQueryAction;
import org.elasticsearch.index.reindex.UpdateByQueryRequestBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rui.n.ES.ansj.domain.Log4j;
import com.rui.n.ES.ansj.domain.Syslog;
import com.rui.n.ES.ansj.domain.User;
import com.rui.n.ES.ansj.domain.WMI;
import com.rui.n.ES.ansj.domain.Winlog;

import joptsimple.internal.Strings;

public class Client {

	private static Logger logger = Logger.getLogger(Client.class.getName());

	private TransportClient client;
	
	private SortOrder sortOrder;
	
	static String [] types = {"log4j","syslog","winlog"};
	
	/**
	 * 连接默认集群
	 */
	@SuppressWarnings("resource")
	public Client() {
		Settings settings = Settings.builder()
				// 设置es集群名称
				.put("cluster.name", "myElasticsearchCluster")
				.put("xpack.security.transport.ssl.enabled", false)
				// 在安装x-pack之后需要增加用户和密码验证
				.put("xpack.security.user","elastic:hsdata.321")
				// 设置监听,自动嗅探
				//.put("client.transport.sniff", true)
				.build();
		        
		try {
			// 普通的transport连接，没有增加安全管理认证
			/*client = new PreBuiltTransportClient(settings)
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.100.114"), 9300));*/
			// 在es安装插件x-pack之后，使用x-pack的transport连接
			client = new PreBuiltXPackTransportClient(settings)
					//.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.100.114"), 9300));
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("40.125.210.39"), 9300));
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
	@SuppressWarnings("resource")
	public Client(String clustername,String ip) {
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
	
	
	/**
	 * 查看链接信息
	 */
	public void catESinfo() {
		
		List<DiscoveryNode> list = client.connectedNodes();
		for (DiscoveryNode node : list) {
	        System.out.println(node);
	    }
	}
	
	/**
	 * @param index
	 * @return
	 */
	public boolean createIndex(String index) {
		// TODO Auto-generated method stub
		boolean result = false;
		
		CreateIndexResponse indexResponse = client.admin()
				.indices()
				.prepareCreate(index)
				.get();
		
		result = indexResponse.isAcknowledged();
		return result;
	}
	
	
	/**
	 * @param index
	 * @return
	 * 判断index是否创建
	 */
	public boolean indexExists(String index) {
		boolean result = false;
		
		IndicesExistsRequest request = new IndicesExistsRequest(index);
		IndicesExistsResponse response = client.admin().indices().exists(request).actionGet();
		
		result = response.isExists();
		
		return result;
	}
	
	/**
	 * @param index
	 * @param type
	 * @param classes
	 * 配置mapping
	 */
	public <T> Boolean createMapping(String index, String type,String mappingproperties) {
		// TODO Auto-generated method stub
		 String template = mappingproperties;
		System.out.println(template);
		
		boolean result = false;
		if (this.indexExists(index)) {
			PutMappingRequest mapping = Requests.putMappingRequest(index).type(type).source(template);
			PutMappingResponse mappingResponse =this.client.admin().indices().putMapping(mapping).actionGet();
			result = mappingResponse.isAcknowledged();
		}else {
			CreateIndexResponse indexResponse = this.client.admin().indices().prepareCreate(index).addMapping(type, template).get();
			result = indexResponse.isAcknowledged();
		}
		
		if (result) {
			System.out.println("-------------------创建mapping成功------------------");
		}
		
		return result;
	}
	
	/**
	 * @param index
	 * @param type
	 * @param classes
	 * 配置mapping
	 */
	public <T> Boolean addMapping(String index, String type,T classes) {
		// TODO Auto-generated method stub
		 String template = "{\n" 
                     +"\t\t\"properties\":{\n"
                             + "\t\t{#}\n" 
                     + "\t\t\t\t}"
                 +"}";
		String fieldString =  getClassMapping(classes);
		template = template.replace("{#}",fieldString);
		System.out.println(template);
		
		boolean result = false;
		if (this.indexExists(index)) {
			PutMappingRequest mapping = Requests.putMappingRequest(index).type(type).source(template);
			PutMappingResponse mappingResponse =this.client.admin().indices().putMapping(mapping).actionGet();
			result = mappingResponse.isAcknowledged();
		}else {
			CreateIndexResponse indexResponse = this.client.admin().indices().prepareCreate(index).addMapping(type, template).get();
			result = indexResponse.isAcknowledged();
		}
		
		if (result) {
			System.out.println("-------------------创建mapping成功------------------");
		}
		
		return result;
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
             if (!fields[i].getName().equals("operation_des")&&!fields[i].getType().getSimpleName().equals("Date")&&!fields[i].getName().equals("id")) {
				fieldstring.append("\t\t\t\t\t\t,\"fielddata\": "
                        + "true" + "\n");
			}
             if (fields[i].getName().contains("_des")) {
            	 fieldstring.append("\t\t\t\t\t\t,\"analyzer\": \""
            			 //+ "english\"" + "\n");
            	 + "index_ansj\"" + "\n");
            	 fieldstring.append("\t\t\t\t\t\t,\"search_analyzer\": \""
            			 //  + "english\"" + "\n");
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
	
	/**
	 * @param index
	 * @param type
	 * @param json
	 */
	@SuppressWarnings("deprecation")
	public void insert(String index,String type,String json) {
		
		IndexRequestBuilder response = client.prepareIndex(index, type);
		response.setSource(json);
		response.execute().actionGet();
	}
	
	/**
	 * @param index
	 * @param type
	 * @param id
	 * 根据index，type，id删除索引
	 */
	public boolean deleteById(String index, String type, String id) {
		// TODO Auto-generated method stub
		boolean result = false;
		
		DeleteResponse response = client.prepareDelete(index, type, id)
		        .get();
		
		result = response.isFragment();
		return result;
	}
	
	public void deleteByType(String index, String type) {
		// TODO Auto-generated method stub
		String deletebyquery = "{\"query\": {\"match_all\": {}}}";

		/*DeleteByQueryResponse response =  new DeleteByQueryRequestBuilder(client,   
		                                  DeleteByQueryAction.INSTANCE)
		                                  .setIndices("blog")
		                                  .setTypes("article")
		                                  .setSource(deletebyquery)
		                                  .execute()
		                                  .actionGet();*/ 
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
	
	public long count(String index, String[] type,QueryBuilder queryBuilder) {
		// TODO Auto-generated method stub
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);
		if (type!=null&&type.length>1) {
			searchRequestBuilder.setTypes(type);
		}
		if (queryBuilder!=null) {
			searchRequestBuilder.setQuery(queryBuilder);
		}
    	SearchResponse response = searchRequestBuilder.get();
    	long length = response.getHits().getTotalHits();
		
		return length;
	}
	
	/**
	 * @param index
	 * @param type
	 * @param param
	 * @return 
	 */
	public List<Map<String, Object>> countGroupBy(String index, String type,String param) {
		// TODO Auto-generated method stub
		// 拼接查询条件
		SearchRequestBuilder sBuilder = client.prepareSearch(index).setTypes(type);
		
		String count = param+"_count";
		// 聚合查询group by
		TermsAggregationBuilder termsQueryBuilder = AggregationBuilders.terms(count).field(param);
		
		sBuilder.addAggregation(termsQueryBuilder);
		
    	SearchResponse response = sBuilder.execute().actionGet();
		
    	Aggregations aggregations = response.getAggregations();
    	
    	Terms types  = aggregations.get(count);
    	
    	List<Map<String, Object>> list = new LinkedList<Map<String,Object>>();
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	for(Bucket bucket:types.getBuckets()) {
    		System.out.println(bucket);
    		map.put(bucket.getKeyAsString(), bucket.getDocCount());
    	}
    	
    	list.add(map);

		return list;
	}
	
	public List<Map<String, Object>> countGroupBytime(String index, String[] type,String param) {
		// TODO Auto-generated method stub
		// 拼接查询条件
		SearchRequestBuilder sBuilder = client.prepareSearch(index);
		if (type!=null&&type.length>0) {
			sBuilder.setTypes(type);
		}
		//sBuilder.setQuery(QueryBuilders.matchPhraseQuery("ip", "storm"));
		String [] date = param.split("-");
		sBuilder.setQuery(QueryBuilders.matchPhraseQuery("logtime_year", date[0]));
		sBuilder.setQuery(QueryBuilders.matchPhraseQuery("logtime_month", date[1]));
		sBuilder.setQuery(QueryBuilders.matchPhraseQuery("logtime_day", date[2]));
		
		String count = "logtime_hour"+"_count";
		// 聚合查询group by
		AggregationBuilder  termsQueryBuilder = AggregationBuilders.terms(count).field("logtime_hour");
		
		sBuilder.addAggregation(termsQueryBuilder);
		
    	SearchResponse response = sBuilder.execute().actionGet();
		
    	Aggregations aggregations = response.getAggregations();
    	
    	Terms types  = aggregations.get(count);
    	
    	List<Map<String, Object>> list = new LinkedList<Map<String,Object>>();
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	for(Bucket bucket:types.getBuckets()) {
    		map.put(bucket.getKeyAsString(), bucket.getDocCount());
    	}
    	
    	list.add(map);

		return list;
	}
	
	/**
	 * @param index
	 * @param type
	 * @return
	 */
	public List<Map<String, Object>> getlist(String index, String type) {
		// TODO Auto-generated method stub
    	SearchResponse searchResponse = client.prepareSearch(index)
    			.setTypes(type)
    			//.addSort("logtime", SortOrder.ASC)
    			.get();
    	
    	SearchHits hits = searchResponse.getHits();
    	
    	SearchHit [] searchHits = hits.getHits();
    	
    	List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
    	if (searchHits.length>0) {
			for(SearchHit hit : searchHits) {
				Map<String, Object> map = hit.getSource();
				map.put("index", hit.getIndex());
				map.put("type", hit.getType());
				map.put("id", hit.getId());
				
				list.add(map);
			}
		}
		
		return list;
	}
	
	
	/**
	 * @param index
	 * @param type
	 * @param param
	 * @param order
	 * @return
	 * TODO 通过某个字段进行排序
	 */
	public List<Map<String, Object>> getListOrderByParam(String index, String type,String param,String order) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		
		if (param.isEmpty()) {
			return list;
		}
		if (order == null) {
			sortOrder = SortOrder.ASC;
		}else if (order.isEmpty()||order.equals("asc")) {
			sortOrder = SortOrder.ASC;
		}else if (order.equals("desc")) {
			sortOrder = SortOrder.DESC;
		}
		
    	SearchResponse searchResponse = client.prepareSearch(index)
    			.setTypes(type)
    			.addSort(param, sortOrder)
    			.get();
    	
    	SearchHits hits = searchResponse.getHits();
    	
    	SearchHit [] searchHits = hits.getHits();
    	
    	
    	if (searchHits.length>0) {
			for(SearchHit hit : searchHits) {
				Map<String, Object> map = hit.getSource();
				map.put("index", hit.getIndex());
				map.put("type", hit.getType());
				map.put("id", hit.getId());
				
				list.add(map);
			}
		}
		
		return list;
	}
	
	public List<Map<String, Object>> getLogListByParam(String index, String type,String param,String order,String equipmentId) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		
		if (param.isEmpty()) {
			return list;
		}
		if (order == null) {
			sortOrder = SortOrder.ASC;
		}else if (order.isEmpty()||order.equals("asc")) {
			sortOrder = SortOrder.ASC;
		}else if (order.equals("desc")) {
			sortOrder = SortOrder.DESC;
		}
		
    	SearchResponse searchResponse = this.client.prepareSearch(index)
    			.setTypes(type)
    			.setQuery(QueryBuilders.matchQuery("equipmentid", equipmentId))
    			.addSort(param, sortOrder)
    			.setSize(100)
    			.get();
    	
    	SearchHits hits = searchResponse.getHits();
    	SearchHit [] searchHits = hits.getHits();
    	
    	if (searchHits.length>0) {
			for(SearchHit hit : searchHits) {
				Map<String, Object> map = hit.getSource();
				map.put("index", hit.getIndex());
				map.put("type", hit.getType());
				map.put("id", hit.getId());
				
				list.add(map);
			}
		}
		
		return list;
	}
	
	
	/**
	 * TODO 批量创建索引
	 * @param requests
	 */
	public void bulk(List<IndexRequest> requests) {
		BulkRequestBuilder bulkRequest = client.prepareBulk();

		for (IndexRequest request : requests) {  
	        bulkRequest.add(request);  
	    }  

		BulkResponse bulkResponse = bulkRequest.get();
		if (bulkResponse.hasFailures()) {  
	        System.out.println("批量创建索引错误！");  
	    } 
	}
	
	/**
	 * TODO 通过id查询
	 * @param index
	 * @param type
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>>  getListById(String index,String type,String id) {
		
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		GetResponse response = client.prepareGet(index, type, id)
                .setOperationThreaded(false)    // 线程安全
                .get();
		Map<String,Object> map = response.getSource();
		map.put("index", response.getIndex());
		map.put("type", response.getType());
		map.put("id", response.getId());
		System.out.println(map);
		list.add(map);
		return list;
	}
	
	/**
	 * @param index
	 * @param types
	 * @param content
	 * @return
	 * 通过正文内容进行查询
	 */
	public List<Map<String, Object>> getListByContentTest(String index,String[] types,String content) {
		
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);
		searchRequestBuilder.setTypes(types);
		searchRequestBuilder.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
		QueryBuilder queryBuilder = null;
		if (content==null||content.equals("")) {
			queryBuilder = QueryBuilders.matchAllQuery();
		}else {
			// 查询效果一般  (trem主要是查询分词库，如果没有建立分词的话是查不到的，而且分词会将字母统一设置为小写)
			//queryBuilder = QueryBuilders.termQuery("operation_des", content);
			
			// "单个匹配"  match查询先将查询的内容进行分词后再去分词库做查询
			queryBuilder = QueryBuilders.matchQuery("operation_des", content);
			
			// 模糊查询的效果和match差不多
			/*Term term = new Term("operation_des", content);
			fuzzyQuery = new FuzzyQuery(term);*/
			//queryBuilder = QueryBuilders.fuzzyQuery("operation_des", content);
			//queryBuilder = QueryBuilders.queryStringQuery(content).field("operation_des");
			//queryBuilder =  QueryBuilders.multiMatchQuery(content,"operation_des");
		}
		//
		//QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(new QueryStringQueryBuilder(content).field("operation_des"));
		// 方法过期
		/*MoreLikeThisQueryBuilder queryBuilder = QueryBuilders.moreLikeThisQuery();
		queryBuilder.boost(1.0f).likeText("测试").minTermFreq(10);  */
		
		searchRequestBuilder.setQuery(queryBuilder);
		//searchRequestBuilder.setQuery((QueryBuilder) fuzzyQuery);
		searchRequestBuilder.setExplain(true);
		searchRequestBuilder.setSize(100);
		
		
		SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
		
		SearchHit [] searchHits = searchResponse.getHits().getHits();
		if (searchHits.length<1) {
			content = "*"+content.toLowerCase()+"*";
			System.out.println("--------------  "+content);
			queryBuilder = QueryBuilders.wildcardQuery("operation_des", content);
			searchRequestBuilder.setQuery(queryBuilder);
			searchResponse = this.search(searchRequestBuilder);
			searchHits = searchResponse.getHits().getHits();
		}
		for(SearchHit hit : searchHits) {
			Map<String, Object> map = hit.getSource();
			map.put("index", hit.getIndex());
			map.put("type", hit.getType());
			map.put("id", hit.getId());
			
			list.add(map);
		}
		
		return list;
	}
	
	
	/**
	 * @param index
	 * @param types
	 * @param map
	 * @return
	 * service层 map
	 */
	public List<Map<String, Object>> getListByMap(String index,String[] types,Map<String, String> map) {
		
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		
		for(Map.Entry<String, String> entry : map.entrySet()){
			QueryBuilder matchqueryBuilder = QueryBuilders.matchQuery(entry.getKey(), entry.getValue());
			boolQueryBuilder.must(matchqueryBuilder);
			/*QueryBuilder wildcardqueryBuilder = QueryBuilders.wildcardQuery(entry.getKey(), "*"+entry.getValue()+"*");
			boolQueryBuilder.must(wildcardqueryBuilder);*/
		}
		
		/*// "单个匹配"  match查询先将查询的内容进行分词后再去分词库做查询
		QueryBuilder matchqueryBuilder = QueryBuilders.matchQuery("operation_level", "ERROR");
		content = "*"+content.toLowerCase()+"*";
		QueryBuilder wildcardqueryBuilder = QueryBuilders.wildcardQuery("operation_des", content);
		boolQueryBuilder.must(wildcardqueryBuilder);*/
		
		list = this.getListByQueryBuilder(index, types, boolQueryBuilder);
		
		
		return list;
	}
	
	/**
	 * @param index
	 * @param types
	 * @param map+content
	 * @return
	 * 混合查询
	 */
	public List<Map<String, Object>> getListByMapAndContent(String index,String[] types,Map<String, String> map,String content) {
		
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		// 默认需要查询的列
		String [] keys = {"equipmentid","operation_level","operation_des"};
		
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		for(Map.Entry<String, String> entry : map.entrySet()){
			/*QueryBuilder matchqueryBuilder = QueryBuilders.matchQuery(entry.getKey(), entry.getValue());
			boolQueryBuilder.must(matchqueryBuilder);*/
			QueryBuilder wildcardqueryBuilder = QueryBuilders.wildcardQuery(entry.getKey(), "*"+entry.getValue()+"*").boost(2);
			boolQueryBuilder.must(wildcardqueryBuilder);
		}
		
		if (!Strings.isNullOrEmpty(content)) {
			Map<String, String> maptmp = new HashMap<String,String>();
			for(String key : keys){
				if (!map.containsKey(key)) {
					maptmp.put(key, content);
				}
			}
			for(Map.Entry<String, String> entry : maptmp.entrySet()){
				QueryBuilder wildcardqueryBuilder = QueryBuilders.wildcardQuery(entry.getKey(), "*"+entry.getValue()+"*").boost(1);
				boolQueryBuilder.should(wildcardqueryBuilder);
			}
		}
		
		list = this.getListByQueryBuilder(index, types, boolQueryBuilder);
		
		return list;
	}
	
	/**
	 * @param index
	 * @param types
	 * @param map + content
	 * @return
	 * service层 
	 */
	public List<Map<String, Object>> getListByMapAndContent1(String index,String[] types,Map<String, String> map,String content) {
		
		String [] keys = {"equipmentid","operation_level","operation_des"};
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		if (!Strings.isNullOrEmpty(content)) {
			for(String key : keys){
				if (!map.containsKey(key)) {
					map.put(key, content);
				}
			}
		}
		for(Map.Entry<String, String> entry : map.entrySet()){
			/*QueryBuilder matchqueryBuilder = QueryBuilders.matchQuery(entry.getKey(), entry.getValue());
			boolQueryBuilder.must(matchqueryBuilder);*/
			QueryBuilder wildcardqueryBuilder = QueryBuilders.wildcardQuery(entry.getKey(), "*"+entry.getValue()+"*");
			boolQueryBuilder.should(wildcardqueryBuilder);
		}
		
		List<Map<String, Object>> list = this.getListByQueryBuilder(index, types, boolQueryBuilder);
		
		return list;
	}
	
	/**
	 * @param index
	 * @param types
	 * @param content
	 * @return
	 * 混合查询 列+正文内容
	 */
	public List<Map<String, Object>> getListByRowAndContent(String index,String[] types,String content) {
		
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);
		searchRequestBuilder.setTypes(types);
		searchRequestBuilder.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
		
		// "单个匹配"  match查询先将查询的内容进行分词后再去分词库做查询
		QueryBuilder matchqueryBuilder = QueryBuilders.matchQuery("operation_level", "ERROR");
		QueryBuilder wildcardqueryBuilder = null;
		if (content!=null) {
			content = "*"+content.toLowerCase()+"*";
			wildcardqueryBuilder = QueryBuilders.wildcardQuery("operation_des", content);
		}
		
			
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		boolQueryBuilder.must(matchqueryBuilder);
		if (wildcardqueryBuilder!=null) {
			boolQueryBuilder.must(wildcardqueryBuilder);
		}
		searchRequestBuilder.setQuery(boolQueryBuilder);
		//searchRequestBuilder.setQuery((QueryBuilder) fuzzyQuery);
		searchRequestBuilder.setExplain(true);
		searchRequestBuilder.setSize(10000);
		
		
		SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
		
		SearchHit [] searchHits = searchResponse.getHits().getHits();
		for(SearchHit hit : searchHits) {
			Map<String, Object> map = hit.getSource();
			map.put("index", hit.getIndex());
			map.put("type", hit.getType());
			map.put("id", hit.getId());
			
			list.add(map);
		}
		
		return list;
	}
	
	
	/**
	 * @param index
	 * @param types
	 * @param content
	 * @return
	 * service层 混合查询 列+正文内容
	 */
	public List<Map<String, Object>> getListByContent(String index,String[] types,String content) {
		
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		
		// "多个匹配"  匹配的列进行归纳,包括设备id，设备ip，日志类型，日志内容
		MultiMatchQueryBuilder multiMatchQueryBuilder  = QueryBuilders.multiMatchQuery(content, "operation_level","operation_des","ip");
		
		list = this.getListByQueryBuilder(index, types, multiMatchQueryBuilder);
		
		if (list.size()<1) {
			content = "*"+content.toLowerCase()+"*";
			
			QueryBuilder wildcardqueryBuilder1 = QueryBuilders.wildcardQuery("operation_des", content);
			QueryBuilder wildcardqueryBuilder2 = QueryBuilders.wildcardQuery("operation_level", content);
			QueryBuilder wildcardqueryBuilder3 = QueryBuilders.wildcardQuery("ip", content);
			QueryBuilder wildcardqueryBuilder4 = QueryBuilders.wildcardQuery("hostname", content);
			QueryBuilder wildcardqueryBuilder5 = QueryBuilders.wildcardQuery("process", content);
			QueryBuilder wildcardqueryBuilder6 = QueryBuilders.wildcardQuery("operation_facility", content);
			QueryBuilder wildcardqueryBuilder7 = QueryBuilders.wildcardQuery("userid", content);
			BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
			boolQueryBuilder.should(wildcardqueryBuilder1);
			boolQueryBuilder.should(wildcardqueryBuilder2);
			boolQueryBuilder.should(wildcardqueryBuilder3);
			boolQueryBuilder.should(wildcardqueryBuilder4);
			boolQueryBuilder.should(wildcardqueryBuilder5);
			boolQueryBuilder.should(wildcardqueryBuilder6);
			boolQueryBuilder.should(wildcardqueryBuilder7);
			list = this.getListByQueryBuilder(index, types, boolQueryBuilder);
		}
		
		return list;
	}
	
	/**
	 * @param index
	 * @param types
	 * @param content
	 * @return
	 * service层 混合查询 列+正文内容
	 */
	public List<Map<String, Object>> getListByHits(String index,String[] types,String content) {
		
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		SearchHit[] hits = null;
		
		// "多个匹配"  匹配的列进行归纳,包括设备id，设备ip，日志类型，日志内容
		MultiMatchQueryBuilder multiMatchQueryBuilder  = QueryBuilders.multiMatchQuery(content,"operation_level","operation_des","ip","hostname","process","operation_facility","userid");
		
		hits = this.getHitsByQueryBuilder(index, types, multiMatchQueryBuilder);
		
		if (hits.length<1) {
			content = "*"+content.toLowerCase()+"*";
			
			QueryBuilder wildcardqueryBuilder1 = QueryBuilders.wildcardQuery("operation_des", content);
			QueryBuilder wildcardqueryBuilder2 = QueryBuilders.wildcardQuery("operation_level", content);
			QueryBuilder wildcardqueryBuilder3 = QueryBuilders.wildcardQuery("ip", content);
			QueryBuilder wildcardqueryBuilder4 = QueryBuilders.wildcardQuery("hostname", content);
			QueryBuilder wildcardqueryBuilder5 = QueryBuilders.wildcardQuery("process", content);
			QueryBuilder wildcardqueryBuilder6 = QueryBuilders.wildcardQuery("operation_facility", content);
			QueryBuilder wildcardqueryBuilder7 = QueryBuilders.wildcardQuery("userid", content);
			BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
			boolQueryBuilder.should(wildcardqueryBuilder1);
			boolQueryBuilder.should(wildcardqueryBuilder2);
			boolQueryBuilder.should(wildcardqueryBuilder3);
			boolQueryBuilder.should(wildcardqueryBuilder4);
			boolQueryBuilder.should(wildcardqueryBuilder5);
			boolQueryBuilder.should(wildcardqueryBuilder6);
			boolQueryBuilder.should(wildcardqueryBuilder7);
			hits = this.getHitsByQueryBuilder(index, types, boolQueryBuilder);
		}
		
		for(SearchHit hit : hits) {
			Map<String, HighlightField> highlightFields = hit.getHighlightFields();
			HighlightField titleField = highlightFields.get("operation_des");
			HighlightField operation_levelField = highlightFields.get("operation_level");
			Map<String, Object> map = hit.getSource();
			map.put("index", hit.getIndex());
			map.put("type", hit.getType());
			map.put("id", hit.getId());
			if (titleField!=null) {
				Text[] texts = titleField.fragments();
				String name = "";
				for(Text text :texts){
					name += text;
				}
				map.put("operation_des",name);
			}
			if (operation_levelField!=null) {
				Text[] texts = operation_levelField.fragments();
				String name = "";
				for(Text text :texts){
					name += text;
				}
				map.put("operation_level",name);
			}
			
			list.add(map);
		}
		
		return list;
	}
	
	
	/**
	 * @param index
	 * @param types
	 * @param queryBuilder
	 * @return
	 * template层  
	 */
	public List<Map<String, Object>> getListByQueryBuilder(String index,String[] types,QueryBuilder queryBuilder) {
		
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);
		searchRequestBuilder.setTypes(types);
		searchRequestBuilder.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
		searchRequestBuilder.setQuery(queryBuilder);
		searchRequestBuilder.setExplain(true);
		searchRequestBuilder.setSize(10000);
		
		SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
		
		SearchHit [] searchHits = searchResponse.getHits().getHits();
		for(SearchHit hit : searchHits) {
			Map<String, Object> map = hit.getSource();
			map.put("index", hit.getIndex());
			map.put("type", hit.getType());
			map.put("id", hit.getId());
			
			list.add(map);
		}
		
		return list;
	}
	
	/**
	 * @param index
	 * @param types
	 * @param queryBuilder
	 * @return
	 * template层  
	 */
	public List<Map<String, Object>> getListByQueryBuilder(String index,String[] types,QueryBuilder queryBuilder,Integer from,Integer size) {
		
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);
		searchRequestBuilder.setTypes(types);
		searchRequestBuilder.setQuery(queryBuilder);
		searchRequestBuilder.setFrom(from).setSize(size);
		
		SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
		
		SearchHit [] searchHits = searchResponse.getHits().getHits();
		for(SearchHit hit : searchHits) {
			Map<String, Object> map = hit.getSource();
			map.put("index", hit.getIndex());
			map.put("type", hit.getType());
			map.put("id", hit.getId());
			
			list.add(map);
		}
		
		return list;
	}
	
	/**
	 * @param index
	 * @param types
	 * @param queryBuilder
	 * @return
	 * template层  
	 */
	public SearchHit[] getHitsByQueryBuilder(String index,String[] types,QueryBuilder queryBuilder) {
		
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);
		searchRequestBuilder.setTypes(types);
		searchRequestBuilder.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
		searchRequestBuilder.setQuery(queryBuilder);
		searchRequestBuilder.setExplain(true);
		searchRequestBuilder.setSize(2);
		
		HighlightBuilder highlightBuilder = new HighlightBuilder().field("*").requireFieldMatch(false);
		highlightBuilder.preTags("<span style=\"color:red\">");
		highlightBuilder.postTags("</span>");
		searchRequestBuilder.highlighter(highlightBuilder);
		
		SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
		
		SearchHit [] searchHits = searchResponse.getHits().getHits();
		for(SearchHit hit : searchHits) {
			Map<String, Object> map = hit.getSource();
			map.put("index", hit.getIndex());
			map.put("type", hit.getType());
			map.put("id", hit.getId());
			
			list.add(map);
		}
		
		return searchHits;
	}
	
	public SearchResponse search(SearchRequestBuilder searchRequestBuilder) {
		
		SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
		return searchResponse;
		
	}
	
	public void updateByQuery(String index, String type, Set<Map<String, Object>> docs, String scriptStr) {
        if (docs == null || docs.isEmpty()){
            //LOG.info("No data can updateByQuery to es! index:{}.", index);
            return;
        }
        UpdateByQueryRequestBuilder ubqrb = UpdateByQueryAction.INSTANCE.newRequestBuilder(client);
        for (Map<String, Object> doc : docs) {
            if (doc==null || doc.isEmpty()){
                return;
            }
            Script script = new Script(scriptStr);
            //BulkIndexByScrollResponse
            BulkByScrollResponse scrollResponse =
                    ubqrb.source(index)
                            .script(script)
                            .filter(QueryBuilders.termQuery("session_id", doc.get("orgin_session_id")))
                            .abortOnVersionConflict(false).get();
            /*for (BulkItemResponse.Failure failure : scrollResponse.getIndexingFailures()) {
                //LOG.error(failure.getMessage());
            }*/
        }
    }
	
	public TransportClient getClient() {
		return client;
	}

	public void setClient(TransportClient client) {
		this.client = client;
	}
	
	public static void main(String[] args) throws IOException, ParseException {

		/*String log = "2017-09-10 11:00:34,132 [INFO] 192.168.4.123  [com.jz.bigdata.common.department.dao.IDepartmentDao.selectAll] debug()[139] [210348] \r\n"; 
		
		Log4j log4j = new Log4j(log);
		
		Gson gson = new Gson();
		
		String json = gson.toJson(log4j);
		System.out.println(json);
		
		String lString = Log4j.class.getName();
		
		String [] name = lString.split("\\.");
		
		System.out.println(name[name.length-1]);*/
		Client client = new Client();
		//Client client = new Client("myElasticsearchCluster","192.168.100.101");
		//client.catESinfo();
		
		//client.createIndex("eslog-analysis");
		//client.deleteById("estest", "Log4j", "AV51EkitWqPY2etmLun6");
		// 删除index
		//client.deleteByIndex("ztslog-analysis");
		// 创建type的mapping
		//client.createMapping("eslog-analysis", "syslog", new Syslog().toMapping());
		
		/*FileInputStream fis=new FileInputStream("D:\\tmp\\九州\\syslog.txt");
        InputStreamReader isr=new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        while ((line=br.readLine())!=null) {
        	System.out.println(line);
        	Syslog syslog = new Syslog(line);
        	//syslog.setLogtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(syslog.getLogtime()));
        	Gson gson = new Gson();
    		String json = gson.toJson(syslog);
    		client.insert("estest", "syslog", json);
        }*/
		/*String log = "2017-09-10 12:10:34,232 [debug] 192.168.4.123  [com.jz.bigdata.common.department.dao.IDepartmentDao.selectAll] debug()[139] [210348] \r\n";
		client.catESinfo();
		Log4j log4j = new Log4j(log);
		log4j.setDeptid("155");
		log4j.setEquipmentid("b6eb1d5cd6f447eb9530b2e62aaaaaaa");
		log4j.setUserid("b3286efc86434783aad60ce89f141d92");
		Gson gson = new Gson();
		
		String json = gson.toJson(log4j);
		client.insert("estest", "log4j", json);*/
		
		String [] types = {"log4j","winlog","syslog","packetfilteringfirewall_log","mysql","unknown"};
		/*QueryBuilder wildcardqueryBuilder = QueryBuilders.wildcardQuery("operation_level", "*error*");*/
		long lentgh = client.count("eslog-analysis", types,null);
		System.out.println(lentgh);
		//List<Map<String, Object>> list = client.countGroupBy("estest", "syslog","ip");
		/*String [] types = {};
		List<Map<String, Object>> list = client.countGroupBytime("estest", types,"2017-11-22");
		//List<Map<String, Object>> list = client.getListOrderByParam("estest", "Log4j","logtime",null);
		
		for(Map<String, Object> maps : list) {
			System.out.println(maps);
		}*/
		
		
		
		/*String log1 = "<30> 2018-02-09 16:26:09 jzhadoop-h10 124.133.246.61 systemd: Starting Delayed Shutdown Service...";
		String [] logs = {log1};
		
		Gson gson = new GsonBuilder()
				 .setDateFormat("yyyy-MM-dd HH:mm:ss")  
				 .create(); 
		String json;
		
		for(String log:logs) {
			Syslog syslog = new Syslog(log);
			syslog.setUserid("f6647fa961684bdcaaba89f9b2bf977b");
			syslog.setDeptid("156");
			syslog.setEquipmentid("b64f65625c6b48ab8b3f64d015e4a772");
			syslog.setEquipmentname("九州大数据集群h10");
			json = gson.toJson(syslog);
			System.out.println(json);
			client.insert("eslog-analysis", "syslog", json);
		}*/
		
		//List<Map<String, Object>> list = client.getLogListByParam("estest", "Log4j","logtime",null,"b6eb1d5cd6f447eb9530b2e628a28b8f");
		
		// 查询日志级别为ERROR的数据
		//List<Map<String, Object>> list = client.getListByRowAndContent("estest", types, null);
		/*Map<String,String> map = new HashMap<>();
		map.put("operation_level", "error");
		List<Map<String, Object>> list = client.getListByMap("estest", types, map);
		int i = 0;
		for(Map<String, Object> maps : list) {
			System.out.println(maps);
			i++;
		}
		System.out.println(i);*/
		
		/*String [] types = {"log4j","syslog","winlog"};
		List<Map<String, Object>> list = client.getListByHits("estest3",types,"com");
		for(Map<String, Object> maps : list) {
			System.out.println(maps);
		}
		
		List<Map<String, Object>> list1 = client.getListByContent("estest3", types, "com");
		for(Map<String, Object> maps : list1) {
			System.out.println(maps);
		}*/
		
		// 新增user
		/*String result = client.getClassMapping(new Winlog());
		System.out.println(result);*/
		
		/*User user = new User();
		user.setName("lihonglian");
		user.setAge(25);
		user.setPassword("123qwe");
		user.setUserid(1003);
		user.setDeptid(10);
		
		Gson gson = new Gson();
		String json = gson.toJson(user);
		
		client.insert("estest1", "user", json);*/
		
		// 通过比较数值大小查询数据
		/*String [] types = {"syslog"};
		//RangeQueryBuilder queryBuilder = QueryBuilders.rangeQuery("age").from(20).to(26);
		List<Map<String, Object>> list = client.getListByQueryBuilder("eslog-analysis", types, null,10,10);
		for(Map<String, Object> maps : list) {
			System.out.println(maps);
		}
		System.out.println(list.size());*/
		
		
		client.closeclient();
		
		
		
	}

}
