package com.jay.algorithm;

/**
 * created by Jay on 2019/7/30
 */
public class Test
{
    public static void main(String[] args) throws Exception
    {
        String hexStr = "5Cf3FC00C019";
        char[] chars = hexStr.toCharArray();
        long value = 0l;
        for (int i = 0; i < chars.length; i++)
        {
            int bit = getInt(chars[i]);
            value += bit * Math.pow(16, chars.length - i - 1);
        }
        value = value + 100;
        System.out.println(Long.toHexString(value));
    }

    private static int getInt(char ch) throws Exception
    {
        String s = new Character(ch).toString();
        if (s.matches("[0-9]")) {return Integer.parseInt(s);}
        switch (ch)
        {
            case 'A':
            case 'a':
                return 10;
            case 'B':
            case 'b':
                return 11;
            case 'C':
            case 'c':
                return 12;
            case 'D':
            case 'd':
                return 13;
            case 'E':
            case 'e':
                return 14;
            case 'F':
            case 'f':
                return 15;
            default:
                throw new Exception("Invalid hex character.");
        }
    }
}
