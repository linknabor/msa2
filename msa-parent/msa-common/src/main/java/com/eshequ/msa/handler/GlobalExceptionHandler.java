package com.eshequ.msa.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshequ.msa.common.BaseResult;
import com.eshequ.msa.common.ResultCode;
import com.eshequ.msa.exception.AppSysException;
import com.eshequ.msa.exception.BusinessException;

@ControllerAdvice("com.eshequ.msa")
@ResponseBody
public class GlobalExceptionHandler<T> {
	
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(BusinessException.class)
    public BaseResult businessExceptionHandler(BusinessException exception) {
        logger.error(exception.getMessage(), exception);
        if (Integer.MIN_VALUE < exception.getCode()) {
        	return BaseResult.failure(ResultCode.FAILURE(exception.getCode()+"", exception.getMessage()));
		}
        return BaseResult.failure(ResultCode.FAILURE(exception.getMessage()));
    }
    
	@ExceptionHandler(AppSysException.class)
    public BaseResult appSysExceptionHandler(AppSysException exception) {
        logger.error(exception.getMessage(), exception);
        if (Integer.MIN_VALUE < exception.getCode()) {
        	return BaseResult.failure(ResultCode.FAILURE(exception.getCode()+"", exception.getMessage()));
		}
        return BaseResult.failure(ResultCode.FAILURE(exception.getMessage()));
    }

	@ExceptionHandler(Exception.class)
    public BaseResult globalExceptionHandler(Exception exception) {
        logger.error(exception.getMessage(), exception);
        return BaseResult.failure(ResultCode.FAILURE(exception.getMessage()));
    }
    
	@ExceptionHandler(Throwable.class)
    public BaseResult globalThrowableHandler(Throwable throwable) {
        logger.error(throwable.getMessage(), throwable);
        return BaseResult.failure(ResultCode.FAILURE(throwable.getMessage()));
    }
    
}
