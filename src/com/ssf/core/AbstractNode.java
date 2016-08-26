package com.ssf.core;

/**
 * @class AbstractNode
 * @author lzxz1234<lzxz1234@gmail.com>
 * @version v1.0
 */
public abstract class AbstractNode implements Node {

    private final Node parent;
	private final String path;
	
	public AbstractNode(AbstractNode priorNode, String label) {
		
	    this.parent = priorNode;
		this.path = priorNode == null ? label : priorNode.path + label;
	}
	
	public String path() {

		return path;
	}

    public Node parent() {
        
        return parent;
    }

}
