package com.own.order.infrastructure.util;


/**
 * 字符串的一些相关操作工具
 *
 * @author CongJiaJia at 2019-1-7
 */
public class StringUtil
{

    /**
     * 删除前后指定的字符串
     *
     * @param str
     * @param c
     * @return
     */
    public static String toTrim(String str, char c)
    {
        if (str == null)
        {
            return null;
        }

        String result = str.replaceAll("^[" + c + "]+", "")
                .replaceAll("[" + c + "]+$", "");
        return result;
    }


    /**
     * 删除左侧指定的字符串
     *
     * @param str
     * @param c
     * @return
     */
    public static String toLtrim(String str, char c)
    {
        if (str == null)
        {
            return null;
        }

        String result = str.replaceAll("^[" + c + "]+", "");
        return result;
    }


    /**
     * 删除右侧指定的字符串
     *
     * @param str
     * @param c
     * @return
     */
    public static String toRtrim(String str, char c)
    {
        if (str == null)
        {
            return null;
        }

        String result = str.replaceAll("[" + c + "]+$", "");
        return result;
    }

}
