package com.ssf.core;

/**
 * @class Matcher
 * @author lzxz1234<lzxz1234@gmail.com>
 * @version v1.0
 */
public interface Matcher {

    /** 
     * 加载敏感词，初始化时使用
     */
    public void addKeyWord(String keyword);
    
    public void clear();
    
    /** 
     * 判断是否非法，返回 True 时非法， False 合法
     */
    public boolean isIllegal(String content);
    
    public Result matches(String content);
    
}
