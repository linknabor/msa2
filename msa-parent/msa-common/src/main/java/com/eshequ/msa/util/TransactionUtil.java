/**
 * 
 */
package com.eshequ.msa.util;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 复杂事务调用这个类。普通事务请用{@link Transactional}
 * 用法：transactionUtil.transact(consumer->function()));
 * @author huym
 * @param <T>
 *
 */
@Component
public class TransactionUtil<T> {
	
	private static final Logger logger = LoggerFactory.getLogger(TransactionUtil.class);

	@Autowired
	private PlatformTransactionManager transactionManager;

	public boolean transact(Consumer<T> consumer) {
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			consumer.accept(null);
			transactionManager.commit(status);
			return true;
		} catch (Exception e) {
			transactionManager.rollback(status);
			logger.error(e.toString());
			return false;
		}

	}

}
