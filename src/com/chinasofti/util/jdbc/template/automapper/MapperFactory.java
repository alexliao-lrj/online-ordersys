/**
 *  Copyright 2015 ChinaSoft International Ltd. All rights reserved.
 */
package com.chinasofti.util.jdbc.template.automapper;

import java.lang.reflect.Proxy;

/**
 * <p>
 * Title:MapperFactory
 * </p>
 * <p>
 * Description: ���ݿ�־û�����Զ�ӳ��װ�乤��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: ChinaSoft International Ltd.
 * </p>
 * 
 * @author etc
 * @version 1.0
 */
public class MapperFactory {

	/**
	 * �ṩ����ģʽ�����Holder�࣬�����ṩ�߳��Ѻõ�LazyLoading
	 * */
	private static class FactorySingletonHolder {
		/**
		 * ��������
		 * */
		private static final MapperFactory INSTANCE = new MapperFactory();
	}

	/**
	 * �Զ�װ��ӳ�乤�ߵķ���
	 * 
	 * @param mapperClass
	 *            ��Ҫװ���ӳ��ӿ�Class����
	 * @return ����װ��õ�Mapper����
	 * */
	public <T>  T getMapper(Class<T> mapperClass) {
		// ����ӳ�䴦����
		MapperHandler handler = new MapperHandler();
		// װ��ӳ�����
		T mapper = (T) Proxy.newProxyInstance(handler.getClass()
				.getClassLoader(), new Class[] { mapperClass }, handler);
		// ����ӳ�����
		return mapper;
	}

	/**
	 * ��֤�����Ĺ�����
	 * */
	private MapperFactory() {
	}

	/**
	 * �����ṩӳ��װ��ľ�̬����
	 * 
	 * @return ����װ��õ�ӳ�����
	 * @param mapperInterfaceClass
	 *            ��Ҫװ���ӳ��ӿ�Class����
	 * */
	public static <T> T getDBMapper(Class<T> mapperInterfaceClass) {
		// װ��ӳ�乤�߲�����
		return FactorySingletonHolder.INSTANCE.getMapper(mapperInterfaceClass);
	}
}
