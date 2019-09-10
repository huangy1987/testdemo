package com.solution.tools;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import org.apache.struts2.ServletActionContext;

import com.sdicons.json.mapper.JSONMapper;
import com.sdicons.json.mapper.MapperException;
import com.sdicons.json.model.JSONValue;
import com.sdicons.json.parser.JSONParser;


public class VOUtils {
	
	/**
	 * 将一个对象转换为JSON格式的串
	 * @param vo 要转换的VO对象
	 * @return 转换后的字符串
	 */
	public static String convertVO2String(Object vo){
		
		try {
			return JSONMapper.toJSON(vo).render(false);
		} catch (MapperException e) {
			throw new RuntimeException("把对象【"+vo+"】转换为字符串的时候出现问题了",e);
		}
		
	}
	
	
	/**
	 * 将一个JSON格式的字符串转换为Java对象
	 * @param message 要转换的JSON格式的字符串
	 * @param destClass 要将这个JSON格式的字符串转换为什么类型的对象
	 * @return 转换之后的Java对象
	 */
	public static Object convertString2VO(String message,Class destClass){
		
		try {
			//先解释字符串为一个JSONValue
			JSONValue value = new JSONParser(new StringReader(message)).nextValue();
			return JSONMapper.toJava(value, destClass);
		} catch (Exception e) {
			throw new RuntimeException("在把字符串【"+message+"】转换为【"+destClass+"】类型的对象时，出现异常" +
					"，可能是你的字符串格式不对，请修正！",e);
		}
	}
	public static void writeJson(Object o){
		try {
			System.out.println("writeJson");
			//对象转换为json字符串
			String str = VOUtils.convertVO2String(o);
			
			System.out.println(str);
//			str = new String(str.getBytes("ISO-8859-1"),"UTF-8");
			//乱码解决
			ServletActionContext.getResponse().setContentType("text/html");
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			PrintWriter out = ServletActionContext.getResponse().getWriter();
			out.write(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void changeObj(String obj) throws IOException{
		//乱码解决
		ServletActionContext.getResponse().setContentType("text/html");
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		out.write(obj);
	}
	
}