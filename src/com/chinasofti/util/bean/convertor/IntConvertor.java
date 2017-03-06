/**
 *  Copyright 2014 ChinaSoft International Ltd. All rights reserved.
 */
package com.chinasofti.util.bean.convertor;
/**
 * <p>
 * Title: IntConvertor
 * </p>
 * <p>
 * Description: ���ַ�������ת��ΪInteger�������ݵĹ���
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: ChinaSoft International Ltd.
 * </p>
 * 
 * @author etc
 * @version 1.0
 */
public class IntConvertor implements TypeConvertor {
	/**
	 * ִ������ת���ķ���
	 * 
	 * @param srcString
	 *            ԭʼ����ֵ
	 * @return ת����Ľ������
	 * */
	@Override
	public Object convertToObject(Object srcString) {
		// TODO Auto-generated method stub
		return new Integer(srcString.toString());
	}

}
