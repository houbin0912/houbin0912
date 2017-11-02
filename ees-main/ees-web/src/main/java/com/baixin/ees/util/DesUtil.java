package com.baixin.ees.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * des 加密解密算法【可跨平台】
 * 
 * @author 胡砥峰
 * 
 */
@SuppressWarnings("restriction")
public class DesUtil {// des算法，加密证书用

    private static String desKey = "hudifeng";// 最好是8位，这样才能兼容不同操作系统（已测试通过win7，winserver2008，Centos6.5-linux这三种系统，加密解密完全一致）


    private static SecretKey generateKey(String secretKey) throws NoSuchAlgorithmException {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(secretKey.getBytes());
        // 为我们选择的DES算法生成一个KeyGenerator对象
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
        }
        kg.init(secureRandom);
        // kg.init(56, secureRandom);

        // 生成密钥
        return kg.generateKey();
    }


    /**
     * 加密 String 明文输入 ,String 密文输出
     */

    public static String encryptStr(String strMing) {
        byte[] byteMi = null;
        byte[] byteMing = null;
        String strMi = "";
        BASE64Encoder base64en = new BASE64Encoder();
        try {
            byteMing = strMing.getBytes("UTF8");
            byteMi = encryptByte(byteMing);
            strMi = base64en.encode(byteMi);
        } catch (Exception e) {
            throw new RuntimeException("系统错误: " + e);
        } finally {
            base64en = null;
            byteMing = null;
            byteMi = null;
        }
        return strMi;
    }


    /**
     * 解密 以 String 密文输入 ,String 明文输出
     * 
     * @param strMi
     * @return
     */
    public String decryptStr(String strMi) {
        BASE64Decoder base64De = new BASE64Decoder();
        byte[] byteMing = null;
        byte[] byteMi = null;
        String strMing = "";
        try {
            byteMi = base64De.decodeBuffer(strMi);
            byteMing = this.decryptByte(byteMi);
            strMing = new String(byteMing, "UTF8");
        } catch (Exception e) {
            throw new RuntimeException("系统错误: " + e);
        } finally {
            base64De = null;
            byteMing = null;
            byteMi = null;
        }
        return strMing;
    }


    /**
     * 加密以 byte[] 明文输入 ,byte[] 密文输出
     * 
     * @param byteS
     * @return
     */
    private static byte[] encryptByte(byte[] byteS) {
        byte[] byteFina = null;
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, generateKey(desKey));
            byteFina = cipher.doFinal(byteS);
        } catch (Exception e) {
            throw new RuntimeException("系统错误: " + e);
        } finally {
            cipher = null;
        }
        return byteFina;
    }


    /**
     * 解密以 byte[] 密文输入 , 以 byte[] 明文输出
     * 
     * @param byteD
     * @return
     */
    private byte[] decryptByte(byte[] byteD) {
        Cipher cipher;
        byte[] byteFina = null;
        try {
            cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, DesUtil.generateKey(DesUtil.desKey));
            byteFina = cipher.doFinal(byteD);
        } catch (Exception e) {
            throw new RuntimeException("系统错误: " + e);
        } finally {
            cipher = null;
        }
        return byteFina;
    }


    public static void main(String[] args) throws Exception {
        DesUtil desUtil = new DesUtil();

        String str1 = "123456";
        String str2 = DesUtil.encryptStr(str1);// DES 加密字符串
        String deStr = desUtil.decryptStr(str2);// DES 解密字符串

        System.out.println("加密前： " + str1);
        System.out.println("加密后： " + str2);
        System.out.println("解密后： " + deStr);
    }
}