package com.chauncy.cloud.common.utils;

import com.google.common.base.Optional;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @Author zhangrt
 * @Date 2019/7/11 19:39
 **/
public class BigDecimalUtil {


    // 保留一位小数点
    private static final DecimalFormat df = new DecimalFormat("0.00");//保留两位小数点



    /**

     * BigDecimal的加法运算封装

     * @author : shijing

     * 2017年3月23日下午4:53:21

     * @param b1

     * @param bn

     * @return

     */

    public static BigDecimal safeAdd(BigDecimal b1, BigDecimal... bn) {

        if (null == b1) {

            b1 = BigDecimal.ZERO;

        }

        if (null != bn) {

            for (BigDecimal b : bn) {

                b1 = b1.add(null == b ? BigDecimal.ZERO : b);

            }

        }

        return b1;

    }



    /**

     * Integer加法运算的封装

     * @author : shijing

     * 2017年3月23日下午4:54:08

     * @param b1   第一个数

     * @param bn   需要加的加法数组

     * @注 ： Optional  是属于com.google.common.base.Optional<T> 下面的class

     * @return

     */

    public static Integer safeAdd(Integer b1, Integer... bn) {

        if (null == b1) {

            b1 = 0;

        }

        Integer r = b1;

        if (null != bn) {

            for (Integer b : bn) {

                r += Optional.fromNullable(b).or(0);

            }

        }

        return r > 0 ? r : 0;

    }



    /**

     * 计算金额方法

     * @author : shijing

     * 2017年3月23日下午4:53:00

     * @param b1

     * @param bn

     * @return

     */

    public static BigDecimal safeSubtract(BigDecimal b1, BigDecimal... bn) {

        return safeSubtract(true, b1, bn);

    }



    /**

     * BigDecimal的安全减法运算

     * @author : shijing

     * 2017年3月23日下午4:50:45

     * @param isZero  减法结果为负数时是否返回0，true是返回0（金额计算时使用），false是返回负数结果

     * @param b1        被减数

     * @param bn        需要减的减数数组

     * @return

     */

    public static BigDecimal safeSubtract(Boolean isZero, BigDecimal b1, BigDecimal... bn) {

        if (null == b1) {

            b1 = BigDecimal.ZERO;

        }

        BigDecimal r = b1;

        if (null != bn) {

            for (BigDecimal b : bn) {

                r = r.subtract((null == b ? BigDecimal.ZERO : b));

            }

        }

        return isZero ? (r.compareTo(BigDecimal.ZERO) == -1 ? BigDecimal.ZERO : r) : r;

    }







    /**

     * 金额除法计算，返回2位小数（具体的返回多少位大家自己看着改吧）

     * @author : shijing

     * 2017年3月23日下午5:02:17

     * @param b1

     * @param b2

     * @return

     */

    public static <T extends Number> BigDecimal safeDivide(T b1, T b2){

        return safeDivide(b1, b2, BigDecimal.ZERO);

    }

    /**
     * @Author zhangrt
     * @Date 2019/10/29 16:43
     * @Description 保留两位，后面的小数舍弃，不是四舍五入
     *
     * @Update
     *
     * @Param [b1, b2]
     * @return java.math.BigDecimal
     **/

    public static <T extends Number> BigDecimal safeDivideDown(T b1, T b2){

        return BigDecimal.valueOf(b1.doubleValue()).divide(BigDecimal.valueOf(b2.doubleValue()), 2, BigDecimal.ROUND_DOWN);

    }



    /**

     * BigDecimal的除法运算封装，如果除数或者被除数为0，返回默认值

     * 默认返回小数位后2位，用于金额计算

     * @author : shijing

     * 2017年3月23日下午4:59:29

     * @param b1

     * @param b2

     * @param defaultValue

     * @return

     */

    public static <T extends Number> BigDecimal safeDivide(T b1, T b2, BigDecimal defaultValue) {

        if (null == b1 ||  null == b2) {

            return defaultValue;

        }

        try {

            return BigDecimal.valueOf(b1.doubleValue()).divide(BigDecimal.valueOf(b2.doubleValue()), 2, BigDecimal.ROUND_HALF_UP);

        } catch (Exception e) {

            return defaultValue;

        }

    }

    /**
     * @Author chauncy
     * @Date 2019-09-19 11:55
     * @Description //BigDecimal的除法运算封装，如果除数或者被除数为0，返回默认值
     *
     * @Update chauncy
     *
     * @Param [b1, b2, defaultValue]
     * @param num 保留位数
     * @return java.math.BigDecimal
     **/
    public static <T extends Number> BigDecimal safeDivide(T b1, T b2,Integer num,BigDecimal defaultValue) {

        if (null == b1 ||  null == b2) {

            return defaultValue;

        }

        try {

            return BigDecimal.valueOf(b1.doubleValue()).divide(BigDecimal.valueOf(b2.doubleValue()), num, BigDecimal.ROUND_HALF_UP);

        } catch (Exception e) {

            return defaultValue;

        }

    }



    /**
     * 四舍五入

     * BigDecimal的乘法运算封装

     * @author : shijing

     * 2017年3月23日下午5:01:57

     * @param b1

     * @param b2

     * @return

     */

    public static <T extends Number> BigDecimal safeMultiply(T b1, T b2) {

        if (null == b1 ||  null == b2) {

            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(b1.doubleValue()).multiply(BigDecimal.valueOf(b2.doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * @Author zhangrt
     * @Date 2019/10/29 16:44
     * @Description 保留两位，后面的小数舍弃，不是四舍五入
     *
     * @Update
     *
     * @Param [b1, b2]
     * @return java.math.BigDecimal
     **/

    public static <T extends Number> BigDecimal safeMultiplyDown(T b1, T b2) {

        if (null == b1 ||  null == b2) {

            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(b1.doubleValue()).multiply(BigDecimal.valueOf(b2.doubleValue())).setScale(2, BigDecimal.ROUND_DOWN);
    }

    /**x

     * BigDecimal的乘法运算封装

     * @author : chauncy

     * 2019年9月19日 10:09

     * @param b1

     * @param b2

     * @param num 保留位数

     * @return

     */

    public static <T extends Number> BigDecimal safeMultiply(T b1, T b2,Integer num) {

        if (null == b1 ||  null == b2) {

            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(b1.doubleValue()).multiply(BigDecimal.valueOf(b2.doubleValue())).setScale(num,BigDecimal.ROUND_HALF_UP);
    }



}