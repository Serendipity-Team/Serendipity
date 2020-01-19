package com.serendipity.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**  
 * 常用加密算法工具类  
 * @author chao.shu  
 */ 
public class EncryptUtils {
	/**  
     * 用MD5算法进行加密  
     * @param str 需要加密的字符串  
     * @return MD5加密后的结果  
     */    
    public static String encodeMD5String(String str) {    
        return encode(str, "MD5");    
    }    
    
    /**  
     * 用SHA算法进行加密  
     * @param str 需要加密的字符串  
     * @return SHA加密后的结果  
     */    
    public static String encodeSHAString(String str) {    
        return encode(str, "SHA");    
    }    
    
    /**  
     * 用base64算法进行加密  
     * @param str 需要加密的字符串  
     * @return base64加密后的结果  
     */    
    public static String encodeBase64String(String str) {    
        BASE64Encoder encoder =  new BASE64Encoder();    
        return encoder.encode(str.getBytes());    
    }    

    /**  
     * 用base64算法进行解密  
     * @param str 需要解密的字符串  
     * @return base64解密后的结果  
     * @throws IOException   
     */    
    public static String decodeBase64String(String str) throws IOException {    
        BASE64Decoder encoder =  new BASE64Decoder();    
        return new String(encoder.decodeBuffer(str));    
    }
    /**
     * 加密
     * 1.构造密钥生成器
     * 2.根据ecnodeRules规则初始化密钥生成器
     * 3.产生密钥
     * 4.创建和初始化密码器
     * 5.内容加密
     * 6.返回字符串
     */
	public static String AESEncode(String encodeRules,String content){
		try {
			//1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen=KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            keygen.init(128, new SecureRandom(encodeRules.getBytes()));
            //3.产生原始对称密钥
            SecretKey original_key=keygen.generateKey();
            //4.获得原始对称密钥的字节数组
            byte [] raw=original_key.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKey key=new SecretKeySpec(raw, "AES");
            //6.根据指定算法AES自成密码器
            Cipher cipher=Cipher.getInstance("AES");
        	//7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte [] byte_encode=content.getBytes("utf-8");
            //9.根据密码器的初始化方式--加密：将数据加密
            byte [] byte_AES=cipher.doFinal(byte_encode);
            //10.将加密后的数据转换为字符串
            //这里用Base64Encoder中会找不到包
            //解决办法：
            //在项目的Build path中先移除JRE System Library，再添加库JRE System Library，重新编译后就一切正常了。
            //String AES_encode=new String(new BASE64Encoder().encode(byte_AES));
            //11.将字符串返回
            //return AES_encode;
            return byte2hex(byte_AES);
      	} catch (NoSuchAlgorithmException e) {
      		e.printStackTrace();
      	} catch (NoSuchPaddingException e) {
      		e.printStackTrace();
      	} catch (InvalidKeyException e) {
      		e.printStackTrace();
      	} catch (IllegalBlockSizeException e) {
          e.printStackTrace();
      	} catch (BadPaddingException e) {
          e.printStackTrace();
      	} catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      	}
          
        //如果有错就返加nulll
        return null;         
	}
	/**
	 * 解密
	 * 解密过程：
	 * 1.同加密1-4步
	 * 2.将加密后的字符串反纺成byte[]数组
	 * 3.将加密内容解密
	 */
	public static String AESDncode(String encodeRules,String content){
		try {
			//1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen=KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            keygen.init(128, new SecureRandom(encodeRules.getBytes()));
            //3.产生原始对称密钥
            SecretKey original_key=keygen.generateKey();
            //4.获得原始对称密钥的字节数组
            byte [] raw=original_key.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKey key=new SecretKeySpec(raw, "AES");
            //6.根据指定算法AES自成密码器
            Cipher cipher=Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, key);
            //8.将加密并编码后的内容解码成字节数组
            //byte [] byte_content= new BASE64Decoder().decodeBuffer(content);
            /*
             * 解密
             */
            //byte [] byte_decode=cipher.doFinal(byte_content);
            //String AES_decode=new String(byte_decode,"utf-8");
            byte[] encrypted1 = hex2byte(content);
            byte[] original = cipher.doFinal(encrypted1);  
            String originalString = new String(original);  
            return originalString;  
            
            //return AES_decode;
        } catch (NoSuchAlgorithmException e) {
        	e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
        	e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
          
        //如果有错就返加nulll
        return null;         
    }
    
    private static String encode(String str, String method) {    
        MessageDigest md = null;    
        String dstr = null;    
        try {    
            md = MessageDigest.getInstance(method);    
            md.update(str.getBytes());    
            byte[] buffer = md.digest();
            StringBuffer sb = new StringBuffer(buffer.length * 2);
            for (int i = 0; i < buffer.length; i++) {
            	sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));
            	sb.append(Character.forDigit(buffer[i] & 15, 16));
            }
            dstr = sb.toString();
        } catch (NoSuchAlgorithmException e) {    
            e.printStackTrace();    
        }    
        return dstr;    
    }
	
    private static byte[] hex2byte(String strhex) {  
        if (strhex == null) {  
            return null;  
        }  
        int l = strhex.length();  
        if (l % 2 == 1) {  
            return null;  
        }  
        byte[] b = new byte[l / 2];  
        for (int i = 0; i != l / 2; i++) {  
            b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2),  
                    16);  
        }  
        return b;  
    }  
  
    private static String byte2hex(byte[] b) {  
        String hs = "";  
        String stmp = "";  
        for (int n = 0; n < b.length; n++) {  
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {  
                hs = hs + "0" + stmp;  
            } else {  
                hs = hs + stmp;  
            }  
        }  
        return hs.toUpperCase();  
    }  
    
    /*
    public static void main(String[] args) throws IOException {    
        String user = "oneadmin";    
        System.out.println("原始字符串 " + user);    
        System.out.println("MD5加密 " + encodeMD5String(user));    
        System.out.println("SHA加密 " + encodeSHAString(user));    
        String base64Str = encodeBase64String(user);    
        System.out.println("Base64加密 " + base64Str);    
        System.out.println("Base64解密 " + decodeBase64String(base64Str));    
        
        String secretKey = "111111112222222233333333";
		System.out.println(encrypt("{patternID : '1001', raq : 'test.raq'}", secretKey));
		System.out.println(encrypt("{patternID : '0', username : 'zhangsan', password : '1'}", secretKey));
		System.out.println(encrypt("{patternID : '0', username : 'zhangsan', password : '2'}", secretKey));
    }
    */
}
