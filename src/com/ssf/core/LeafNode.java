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
public class LeafNode extends AbstractNode implements Node {

	private final Result RESULT_TRUE = new Result(this, true);
	
    public LeafNode(AbstractNode priorNode, String label) {
    	
    	super(priorNode, label);
    }
    
    public LeafNode(BranchNode priorNode, char c) {
		
    	this(priorNode, String.valueOf(c));
	}

	@Override
    public void parse(char[] keywords, int start) {
        
    }

    @Override
    public Result matches(char[] target, int start) {

        return RESULT_TRUE;
    }

}
