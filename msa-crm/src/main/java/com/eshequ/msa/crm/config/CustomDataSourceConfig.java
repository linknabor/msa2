/**
 * 
 */
package com.eshequ.msa.crm.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

/**
 * 从数据源
 * @author davidhardson
 *
 */
@Configuration
@MapperScan(basePackages = "com.eshequ.msa.codes.mapper", sqlSessionFactoryRef = "customSqlSessionFactory")
public class CustomDataSourceConfig {
	
	@Value("${mybatis.mapper.resource}")
	private String mapperResource;
	
	@Autowired
	private Environment env;

	@Bean(name = "customDataSource")
	public DataSource customDataSource() {
		DataSource dataSource = DruidDataSourceBuilder.create().build(env, "spring.datasource.druid.two.");
		return dataSource;
	}
	
    @Bean(name = "customTransactionManager")
    public DataSourceTransactionManager customTransactionManager() {
        return new DataSourceTransactionManager(customDataSource());
    }

    @Bean(name = "customSqlSessionFactory")
    public SqlSessionFactory customSqlSessionFactory(@Qualifier("customDataSource") DataSource customDataSource)
            throws Exception {
    	
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(customDataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources(mapperResource));
        return sessionFactory.getObject();
    }
	
	
}
