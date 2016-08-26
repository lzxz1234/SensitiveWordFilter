package com.ssf.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @class MapLink
 * @author lzxz1234<lzxz1234@gmail.com>
 * @version v1.0
 */
public class MapLink implements Link<Character, Node> {

    private Map<Character, Node> map = new HashMap<Character, Node>();
    
    public void put(Character key, Node value) {
        
        map.put(key, value);
    }

    public Node get(Character key) {
        
        return map.get(key);
    }

    public MapLink() {
    }
    
    public int size() {
        return map.size();
    }

    public List<Node> values() {

        return new ArrayList<Node>(map.values());
    }

}
