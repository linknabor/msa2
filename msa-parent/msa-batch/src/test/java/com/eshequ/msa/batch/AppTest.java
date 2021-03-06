package com.eshequ.msa.batch;

import java.io.File;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.eshequ.msa.batch.config.AppInit;
import com.eshequ.msa.batch.mapper.normal.MsaBaseAcctInfoMapper;
import com.eshequ.msa.batch.mapper.normal.MsaBaseMchInfoMapper;
import com.eshequ.msa.batch.mapper.normal.MsaRelateAcctSectMapper;
import com.eshequ.msa.batch.mapper.normal.MsaTradePayOrderMapper;
import com.eshequ.msa.batch.model.MsaBaseAcctInfo;
import com.eshequ.msa.batch.model.MsaBaseMchInfo;
import com.eshequ.msa.batch.model.MsaRelateAcctSect;
import com.eshequ.msa.batch.model.MsaTradePayOrder;
import com.eshequ.msa.batch.service.reconciliation.ReconcilFactory;
import com.eshequ.msa.batch.service.reconciliation.ReconcilService;
import com.eshequ.msa.batch.service.reconciliation.cfg.ReconcilCfg;
import com.eshequ.msa.batch.service.reconciliation.dto.ReconcilFileBody;
import com.eshequ.msa.batch.service.reconciliation.dto.ReconcilFileDTO;
import com.eshequ.msa.batch.service.tranlsnr.TranDTO;
import com.eshequ.msa.batch.service.tranlsnr.TranlsnrStarter;
import com.eshequ.msa.codes.MchStatus;
import com.eshequ.msa.codes.MergerStatus;
import com.eshequ.msa.codes.PayChannel;
import com.eshequ.msa.codes.PayMethod;
import com.eshequ.msa.util.BeanUtil;
import com.eshequ.msa.util.DateUtil;
import com.eshequ.msa.util.FeeCalculator;
import com.eshequ.msa.util.ReflectUtil;
import com.eshequ.msa.util.SnowFlake;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 * 
 * @param <T>
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
	private MsaRelateAcctSectMapper msaRelateAcctSectMapper;

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

		MsaBaseAcctInfo msaBaseAcctInfo = new MsaBaseAcctInfo();
		msaBaseAcctInfo.setId(snowFlake.nextId());
		msaBaseAcctInfo.setLiquidationCycle(1);
		msaBaseAcctInfo.setEntityName("测试商户");
		msaBaseAcctInfo.setBankName("兴业银行");
		msaBaseAcctInfo.setAccountName("格拉摩根伯爵");
		msaBaseAcctInfo.setAccountNo("6212345672345723");
		msaBaseAcctInfo.setPhone("1234512578324");
		msaBaseAcctInfo.setStatus(MchStatus.QiYong.toString());
		msaBaseAcctInfo.setDataSource("1");
		msaBaseAcctInfo.setCompanyName("格拉摩根伯爵");
		msaBaseAcctInfo.setCompanyId(130320100000000059l);
		msaBaseAcctInfo.setCityId(0l);
		msaBaseAcctInfo.setProvinceId(1l);
		msaBaseAcctInfoMapper.insert(msaBaseAcctInfo);

		// 创建客户商户关系
		MsaRelateAcctSect msaRelateAcctSect = new MsaRelateAcctSect();
		msaRelateAcctSect.setAcctId(msaBaseAcctInfo.getId());
		msaRelateAcctSect.setSectId(130320100000000083l);
		msaRelateAcctSectMapper.insert(msaRelateAcctSect);

		msaBaseAcctInfo = new MsaBaseAcctInfo();
		msaBaseAcctInfo.setId(snowFlake.nextId());
		msaBaseAcctInfo.setLiquidationCycle(1);
		msaBaseAcctInfo.setEntityName("测试商户2");
		msaBaseAcctInfo.setBankName("兴业银行");
		msaBaseAcctInfo.setAccountName("兰开斯特公爵");
		msaBaseAcctInfo.setAccountNo("6289345672345724");
		msaBaseAcctInfo.setPhone("2345675678543");
		msaBaseAcctInfo.setStatus(MchStatus.QiYong.toString());
		msaBaseAcctInfo.setDataSource("1");
		msaBaseAcctInfo.setCompanyName("兰开斯特公爵");
		msaBaseAcctInfo.setCompanyId(130320100000000059l);
		msaBaseAcctInfo.setCityId(0l);
		msaBaseAcctInfo.setProvinceId(1l);
		msaBaseAcctInfoMapper.insert(msaBaseAcctInfo);

		// 创建客户商户关系
		msaRelateAcctSect = new MsaRelateAcctSect();
		msaRelateAcctSect.setAcctId(msaBaseAcctInfo.getId());
		msaRelateAcctSect.setSectId(130320100000000083l);
		msaRelateAcctSectMapper.insert(msaRelateAcctSect);

	}

	@Test
	public void testDownloadFile() {

		String beginDate = "20190606";
		String endDate = DateUtil.getSysDate();
		while (DateUtil.dateMargin(beginDate, endDate) >= 0) {
			ReconcilService reconcilService = reconcilFactory.getCollectionInstance(ReconcilCfg.UnionPay);
			reconcilService.downloadFile(beginDate);
			beginDate = DateUtil.getNextDateByNum(beginDate, 1);
		}
	}

	@Test
	public void createTrade() {

		String folderPath = "D:\\unionpay\\data\\";
		File folder = new File(folderPath);
		String[] files = folder.list();
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
					BigDecimal consultAmt = FeeCalculator.calculateConsultAmt(new BigDecimal(detail.getTranAmt()),
							order.getConsultRate());
					order.setConsultAmt(consultAmt);
					order.setTranAmt(new BigDecimal(detail.getTranAmt()));
					String payPro = detail.getPayPro();
					String paymethod = PayMethod.WechatMicropay.toString();
					if (payPro.contains("微信")) {
						// do nothing
					} else {
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

					MsaBaseMchInfo mchInfo = msaBaseMchInfoMapper.selectByPrimaryKey(111302473800617984l);
					order.setMchId(mchInfo.getId());
					order.setMchNo(mchInfo.getMchNo());
					order.setMchName(mchInfo.getMchName());
					order.setMchAbbre(mchInfo.getMchAbbre());
					order.setPayChannel(PayChannel.UnionPay.toString());

					MsaBaseAcctInfo acctInfo = msaBaseAcctInfoMapper.selectByPrimaryKey(111303355682394112l);
					order.setAccountName(acctInfo.getAccountName());
					order.setAccountNo(acctInfo.getAccountNo());

					msaTradePayOrderMapper.insert(order);

				}

			}

		}

	}

	/**
	 * 文件落表
	 */
	@Test
	public void testSaveFile() {

		String folderPath = "D:\\unionpay\\data\\";
		File folder = new File(folderPath);
		String[] files = folder.list();
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
	
	@Test
	public void insertBean() {
		try {
			MsaTradePayOrder order = msaTradePayOrderMapper.selectByPrimaryKey(190528100468646329l);
			TranDTO<MsaTradePayOrder> dto = new TranDTO<>();
			dto.setEntity(order);
			dto.setEntityName("MsaTradePayOrder");
			
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(dto);
			
			JsonNode jsonNode = objectMapper.readTree(json);
			List<String> node = jsonNode.findValuesAsText("entityName");
			String entityName = node.get(0);
			System.out.println(node);
			
			JsonNode entityNode = jsonNode.findValue("entity");
			Object o = objectMapper.readValue(entityNode.toString(), ReflectUtil.getNamespace(entityName));
			Class<?> clazz = ReflectUtil.getMapper(entityName);
			
			Method method = clazz.getMethod("insert", Object.class);
			method.invoke(BeanUtil.getBean(clazz), o);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Autowired
	private TranlsnrStarter tranlsnrStarter;
	
	@Test
	public void testQueue() {
		
		tranlsnrStarter.startLisnter();
	}



}
