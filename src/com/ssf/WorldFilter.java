package com.ssf;

import com.ssf.core.BranchNode;
import com.ssf.core.Matcher;
import com.ssf.core.Node;
import com.ssf.core.Result;
import com.ssf.utils.StringUtils;

/**
 * @class Matcher
 * @author lzxz1234<lzxz1234@gmail.com>
 * @version v1.0
 */
public class WorldFilter implements Matcher{

    private Node root = new BranchNode(null, "");
    
    /** 
     * 加载敏感词，初始化时使用
     */
    public void addKeyWord(String keyword) {
        
        if(StringUtils.isEmpty(keyword)) return;
        root.parse(keyword.toCharArray(), 0);
    }
    
    public void clear() {
        
        root = new BranchNode(null, "");
    }
    
    /** 
     * 判断是否非法，返回 True 时非法， False 合法
     */
    public boolean isIllegal(String content) {
        
        return matches(content).isHit();
    }
    
    public Result matches(String content) {
    	
    	if(StringUtils.isEmpty(content)) return Result.ZERO_HIT;
        char[] chars = content.toCharArray();
        Result result;
        for(int i = 0; i < chars.length; i ++) {
            if((result = root.matches(chars, i)).isHit()) return result;
        }
        return Result.ZERO_HIT;
    }
    
}
