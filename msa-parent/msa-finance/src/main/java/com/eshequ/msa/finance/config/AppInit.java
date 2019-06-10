package com.eshequ.msa.finance.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

//多数据源需要exclude后面那2个类，不然spring会自动配置一个数据源
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class })
@ComponentScan("com.eshequ.msa")
@EnableSwagger2
public class AppInit {
	
	public static void main(String[] args) {
		
		SpringApplication app = new SpringApplication(AppInit.class);
	    app.run(args);
		
	}
	
}
