package com.eshequ.msa.reconciliation;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.eshequ.msa.codes.MchStatus;
import com.eshequ.msa.codes.MergerStatus;
import com.eshequ.msa.codes.PayChannel;
import com.eshequ.msa.codes.PayMethod;
import com.eshequ.msa.reconciliation.config.AppInit;
import com.eshequ.msa.reconciliation.mapper.normal.MsaBaseAcctInfoMapper;
import com.eshequ.msa.reconciliation.mapper.normal.MsaBaseMchInfoMapper;
import com.eshequ.msa.reconciliation.mapper.normal.MsaRelateMchCustMapper;
import com.eshequ.msa.reconciliation.mapper.normal.MsaTradePayOrderMapper;
import com.eshequ.msa.reconciliation.model.MsaBaseAcctInfo;
import com.eshequ.msa.reconciliation.model.MsaBaseMchInfo;
import com.eshequ.msa.reconciliation.model.MsaRelateMchCust;
import com.eshequ.msa.reconciliation.model.MsaTradePayOrder;
import com.eshequ.msa.reconciliation.service.ReconcilFactory;
import com.eshequ.msa.reconciliation.service.ReconcilService;
import com.eshequ.msa.reconciliation.service.cfg.ReconcilCfg;
import com.eshequ.msa.reconciliation.service.dto.ReconcilFileBody;
import com.eshequ.msa.reconciliation.service.dto.ReconcilFileDTO;
import com.eshequ.msa.util.DateUtil;
import com.eshequ.msa.util.FeeCalculator;
import com.eshequ.msa.util.SnowFlake;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 * @param <T>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInit.class)
public class AppTest extends TestCase {

	@Autowired
	private SnowFlake snowFlake;
	@Autowired
	private MsaBaseMchInfoMapper msaBaseMchInfoMapper;
	@Autowired
	private MsaBaseAcctInfoMapper msaBaseAcctInfoMapper;
	@Autowired
	private ReconcilFactory reconcilFactory;
	@Autowired
	private MsaTradePayOrderMapper msaTradePayOrderMapper;
	@Autowired
	private MsaRelateMchCustMapper msaRelateMchCustMapper;
	
	/**
	 * 添加商户测试数据
	 */
	@Test
	public void createMch() {
		
		MsaBaseMchInfo msaBaseMchInfo = new MsaBaseMchInfo();
		msaBaseMchInfo.setId(snowFlake.nextId());
		msaBaseMchInfo.setMchNo("888290059501308");
		msaBaseMchInfo.setMchStatus(MchStatus.QiYong.toString());
		msaBaseMchInfo.setMchName("测试商户");
		msaBaseMchInfo.setMchAbbre("测试商户");
		msaBaseMchInfo.setMethodType("1");
		msaBaseMchInfo.setPayChannel(PayChannel.UnionPay.toString());
		msaBaseMchInfo.setLinkMan("格拉摩根伯爵");
		msaBaseMchInfo.setCertNo("123456789");
		msaBaseMchInfo.setTel("1234567812345");
		msaBaseMchInfo.setEmail("aaa@aa.com");
		msaBaseMchInfo.setCustomerTel("1234571234572");
		msaBaseMchInfo.setIndustryType("1");
		msaBaseMchInfo.setConsultRate(new BigDecimal("0.03"));
		msaBaseMchInfo.setConsultLimit(new BigDecimal("1"));
		msaBaseMchInfo.setDataSource("1");
		msaBaseMchInfoMapper.insert(msaBaseMchInfo);
		
	}
	
	/**
	 * 创建账户信息
	 */
	@Test
	public void createAccount() {
		
//		MsaBaseAcctInfo msaBaseAcctInfo = new MsaBaseAcctInfo();
//		msaBaseAcctInfo.setId(SnowFlake.nextId());
//		msaBaseAcctInfo.setLiquidationCycle(1);
//		msaBaseAcctInfo.setEntityName("测试商户");
//		msaBaseAcctInfo.setBankName("兴业银行");
//		msaBaseAcctInfo.setAccountName("格拉摩根伯爵");
//		msaBaseAcctInfo.setAccountNo("6212345672345723");
//		msaBaseAcctInfo.setPhone("1234512578324");
//		msaBaseAcctInfo.setStatus(MchStatus.QiYong.toString());
//		msaBaseAcctInfo.setDataSource("1");
//		msaBaseAcctInfo.setCspName("格拉摩根伯爵");
//		msaBaseAcctInfo.setCompanyId(130320100000000059l);
//		msaBaseAcctInfo.setCityId(0l);
//		msaBaseAcctInfo.setProvinceId(1l);
//		msaBaseAcctInfoMapper.insert(msaBaseAcctInfo);
//		
//		//创建客户商户关系
//		MsaRelateMchCust msaRelateMchCust = new MsaRelateMchCust();
//		msaRelateMchCust.setEntityId(msaBaseAcctInfo.getId());
//		msaRelateMchCust.setMchId(109495968801624064l);
//		msaRelateMchCust.setCustId(130320100000000083l);
//		msaRelateMchCustMapper.insert(msaRelateMchCust);
		
		MsaBaseAcctInfo msaBaseAcctInfo = new MsaBaseAcctInfo();
		msaBaseAcctInfo.setId(snowFlake.nextId());
		msaBaseAcctInfo.setLiquidationCycle(1);
		msaBaseAcctInfo.setEntityName("测试商户2");
		msaBaseAcctInfo.setBankName("兴业银行");
		msaBaseAcctInfo.setAccountName("兰开斯特公爵");
		msaBaseAcctInfo.setAccountNo("6289345672345724");
		msaBaseAcctInfo.setPhone("2345675678543");
		msaBaseAcctInfo.setStatus(MchStatus.QiYong.toString());
		msaBaseAcctInfo.setDataSource("1");
		msaBaseAcctInfo.setCspName("兰开斯特公爵");
		msaBaseAcctInfo.setCompanyId(130320100000000059l);
		msaBaseAcctInfo.setCityId(0l);
		msaBaseAcctInfo.setProvinceId(1l);
		msaBaseAcctInfoMapper.insert(msaBaseAcctInfo);
		
		//创建客户商户关系
		MsaRelateMchCust msaRelateMchCust = new MsaRelateMchCust();
		msaRelateMchCust.setEntityId(msaBaseAcctInfo.getId());
		msaRelateMchCust.setMchId(109495968801624064l);
		msaRelateMchCust.setCustId(130320100000000084l);
		msaRelateMchCustMapper.insert(msaRelateMchCust);
		
	}
	
	
	@Test
	public void testDownloadFile() {
		
		String beginDate = "20190606";
		String endDate = DateUtil.getSysDate();
		while(DateUtil.dateMargin(beginDate, endDate) >= 0) {
			ReconcilService reconcilService = reconcilFactory.getCollectionInstance(ReconcilCfg.UnionPay);
			reconcilService.downloadFile(beginDate);
			beginDate = DateUtil.getNextDateByNum(beginDate, 1);
		}
	}
	
