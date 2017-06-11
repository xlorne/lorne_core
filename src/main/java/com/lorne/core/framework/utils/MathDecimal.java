package com.lorne.core.framework.utils;

import java.math.BigDecimal;

/**
 * Created by yuliang on 2015/9/7.
 */
public class MathDecimal {

    // 默认除法运算精度
    private  final int DEF_DIV_SCALE = 10;

    private BigDecimal count = null;

    public MathDecimal(){
        count =   new BigDecimal("0");
    }

    public MathDecimal(double val){
        count =  new BigDecimal(String.valueOf(val));
    }

    public MathDecimal(Double val){
        count =  new BigDecimal(String.valueOf(val));
    }

    public MathDecimal(int val){
        count =  new BigDecimal(String.valueOf(val));
    }

    public MathDecimal(Integer val){
        count =  new BigDecimal(String.valueOf(val));
    }

    public MathDecimal(float val){
        count =   new BigDecimal(String.valueOf(val));
    }

    public MathDecimal(Float val){
        count =  new BigDecimal(String.valueOf(val));
    }

    public MathDecimal(long val){
        count =   new BigDecimal(String.valueOf(val));
    }

    public MathDecimal(Long val){
        count =  new BigDecimal(String.valueOf(val));
    }

    public double toDouble(){
        return count.doubleValue();
    }

    public double toInt(){
        return count.intValue();
    }

    public float toFloat(){
        return count.floatValue();
    }


    public BigDecimal toBigDecimal(){
        return count;
    }

    public MathDecimal add(double... ds){
        if(ds!=null&&ds.length>0){
            for(double d:ds){
                BigDecimal val = new BigDecimal(Double.toString(d));
                count = count.add(val);
            }
        }
        return this;
    }

    public MathDecimal add(BigDecimal... ds){
        if(ds!=null&&ds.length>0){
            for(BigDecimal d:ds){
                count = count.add(d);
            }
        }
        return this;
    }

    public MathDecimal add(MathDecimal... ds){
        if(ds!=null&&ds.length>0){
            for(MathDecimal d:ds){
                count = count.add(d.toBigDecimal());
            }
        }
        return this;
    }

    public MathDecimal sub(double... ds){
        if(ds!=null&&ds.length>0){
            for(double d:ds){
                BigDecimal val = new BigDecimal(Double.toString(d));
                count = count.subtract(val);
            }
        }
        return this;
    }

    public MathDecimal sub(BigDecimal... ds){
        if(ds!=null&&ds.length>0){
            for(BigDecimal d:ds){
                count = count.subtract(d);
            }
        }
        return this;
    }

    public MathDecimal sub(MathDecimal... ds){
        if(ds!=null&&ds.length>0){
            for(MathDecimal d:ds){
                count = count.subtract(d.toBigDecimal());
            }
        }
        return this;
    }

    public MathDecimal mul(double... ds){
        if(ds!=null&&ds.length>0){
            for(double d:ds){
                BigDecimal val = new BigDecimal(Double.toString(d));
                count = count.multiply(val);
            }
        }
        return this;
    }

    public MathDecimal mul(BigDecimal... ds){
        if(ds!=null&&ds.length>0){
            for(BigDecimal d:ds){
                count = count.multiply(d);
            }
        }
        return this;
    }

    public MathDecimal mul(MathDecimal... ds){
        if(ds!=null&&ds.length>0){
            for(MathDecimal d:ds){
                count = count.multiply(d.toBigDecimal());
            }
        }
        return this;
    }
    public  MathDecimal div(double ...ds) {
        if(ds!=null&&ds.length>0){
            for(double d:ds){
                BigDecimal val = new BigDecimal(Double.toString(d));
                count = count.divide(val, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
            }
        }
        return this;
    }

    public  MathDecimal div(BigDecimal...ds) {
        if(ds!=null&&ds.length>0){
            for(BigDecimal d:ds){
                count = count.divide(d, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
            }
        }
        return this;
    }

    public  MathDecimal div(MathDecimal ...ds) {
        if(ds!=null&&ds.length>0){
            for(MathDecimal d:ds){
                count = count.divide(d.toBigDecimal(), DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
            }
        }
        return this;
    }

}
