package com.zerodeng.utils;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName:Encrypt
 * @Description:TODO Hash算法
 * @Author:ZeroDeng
 * @Date:2018-12-05 13:35
 * @Version:1.0
 **/
public class Encrypt {
    private static Logger logger = Logger.getLogger(Encrypt.class);



    /**
    * @Author ZeroDeng
    * @Description TODO 用户密码加解密
    * @Date 13:47 2018-12-05
    * @Param [strText]
    * @return java.lang.String
    * @Version 1.0
    **/
    public static String UserPwdSHA256(final String strText,final String S) {
        return SHA(strText+S, "SHA-256");
    }
    /**
     * 传入文本内容，返回 SHA-256 串
     *
     * @param strText
     * @return
     */
    public static String SHA256(final String strText) {
        return SHA(strText, "SHA-256");
    }

    /**
     * 传入文本内容，返回 SHA-512 串
     *
     * @param strText
     * @return
     */
    public static String SHA512(final String strText) {
        return SHA(strText, "SHA-512");
    }

    /**
    * @Author ZeroDeng
    * @Description TODO 字符串SHA加密
    * @Date 13:37 2018-12-05
    * @Param [strText, strType]
    * @return java.lang.String
    * @Version 1.0
    **/
    private static String SHA(final String strText, final String strType) {
        // 返回值
        String strResult = null;

        // 是否是有效字符串
        if (strText != null && strText.length() > 0) {
            try {
                // SHA 加密开始
                // 创建加密对象 并傳入加密類型
                MessageDigest messageDigest = MessageDigest.getInstance(strType);
                // 传入要加密的字符串
                messageDigest.update(strText.getBytes());
                // 得到 byte 類型结果
                byte byteBuffer[] = messageDigest.digest();

                // 將 byte 轉換爲 string
                StringBuffer strHexString = new StringBuffer();
                // 遍歷 byte buffer
                for (int i = 0; i < byteBuffer.length; i++) {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length() == 1) {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                // 得到返回結果
                strResult = strHexString.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        return strResult;
    }
}
