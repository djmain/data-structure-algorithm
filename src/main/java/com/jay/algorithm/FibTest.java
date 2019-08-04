package com.jay.algorithm;

import com.jay.tool.time.TimeTool;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * created by Jay on 2019/7/29
 * 1 1 2 3 5 8
 */
public class FibTest
{

    /**
     * 通过递归获取斐波那契数列的值
     *
     * @param n
     * @return
     */
    public int fib1(int n)
    {
        if (n <= 2) {return 1;}
        return fib1(n - 1) + fib1(n - 2);
    }


    public int fib2(int n)
    {
        int first = 1;
        int second = 1;
        for (int i = 1; i < n - 1; i++)
        {
            int sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }


    @Test
    public void testFib()
    {
//        TimeTool.cost("fib1", () -> {
//            System.out.println(fib1(40));
//        });
//
//        TimeTool.cost("fib2", () -> {
//            System.out.println(fib2(40));
//        });
        char a = 'a';
        String s = String.valueOf(a);
        byte[] data = s.getBytes();
        System.out.println(data.length);
    }

    public static void main(String[] args)
    {
        float numG = Float.parseFloat(args[0]);
        int num = (int) numG;
        float remain = numG - num;
        int byteLen = num;
        if (remain > 0)
        {
            byteLen++;
        }

        byte[][] bytes = new byte[byteLen][1];
        for (int i = 0; i < num; i++)
        {
            bytes[i] = new byte[1024 * 1024 * 1024];
        }
        if (remain > 0)
        {
            int remainByte = (int) (remain * 1024 * 1024 * 1024);
            bytes[byteLen - 1] = new byte[remainByte];
        }

        try
        {
            TimeUnit.DAYS.sleep(Long.MAX_VALUE);
        }
        catch (InterruptedException e)
        {

        }

        for (int i = 0; i < num; i++)
        {
            bytes[i][0] = 0;
        }
    }
}
