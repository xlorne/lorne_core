package com.lorne.core.framework.utils.encode;

import org.apache.axis.encoding.Base64;


/**
 * Created by yuliang on 2015/11/13.
 */
public class Base64Utils {



    public static String encode(byte[] bs){
        return Base64.encode(bs);
    }

    public static byte[] decode(String str){
        return Base64.decode(str);
    }


}
