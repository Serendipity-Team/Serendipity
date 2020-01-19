package com.serendipity.util;

import java.text.*;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * @author shuchao
 */
public class StrTools {
	private static final FieldPosition HELPER_POSITION = new FieldPosition(0);
	private final static Format dateFormat = new SimpleDateFormat("YYMMddHHmmss");
	private final static NumberFormat numberFormat = new DecimalFormat("00");
	private final static NumberFormat LongNumberFormat = new DecimalFormat("0000");
	private static int seq = 0;
	private static final int MAX = 99;
	
	private static int longseq = 0;
	private static final int longMAX = 9999;
	/**
	 * 获取6位随机数
	 * @return
	 */
	public static String random()
	{
		int r = (int)((Math.random()*9+1)*100000);
		return String.valueOf(r).substring(0,6);
	}
	
    /**
     * 获取14位短日期 +序列
     * @return
     */
	public static String createDateUUID() {
		Calendar rightNow = Calendar.getInstance();
		StringBuffer sb = new StringBuffer();
		dateFormat.format(rightNow.getTime(),sb,HELPER_POSITION);
		numberFormat.format(seq, sb, HELPER_POSITION);
        if (seq == MAX) {
            seq = 0;
        } else {
            seq++;
        }
        return sb.toString();
	}
	
	/**
     * 获取16位短日期 +序列 (Long的最大长度16位)
     * @return
     */
	public static String createLongDateUUID() {
		Calendar rightNow = Calendar.getInstance();
		StringBuffer sb = new StringBuffer();
		dateFormat.format(rightNow.getTime(),sb,HELPER_POSITION);
		LongNumberFormat.format(longseq, sb, HELPER_POSITION);
        if (longseq == longMAX) {
        	longseq = 0;
        } else {
        	longseq++;
        }
        return sb.toString();
	}
	
    /**
     * 验证表达试是否匹配成功
     * @param str
     * @param pattenStr 匹配表达式
     * @return
     */
    public static boolean checkResult(String str, String pattenStr) {
        Pattern p = Pattern.compile(pattenStr);
        Matcher m = p.matcher(str);
        return m.matches();
    }
    /**
     * 生成UUID
     * @param isDowm 是否转换为小写并去掉-
     * @return
     */
    public static String createUUID(boolean isDowm)
    {
        String uuidStr = java.util.UUID.randomUUID().toString();
        if (isDowm) {
        	uuidStr = uuidStr.toLowerCase();
        	uuidStr = uuidStr.replaceAll("-", "");
        }
        return uuidStr;
    }
    /**
     * 判断字符串是否不为空
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str){
    	if(str!=null && !str.equals("")){
    		return true;
    	}
    	return false;
    }
    
    /**
	 * 去除字符串中的html标签
	 * @param htmlStr
	 * @return
	 */
	public static String delHTMLTag(String htmlStr) {
		String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
		String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

		Pattern p_script = Pattern.compile(regEx_script,
				Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // 过滤script标签

		Pattern p_style = Pattern
				.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签

		htmlStr = htmlStr.replaceAll("\\u0026nbsp;", "");

		return htmlStr.trim(); // 返回文本字符串
	}
	/**
     * 判断是否是数字
     * @Title: isNumberic 
     * @Description: 
     * @param str
     * @return boolean   
     * @throws
     */
    public static boolean isNumberic(String str) {
    	if (str == null) {
    		return false;
    	}
    	int sz = str.length();
    	for (int i = 0; i < sz; i++) {
    		if (Character.isDigit(str.charAt(i)) == false) {
    			return false;
    		} 
    	}
    	return true;    
	}
}
