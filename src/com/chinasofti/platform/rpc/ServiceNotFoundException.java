/**
 *  Copyright 2014 ChinaSoft International Ltd. All rights reserved.
 */
package com.chinasofti.platform.rpc;

/**
 * <p>
 * Title: ServiceNotFoundException
 * </p>
 * <p>
 * Description: Զ�̷����޷��������������쳣����
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
public class ServiceNotFoundException extends RuntimeException {
	// ������
	public ServiceNotFoundException() {
		// TODO Auto-generated constructor stub
		// �����޷�������������Ĵ�����ʾ
		super("�����ҵķ����޷��ҵ���");
	}
}
