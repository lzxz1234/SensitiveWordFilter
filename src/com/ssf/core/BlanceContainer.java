package com.ssf.core;

/**
 * @class BlanceContainer
 * @author lzxz1234<lzxz1234@gmail.com>
 * @version v1.0
 */
public class BlanceContainer implements Container<Character, Matcher> {

    private Container<Character, Matcher> container = new ArrayContainer();
    
    /* (non-Javadoc)
     * @see com.hyxq.pointcut.keywords.Container#put(java.lang.Object, java.lang.Object)
     */
    @Override
    public void put(Character key, Matcher value) {
        
        if(container.size() == 11) {
            container = new MapContainer((ArrayContainer)container);
        }
        container.put(key, value);
    }

    /* (non-Javadoc)
     * @see com.hyxq.pointcut.keywords.Container#get(java.lang.Object)
     */
    @Override
    public Matcher get(Character key) {

        return container.get(key);
    }

    /* (non-Javadoc)
     * @see com.hyxq.pointcut.keywords.Container#size()
     */
    @Override
    public int size() {

        return container.size();
    }

}
