package com.ssf.core;

import com.ssf.utils.StringUtils;

/**
 * @class Matcher
 * @author lzxz1234<lzxz1234@gmail.com>
 * @version v1.0
 */
public class Matcher {

    private static final Node root = new Node();
    
    /** 
     * 加载敏感词，初始化时使用
     */
    public static void addKeyWord(String keyword) {
        
        if(StringUtils.isEmpty(keyword)) return;
        root.parse(keyword.toCharArray(), 0);
    }
    
    /** 
     * 判断是否非法，返回 True 时非法， False 合法
     */
    public static boolean isIllegal(String content) {
        
        if(StringUtils.isEmpty(content)) return false;
        char[] chars = content.toCharArray();
        for(int i = 0; i < chars.length; i ++) {
            if(root.matches(chars, i)) return true;
        }
        return false;
    }
    
}
