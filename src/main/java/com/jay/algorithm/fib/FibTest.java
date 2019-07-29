package com.jay.algorithm.fib;

import com.jay.tool.time.TimeTool;
import org.junit.Test;

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
        for (int i = 1; i < n-1; i++)
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
        TimeTool.cost("fib1", () -> {
            System.out.println(fib1(40));
        });

        TimeTool.cost("fib2", () -> {
            System.out.println(fib2(40));
        });
    }
}
