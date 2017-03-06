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
 * Description: 数据库持久化组件自动映射处理器
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
	 * 表示SQL中需要插入的对象参数占位符正则表达式，类似与#{orderId}形式
	 * */
	public static final String OBJECT_ARG_REGEX = "\\#\\s*\\{\\s*\\w+(\\s*\\.\\s*\\w+)*\\s*\\}";
	/**
	 * 表示SQL中需要插入的字符串串联占位符正则表达式，类似与${orderId}形式
	 * */
	public static final String STRING_ARG_REGEX = "\\$\\s*\\{\\s*\\w+(\\s*\\.\\s*\\w+)*\\s*\\}";

	/**
	 * 带有简易数据库连接池的JDBC模板封装工具
	 * */
	JDBCTemplateWithDS template = JDBCTemplateWithDS.getJDBCHelper();

	/**
	 * 处理器核心方法
	 * 
	 * @return 返回给方法调用者的实际返回值
	 * @param proxy
	 *            代理以后的对象
	 * @param method
	 *            实际希望调用的方法
	 * @param args
	 *            调用方法时实际传递的参数值
	 * */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		// 参数名与参数值的映射，参数名由方法参数中的@param标注决定
		HashMap<String, Object> argsMap = new HashMap<String, Object>();
		// 判定方法上是否存在Select标注，如果存在，则说明该方法是希望执行查询操作
		if (method.getAnnotation(Select.class) != null) {
			// 获取Select标注对象
			Select selectAnno = method.getAnnotation(Select.class);
			// 获取Select标注中包括的查询语句
			String sql = selectAnno.value();

			// 将方法中传递的实参数值按照Param标注提供的参数名一一保存到列表中
			initMethodArgs(method, args, argsMap);

			// 初始化字符串串联参数
			sql = initStringArgs(sql, argsMap);
			// 新建SQL参数列表
			ArrayList<Object> argValues = new ArrayList<Object>();
			// 初始化预编译SQL语句（将参数用？占位符代替，并将对应的参数存放在参数列表中）
			sql = initObjectArgs(sql, argsMap, argValues);
			System.out.println(sql);
			// 获取预编译SQL参数数组
			Object[] argsArray = argValues.toArray();
			// 获取方法的返回值类型（目的是为了向自动填充JavaBean提供目标依据）
			Class<?> returnType = method.getReturnType();
			// 如果方法返回值是数组
			if (returnType.isArray()) {
				// 获取数组中的元素类型
				Class<?> entityType = returnType.getComponentType();
				// 如果元素类型的包以java.开头，本案例默认为基本数据类型的包装型或字符串，直接将结果集的第一行以对象数组的方式返回
				if (entityType.getPackage().getName().trim()
						.startsWith("java.")) {
					// 返回对象数组
					return template.preparedQueryForObjectArray(sql, argsArray);
				}
				// 否则认为数组中存放的应该是实体对象，因此先获取实体对象列表
				ArrayList<?> list = template.preparedQueryForList(sql,
						argsArray, entityType);
				// 将列表转换为对象数组后返回
				return list.toArray((Object[]) Array.newInstance(entityType,
						list.size()));
				// 如果方法返回值是List集合
			} else if (List.class.isAssignableFrom(returnType)) {
				// 获取方法返回值中的泛型
				Class<?> entityType = (Class<?>) ((ParameterizedType) method
						.getGenericReturnType()).getActualTypeArguments()[0];
				// 返回集合
				return template
						.preparedQueryForList(sql, argsArray, entityType);
				// 如果方法返回值是基本数据类型或java.包开头的类型
			} else if (returnType.isPrimitive()
					|| returnType.getPackage().getName().trim()
							.startsWith("java.")) {
				// 返回单一的对象结果
				return template.preparedQueryForObject(sql, argsArray);
				// 如果返回值是一个单个的实体对象
			} else {
				// 查询结果
				ArrayList<?> list = template.preparedQueryForList(sql,
						argsArray, returnType);
				// 返回实体对象
				return list.size() > 0 ? list.get(0) : null;
			}
			// 判定方法上是否存在Update标注，如果存在，则说明该方法是希望执行更新操作
		} else if (method.getAnnotation(Update.class) != null) {
			// 获取Update标注对象
			Update updateAnno = method.getAnnotation(Update.class);
			// 获取Update标注中包括的查询语句
			String sql = updateAnno.value();
			// 将方法中传递的实参数值按照Param标注提供的参数名一一保存到列表中
			initMethodArgs(method, args, argsMap);
			// 初始化字符串串联参数
			sql = initStringArgs(sql, argsMap);
			// 新建SQL参数列表
			ArrayList<Object> argValues = new ArrayList<Object>();
			// 初始化预编译SQL语句（将参数用？占位符代替，并将对应的参数存放在参数列表中）
			sql = initObjectArgs(sql, argsMap, argValues);
			// 获取预编译SQL参数数组
			Object[] argsArray = argValues.toArray();
			// 如果方法上还存在ReturnGenerateKeys标注，说明方法需要返回刚才插入操作对应的数据库自动生成的主键
			if (method.getAnnotation(ReturnGeneratedKeys.class) != null) {
				// 执行插入操作并返回自动生成的主键
				return template.preparedInsertForGeneratedKeys(sql, argsArray);
				// 不需要返回自动生成的主键
			} else {
				// 执行更新操作并返回受影响的行数
				return new Integer(template.executePreparedUpdate(sql,
						argsArray));
			}

		}
		// 返回null
		return null;
	}

	/**
	 * 初始化方法参数列表的方法，将方法中的实际参数值和参数对应的Param标注值进行一一对应映射后存入HashMap中
	 * 
	 * @param method
	 *            要处理的方法
	 * @param args
	 *            调用方法时实际传递的参数值
	 * @param argsMap
	 *            用于保存映射结果的HashMap对象
	 * */
	private void initMethodArgs(Method method, Object[] args,
			HashMap<String, Object> argsMap) {
		// 获取方法参数对应的标注集合
		Annotation[][] parmAnnos = method.getParameterAnnotations();
		// 如果调用方法时传递有实参
		if (args != null) {
			// 遍历方法的实参
			for (int i = 0; i < args.length; i++) {
				// 获取对应参数位置的参数标注个数，如果标注个数大于0（本案例中只可能是0或者1），说明参数上有Param标注,对应SQL语句中的映射参数值
				if (parmAnnos[i].length > 0) {
					// 将映射参数值写入集合
					argsMap.put(((Param) parmAnnos[i][0]).value().trim(),
							args[i]);
				}
			}
		}

	}

	/**
	 * 初始化预编译SQL语句指令的方法，处理后的SQL语句的参数位置用？代替，具体的参数值将保存在argValues列表中（列表中原有数据将被清空）
	 * 
	 * @param sql
	 *            原始SQL语句
	 * @param argsMap
	 *            参数映射列表
	 * @param argValues
	 *            用于预编译SQL指令的参数列表
	 * @return 返回处理后的SQL语句字符串（参数对应的位置以?占位符取代）
	 * */
	private String initObjectArgs(String sql, HashMap<String, Object> argsMap,
			ArrayList<Object> argValues) {
		// 保存SQL副本
		String src = sql;
		// 清空参数列表
		argValues.clear();
		// 获取SQL语句中所有的对象参数
		ArrayList<String> args = getArgs(src, OBJECT_ARG_REGEX);
		// 遍历参数
		for (String arg : args) {
			// 获取参数对应的参数值
			Object argValue = getArgValue(arg, argsMap);
			// 将参数值存放到列表中
			argValues.add(argValue);
		}
		// 重置SQL语句的格式
		src = src.replaceAll(OBJECT_ARG_REGEX, "?");
		// 返回重置后的SQL字符串
		return src;
	}

	/**
	 * 初始化字符串替换参数SQL语句指令的方法，处理后的SQL语句的参数位置用参数值的字符串形式替换
	 * 
	 * @param sql
	 *            原始SQL语句
	 * @param argsMap
	 *            用于保存映射结果的HashMap对象
	 * @return 返回处理后的SQL语句字符串
	 * */
	private String initStringArgs(String sql, HashMap<String, Object> argsMap) {
		// 保存SQL副本
		String src = sql;
		// 获取SQL语句中所有的对象参数
		ArrayList<String> args = getArgs(src, STRING_ARG_REGEX);
		// 创建参数值列表
		ArrayList<String> argValues = new ArrayList<String>();
		// 遍历对象参数列表
		for (String arg : args) {
			// 获取参数的实际值
			Object argValue = getArgValue(arg, argsMap);
			// 将参数值的字符串形式存放到参数值列表中
			argValues.add(argValue.toString());
		}
		// 将所有的参数转换为"@"
		src = src.replaceAll(STRING_ARG_REGEX, "@");
		// 定义计数器
		int index = 0;
		// 循环遍历SQL字符串中的@符号，每一个符号代表一个参数位置
		while (src.indexOf("@") != -1) {
			// 用实际参数值代替@符号
			src = src.substring(0, src.indexOf("@")) + argValues.get(index)
					+ src.substring(src.indexOf("@") + 1);

			// 计数器累加
			index++;
		}

		// 返回处理成功的SQL
		return src;
	}

	/**
	 * 获取SQL语句中所有动态参数位置的方法
	 * 
	 * @param src
	 *            原始SQL语句
	 * @param argRegex
	 *            用于检索参数的正则表达式，文本替换类参数类似${obj.attr},预编译SQL参数类似#{obj,attr}
	 * @return 返回处理后的SQL语句字符串
	 * */
	private ArrayList<String> getArgs(String src, String argRegex) {

		// 利用正则表达式创建正则表达式工具
		Pattern pattern = Pattern.compile(argRegex);
		// 获取正则匹配工具
		Matcher mathcer = pattern.matcher(src);
		// 创建动态参数列表
		ArrayList<String> args = new ArrayList<String>();
		// 循环查找SQL中符合参数格式要求的字串
		while (mathcer.find()) {
			// 获取下一个找到的字串
			String arg = mathcer.group();
			// 将字串中的空白替换为""
			arg = arg.replaceAll("\\s+", "");
			// 提取参数描述字符串
			arg = arg.substring(arg.indexOf("{") + 1, arg.indexOf("}"));
			// 将参数描述字符串加入列表
			args.add(arg.trim());
		}
		// 返回参数列表
		return args;
	}

	/**
	 * 获取动态参数实际取值的方法
	 * 
	 * @param arg
	 *            通过正则表达式获取的参数描述字符串
	 * @param argMap
	 *            实际参数映射列表
	 * @return 返回获取到的实际参数值
	 * */
	private Object getArgValue(String arg, HashMap<String, Object> argsMap) {
		// 定义返回值对象
		Object argValue = null;
		// 利用"."对参数描述字符串进行分割
		String[] argElement = arg.split("\\.");
		// 利用第一的元素在参数值映射列表中获取对象值
		argValue = argsMap.get(argElement[0].trim());
		// 如果元素数大于1，说明参数类似obj.attr.subattr形式，需要获取对象的特定属性值
		if (argElement.length > 1) {
			// 尝试获取具体的属性值
			try {
				// 循环遍历元素（每一个元素都是一个属性名称）
				for (int i = 1; i < argElement.length; i++) {
					// 利用内省获取Bean信息
					BeanInfo info = Introspector.getBeanInfo(argValue
							.getClass());
					// 获取Bean的属性描述列表
					PropertyDescriptor pds[] = info.getPropertyDescriptors();
					// 遍历属性描述列表
					for (PropertyDescriptor pd : pds) {
						// 如果属性名称和需要获取的属性名称一致
						if (pd.getName().equals(argElement[i].trim())) {
							// 获取属性的getter方法
							Method getter = pd.getReadMethod();
							// 调用getter方法获取具体的属性值
							argValue = getter.invoke(argValue, new Object[] {});
						}
					}
				}
				// 捕获异常
			} catch (Exception ex) {
				// 输出异常信息
				ex.printStackTrace();
			}
		}
		// 返回获取到的具体属性值
		return argValue;

	}

}
