package com.eshequ.msa.exception;


/**
 * <p>标    题: 核心框架</p>
 * <p>描    述: </p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2010-12-13 上午11:23:42</p>
 * @author 产品开发部
 * @version 2.0
 * NotFoundException
 */
public class NotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5463176314009257910L;

	public NotFoundException(String msg) {

		super(msg);
	}

	public NotFoundException(String msg, Throwable ex) {

		super(msg, ex);
	}
}