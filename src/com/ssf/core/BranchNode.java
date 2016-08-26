package com.ssf.core;

import java.util.List;

/**
 * @class BranchNode
 * @author lzxz1234<lzxz1234@gmail.com>
 * @version v1.0
 */
public class BranchNode extends AbstractNode implements Node {

	private final Result RESULT_FALSE = new Result(this, false);
	
    public BranchNode(AbstractNode priorNode, String label) {
    	
		super(priorNode, label);
	}

	public BranchNode(BranchNode priorNode, Character c) {
		
		this(priorNode, c.toString());
	}

	private final Link<Character, Node> nexts = new MapLink();
    
    /** 
     * 构建敏感词树
     */
    public synchronized void parse(char[] keywords, int start) {
        
        if(start < 0 || start >= keywords.length) return;
        
        if(start < keywords.length - 1) 
            this.createNextNode(keywords[start]).parse(keywords, start + 1);
        else
            this.nexts.put(keywords[start], new LeafNode(this, keywords[start]));
    }
    
    /** 
     * 遍历敏感词树进行匹配测试
     */
    public Result matches(char[] target, int start) {
        
        if(start == target.length) return RESULT_FALSE;
        Node matcher = nexts.get(target[start]);
        return matcher == null ? RESULT_FALSE : matcher.matches(target, start + 1);
    }
    
    /** 
     * create 为 True 时构建并返回下级结点， False 时返回不构建下级结点
     */
    private Node createNextNode(Character c) {
        
        Node matcher = nexts.get(c);
        if(matcher != null) return matcher;
        matcher = new BranchNode(this, c);
        nexts.put(c, matcher);
        return matcher;
    }

    public List<Node> children() {

        return this.nexts.values();
    }
    
}
