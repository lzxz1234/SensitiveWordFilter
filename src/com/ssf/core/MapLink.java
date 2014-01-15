package com.ssf.core;

import java.util.HashMap;
import java.util.Map;

/**
 * @class MapLink
 * @author lzxz1234<lzxz1234@gmail.com>
 * @version v1.0
 */
public class MapLink implements Link<Character, Node> {

    private Map<Character, Node> map = new HashMap<Character, Node>();
    
    @Override
    public void put(Character key, Node value) {
        
        map.put(key, value);
    }

    @Override
    public Node get(Character key) {
        
        return map.get(key);
    }

    public MapLink() {
    }
    
    public int size() {
        return map.size();
    }

}
