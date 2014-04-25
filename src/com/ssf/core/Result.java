package com.ssf.core;

/**
 * @class Result
 * @author lzxz1234<lzxz1234@gmail.com>
 * @version v1.0
 */
public class Result {

	public static final Result ZERO_HIT = new Result(null, false) {
		public String maxHit() {
			return "";
		}
		public boolean isHit() {
			return false;
		}
	};
	
	private final Node endNode;
	private final boolean isHit;
	
	public Result(Node endNode, boolean isHit) {

		this.endNode = endNode;
		this.isHit = isHit;
	}
	
	public String maxHit() {
		
		return endNode.path();
	}
	
	public boolean isHit() {
		
		return isHit;
	}
	
}
