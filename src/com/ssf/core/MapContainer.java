package com.ssf.core;

import java.util.HashMap;
import java.util.Map;

/**
 * @class MapContainer
 * @author lzxz1234<lzxz1234@gmail.com>
 * @version v1.0
 */
public class MapContainer implements Container<Character, Matcher> {

    private Map<Character, Matcher> map = new HashMap<Character, Matcher>();
    
    /* (non-Javadoc)
     * @see com.hyxq.pointcut.keywords.Container#put(java.lang.Object, java.lang.Object)
     */
    @Override
    public void put(Character key, Matcher value) {
        
        map.put(key, value);
    }

    /* (non-Javadoc)
     * @see com.hyxq.pointcut.keywords.Container#get(java.lang.Object)
     */
    @Override
    public Matcher get(Character key) {
        
        return map.get(key);
    }

    public MapContainer(ArrayContainer src) {

        for(int i = 0; i < src.keys.length; i ++) {
            map.put(src.keys[i], src.container[i]);
        }
    }
    
    public MapContainer() {
    }
    
    public int size() {
        return map.size();
    }

}
