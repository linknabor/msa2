/**
 * 
 */
package com.eshequ.msa.reconciliation.service.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.eshequ.msa.codes.AccountStatus;
import com.eshequ.msa.codes.IsFlag;
import com.eshequ.msa.codes.MchStatus;
import com.eshequ.msa.codes.MergerStatus;
import com.eshequ.msa.codes.PayChannel;
import com.eshequ.msa.codes.PayMethod;
import com.eshequ.msa.exception.BusinessException;
import com.eshequ.msa.reconciliation.mapper.custom.UnionPayReconcilMapper;
import com.eshequ.msa.reconciliation.mapper.normal.MsaBaseAcctInfoMapper;
import com.eshequ.msa.reconciliation.mapper.normal.MsaBaseCheckDetailMapper;
import com.eshequ.msa.reconciliation.mapper.normal.MsaBaseCheckSumMapper;
import com.eshequ.msa.reconciliation.mapper.normal.MsaBaseMchInfoMapper;
import com.eshequ.msa.reconciliation.mapper.normal.MsaBaseOriginReconFileMapper;
import com.eshequ.msa.reconciliation.mapper.normal.MsaTradePayOrderMapper;
import com.eshequ.msa.reconciliation.model.MsaBaseAcctInfo;
import com.eshequ.msa.reconciliation.model.MsaBaseCheckDetail;
import com.eshequ.msa.reconciliation.model.MsaBaseCheckSum;
import com.eshequ.msa.reconciliation.model.MsaBaseMchInfo;
import com.eshequ.msa.reconciliation.model.MsaBaseOriginReconFile;
import com.eshequ.msa.reconciliation.model.MsaTradePayOrder;
import com.eshequ.msa.reconciliation.service.ReconcilService;
import com.eshequ.msa.reconciliation.service.dto.ReconcilDTO;
import com.eshequ.msa.reconciliation.service.dto.ReconcilFileBody;
import com.eshequ.msa.reconciliation.service.dto.ReconcilFileDTO;
import com.eshequ.msa.reconciliation.service.dto.ReconcilFileHead;
import com.eshequ.msa.reconciliation.util.UnionPayUtil;
import com.eshequ.msa.util.DateUtil;
import com.eshequ.msa.util.FeeCalculator;
import com.eshequ.msa.util.ObjectUtil;
import com.eshequ.msa.util.SnowFlake;
import com.eshequ.msa.util.TransactionUtil;

/**
 *银联支付对账实现类
 * @author david
 * @param <T>
 *
 */
@Service
public class UnionPayReconcilServiceImpl implements ReconcilService {

	private static final String REQUEST_VERSION = "V1.1";
	private static final String REQUEST_TYPE = "14";
	private static final String DEFAULT_CHARSET = "UTF-8";
	private static final String UNIONPAY_SUCCESS = "0000";
	
	private static final String TRAN_TYPE_FUND = "消费";
	private static final String TRAN_TYPE_REFUND = "退货";
	private static final String TRAN_TYPE_DAIFU = "代付";
	
	private static final String WECHAT_MICROPAY = "微信.刷卡支付";
	private static final String ALIPAY_MICROPAY = "支付宝.刷卡支付";
	
	private static String REQUEST_URL =  "https://www.zfzlpay.com/payment-gate-web/gateway/api/backTransReq";
	
	private static Logger logger = LoggerFactory.getLogger(UnionPayReconcilServiceImpl.class);
	private static Map<String, String> payProductMap;
	
