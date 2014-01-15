/*
 * @title: Node.java
 * @package com.ssf.core
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014-1-15 上午10:33:39
 * @version V1.0
 * Copyright (c) 2012 Aspirecn.com. All Right Reserved
 */
package com.ssf.core;

/**
 * @class Node
 * @author lzxz1234<lzxz1234@gmail.com>
 * @version v1.0
 */
public interface Node {

    public abstract void parse(char[] keywords, int start);
    
    public abstract boolean matches(char[] target, int start);
    
}
