package com.eshequ.msa.reconciliation.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//多数据源需要exclude后面那2个类，不然spring会自动配置一个数据源
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})	
@ComponentScan({"com.eshequ.msa", "com.eshequ.msa.reconciliation"})
public class AppInit implements CommandLineRunner {

	public static void main(String[] args) {
		
		SpringApplication app = new SpringApplication(AppInit.class);
	    app.run(args);
		
	}
	
	@Override
	public void run(String... args) throws Exception {

		//TODO
	}
	
}
