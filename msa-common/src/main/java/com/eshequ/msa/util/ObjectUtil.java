package com.eshequ.msa.util;

public class ObjectUtil {

	/**
	 * 判断String类型对象是否为空(空串或null)
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {

		if( str == null || str.trim().equals("") ) {
			return true;
		}
		return false;
	}

	/**
	 * 判断一个对象数组是否为空(没有成员)
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(Object[] array) {

		if( array == null || array.length == 0 ) {
			return true;
		}
		return false;
	}
	
}
