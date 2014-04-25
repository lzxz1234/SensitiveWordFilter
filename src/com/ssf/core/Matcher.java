package com.ssf.core;

import com.ssf.utils.StringUtils;

/**
 * @class Matcher
 * @author lzxz1234<lzxz1234@gmail.com>
 * @version v1.0
 */
public class Matcher {

    private static Node root = new BranchNode(null, "");
    
    /** 
     * 加载敏感词，初始化时使用
     */
    public static void addKeyWord(String keyword) {
        
        if(StringUtils.isEmpty(keyword)) return;
        root.parse(keyword.toCharArray(), 0);
    }
    
    public static void clear() {
        
        root = new BranchNode(null, "");
    }
    
    /** 
     * 判断是否非法，返回 True 时非法， False 合法
     */
    public static boolean isIllegal(String content) {
        
        return matches(content).isHit();
    }
    
    public static Result matches(String content) {
    	
    	if(StringUtils.isEmpty(content)) return Result.ZERO_HIT;
        char[] chars = content.toCharArray();
        Result result;
        for(int i = 0; i < chars.length; i ++) {
            if((result = root.matches(chars, i)).isHit()) return result;
        }
        return Result.ZERO_HIT;
    }
}