	public void init() {
		
		try {
			if (payProductMap == null) {
				payProductMap = new HashMap<>();
				payProductMap.put(WECHAT_MICROPAY, PayMethod.WechatMicropay.toString());
				payProductMap.put(ALIPAY_MICROPAY, PayMethod.AliPayMicropay.toString());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	@Autowired
	private SnowFlake snowFlake;
	
	@Autowired
	@Qualifier("restTemplateWeakSsl")
	private RestTemplate restTemplate;
	@Autowired
	private UnionPayUtil unionPayUtil;
	@Value("${unionpay_data_file}")
	private String localFolder;
	
	@Autowired
	private TransactionUtil transacionUtil;
	@Autowired
	private PlatformTransactionManager transactionManager;
	@Autowired
	private MsaBaseMchInfoMapper msaBaseMchInfoMapper;
	@Autowired
	private UnionPayReconcilMapper unionPayReconcilMapper;
	@Autowired
	private MsaBaseOriginReconFileMapper msaBaseOriginReconFileMapper;
	@Autowired
	private MsaTradePayOrderMapper msaTradePayOrderMapper;
	@Autowired
	private MsaBaseCheckDetailMapper msaBaseCheckDetailMapper;
	@Autowired
	private MsaBaseCheckSumMapper msaBaseCheckSumMapper;
	@Autowired
	private MsaBaseAcctInfoMapper msaBaseAcctInfoMapper;
	
	/**
	 * 获取需要对账的商户号
	 * 包括两部分：
	 * 1.当前正启用的商户
	 * 2.已关闭商户但存在未对账交易的商户
	 * @param reconcilDate 跑批日期
	 */
	private List<MsaBaseMchInfo> getMchList(String reconcilDate) {
		
		//1.获取正启用的银联商户号
		MsaBaseMchInfo mchInfo = new MsaBaseMchInfo();
		mchInfo.setPayChannel(PayChannel.UnionPay.toString());
		List<MsaBaseMchInfo> mchList = msaBaseMchInfoMapper.select(mchInfo);
		
		//2.获取已关闭的，但仍有未清算交易的商户号
		List<MsaBaseMchInfo> unReconcilMchlist = unionPayReconcilMapper.getUnreconcilTradeMch(MergerStatus.YiZhiFu.toString(), 
				reconcilDate, PayChannel.UnionPay.toString(), MchStatus.QiYong.toString());
		
		mchList.addAll(unReconcilMchlist);
		return mchList;
	}
	
	/**
	 * 下载对账文件
	 * @param reconcilDate 跑批日期
	 */
	@Override
	public List<String> downloadFile(String batchDate) {
		
		List<MsaBaseMchInfo> mchList = getMchList(batchDate);
		List<String> filePahtList = new ArrayList<>();
		for (MsaBaseMchInfo msaBaseMchInfo : mchList) {
			String mchNo = msaBaseMchInfo.getMchNo();
			String respStr = getFileStr(batchDate, mchNo);
			
			if (!ObjectUtil.isEmpty(respStr)) {
				String filePath = localFolder + batchDate+"_"+mchNo+".dat";
				try {
					File localfile = new File(filePath);
					if (!localfile.exists()) {
						FileUtils.writeStringToFile(new File(filePath), respStr, DEFAULT_CHARSET);;
					}
					
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				} 
				filePahtList.add(filePath);
			}
		}
		return filePahtList;
	
	}

	/**
	 * 从银联获取文件流
	 * @param collectionDate
	 * @return
	 */
	private String getFileStr(String collectionDate, String mchNo) {
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
		
		Map<String, String> respMap = unionPayUtil.convertRespToMap(resp);
		String responseCode = (String) respMap.get("respCode");
		
		String respStr = "";
		if (UNIONPAY_SUCCESS.equals(responseCode)) {
			String fileContent = (String) respMap.get("filecontent");
			fileContent = fileContent.replace("\r\n", "");
			respStr = new String(Base64.getDecoder().decode(fileContent));
		}
		return respStr;
	}

	/**
	 * 解析文件
	 */
	@Override
	public ReconcilFileDTO parseFile(String filePath) {
		
		File file = new File(filePath);
		List<String> dataList = new ArrayList<>(100);
		try {
			dataList = FileUtils.readLines(file, DEFAULT_CHARSET);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		ReconcilFileDTO dto = null;
		if (dataList.size()>0) {
			String fileContent = dataList.get(0);
			
			//处理第一行合计
			ReconcilFileHead head = new ReconcilFileHead();
			String[]headLine = fileContent.split("\\|");
			head.setTranDate(headLine[0]);
			head.setTotalCount(headLine[1]);
			head.setTotalTranAmt(headLine[2]);
			head.setTotalFeeAmt(headLine[3]);
			head.setTotalLiquiateAmt(headLine[4]);
			
			//处理明细
			List<ReconcilFileBody> bodyList = new ArrayList<>(100);
			for (int i = 1; i < dataList.size(); i++) {
				
				String[]detailLines = dataList.get(i).split("\\|");
				ReconcilFileBody body = new ReconcilFileBody();
				body.setAcctDate(detailLines[0]);
				body.setParentMch(detailLines[1]);
				body.setMchId(detailLines[2]);
				body.setPayPro(detailLines[3]);
				body.setTranType(detailLines[4]);
				body.setOrderId(detailLines[5]);	//订单号
				body.setTranDateTime(detailLines[6]);	//交易日期时间,yyyy-MM-dd hh:mm:ss
				body.setTranAmt(detailLines[7]);
				body.setFeeAmt(detailLines[8]);
				body.setLiquidateAmt(detailLines[9]);
				body.setOriOrderId(detailLines[10]);
				body.setOriTranDateTime(detailLines[11]);
				body.setRemark(detailLines[12]);
				bodyList.add(body);
			}
			dto = new ReconcilFileDTO(head, bodyList);
		}
		
		return dto;
	}

	/**
	 * 文件落地存表
	 * @param dto
	 */
	@Override
	public void saveFile2DB(ReconcilFileDTO dto) {
		//手动控制事务，失败只回滚单条
		List<ReconcilFileBody> detailList = dto.getBody();
		for (ReconcilFileBody reconcilFile : detailList) {
			transacionUtil.transact(consumer->saveFileManually(reconcilFile));
		}
	}

	/**
	 * 文件落表，手动控制事务
	 * @param reconcilFile
	 */
	private void saveFileManually(ReconcilFileBody reconcilFile) {
		
		//先根据订单号查一遍当前交易是否已经在表里存在。如果存在不做处理，不存在则做新增
		MsaBaseOriginReconFile oriFile = new MsaBaseOriginReconFile();
		oriFile.setOrderId(Long.valueOf(reconcilFile.getOrderId()));
		oriFile = msaBaseOriginReconFileMapper.selectOne(oriFile);
		if (oriFile == null) {
			
			if (TRAN_TYPE_DAIFU.equals(reconcilFile.getTranType())) {	//代付的直接跳过不做处理。
				return;
			}
			oriFile = new MsaBaseOriginReconFile();
			oriFile.setId(snowFlake.nextId());
			oriFile.setFileCreateDate(reconcilFile.getAcctDate());
			oriFile.setTranDate(reconcilFile.getTranDate());
			oriFile.setTranTime(reconcilFile.getTranTime());
			oriFile.setSysMchNo(reconcilFile.getMchId());
			oriFile.setTranAmt(new BigDecimal(reconcilFile.getTranAmt()));
			oriFile.setFeeAmt(new BigDecimal(reconcilFile.getFeeAmt()));
			oriFile.setPayProduct(reconcilFile.getPayPro());
			oriFile.setTranType(reconcilFile.getTranType());
			oriFile.setOrderId(Long.valueOf(reconcilFile.getOrderId()));
			if (TRAN_TYPE_REFUND.equals(reconcilFile.getTranType())) {
				oriFile.setOriginOrderId(Long.valueOf(reconcilFile.getOriOrderId()));
				oriFile.setOriginTranDate(reconcilFile.getOriTranDate());
			}
			oriFile.setPayChannel(PayChannel.UnionPay.toString());	//银联实现类写死银联
			oriFile.setCheckFlag(IsFlag.Fou.toString());
			if (msaBaseOriginReconFileMapper.insert(oriFile)!=1) {
				throw new BusinessException("保存对账文件信息失败。订单ID：" + oriFile.getOrderId());
			}
			
		}
	}
	
	/**
	 * 对账处理
	 * 跟订单表中已经完成的交易做匹配，能匹配上的记录对账明细和汇总
	 */
	@Override
	public void runReconcil() {
		
		ReconcilDTO dto = groupCheckDetail();	//匹配交易，分组对账明细。如果匹配不上，或者匹配上的有其他问题则保存有问题的记录
		saveCheckDetail(dto);	//保存对账明细
		
	}
	
	/**
	 * 分组对账明细，并且记录有问题的对账明细
	 * @return
	 */
	private ReconcilDTO groupCheckDetail() {
		
		init();
		MsaBaseOriginReconFile file = new MsaBaseOriginReconFile();
		file.setCheckFlag(IsFlag.Fou.toString());
		file.setPayChannel(PayChannel.UnionPay.toString());	//银联实现类只查银联渠道的
		
		Map<String, List<MsaBaseCheckDetail>> checkMap = new HashMap<>(2<<10);	//key为sect_id+"_"+"_"+mch_id+"_"+entity_id，value为对应的对账明细集合
		Map<Long, String> sectMap = new HashMap<>();	//key->sect_id, value->sect_name
		Map<Long, String> cspMap = new HashMap<>();		//key->csp_id, value->csp_name
		Map<Long, Long> cspSectMap = new HashMap<>();	//key->sect_id, value->csp_id
		
		ReconcilDTO dto = new ReconcilDTO();
		dto.setCheckMap(checkMap);
		dto.setSectMap(sectMap);
		dto.setCspMap(cspMap);
		dto.setCspSectMap(cspSectMap);
		
		//获取未对账交易列表
		List<MsaBaseOriginReconFile> fileList = msaBaseOriginReconFileMapper.select(file);
		for (MsaBaseOriginReconFile reconFile : fileList) {
			
			if (IsFlag.Shi.toString().equals(reconFile.getCheckFlag())) {	//已对过帐的跳过
				continue;
			}
			
			TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());	//开新事务
			try {
				MsaTradePayOrder order = new MsaTradePayOrder();
				//先确定交易类型，是消费还是退款
				if (TRAN_TYPE_FUND.equals(reconFile.getTranType())) {	//消费处理	
					order.setId(reconFile.getOrderId());
				}else if (TRAN_TYPE_REFUND.equals(reconFile.getTranType())) {	//退货处理
					order.setId(reconFile.getOriginOrderId());	//退款根据原交易ID查找
				}
				
				order = msaTradePayOrderMapper.selectByPrimaryKey(order);	//查询订单表
				if (order == null) {	//未在订单表中查询到，说明漏交易
					reconFile.setRemark("漏交易");
					if (msaBaseOriginReconFileMapper.updateByPrimaryKey(reconFile)!=1) {
						throw new BusinessException("更新对账文件原始信息表失败， id : " + reconFile.getId());
					}
					
				}else {	//能匹配上的，先set对账明细
					
					MsaBaseCheckDetail detail = new MsaBaseCheckDetail();
					detail.setId(snowFlake.nextId());
					detail.setOrderId(reconFile.getOrderId());
					detail.setTranAmt(reconFile.getTranAmt());
					String payProStr = reconFile.getPayProduct();
					String reconPayMethod = payProductMap.get(payProStr);
					String payMethod = order.getPayMethod();
					if (!reconPayMethod.equals(payMethod)) {
						logger.error("对账文件记录的支付方式与实际交易记录不符， 交易ID ： " + order.getId() + ", 实际支付方式： " + reconPayMethod);
					}
					if (reconFile.getTranAmt().compareTo(order.getTranAmt())!=0) {	//文件金额与订单表中金额不符
						reconFile.setRemark("对账文件交易金额与订单表中交易金额不符。交易ID ：" + order.getId());
						if (msaBaseOriginReconFileMapper.updateByPrimaryKey(reconFile)!=1) {
							throw new BusinessException("更新对账文件原始信息表失败， id : " + reconFile.getId());
						}
					}
					detail.setPayMethod(payProductMap.get(payProStr));
					detail.setPayProduct(order.getPayProduct());
					detail.setTranDate(reconFile.getTranDate());
					detail.setTranTime(reconFile.getTranTime());
					detail.setConsultRate(order.getConsultRate());
					detail.setConsultAmt(order.getConsultAmt());
					detail.setChannelRate(FeeCalculator.calculateFeeRate(reconFile.getFeeAmt(), reconFile.getTranAmt()));
					detail.setChannelAmt(reconFile.getFeeAmt());
					detail.setMchNo(order.getMchNo());
					detail.setSectName(order.getSectName());
					detail.setSectId(order.getSectId());
					detail.setCspName(order.getCspName());
					detail.setCspId(order.getCspId());
					
					if (TRAN_TYPE_REFUND.equals(reconFile.getTranType())) {	//退货处理
						detail.setOriginOrderId(reconFile.getOriginOrderId());	//原交易ID
						detail.setOriginTranDate(reconFile.getOriginTranDate());	//原交易日期
					}
					
					Long sect_id = order.getSectId();	//小区ID
					Long entity_id = order.getEntityId(); 	//清算实体ID
					Long mch_id = order.getMchId();	//商户号
					
					if (!sectMap.containsKey(sect_id)) {
						sectMap.put(sect_id, order.getSectName());
					}
					if (!cspMap.containsKey(order.getCspId())) {
						cspMap.put(order.getCspId(), order.getCspName());
					}
					if (!cspSectMap.containsKey(sect_id)) {
						cspSectMap.put(sect_id, order.getCspId());
					}
					
					String key = sect_id + "_" + mch_id + "_" + entity_id;	//key为sect_id+"_"+mch_id+"_"+entity_id，value为对应的对账明细集合
					if (!checkMap.containsKey(key)) {
						List<MsaBaseCheckDetail> detailList = new ArrayList<>();
						detailList.add(detail);
						checkMap.put(key, detailList);
					}else {
						List<MsaBaseCheckDetail> detailList = checkMap.get(key);
						detailList.add(detail);
					}
					
				}
				
				transactionManager.commit(status);
				
			} catch (Exception e) {

				logger.error(e.getMessage(), e);
				transactionManager.rollback(status);
			}
			
		}
		
		return dto;
	}

	/**
	 * 保存对账明细
	 * @param dto
	 */
	private void saveCheckDetail(ReconcilDTO dto) {
		
		Map<String, List<MsaBaseCheckDetail>> checkMap = dto.getCheckMap();
		Map<Long, String> sectMap = dto.getSectMap();
		Map<Long, String> cspMap = dto.getCspMap();
		Map<Long, Long> cspSectMap = dto.getCspSectMap();
		Iterator<Map.Entry<String, List<MsaBaseCheckDetail>>> it = checkMap.entrySet().iterator();
		while(it.hasNext()) {
			
			TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());	//开新事务，一条汇总一个事务
			
			try {
				Map.Entry<String, List<MsaBaseCheckDetail>> entry = it.next();
				String key = entry.getKey();
				List<MsaBaseCheckDetail> detailList = entry.getValue();
				
				//保存对账汇总
				long sumId = snowFlake.nextId();	//生成对账汇总ID
				BigDecimal sumTranAmt = BigDecimal.ZERO;
				BigDecimal sumAcctAmt = BigDecimal.ZERO;
				int totalCount = 0;
				
				//循环保存明细
				for (MsaBaseCheckDetail msaBaseCheckDetail : detailList) {
					
					try {
						
						/*汇总累计 start*/
						sumTranAmt = sumTranAmt.add(msaBaseCheckDetail.getTranAmt());	//汇总 应结算金额
						BigDecimal unitAcctAmt = msaBaseCheckDetail.getTranAmt().subtract(msaBaseCheckDetail.getChannelAmt());	//TODO 是不是这么减？
						sumAcctAmt = sumAcctAmt.add(unitAcctAmt);	//汇总 到账金额
						totalCount++;	//明细笔数+1
						/*汇总累计 end*/
						
						/*更新对账文件状态 start*/
						MsaBaseOriginReconFile oriFile = new MsaBaseOriginReconFile();
						oriFile.setOrderId(msaBaseCheckDetail.getOrderId());	//根据交易订单号查询出 对应的 原始对账文件信息
						oriFile = msaBaseOriginReconFileMapper.selectOne(oriFile);
						oriFile.setCheckFlag(IsFlag.Shi.toString());	//将文件状态置为已对账
						
						if (msaBaseOriginReconFileMapper.updateByPrimaryKey(oriFile)!=1) {
							throw new BusinessException("更新原始对账文件状态失败！支付订单ID：" + oriFile.getOrderId());
						}
						/*更新对账文件状态 end*/
						
						if (190429170117993890l == oriFile.getOrderId()) {
							throw new BusinessException("test rollback 1");
						}
						
						/*保存对账明细 start */
						msaBaseCheckDetail.setCheckId(sumId);		//设置对账汇总ID
						if (msaBaseCheckDetailMapper.insert(msaBaseCheckDetail)!=1) {	
							throw new BusinessException("保存对账明细失败！支付订单ID：" + msaBaseCheckDetail.getOrderId());
						}
						/*保存对账明细 end */
						
						
					} catch (Exception e) {
						
						logger.error(e.getMessage(), e);
					}
					
					
				}
				
				/*保存对账汇总。 同个小区同个清算实体保存一条*/
				String[]tmpKey = key.split("_");
				String sect_id = tmpKey[0];
				String mch_id = tmpKey[1];	
				String entity_id = tmpKey[2];	//entity_id可能建立交易时还未填上，所以此处可能未空。
				
				MsaBaseCheckSum checkSum = new MsaBaseCheckSum();
				checkSum.setId(sumId);
				checkSum.setShouldPayAmt(sumTranAmt);
				checkSum.setShouldDate(DateUtil.getSysDate());	//TODO 到底那天
				checkSum.setAccountAmt(sumAcctAmt);
				checkSum.setAccountDate(DateUtil.getSysDate());	//TODO
				checkSum.setPayNum(totalCount);
				checkSum.setAccountStatus(AccountStatus.WeiJieSuan.toString());
				if ("null".equalsIgnoreCase(entity_id)) {	//如果清算实体id为空，则查询商户表
					MsaBaseAcctInfo acctInfo = unionPayReconcilMapper.getAcctEntityByTrade(MchStatus.QiYong.toString(), mch_id, sect_id);
					Long entityId = acctInfo.getId();	//清算实体ID
					if (entityId == null) {	//仍旧没有填写
						//TODO ???如何处理 。 如果跳过是否删除刚才已保存的明细
						continue;
					}else {
						entity_id = String.valueOf(entityId);
					}
				}
				checkSum.setEntityId(Long.valueOf(entity_id));	//清算实体ID
				
				//如果清算实体ID不为空，则填写以下字段
				MsaBaseAcctInfo acctInfo = new MsaBaseAcctInfo();
				acctInfo.setId(checkSum.getEntityId());	//根据清算实体ID找出结算账户信息
				acctInfo = msaBaseAcctInfoMapper.selectByPrimaryKey(acctInfo);
				checkSum.setEntityName(acctInfo.getEntityName());	//实体名称
				checkSum.setAccountNo(acctInfo.getAccountNo());	//结算账户
				
				checkSum.setSectId(Long.valueOf(sect_id));
				checkSum.setCspId(cspSectMap.get(Long.valueOf(sect_id)));
				checkSum.setSectName(sectMap.get(checkSum.getSectId()));
				checkSum.setCspName(cspMap.get(checkSum.getCspId()));
				
				if (msaBaseCheckSumMapper.insert(checkSum)!=1) {
					throw new BusinessException("保存对账汇总表失败。");
				}
				
				transactionManager.commit(status);
				
			} catch (Exception e) {

				logger.error(e.getMessage(), e);
			}
			
		}
	}


	
}
