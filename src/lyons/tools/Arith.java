package lyons.tools;

import java.math.BigDecimal;

/**
 * 高精度四则运算
 * @author lyons(weizq)
 */
public class Arith {
    private static final int DEF_DIV_SCALE = 2;

    private Arith() {
    }

    /**
     * 提供精确的加法运算
     * @param v1,v2
     * @return sum
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v1));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的减法程序
     * @param v1,v2
     * @return sub
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确的乘法运算
     * @param v1,v2
     * @return mul
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供（相对）精确地除法运算，当发生除不尽的情况时，精确到
     * 小数点以后2位，以后的数字四舍五入
     * @param v1,v2
     * @return div
     */
    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供相对精确的除法运算，当发生除不尽的情况时，有scale参数指定
     * 经度，以后的数字四舍五入
     * @param v1,v2,scale
     * @return div
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理
     * @param v,scale
     * @return res
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}