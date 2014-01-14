/*
 * @title: Node.java
 * @package com.ssf.core
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014-1-14 上午9:22:36
 * @version V1.0
 * Copyright (c) 2012 Aspirecn.com. All Right Reserved
 */
package com.ssf.core;

import java.util.HashSet;
import java.util.Set;

/**
 * @class Node
 * @author lzxz1234<lzxz1234@gmail.com>
 * @version v1.0
 */
public class Node {

    private Set<Character> ends = new HashSet<Character>();
    private Link<Character, Node> nexts = new MapLink();
    
    /** 
     * 构建敏感词树
     */
    public synchronized void parse(char[] keywords, int start) {
        
        if(start < 0 || start > keywords.length - 1) return;
        
        if(start < keywords.length -  1) 
            this.createNextNode(keywords[start]).parse(keywords, start + 1);
        else
            this.ends.add(keywords[start]);
    }
    
    /** 
     * 遍历敏感词树进行匹配测试
     */
    public boolean matches(char[] target, int start) {
        
        if(ends.contains(target[start])) return true;
        Node matcher = nexts.get(target[start]);
        return (matcher == null || start + 1 == target.length) ? false : matcher.matches(target, start + 1);
    }
    
    /** 
     * create 为 True 时构建并返回下级结点， False 时返回不构建下级结点
     */
    private Node createNextNode(Character c) {
        
        Node matcher = nexts.get(c);
        if(matcher != null) return matcher;
        matcher = new Node();
        nexts.put(c, matcher);
        return matcher;
    }
    
}
