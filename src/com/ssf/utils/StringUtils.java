package com.ssf.utils;

/**
 * @class StringUtils
 * @author lzxz1234<lzxz1234@gmail.com>
 * @version v1.0
 */
public class StringUtils {

    /** 
     * @param keyword
     * @return
     */
    public static boolean isEmpty(String keyword) {
        
        return keyword == null || keyword.length() == 0;
    }

}
