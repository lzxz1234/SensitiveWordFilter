package com.ssf.core;

/**
 * @class ArrayContainer
 * @author lzxz1234<lzxz1234@gmail.com>
 * @version v1.0
 */
public class ArrayContainer implements Container<Character, Matcher> {

    public char[] keys = new char[0];
    public Matcher[] container = new Matcher[0];
    
    /* (non-Javadoc)
     * @see com.hyxq.pointcut.keywords.Container#put(java.lang.Object, java.lang.Object)
     */
    @Override
    public synchronized void put(Character key, Matcher value) {
        
        char[] newKeys = new char[keys.length + 1];
        System.arraycopy(keys, 0, newKeys, 0, keys.length);
        newKeys[keys.length] = key;
        keys = newKeys;
        
        Matcher[] target = new Matcher[container.length + 1];
        System.arraycopy(container, 0, target, 0, container.length);
        target[container.length] = value;
        container = target;
    }

    /* (non-Javadoc)
     * @see com.hyxq.pointcut.keywords.Container#get(java.lang.Object)
     */
    @Override
    public Matcher get(Character key) {
        
        for(int i = 0; i < keys.length; i ++) {
            if(keys[i] == key) return container[i];
        }
        return null;
    }

    /* (non-Javadoc)
     * @see com.hyxq.pointcut.keywords.Container#size()
     */
    @Override
    public int size() {
        
        return container.length;
    }

}
