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
 * Description: ˵��ӳ�䷽���������ı�ע
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
	 * Mapper�ӿ���ӳ�䷽����ӦSQL����ж�̬���������ƣ���Ϊ��ʹ����ʱ����-g������Java�ı�׼�������Ҳ�޷���ȡ�����Ĳ�������
	 * ʹ���ֽ����ȡ���߶�ȡ������һ����Ч�ʣ���˲��ñ�ע��ʽ˵������ӳ���ϵ
	 * */
	String value() default "";
}
