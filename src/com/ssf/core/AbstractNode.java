package com.ssf.core;

/**
 * @class AbstractNode
 * @author lzxz1234<lzxz1234@gmail.com>
 * @version v1.0
 */
public abstract class AbstractNode implements Node {

	private final String path;
	
	public AbstractNode(AbstractNode priorNode, String label) {
		
		this.path = priorNode == null ? label : priorNode.path + label;
	}
	
	@Override
	public String path() {

		return path;
	}

}
