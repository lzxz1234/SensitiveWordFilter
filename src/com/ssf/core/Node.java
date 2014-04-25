package com.ssf.core;

/**
 * @class Node
 * @author lzxz1234<lzxz1234@gmail.com>
 * @version v1.0
 */
public interface Node {

    public abstract void parse(char[] keywords, int start);
    
    public abstract Result matches(char[] target, int start);
    
    public abstract String path();
    
}
