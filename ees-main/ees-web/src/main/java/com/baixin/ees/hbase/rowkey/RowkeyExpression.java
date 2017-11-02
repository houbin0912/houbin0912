package com.baixin.ees.hbase.rowkey;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RowkeyExpression {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public static final String METHOD_REGEX = "\\+";
	public static final String METHOD_FIELD_REGEX = ",";
	public static final String ROWKEY_SEP = "\003";
	
	private String rowkeyExpStr = "" ;

	private List<MethodExp> methodExpList;
	
	
	public RowkeyExpression(String rowkeyExpStr) {
		this.rowkeyExpStr = rowkeyExpStr;
		initRowExp();
	}
	
	public void initRowExp() {
		if(StringUtils.isNotBlank(rowkeyExpStr)) {
			String[] expArray = rowkeyExpStr.split(METHOD_REGEX,-1);
			methodExpList = new ArrayList<MethodExp>();
			for(String exp:expArray) {
				if(StringUtils.isNotBlank(exp)) {
					int methodNameEndIdex = exp.indexOf("(");
					int methodEndIndex = exp.lastIndexOf(")");
					if(methodNameEndIdex != -1 && methodEndIndex != -1) {
						String methodName = exp.substring(0, methodNameEndIdex);
						String argsMehodStr = exp.substring(methodNameEndIdex+1, methodEndIndex);
						methodExpList.add(new MethodExp(methodName, argsMehodStr.split(METHOD_FIELD_REGEX, -1)));
					}else {//表达式内不包含方法，方法名成为空，直接取数据中这个字段的值
						String[] argsList = new String[1];
						argsList[0] = exp;
						methodExpList.add(new MethodExp(null, argsList));
					}
				}
			}
		}else {
			logger.error("rowkey表达式为空");
			throw new RuntimeException("rowkey expression is null");
		}
	}
	
	public String getRowkey(Map<String,String> dataMap) throws Exception {
		if(methodExpList != null) {
			StringBuilder sbu = new StringBuilder();
			for(int index = 0 ; index < methodExpList.size() ; index++) {
				MethodExp methodExp = methodExpList.get(index);
				sbu.append(methodExp.doMethod(dataMap));
				if(index < methodExpList.size()-1 ) {
					sbu.append(ROWKEY_SEP);
				}
			}
			return sbu.toString();
		}
		logger.error("methodExpList表达式方法为空");
		return null;
	}
	
	public static class MethodExp {
		private String methodName;
		private String[] argsNameList;
		
		public MethodExp() {}

		public MethodExp(String methodName, String[] argsNameList) {
			this.methodName = methodName;
			this.argsNameList = argsNameList;
		}

		public String doMethod(Map<String,String> dataMap) throws Exception {
			if(StringUtils.isNotBlank(methodName)) {
				Object[] argsObject = new Object[argsNameList.length];
				for( int index = 0 ; index < argsNameList.length ; index++ ) {
					String argName = argsNameList[index];
					argsObject[index] = dataMap.get(argName);
				}
				Class<RowkeyMethods> methodClass = RowkeyMethods.class;
				Map<String,Method> methodMap = getAllMethod();
				Method method = methodMap.get(methodName);
				return (String)method.invoke(methodClass.newInstance(), argsObject);
			}else if(argsNameList.length == 1){
				return dataMap.get(argsNameList[0]);
			}else{
				throw new Exception("未知的rowkey表达式方法："+methodName);
			}
		}
		
		public Map<String,Method> getAllMethod() {
			Map<String,Method> methodMap = new HashMap<String,Method>();
			Class<RowkeyMethods> methodClass = RowkeyMethods.class;
			for(Method md:methodClass.getMethods()) {
				methodMap.put(md.getName(), md);
			}
			return methodMap;
		}
		
		
	}
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
//		MethodExp methodExp = new MethodExp();
//		Class<RowkeyMethods> methodClass = RowkeyMethods.class;
//		Map<String,Method> methodMap = methodExp.getAllMethod();
//		Method method = methodMap.get("md5");
//		Object[] argsObject = new Object[2];
//		argsObject[0] = "test";
//		argsObject[1] = "after";
//		System.out.println(method.invoke(methodClass.newInstance(), argsObject));
		
		
		String[] expArray = "md5(name,id)".split(METHOD_REGEX,-1);
		for(String exp:expArray) {
			
			int methodNameEndIdex = exp.indexOf("(");
			System.out.println(methodNameEndIdex);
			int methodEndIndex = exp.indexOf(')');
			System.out.println(methodEndIndex);
			String methodName = exp.substring(0, methodNameEndIdex);
			String argsMehodStr = exp.substring(methodNameEndIdex+1, methodEndIndex);
			System.out.println(methodName);
			System.out.println(argsMehodStr);
		}
		
	}
	
}
