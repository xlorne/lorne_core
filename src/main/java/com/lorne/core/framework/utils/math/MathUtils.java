package com.lorne.core.framework.utils.math;

import java.math.BigDecimal;

public class MathUtils {

    // 默认除法运算精度
    private static final int DEF_DIV_SCALE = 10;


    public static double add(double... ds) {
        BigDecimal count = new BigDecimal("0");
        if (ds != null && ds.length > 0) {
            for (double d : ds) {
                BigDecimal val = new BigDecimal(Double.toString(d));
                count = count.add(val);
            }
        }
        return count.doubleValue();
    }



    public static double sub(double... ds) {
        if (ds == null)
            return 0;
        BigDecimal count = new BigDecimal(String.valueOf(ds[0]));
        if (ds != null && ds.length > 0) {
            int i = 0;
            for (double d : ds) {
                i++;
                if (i == 1)
                    continue;
                BigDecimal val = new BigDecimal(Double.toString(d));
                count = count.subtract(val);
            }
        }
        return count.doubleValue();
    }


    public static double mul(double... ds) {
        if(ds==null)
            return 0;
        BigDecimal count = new BigDecimal(String.valueOf(ds[0]));
        if(ds!=null&&ds.length>0){
            int i = 0;
            for(double d:ds){
                i++;
                if(i==1)
                    continue;
                BigDecimal val = new BigDecimal(Double.toString(d));
                count = count.multiply(val);
            }
        }
        return count.doubleValue();
    }


    public static double div(double... ds) {
        return div(DEF_DIV_SCALE, ds);
    }

    private static double div(int scale, double... ds) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        if (ds.length < 0) {
            throw new RuntimeException("参数无效");
        }
        BigDecimal count = new BigDecimal(String.valueOf(ds[0]));
        if (ds != null && ds.length > 0) {
            for (int i = 1; i < ds.length; i++) {
                double d = ds[i];
                BigDecimal val = new BigDecimal(Double.toString(d));
                count = count.divide(val, scale, BigDecimal.ROUND_HALF_UP);
            }
        }
        return count.doubleValue();
    }

}
