/*
 * @(#)KeywordChecker.java
 * 
 * Create Version: 1.0.0
 * Author: James Liu
 * Create Date: 2014-01-10
 * 
 * Copyright (c) 2014 Aspire Information Technologies Ltd. All Right Reserved.
 */
package liushan.keyword;


import java.util.HashMap;
import java.util.Map;


/**
 * 关键字检查器。
 * 将所有关键字单词组装成单字树，遍历待查字符串的每个字，发现关键字树根有这个字开始检查。
 * 
 * @version 1.0.0   2014-01-10
 * @author  James Liu
 */
public class KeywordChecker {
    
    private Map<Integer, Object> root = new HashMap<Integer, Object>();
    
    
    /**
     * 构造方法。
     * 
     * @param   keywords        关键字数组
     */
    public KeywordChecker(String[] keywords) {
        
        if ((keywords == null) || (keywords.length == 0)) return;
        
        for (String keyword : keywords) this.parseWord(this.getUnicodeCharArray(keyword));
    }
    
    /*
     * 解析每个单词。
     * 
     * @param   word            单词的代码点数组
     */
    private void parseWord(Integer[] word) {
        
        if ((word == null) || (word.length == 0)) return;
        
        Integer parentChar = null;
        Map<Integer, Object> parentNode = null;
        for (int i = 0; i < word.length; i++) {
            parentNode = this.parseChar(parentChar, parentNode, word[i], (i == (word.length - 1)) ? true : false);
            parentChar = word[i];
            if (parentNode == null) break;
        }
    }
    
    /*
     * 解析每个字符.
     * 当碰到字符树的分支结束符时，提前结束单词解析。分支结束符的节点是Object，不是Map。
     * 比如：“ab”和“abcd”，当解析“abcd”的“c”时，发现已经有“ab”了，则“abcd”将剔除，不作为关键字。
     * 同样：“abcd”和“ab”，当解析“ab”完成时，会清除掉所有“ab”后面的分支，则“abcd”将剔除，不作为关键字。
     * 
     * @param   prevChar        前一个字符
     * @param   prevNode        前一个节点
     * @param   curChar         当前解析的字符
     * @param   isEndChar       当前解析字符是否是单词最后一个字符
     * 
     * @return  当前字符节点，null为结束单词解析。
     */
    @SuppressWarnings("unchecked")
    private Map<Integer, Object> parseChar(Integer prevChar, Map<Integer, Object> prevNode, 
                                           Integer curChar, boolean isEndChar) {
        
        Map<Integer, Object> curNode = this.root;
        if (prevChar != null) {
            Object current = prevNode.get(prevChar);
            if (current == null) {
                curNode = null;
            } else if (current instanceof Map) {
                curNode = (Map<Integer, Object>) current;
            } else {
                return null;
            }
        }
        
        if (curNode == null) {
            curNode = new HashMap<Integer, Object>();
            if (isEndChar) {
                curNode.put(curChar, new Object());
            } else {
                curNode.put(curChar, null);
            }
            prevNode.put(prevChar, curNode);
        } else {
            Object nextNode = curNode.get(curChar);
            if ((nextNode != null) && !(nextNode instanceof Map)) return null;
            
            if (nextNode == null) {
                if (isEndChar) {
                    curNode.put(curChar, new Object());
                } else {
                    curNode.put(curChar, null);
                }
            } else if (isEndChar) {
                curNode.put(curChar, new Object());
            }
        }
        
        return (isEndChar) ? null : curNode;
    }
    
    /*
     * 获得字符串代码点数组。
     * 
     * @param   s               要转换的字符串
     * 
     * @return  字符串代码点数组。如果s是null，则返回null；如果s是空字符串，则返回长度为零的数组。
     */
    private Integer[] getUnicodeCharArray(String s) {
        
        if (s == null) return null;
        
        if (s.length() == 0) return new Integer[0];
        
        int codePointCount = s.codePointCount(0, s.length());
        Integer[] result = new Integer[codePointCount];
        for (int i = 0; i < codePointCount; i++) result[i] = s.codePointAt(i);
        
        return result;
    }
    
    /**
     * 检查字符串是否包含关键字。
     * 
     * @param   s               要检查的字符串
     * 
     * @return  第一个找到的关键字的代码点位置，-1为没有找到。
     */
    public int check(String s) {
        
        if ((s == null) || (s.length() == 0)) return -1;
        
        int result = -1;
        
        Integer[] chars = this.getUnicodeCharArray(s);
        for (int i = 0; i < chars.length; i++) {
            if (this.match(this.root, chars, i)) {
                result = i;
                break;
            }
        }
        
        return result;
    }
    
    /*
     * 匹配关键字。
     * 
     * @param   node            要匹配查找的节点
     * @param   chars           字符串代码点数组
     * @param   pos             当前要匹配的字符
     * 
     * @return  匹配成功与否
     */
    @SuppressWarnings("unchecked")
    private boolean match(Map<Integer, Object> node, Integer[] chars, int pos) {
        
        if (pos == chars.length) return false;
        
        Object findResult = node.get(chars[pos]);
        if (findResult == null) {
            return false;
        } else if (findResult instanceof Map) {
            return this.match((Map<Integer, Object>) findResult, chars, pos + 1);
        } else {
            return true;
        }
    }
    
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("KeywordChecker [root=");
        builder.append(this.root);
        builder.append("]");
        
        return builder.toString();
    }
}