	@Test
	public void createTrade() {
		
		String folderPath = "D:\\unionpay\\data\\";
		File folder = new File(folderPath);
		String[]files = folder.list();
		for (String file : files) {
			
			String filePath = folderPath + file;
			ReconcilService reconcilService = reconcilFactory.getCollectionInstance(ReconcilCfg.UnionPay);
			ReconcilFileDTO dto = reconcilService.parseFile(filePath);
			if (dto == null) {
				continue;
			}
			List<ReconcilFileBody> detailList = dto.getBody();
			for (ReconcilFileBody detail : detailList) {
				
				if ("消费".equals(detail.getTranType())) {
					MsaTradePayOrder order = new MsaTradePayOrder();
					order.setId(Long.valueOf(detail.getOrderId()));
					order.setTranStatus(MergerStatus.YiZhiFu.toString());
					order.setConsultRate(new BigDecimal("0.03"));
					BigDecimal consultAmt = FeeCalculator.calculateConsultAmt(new BigDecimal(detail.getTranAmt()), order.getConsultRate());
					order.setConsultAmt(consultAmt);
					order.setTranAmt(new BigDecimal(detail.getTranAmt()));
					String payPro = detail.getPayPro();
					String paymethod = PayMethod.WechatMicropay.toString();
					if (payPro.contains("微信")) {
						//do nothing
					}else {
						paymethod = PayMethod.AliPayMicropay.toString();
					}
					order.setPayMethod(paymethod);
					order.setTranDate(detail.getTranDate());
					order.setTranTime(detail.getTranTime());
					order.setAcctDate(detail.getTranDate());
					order.setAcctTime(detail.getTranTime());
					order.setCspId(130320100000000059l);
					order.setCspName("演示物业");
					order.setSectId(130320100000000083l);
					order.setSectName("森兰明轩（演示）");
					order.setStaffName("瓦拉几亚国王");
					order.setOrderAttach(detail.getRemark());
					
					MsaBaseMchInfo mchInfo = msaBaseMchInfoMapper.selectByPrimaryKey(109495968801624064l);
					order.setMchId(mchInfo.getId());
					order.setMchNo(mchInfo.getMchNo());
					order.setMchName(mchInfo.getMchName());
					order.setMchAbbre(mchInfo.getMchAbbre());
					order.setPayChannel(PayChannel.UnionPay.toString());
					
					MsaBaseAcctInfo acctInfo = msaBaseAcctInfoMapper.selectByPrimaryKey(109496103799492608l);
					order.setAccountName(acctInfo.getAccountName());
					order.setAccountNo(acctInfo.getAccountNo());
					
					msaTradePayOrderMapper.insert(order);
					
					
				}
				
			}
			
		}
		
	}
	
	/**
	 *文件落表
	 */
	@Test
	public void testSaveFile() {
		
		String folderPath = "D:\\unionpay\\data\\";
		File folder = new File(folderPath);
		String[]files = folder.list();
		for (String file : files) {
			
			String filePath = folderPath + file;
			ReconcilService reconcilService = reconcilFactory.getCollectionInstance(ReconcilCfg.UnionPay);
			ReconcilFileDTO dto = reconcilService.parseFile(filePath);
			if (dto == null) {
				continue;
			}
			reconcilService.saveFile2DB(dto);
		}
		
	}
	
	/**
	 * 对账
	 */
	@Test
	public void runReconcil() {
			
		ReconcilService reconcilService = reconcilFactory.getCollectionInstance(ReconcilCfg.UnionPay);
		reconcilService.runReconcil();
	}
	
	
	
}
