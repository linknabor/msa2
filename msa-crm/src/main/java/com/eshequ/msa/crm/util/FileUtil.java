package com.eshequ.msa.crm.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.eshequ.msa.crm.mapper.repairmng.FileMngMapper;
import com.eshequ.msa.crm.model.repairmng.FileMng;
import com.eshequ.msa.crm.web.repairmng.OAuth2Controller;
import com.eshequ.msa.util.DateUtil;
import com.eshequ.msa.util.ObjectUtil;
import com.eshequ.msa.util.SnowFlake;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;

@Component
public class FileUtil {
	    @Value(value = "${tmpfile.dir}")
	    private String tmpFileRoot;
	    
	    @Value(value = "${qiniu.domain}")
	    private String domain;
	    
	    @Autowired
		private QiYeWeiXinUtil qiYeWeiXinUtil;
	    
	    @Autowired
		private FileMngMapper fileMngMapper;

		@Autowired
		private SnowFlake snowFlake;
	    
	private static Logger log = LoggerFactory.getLogger(OAuth2Controller.class);
	
	/**
	 * 下载文件URL
	 */
	private static final String dwonloadFileURL = "https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
	/**
	 * 上传图片到七牛服务器
	 * @param ret
	 * @param inputStream
	 * @param uploadIds
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public void upload2Qiniu(String uploadIds,String repairId ){
		
		InputStream inputStream = null;
		if (!ObjectUtil.isEmpty(uploadIds)) {
			
			uploadIds = uploadIds.substring(0, uploadIds.length()-1);	//截掉最后一个逗号
			String[]uploadIdArr = uploadIds.split(",");
			
			String uptoken = QiniuUtil.getInstance().getUpToken();	//获取qiniu上传文件的token
			
			log.error("qiniu token :" + uptoken);
			
			String currDate = DateUtil.getSysDate();
			String currTime = DateUtil.getSysTime();
			String tmpPathRoot = tmpFileRoot+File.separator+currDate+File.separator;
			
			File file = new File(tmpPathRoot);
			if (!file.exists()||!file.isDirectory()) {
				file.mkdirs();
			}
			String keyListStr = "";
			String imgHeight = "";
			String imgWidth = "";
			
			PutExtra extra = new PutExtra();
			File img = null;
			
			try {
				for (int i = 0; i < uploadIdArr.length; i++) {
					
					String uploadId = uploadIdArr[i];
					int imgcounter = 0;
					inputStream = null;
					while(inputStream==null&&imgcounter<3) {
						inputStream = downloadFile(uploadId);		//下载图片
						if (inputStream==null) {
							log.error("获取图片附件失败。");
						}
						imgcounter++;
					}
					
					if (inputStream==null) {
						log.error("多次从腾讯获取图片失败。");
						return;
					}
					String tmpPath = tmpPathRoot+currTime+"_"+i;
					inputStream2File(inputStream, tmpPath);
					String key = currDate+"_"+currTime+"_"+i;
					img = new File(tmpPath);
					
					if (img.exists() && img.getTotalSpace()>0) {

						PutRet putRet = IoApi.putFile(uptoken, key, img, extra);
						log.error("ret msg is : " + putRet.getException());
						log.error("putRet is : " + putRet.toString());
						
						while (putRet.getException()!=null) {
							putRet = IoApi.putFile(uptoken, key, img, extra);
							java.lang.Thread.sleep(100);
						}
						
						boolean isUploaded = false;
						int counter = 0;
						Map map = null;
						while (!isUploaded && counter <3 ) {

							map = QiniuUtil.getInstance().getImgs(domain+key);
							Object error = map.get("error");
							if (error != null) {
								log.error((String)error);
								log.error("start to re-upload ...");
								putRet = IoApi.putFile(uptoken, key, img, extra);
								java.lang.Thread.sleep(100);
							}else {
								isUploaded = true;
								break;
							}
							
							counter++;
						}
						
						if (isUploaded) {
							
							keyListStr=domain+key;
							
							FileMng f=new FileMng();
							f.setCreateDate(new Date());
							f.setFileId(String.valueOf(snowFlake.nextId()));
							f.setFilePath(keyListStr);
							f.setRepairId(repairId);
							fileMngMapper.insertSelective(f);
							
							/*Integer width = (Integer)map.get("width");
							Integer height = (Integer)map.get("height");
							
							imgWidth+=width;
							imgHeight+=height;
							
							if (i!=uploadIdArr.length-1) {
								keyListStr+=",";
								imgWidth+=",";
								imgHeight+=",";
							}*/
						}
						
					
					}
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
			}finally{
				if (inputStream!=null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
						log.error(e.getMessage());
					}
				}
			}
		
			/*ret.setAttachmentUrl(keyListStr);
			ret.setImgHeight(imgHeight);
			ret.setImgWidth(imgWidth);
			communityService.updateThread(ret);	//更新上传文件路径
*/		
		}
		
		
	}
	
	/**
	 * 下载文件
	 * @param mediaId
	 */
	public  InputStream downloadFile(String mediaId){
		
		String requestUrl = dwonloadFileURL.replace("ACCESS_TOKEN", qiYeWeiXinUtil.getAccessToken().getAccess_token()).replace("MEDIA_ID", mediaId);
		try {
			HttpGet httpGet = new HttpGet(requestUrl);
			HttpClient httpclient = HttpClients.createDefault();
			
			log.debug("start to call httpclient ... ");
			HttpResponse response = httpclient.execute(httpGet);

			HttpEntity entity = response.getEntity();
			log.info("start to get response ...");
			log.info(response.getStatusLine().toString());
			
			String responseStr = null;
			if (entity != null) {

				log.info("response content length: " + entity.getContentLength());
				Header header = entity.getContentType();
				
				log.info("header :" + header.getName()+":"+header.getValue());
				
				InputStream input = entity.getContent();
				if (header.getValue().contains("image")) {	//返回图片
					
					return input;
				
				}else {	//返回错误信息
					
					byte[]bytes = new byte[1024];	//错误信息
					input.read(bytes);
				    responseStr = new String(bytes,"UTF-8");	//转UTF-8
				    responseStr = responseStr.trim();
				    log.error("response : \n" + responseStr);
				   // throw new BizValidateException(responseStr);
				    
				}
				
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			 //throw new BizValidateException(e.getMessage());
		}
		
		return null;
	}
	/**
	 * inputStream转文件
	 * @param is
	 * @param filePath
	 * @return
	 */
	public static void inputStream2File(InputStream is, String filePath){
		
		try {
			
			OutputStream os = new FileOutputStream(new File(filePath));
			int bytesRead = 0;
			byte[] buffer = new byte[1024*1024];
			while ((bytesRead = is.read(buffer, 0, 1024*1024)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.flush();
			os.close();
			is.close();
		
		} catch (Exception e) {
			
			e.printStackTrace();
			log.error("convert stream to file failed ...");
		}
		
	}
	
}
