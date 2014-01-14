package com.ssf.core;

/**
 * @class BlanceLink
 * @author lzxz1234<lzxz1234@gmail.com>
 * @version v1.0
 */
public class BlanceLink implements Link<Character, Node> {

    private Link<Character, Node> container = new SingleLink();
    
    @Override
    public void put(Character key, Node value) {
        
        if(container.size() == 1) {
            container = new MapLink((SingleLink)container);
        }
        container.put(key, value);
    }

    @Override
    public Node get(Character key) {

        return container.get(key);
    }

    @Override
    public int size() {

        return container.size();
    }

}
