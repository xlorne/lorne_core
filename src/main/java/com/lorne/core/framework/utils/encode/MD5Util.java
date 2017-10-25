package com.lorne.core.framework.utils.encode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5 加密工具类
 */
public class MD5Util {


    private static Logger logger = LoggerFactory.getLogger(MD5Util.class);

    public static String convertMD5(String inStr) {
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        return new String(a);
    }

    public static String string2MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }


    public static String md5(File file) {
        try {
            MessageDigest msgDigest = null;
            try {
                msgDigest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalStateException(
                        "System doesn't support MD5 algorithm.");
            }

            FileInputStream fileInputStream = new FileInputStream(file);

            byte[] bs = new byte[1024];
            while (fileInputStream.read(bs) != -1) {
                msgDigest.update(bs);
            }
            byte[] bytes = msgDigest.digest();
            if (fileInputStream != null)
                fileInputStream.close();
            return byteToString(bytes);
        } catch (java.io.IOException e) {
            logger.error(e.getMessage());
            return null;
        }
    }


    private static String byteToString(byte[] bytes){
        byte tb;
        char low;
        char high;
        char tmpChar;
        String md5Str="";
        for (int i = 0; i < bytes.length; i++) {
            tb = bytes[i];
            tmpChar = (char) ((tb >>> 4) & 0x000f);
            if (tmpChar >= 10) {
                high = (char) (('a' + tmpChar) - 10);
            } else {
                high = (char) ('0' + tmpChar);
            }
            md5Str += high;
            tmpChar = (char) (tb & 0x000f);
            if (tmpChar >= 10) {
                low = (char) (('a' + tmpChar) - 10);
            } else {
                low = (char) ('0' + tmpChar);
            }
            md5Str += low;
        }
        return md5Str;
    }

    public static String md5(byte[] bs) {
        MessageDigest msgDigest = null;
        try {
            msgDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
            throw new IllegalStateException(
                    "System doesn't support MD5 algorithm.");
        }
        msgDigest.update(bs);
        byte[] bytes = msgDigest.digest();
        return byteToString(bytes);
    }

    public static byte[] md5Byte(String str) {
        byte[] utfBytes;
        try {
            utfBytes = str.getBytes("UTF-8");
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(utfBytes);
            return mdTemp.digest();
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
