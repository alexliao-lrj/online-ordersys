/**
 *  Copyright 2015 ChinaSoft International Ltd. All rights reserved.
 */
package com.chinasofti.util.jdbc.template.automapper.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * Title:Param
 * </p>
 * <p>
 * Description: 说明映射方法参数名的标注
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
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Param {
	/**
	 * Mapper接口中映射方法对应SQL语句中动态参数的名称，因为即使编译时带有-g参数，Java的标准反射机制也无法读取参数的参数名，
	 * 使用字节码读取工具读取会牺牲一部分效率，因此采用标注方式说明参数映射关系
	 * */
	String value() default "";
}
