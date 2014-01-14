package com.ssf.core;

/**
 * @class SingleLink
 * @author lzxz1234<lzxz1234@gmail.com>
 * @version v1.0
 */
public class SingleLink implements Link<Character, Node> {

    protected char key;
    protected Node container;
    
    @Override
    public void put(Character key, Node value) {
        
        this.key = key;
        this.container = value;
    }

    @Override
    public Node get(Character key) {
        
        return this.key == key.charValue() ? container : null;
    }

    @Override
    public int size() {
        
        return container == null ? 0 : 1;
    }

}
