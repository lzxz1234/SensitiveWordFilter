package com.ssf.utils;

import java.io.InputStream;

/**
 * @class IOUtils
 * @author lzxz1234<lzxz1234@gmail.com>
 * @version v1.0
 */
public class IOUtils {

    /** 
     * @param is
     */
    public static void closeQuietly(InputStream is) {
        
        try {
            if(is != null) {
                is.close();
            }
        } catch (Exception e) {
            //Ignore
        }
    }

}
