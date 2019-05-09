package com.eshequ.msa.crm.config;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

import com.eshequ.msa.crm.filter.SsoSessionFilter;

@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig extends AbstractHttpSessionApplicationInitializer {

    @Value(value = "${spring.redis.host}")
    private String host;
    @Value(value = "${spring.redis.port}")
    private Integer port;
    @Value(value = "${spring.redis.database}")
    private Integer database;
    @Value(value = "${spring.redis.password}")
    private String password;
    
    @Bean
    public JedisConnectionFactory connectionFactory() {
    	JedisConnectionFactory factory = new JedisConnectionFactory(redisStandaloneConfiguration());
    	return factory;
    }
    
    @Bean
    public RedisStandaloneConfiguration redisStandaloneConfiguration() {
    	
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setDatabase(database);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
        return redisStandaloneConfiguration;
    }
    
    /**
     * If you are using @EnableRedisHttpSession the SessionMessageListener and enabling the necessary Redis Keyspace events is done automatically. 
     * However, in a secured Redis enviornment the config command is disabled. 
     * This means that Spring Session cannot configure Redis Keyspace events for you. 
     * To disable the automatic configuration add ConfigureRedisAction.NO_OP as a bean.
     * @return
     */
    @Bean
    public static ConfigureRedisAction configureRedisAction() {
    	return ConfigureRedisAction.NO_OP;
    }
    
    
    /**
     * 这个类在sessionRepositoryFilter之前装载，里面加载的filter优先级顺序如果比sessionRepositoryFilter高，会先加载这个类
     * 如果filter不在这里装载，优先级顺序即便比sessionRepositoryFilter高，也会比其后加载
     */
    @Override
	protected void beforeSessionRepositoryFilter(ServletContext servletContext) {

		servletContext.addFilter("ssoSessionFilter", SsoSessionFilter.class);
		super.beforeSessionRepositoryFilter(servletContext);
	}
    
    
}
