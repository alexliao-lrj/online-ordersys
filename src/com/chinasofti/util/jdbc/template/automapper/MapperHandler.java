/**
 *  Copyright 2015 ChinaSoft International Ltd. All rights reserved.
 */
package com.chinasofti.util.jdbc.template.automapper;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.chinasofti.util.jdbc.template.JDBCTemplateWithDS;
import com.chinasofti.util.jdbc.template.automapper.annotation.Param;
import com.chinasofti.util.jdbc.template.automapper.annotation.ReturnGeneratedKeys;
import com.chinasofti.util.jdbc.template.automapper.annotation.Select;
import com.chinasofti.util.jdbc.template.automapper.annotation.Update;

/**
 * <p>
 * Title:MapperHandler
 * </p>
 * <p>
 * Description: ���ݿ�־û�����Զ�ӳ�䴦����
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
public class MapperHandler implements InvocationHandler {

	/**
	 * ��ʾSQL����Ҫ����Ķ������ռλ��������ʽ��������#{orderId}��ʽ
	 * */
	public static final String OBJECT_ARG_REGEX = "\\#\\s*\\{\\s*\\w+(\\s*\\.\\s*\\w+)*\\s*\\}";
	/**
	 * ��ʾSQL����Ҫ������ַ�������ռλ��������ʽ��������${orderId}��ʽ
	 * */
	public static final String STRING_ARG_REGEX = "\\$\\s*\\{\\s*\\w+(\\s*\\.\\s*\\w+)*\\s*\\}";

	/**
	 * ���м������ݿ����ӳص�JDBCģ���װ����
	 * */
	JDBCTemplateWithDS template = JDBCTemplateWithDS.getJDBCHelper();

	/**
	 * ���������ķ���
	 * 
	 * @return ���ظ����������ߵ�ʵ�ʷ���ֵ
	 * @param proxy
	 *            �����Ժ�Ķ���
	 * @param method
	 *            ʵ��ϣ�����õķ���
	 * @param args
	 *            ���÷���ʱʵ�ʴ��ݵĲ���ֵ
	 * */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		// �����������ֵ��ӳ�䣬�������ɷ��������е�@param��ע����
		HashMap<String, Object> argsMap = new HashMap<String, Object>();
		// �ж��������Ƿ����Select��ע��������ڣ���˵���÷�����ϣ��ִ�в�ѯ����
		if (method.getAnnotation(Select.class) != null) {
			// ��ȡSelect��ע����
			Select selectAnno = method.getAnnotation(Select.class);
			// ��ȡSelect��ע�а����Ĳ�ѯ���
			String sql = selectAnno.value();

			// �������д��ݵ�ʵ����ֵ����Param��ע�ṩ�Ĳ�����һһ���浽�б���
			initMethodArgs(method, args, argsMap);

			// ��ʼ���ַ�����������
			sql = initStringArgs(sql, argsMap);
			// �½�SQL�����б�
			ArrayList<Object> argValues = new ArrayList<Object>();
			// ��ʼ��Ԥ����SQL��䣨�������ã�ռλ�����棬������Ӧ�Ĳ�������ڲ����б��У�
			sql = initObjectArgs(sql, argsMap, argValues);
			System.out.println(sql);
			// ��ȡԤ����SQL��������
			Object[] argsArray = argValues.toArray();
			// ��ȡ�����ķ���ֵ���ͣ�Ŀ����Ϊ�����Զ����JavaBean�ṩĿ�����ݣ�
			Class<?> returnType = method.getReturnType();
			// �����������ֵ������
			if (returnType.isArray()) {
				// ��ȡ�����е�Ԫ������
				Class<?> entityType = returnType.getComponentType();
				// ���Ԫ�����͵İ���java.��ͷ��������Ĭ��Ϊ�����������͵İ�װ�ͻ��ַ�����ֱ�ӽ�������ĵ�һ���Զ�������ķ�ʽ����
				if (entityType.getPackage().getName().trim()
						.startsWith("java.")) {
					// ���ض�������
					return template.preparedQueryForObjectArray(sql, argsArray);
				}
				// ������Ϊ�����д�ŵ�Ӧ����ʵ���������Ȼ�ȡʵ������б�
				ArrayList<?> list = template.preparedQueryForList(sql,
						argsArray, entityType);
				// ���б�ת��Ϊ��������󷵻�
				return list.toArray((Object[]) Array.newInstance(entityType,
						list.size()));
				// �����������ֵ��List����
			} else if (List.class.isAssignableFrom(returnType)) {
				// ��ȡ��������ֵ�еķ���
				Class<?> entityType = (Class<?>) ((ParameterizedType) method
						.getGenericReturnType()).getActualTypeArguments()[0];
				// ���ؼ���
				return template
						.preparedQueryForList(sql, argsArray, entityType);
				// �����������ֵ�ǻ����������ͻ�java.����ͷ������
			} else if (returnType.isPrimitive()
					|| returnType.getPackage().getName().trim()
							.startsWith("java.")) {
				// ���ص�һ�Ķ�����
				return template.preparedQueryForObject(sql, argsArray);
				// �������ֵ��һ��������ʵ�����
			} else {
				// ��ѯ���
				ArrayList<?> list = template.preparedQueryForList(sql,
						argsArray, returnType);
				// ����ʵ�����
				return list.size() > 0 ? list.get(0) : null;
			}
			// �ж��������Ƿ����Update��ע��������ڣ���˵���÷�����ϣ��ִ�и��²���
		} else if (method.getAnnotation(Update.class) != null) {
			// ��ȡUpdate��ע����
			Update updateAnno = method.getAnnotation(Update.class);
			// ��ȡUpdate��ע�а����Ĳ�ѯ���
			String sql = updateAnno.value();
			// �������д��ݵ�ʵ����ֵ����Param��ע�ṩ�Ĳ�����һһ���浽�б���
			initMethodArgs(method, args, argsMap);
			// ��ʼ���ַ�����������
			sql = initStringArgs(sql, argsMap);
			// �½�SQL�����б�
			ArrayList<Object> argValues = new ArrayList<Object>();
			// ��ʼ��Ԥ����SQL��䣨�������ã�ռλ�����棬������Ӧ�Ĳ�������ڲ����б��У�
			sql = initObjectArgs(sql, argsMap, argValues);
			// ��ȡԤ����SQL��������
			Object[] argsArray = argValues.toArray();
			// ��������ϻ�����ReturnGenerateKeys��ע��˵��������Ҫ���ظղŲ��������Ӧ�����ݿ��Զ����ɵ�����
			if (method.getAnnotation(ReturnGeneratedKeys.class) != null) {
				// ִ�в�������������Զ����ɵ�����
				return template.preparedInsertForGeneratedKeys(sql, argsArray);
				// ����Ҫ�����Զ����ɵ�����
			} else {
				// ִ�и��²�����������Ӱ�������
				return new Integer(template.executePreparedUpdate(sql,
						argsArray));
			}

		}
		// ����null
		return null;
	}

	/**
	 * ��ʼ�����������б�ķ������������е�ʵ�ʲ���ֵ�Ͳ�����Ӧ��Param��עֵ����һһ��Ӧӳ������HashMap��
	 * 
	 * @param method
	 *            Ҫ����ķ���
	 * @param args
	 *            ���÷���ʱʵ�ʴ��ݵĲ���ֵ
	 * @param argsMap
	 *            ���ڱ���ӳ������HashMap����
	 * */
	private void initMethodArgs(Method method, Object[] args,
			HashMap<String, Object> argsMap) {
		// ��ȡ����������Ӧ�ı�ע����
		Annotation[][] parmAnnos = method.getParameterAnnotations();
		// ������÷���ʱ������ʵ��
		if (args != null) {
			// ����������ʵ��
			for (int i = 0; i < args.length; i++) {
				// ��ȡ��Ӧ����λ�õĲ�����ע�����������ע��������0����������ֻ������0����1����˵����������Param��ע,��ӦSQL����е�ӳ�����ֵ
				if (parmAnnos[i].length > 0) {
					// ��ӳ�����ֵд�뼯��
					argsMap.put(((Param) parmAnnos[i][0]).value().trim(),
							args[i]);
				}
			}
		}

	}

	/**
	 * ��ʼ��Ԥ����SQL���ָ��ķ�����������SQL���Ĳ���λ���ã����棬����Ĳ���ֵ��������argValues�б��У��б���ԭ�����ݽ�����գ�
	 * 
	 * @param sql
	 *            ԭʼSQL���
	 * @param argsMap
	 *            ����ӳ���б�
	 * @param argValues
	 *            ����Ԥ����SQLָ��Ĳ����б�
	 * @return ���ش�����SQL����ַ�����������Ӧ��λ����?ռλ��ȡ����
	 * */
	private String initObjectArgs(String sql, HashMap<String, Object> argsMap,
			ArrayList<Object> argValues) {
		// ����SQL����
		String src = sql;
		// ��ղ����б�
		argValues.clear();
		// ��ȡSQL��������еĶ������
		ArrayList<String> args = getArgs(src, OBJECT_ARG_REGEX);
		// ��������
		for (String arg : args) {
			// ��ȡ������Ӧ�Ĳ���ֵ
			Object argValue = getArgValue(arg, argsMap);
			// ������ֵ��ŵ��б���
			argValues.add(argValue);
		}
		// ����SQL���ĸ�ʽ
		src = src.replaceAll(OBJECT_ARG_REGEX, "?");
		// �������ú��SQL�ַ���
		return src;
	}

	/**
	 * ��ʼ���ַ����滻����SQL���ָ��ķ�����������SQL���Ĳ���λ���ò���ֵ���ַ�����ʽ�滻
	 * 
	 * @param sql
	 *            ԭʼSQL���
	 * @param argsMap
	 *            ���ڱ���ӳ������HashMap����
	 * @return ���ش�����SQL����ַ���
	 * */
	private String initStringArgs(String sql, HashMap<String, Object> argsMap) {
		// ����SQL����
		String src = sql;
		// ��ȡSQL��������еĶ������
		ArrayList<String> args = getArgs(src, STRING_ARG_REGEX);
		// ��������ֵ�б�
		ArrayList<String> argValues = new ArrayList<String>();
		// ������������б�
		for (String arg : args) {
			// ��ȡ������ʵ��ֵ
			Object argValue = getArgValue(arg, argsMap);
			// ������ֵ���ַ�����ʽ��ŵ�����ֵ�б���
			argValues.add(argValue.toString());
		}
		// �����еĲ���ת��Ϊ"@"
		src = src.replaceAll(STRING_ARG_REGEX, "@");
		// ���������
		int index = 0;
		// ѭ������SQL�ַ����е�@���ţ�ÿһ�����Ŵ���һ������λ��
		while (src.indexOf("@") != -1) {
			// ��ʵ�ʲ���ֵ����@����
			src = src.substring(0, src.indexOf("@")) + argValues.get(index)
					+ src.substring(src.indexOf("@") + 1);

			// �������ۼ�
			index++;
		}

		// ���ش���ɹ���SQL
		return src;
	}

	/**
	 * ��ȡSQL��������ж�̬����λ�õķ���
	 * 
	 * @param src
	 *            ԭʼSQL���
	 * @param argRegex
	 *            ���ڼ���������������ʽ���ı��滻���������${obj.attr},Ԥ����SQL��������#{obj,attr}
	 * @return ���ش�����SQL����ַ���
	 * */
	private ArrayList<String> getArgs(String src, String argRegex) {

		// ����������ʽ����������ʽ����
		Pattern pattern = Pattern.compile(argRegex);
		// ��ȡ����ƥ�乤��
		Matcher mathcer = pattern.matcher(src);
		// ������̬�����б�
		ArrayList<String> args = new ArrayList<String>();
		// ѭ������SQL�з��ϲ�����ʽҪ����ִ�
		while (mathcer.find()) {
			// ��ȡ��һ���ҵ����ִ�
			String arg = mathcer.group();
			// ���ִ��еĿհ��滻Ϊ""
			arg = arg.replaceAll("\\s+", "");
			// ��ȡ���������ַ���
			arg = arg.substring(arg.indexOf("{") + 1, arg.indexOf("}"));
			// �����������ַ��������б�
			args.add(arg.trim());
		}
		// ���ز����б�
		return args;
	}

	/**
	 * ��ȡ��̬����ʵ��ȡֵ�ķ���
	 * 
	 * @param arg
	 *            ͨ��������ʽ��ȡ�Ĳ��������ַ���
	 * @param argMap
	 *            ʵ�ʲ���ӳ���б�
	 * @return ���ػ�ȡ����ʵ�ʲ���ֵ
	 * */
	private Object getArgValue(String arg, HashMap<String, Object> argsMap) {
		// ���巵��ֵ����
		Object argValue = null;
		// ����"."�Բ��������ַ������зָ�
		String[] argElement = arg.split("\\.");
		// ���õ�һ��Ԫ���ڲ���ֵӳ���б��л�ȡ����ֵ
		argValue = argsMap.get(argElement[0].trim());
		// ���Ԫ��������1��˵����������obj.attr.subattr��ʽ����Ҫ��ȡ������ض�����ֵ
		if (argElement.length > 1) {
			// ���Ի�ȡ���������ֵ
			try {
				// ѭ������Ԫ�أ�ÿһ��Ԫ�ض���һ���������ƣ�
				for (int i = 1; i < argElement.length; i++) {
					// ������ʡ��ȡBean��Ϣ
					BeanInfo info = Introspector.getBeanInfo(argValue
							.getClass());
					// ��ȡBean�����������б�
					PropertyDescriptor pds[] = info.getPropertyDescriptors();
					// �������������б�
					for (PropertyDescriptor pd : pds) {
						// ����������ƺ���Ҫ��ȡ����������һ��
						if (pd.getName().equals(argElement[i].trim())) {
							// ��ȡ���Ե�getter����
							Method getter = pd.getReadMethod();
							// ����getter������ȡ���������ֵ
							argValue = getter.invoke(argValue, new Object[] {});
						}
					}
				}
				// �����쳣
			} catch (Exception ex) {
				// ����쳣��Ϣ
				ex.printStackTrace();
			}
		}
		// ���ػ�ȡ���ľ�������ֵ
		return argValue;

	}

}
