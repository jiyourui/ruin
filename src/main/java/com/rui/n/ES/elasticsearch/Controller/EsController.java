package com.rui.n.ES.elasticsearch.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import org.elasticsearch.action.admin.cluster.snapshots.create.CreateSnapshotAction;
import org.elasticsearch.action.admin.cluster.snapshots.create.CreateSnapshotRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;

public class EsController {

	
    private static final String BASE_SNAPSHOT = "snapshot_geleevr_";
	
	/**
	 * 每天执行Es集群快照
	 * 
	 * @throws Exception	
	 */
//	@Scheduled(cron="0 0 1 * * ?") 
//	@Scheduled(fixedRate=30000000)
   /* public void execute() throws Exception {
    	TransportClient client = ESClient.me();
    	CreateSnapshotRequestBuilder builder = CreateSnapshotAction.INSTANCE.newRequestBuilder(client);
    	
    	String snapshotName = BASE_SNAPSHOT + UTCDateUtil.userFormat(new Date());
    	long start = System.currentTimeMillis();
    	// 快照执行的时间可能比较长，而且不可控（只能定时通过status api查询），所以异步获取状态
    	builder.setIndices(ESConfig.COMPANY, ESConfig.ANALYZE)
    			.setRepository(ESConfig.ES_REPOSITORY)
    			.setSnapshot(snapshotName)
    			.execute(new ActionListener<CreateSnapshotResponse>() {
					
					@Override
					public void onResponse(CreateSnapshotResponse response) {
						long end = System.currentTimeMillis();
						long seconds = (long) ((end - start)/1000); 
						logger.info("task of snapshot {} is sync sussess , total time is {} seconds !", snapshotName, seconds);
					}
					
					@Override
					public void onFailure(Exception e) {
						long end = System.currentTimeMillis();
						long seconds = (long) ((end - start)/1000); 
						logger.error("task of snapshot {} is failure , total time is {} seconds !", snapshotName, seconds);
						
					}
				});
    	
    }*/
	
	public void doCutl(String type,String url) {
		String[] cmds = {"curl", type, url};  
        ProcessBuilder processBuilder = new ProcessBuilder(cmds);  
        processBuilder.redirectErrorStream(true);  
        Process process;  
        try {  
        	process = processBuilder.start();  
            BufferedReader br = null;  
            String line = null;  
            br = new BufferedReader(new InputStreamReader(process.getInputStream()));  
            while ((line = br.readLine()) != null) {  
                System.out.println("\t" + line);  
            }  
            br.close();  
            process.destroy();
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
	}
	
	public void restore() {
		
		// 首先关闭需要恢复的索引
		String  closeUrl = "\"http://192.168.100.101:9200/es-analysis/_close";
		doCutl("-XPOST", closeUrl);
		// 回复快照
		String restoreUrl = "\"http://192.168.100.101:9200/_snapshot/EsBackup/snapshot_20180207/_restore\"";
		doCutl("-XPOST", restoreUrl);
		// 恢复完成之后打开索引
		String openUrl = "\"http://192.168.100.101:9200/es-analysis/_open";
		doCutl("-XPOST", openUrl);
		
	}
	
	public static void main(String [] agrs) {
		//curl -XPUT http://192.168.3.100:9200/_snapshot/back_2/back_6 -d "{\"indices\": \"indexdemo1\"}"
		
		//String url = "\"http://192.168.3.100:9200/_snapshot/back_2/back_133\" -d \"{\"\"\"indices\"\"\": \"\"\"indexdemo1\"\"\"}\"";
		//String url = "http://192.168.100.101:9200/";
		// 创建备份仓库
		String url = "\"http://192.168.100.101:9200/_snapshot/EsBackup\" -d \"{\"\"\"type\"\"\": \"\"\"fs\"\"\",\"\"\"settings\"\"\": {\"\"\"location\"\"\": \"\"\"/home/elsearch/es_backups/my_backup\"\"\"}}\"";
		// 创建快照
		String snapshotUrl = "\"http://192.168.100.101:9200/_snapshot/EsBackup/snapshot_20180107?wait_for_completion=false";
		// 创建快照并指定索引
		String snapshotUrlByindices = "\"http://192.168.100.101:9200/_snapshot/EsBackup/snapshot_20180207\" -d \"{\"\"\"indices\"\"\":\"\"\"es-analysis\"\"\",\"\"\"wait_for_completion\"\"\":false}\"";
		// 查看快照
		String getSnapshotUrl = "\"http://192.168.100.101:9200/_snapshot/EsBackup/snapshot_20180207\"";
		// 查看快照进度
		String getSnapshotStatusUrl = "\"http://192.168.100.101:9200/_snapshot/EsBackup/snapshot_20180207/_status\"";
		// 回复快照
		String restoreUrl = "\"http://192.168.100.101:9200/_snapshot/EsBackup/snapshot_20180207/_restore\"";
		// 删除快照
		String deleteUrl = "\"http://192.168.100.101:9200/_snapshot/EsBackup/snapshot_20180207\"";
		EsController esController = new EsController();
		//esController.doCutl("-XDELETE ", deleteUrl);
		esController.doCutl("-XPOST ", getSnapshotUrl);
		//esController.restore();
	}
}
