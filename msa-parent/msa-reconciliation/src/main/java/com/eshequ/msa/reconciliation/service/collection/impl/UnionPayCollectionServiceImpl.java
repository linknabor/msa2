/**
 * 
 */
package com.eshequ.msa.reconciliation.service.collection.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.eshequ.msa.reconciliation.service.collection.CollectionService;
import com.eshequ.msa.reconciliation.service.collection.dto.CollectionBody;
import com.eshequ.msa.reconciliation.service.collection.dto.CollectionFileDTO;
import com.eshequ.msa.reconciliation.service.collection.dto.CollectionHead;
import com.eshequ.msa.reconciliation.util.UnionPayUtil;
import com.eshequ.msa.util.ObjectUtil;
import com.eshequ.msa.util.SnowFlake;

/**
 * @author david
 *
 */
@Service
public class UnionPayCollectionServiceImpl implements CollectionService {

	private static String REQUEST_VERSION = "V1.1";
	private static String REQUEST_TYPE = "14";
	private static String DEFAULT_CHARSET = "UTF-8";
	private static String UNIONPAY_SUCCESS = "0000";
	
	private static Logger logger = LoggerFactory.getLogger(UnionPayCollectionServiceImpl.class);
	private static String REQUEST_URL =  "https://www.zfzlpay.com/payment-gate-web/gateway/api/backTransReq";
	
	@Autowired
	private SnowFlake snowFlake;
	
	@Autowired
	@Qualifier("restTemplateWeakSsl")
	private RestTemplate restTemplate;
	
	@Autowired
	private UnionPayUtil unionPayUtil;
	
	@Value("${unionpay_data_file}")
	private String localFolder;
	
	@Override
	public List<String> downloadFile(String collectionDate) {
		
		String mchNo = "888290059501308";	//TODO 商户号，暂时写死
		String respStr = getFileStream(collectionDate, mchNo);
		List<String> list = new ArrayList<>();
		if (!ObjectUtil.isEmpty(respStr)) {
			String filePath = localFolder + collectionDate+"_"+mchNo+".dat";
			try {
				File localfile = new File(filePath);
				if (!localfile.exists()) {
					FileUtils.writeStringToFile(new File(filePath), respStr, DEFAULT_CHARSET);;
				}
				
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			} 
			list.add(filePath);
		}
		return list;
	
	}

	/**
	 * 从银联获取文件流
	 * @param collectionDate
	 * @return
	 */
	private String getFileStream(String collectionDate, String mchNo) {
		Map<String, String> map = new TreeMap<String, String>();
		map.put("requestNo", String.valueOf(snowFlake.nextId())); //请求流水号
		map.put("version", REQUEST_VERSION); //版本号  默认值
		map.put("transId", REQUEST_TYPE);
		map.put("merNo", mchNo);
		map.put("postingDate", collectionDate);
		
		String signData = unionPayUtil.createSign(map);
		map.put("signature", signData);
		
		LinkedMultiValueMap<String, String> multiMap = new LinkedMultiValueMap<>();
		multiMap.setAll(map);
		
		String reqUrl = REQUEST_URL; //请求地址 TODO 先写死
		
		logger.info("请求银联前数据：" + multiMap);
		String resp = restTemplate.postForObject(reqUrl, multiMap, String.class);
		logger.info("请求银联后数据：" + resp);
		
		Map<String, String> respMap = convertRespToMap(resp);
		String responseCode = (String) respMap.get("respCode");
		
		String respStr = "";
		if (UNIONPAY_SUCCESS.equals(responseCode)) {
			String fileContent = (String) respMap.get("filecontent");
			fileContent = fileContent.replace("\r\n", "");
			respStr = new String(Base64.getDecoder().decode(fileContent));
		}
		return respStr;
	}

	@Override
	public CollectionFileDTO paseFile(String filePath) {
		
		File file = new File(filePath);
		List<String> dataList = new ArrayList<>(100);
		try {
			dataList = FileUtils.readLines(file, DEFAULT_CHARSET);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		CollectionFileDTO dto = null;
		if (dataList.size()>0) {
			String fileContent = dataList.get(0);
			
			//处理第一行合计
			CollectionHead head = new CollectionHead();
			String[]headLine = fileContent.split("\\|");
			head.setTranDate(headLine[0]);
			head.setTotalCount(headLine[1]);
			head.setTotalTranAmt(headLine[2]);
			head.setTotalFeeAmt(headLine[3]);
			head.setTotalLiquiateAmt(headLine[4]);
			
			//处理明细
			List<CollectionBody> bodyList = new ArrayList<>(100);
			for (int i = 1; i < dataList.size(); i++) {
				
				String[]detailLines = dataList.get(i).split("\\|");
				CollectionBody body = new CollectionBody();
				body.setTranDate(detailLines[0]);
				body.setParentMch(detailLines[1]);
				body.setMchId(detailLines[2]);
//				body.setPayPro(detailLines[3]);
//				body.setTranType(detailLines[4]);
//				body.setTransactionId(detailLines[5]);
//				body.setTranTime(detailLines[6]);
//				body.setTranAmt(detailLines[7]);
//				body.setFeeAmt(detailLines[8]);
//				body.setLiquidateAmt(detailLines[9]);
//				body.setOriTransactionId(detailLines[10]);
				body.setOriTranTime(detailLines[11]);
				body.setRemark(detailLines[12]);
				bodyList.add(body);
			}
			dto = new CollectionFileDTO(head, bodyList);
		}
		
		return dto;
	}

	@Override
	@Transactional
	public void collection(CollectionFileDTO dto) {

		List<CollectionBody> detailList = dto.getBody();
		for (CollectionBody collectionBody : detailList) {
			
		}
		
		
	}
	
	private static Map<String, String> convertRespToMap(String str) {
		Map<String, String> map = new HashMap<String, String>();
		String data[] = str.split("&");
        for (int i = 0; i < data.length; i++) {
            String tmp[] = data[i].split("=", 2);
            map.put(tmp[0], tmp[1]);
        }
        return map;
	}

	
}
