package com.ssf.core;

/**
 * @class ArrayContainer
 * @author lzxz1234<lzxz1234@gmail.com>
 * @version v1.0
 */
public class ArrayContainer implements Container<Character, Matcher> {

    private Matcher[] container = new Matcher[0];
    
    /* (non-Javadoc)
     * @see com.hyxq.pointcut.keywords.Container#put(java.lang.Object, java.lang.Object)
     */
    @Override
    public void put(Character key, Matcher value) {
        
        Matcher[] target = new Matcher[container.length + 1];
        System.arraycopy(container, 0, target, 0, container.length);
        target[target.length - 1] = value;
        container = target;
    }

    /* (non-Javadoc)
     * @see com.hyxq.pointcut.keywords.Container#get(java.lang.Object)
     */
    @Override
    public Matcher get(Character key) {
        
        for(int i = 0; i < container.length; i ++) {
            if(container[i].path == key) return container[i];
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

    public Matcher[] getContainer() {
        
        return this.container;
    }
}
