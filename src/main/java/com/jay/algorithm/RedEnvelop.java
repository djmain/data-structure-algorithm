package com.jay.algorithm;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

/**
 * 红包算法
 * created by Jay on 2019/8/3
 */
public class RedEnvelop
{
    private static final int DEF_DIV_SCALE = 10;

    /**
     * java中直接用float进行浮点运算，结果不准确
     */
    @Test
    public void testFloat1()
    {
//        float secondValue = new BigDecimal(max).subtract(new BigDecimal(firstValue)).floatValue();
        float a = 4.02F;
        float b = 2.13F;
        float c = a - b;
        System.out.println(c); //1.8899999
    }

    @Test
    public void testBigDecimal1()
    {
        float a = 4.02F;
        float b = 2.13F;
        float c = new BigDecimal(a).subtract(new BigDecimal(b)).floatValue();
        System.out.println(c); //1.8899999
    }

    @Test
    public void testBigDecimal2()
    {
        float a = 4.02F;
        float b = 2.13F;
        float c = new BigDecimal(Float.toString(a)).subtract(new BigDecimal(Float.toString(b))).floatValue();
        System.out.println(c); //1.89
    }


    //相加
    public static double add(double d1, double d2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.add(b2).doubleValue();

    }

    //相加
    public static float add(float d1, float d2)
    {
        BigDecimal b1 = new BigDecimal(Float.toString(d1));
        BigDecimal b2 = new BigDecimal(Float.toString(d2));
        return b1.add(b2).floatValue();

    }

    //相减
    public static double sub(double d1, double d2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.subtract(b2).doubleValue();

    }

    public static float sub(float d1, float d2)
    {
        BigDecimal b1 = new BigDecimal(Float.toString(d1));
        BigDecimal b2 = new BigDecimal(Float.toString(d2));
        return b1.subtract(b2).floatValue();

    }

    //相乘
    public static double mul(double d1, double d2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.multiply(b2).doubleValue();

    }

    //相除
    public static double div(double d1, double d2)
    {

        return div(d1, d2, DEF_DIV_SCALE);

    }

    public static double div(double d1, double d2, int scale)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

    }


    public static void main(String[] args) throws Exception
    {
        for (int i = 0; i < 1000; i++)
        {
            List<Float> list = createRedEnvelop(100.5F, 10);
            System.out.println(list);
            float total = 0F;
            for (Float value : list)
            {
                total = add(total, value);
            }
            Assert.assertEquals(new BigDecimal(Float.toString(100.5F)), new BigDecimal(Float.toString(total)));
        }

    }

    /**
     * 随机生成红包
     *
     * @param total 总金额
     * @param num   红包个数
     * @return 所有红包列表
     */
    public static List<Float> createRedEnvelop(float total, int num)
    {
        List<Float> redList = new ArrayList<>();
        redList.add(total);
        Random random = null;
        if (num > 1)
        {
            random = new Random();
        }
        while (redList.size() < num)
        {
            float max = Collections.max(redList);
            redList.remove(max);
            float first = 0F;
            while (first == 0F)
            {
                first = remainTwoScale(max * random.nextFloat());
            }
            float second = sub(max, first);
            redList.add(first);
            redList.add(second);
        }
        return redList;
    }


    /**
     * 错误算法
     *
     * @param total
     * @param num
     * @return
     */
//    public static List<Float> createRedEnvelop(float total, int num)
//    {
//        float max = total;
//        float secondMax = 0F;
//        int maxIndex = 0;
//        int secondMaxIndex = 0;
//        List<Float> redList = new ArrayList<>();
//        if (num == 1)
//        {
//            redList.add(total);
//        }
//        else
//        {
//            Random random = new Random();
//            while (redList.size() < num)
//            {
//                System.out.println(redList);
//                float all = 0F;
//                for (Float value : redList)
//                {
//                    all = add(all, value);
//                }
//                System.out.println(all);
//                if (redList.size() > 0)
//                {
//                    redList.remove(maxIndex);
//                    System.out.println("after removed:" + redList);
//                }
//                float firstValue = 0F;
//                while (firstValue == 0F)
//                {
//                    firstValue = remainTwoScale(max * random.nextFloat());
//                }
//                float secondValue = sub(max, firstValue);
//                System.out.println("max:" + max + ", firstValue:" + firstValue + ", secondValue:" + secondValue + ", secondMax:" + secondMax);
//                if (redList.isEmpty())
//                {
//                    max = Math.max(firstValue, secondValue);
//                    secondMax = Math.min(firstValue, secondValue);
//                    maxIndex = max == firstValue ? 0 : 1;
//                    secondMaxIndex = maxIndex == 0 ? 1 : 0;
//                }
//                else
//                {
//                    max = Math.max(Math.max(firstValue, secondValue), secondMax);
//                    if (max == firstValue)
//                    {
//                        secondMax = Math.max(secondValue, secondMax);
//                        maxIndex = redList.size();
//                        secondMaxIndex = secondMax == secondValue ? redList.size() + 1 : (secondMaxIndex > 0 ? secondMaxIndex - 1 : 0);
//                    }
//                    else if (max == secondValue)
//                    {
//                        secondMax = Math.max(firstValue, secondMax);
//                        maxIndex = redList.size() + 1;
//                        secondMaxIndex = secondMax == firstValue ? redList.size() : (secondMaxIndex > 0 ? (secondMaxIndex > maxIndex ? secondMaxIndex - 1 : 0);
//                    }
//                    else
//                    {
//                        secondMax = Math.max(firstValue, secondValue);
//                        maxIndex = (secondMaxIndex > 0 ? secondMaxIndex - 1 : 0);
//                        secondMaxIndex = secondMax == firstValue ? redList.size() : redList.size() + 1;
//                    }
//                    System.out.println("max:" + max + ", secondMax:" + secondMax + ", maxIndex:" + maxIndex + ", secondMaxIndex:" + secondMaxIndex);
//                }
//                redList.add(firstValue);
//                redList.add(secondValue);
//            }
//        }
//
//        return redList;
//    }

    /**
     * 返回保留2位小数的值
     *
     * @param a
     * @return
     */
    public static float remainTwoScale(float a)
    {
        BigDecimal bg = new BigDecimal(Float.toString(a));
        float f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        return f1;
    }
}
