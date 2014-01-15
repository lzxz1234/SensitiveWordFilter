/*
 * @title: LeafNode.java
 * @package com.ssf.core
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014-1-15 上午10:34:37
 * @version V1.0
 * Copyright (c) 2012 Aspirecn.com. All Right Reserved
 */
package com.ssf.core;

/**
 * @class LeafNode
 * @author lzxz1234<lzxz1234@gmail.com>
 * @version v1.0
 */
public class LeafNode implements Node {

    public static final LeafNode INS = new LeafNode();
    
    private LeafNode() {}
    
    @Override
    public void parse(char[] keywords, int start) {
        
    }

    @Override
    public boolean matches(char[] target, int start) {

        return true;
    }

}
