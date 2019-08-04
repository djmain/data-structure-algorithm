package com.jay.algorithm;

import java.util.List;

/**
 * created by Jay on 2019/8/4
 */
public class GetRedEnvelop implements Runnable
{

    List<Float> redList;

    public GetRedEnvelop(List<Float> redList)
    {
        this.redList = redList;
    }

    @Override
    public void run()
    {
        synchronized (redList)
        {
            if (redList.isEmpty())
            {
                System.out.println("红包已经被抢完.");
            }
            else
            {
                System.out.println("抢到红包:" + redList.remove(0));
            }
        }
    }
}
