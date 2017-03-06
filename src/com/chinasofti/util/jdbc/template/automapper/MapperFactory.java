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
 * Description: 数据库持久化组件自动映射装配工厂
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
	 * 提供单例模式对象的Holder类，用于提供线程友好的LazyLoading
	 * */
	private static class FactorySingletonHolder {
		/**
		 * 单例对象
		 * */
		private static final MapperFactory INSTANCE = new MapperFactory();
	}

	/**
	 * 自动装配映射工具的方法
	 * 
	 * @param mapperClass
	 *            需要装配的映射接口Class描述
	 * @return 返回装配好的Mapper对象
	 * */
	public <T>  T getMapper(Class<T> mapperClass) {
		// 构建映射处理器
		MapperHandler handler = new MapperHandler();
		// 装配映射对象
		T mapper = (T) Proxy.newProxyInstance(handler.getClass()
				.getClassLoader(), new Class[] { mapperClass }, handler);
		// 返回映射对象
		return mapper;
	}

	/**
	 * 保证单例的构造器
	 * */
	private MapperFactory() {
	}

	/**
	 * 对外提供映射装配的静态方法
	 * 
	 * @return 返回装配好的映射对象
	 * @param mapperInterfaceClass
	 *            需要装配的映射接口Class描述
	 * */
	public static <T> T getDBMapper(Class<T> mapperInterfaceClass) {
		// 装配映射工具并返回
		return FactorySingletonHolder.INSTANCE.getMapper(mapperInterfaceClass);
	}
}
