package com.eshequ.msa.batch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.eshequ.msa.batch.service.reconciliation.ReconcilStarter;
import com.eshequ.msa.util.DateUtil;

//多数据源需要exclude后面那2个类，不然spring会自动配置一个数据源
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})	
@ComponentScan({"com.eshequ.msa", "com.eshequ.msa.batch"})
public class AppInit implements CommandLineRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(AppInit.class);

	public static void main(String[] args) {

//		args = new String[2];
//		args[0] = "1";
//		args[1] = "20190510";
		
		SpringApplication app = new SpringApplication(AppInit.class);
	    app.run(args);
	    System.exit(0);
		
	}
	
	@Autowired
	private ReconcilStarter reconcilStarter;
	
	@Override
	public void run(String... args) throws Exception {
		
//		if (args == null) {
//			logger.warn("输入参数为空，默认开始日期为当天的对账...");
//			reconcilStarter.start(DateUtil.getSysDate());
//		}else if (args.length == 1) {
//			logger.warn("输入参数： " + args[0] + ", 开始" + args[0] + "的对账...");
//			reconcilStarter.start(args[0]);
//		}else if (args.length == 2) {
//			logger.warn("输入参数：" + args[0] + ", " + args[1]);
//			if ("1".equals(args[0])) {
//				logger.warn("开始日期为"+args[1]+"的对账...");
//				reconcilStarter.start(args[1]);
//			}else if ("2".equals(args[0])) {
//				logger.warn("开始删除日期为"+args[1]+"的对账...");
//				reconcilStarter.del(args[1]);
//			}else if ("3".equals(args[0])) {
//				logger.warn("开始监听交易同步。");
//				//TODO
//			}else {
//				logger.error("参数错误！");
//			}
//		}else {
//			logger.error("参数错误！");
//			
//		}
		
	}
	
}
