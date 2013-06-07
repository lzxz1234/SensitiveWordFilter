package com.ssf.core;

import java.util.Arrays;

import com.ssf.utils.StringUtils;

/**
 * @class Matcher
 * @author lzxz1234<lzxz1234@gmail.com>
 * @version v1.0
 */
public class Matcher {

    private static final Matcher root = new Matcher();
    
    public char[] ends = new char[0];
    public Container<Character, Matcher> nexts = new BlanceContainer();
    
    /** 
     * 加载敏感词，初始化时使用
     */
    public static void addKeyWord(String keyword) {
        
        if(StringUtils.isEmpty(keyword)) return;
        root.parse(keyword.toCharArray(), 0);
    }
    
    /** 
     * 判断是否非法，返回 True 时非法， False 合法
     */
    public static boolean isIllegal(String content) {
        
        if(StringUtils.isEmpty(content)) return false;
        char[] chars = content.toCharArray();
        for(int i = 0; i < chars.length; i ++) {
            if(root.matches(chars, i)) return true;
        }
        return false;
    }
        
    /** 
     * 构建敏感词树
     */
    private void parse(char[] keywords, int start) {
        if(start < 0 || start > keywords.length - 1) {
            return;
        }
        if(start < keywords.length -  1) {
            this.createNextMatcher(keywords[start]).parse(keywords, start + 1);
            return;
        }
        this.addEnds(keywords[start]);
    }
    /** 
     * 遍历敏感词树进行匹配测试
     */
    private boolean matches(char[] target, int start) {
        if(contains(ends, target[start])) {
            return true;
        }
        Matcher matcher = this.getNextMatcher(target[start]);
        return (matcher == null || start + 1 == target.length) ? false : matcher.matches(target, start + 1);
    }
    /** 
     * 扩展 Ends 数组
     */
    private synchronized void addEnds(char end) {
        if(contains(ends, end)) return;
        char[] newEnds = new char[ends.length + 1];
        System.arraycopy(ends, 0, newEnds, 0, ends.length);
        newEnds[newEnds.length - 1] = end;
        Arrays.sort(newEnds);
        ends = newEnds;
    }
    /** 
     * create 为 True 时构建并返回下级结点， False 时返回不构建下级结点
     */
    private synchronized Matcher createNextMatcher(char c) {
        Matcher matcher = nexts.get(c);
        if(matcher != null) return matcher;
        matcher = new Matcher();
        nexts.put(c, matcher);
        return matcher;
    }
    private synchronized Matcher getNextMatcher(char c) {
        return nexts.get(c);
    }
    private boolean contains(char[] sortArrays, char target) {
        return Arrays.binarySearch(sortArrays, target) >= 0;
    }
    
}
