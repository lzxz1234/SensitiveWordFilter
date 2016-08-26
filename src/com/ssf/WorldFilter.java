package com.ssf;

import java.util.concurrent.atomic.AtomicInteger;

import com.ssf.core.BranchNode;
import com.ssf.core.LeafNode;
import com.ssf.core.Matcher;
import com.ssf.core.Node;
import com.ssf.core.Result;
import com.ssf.utils.StringUtils;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * @class Matcher
 * @author lzxz1234<lzxz1234@gmail.com>
 * @version v1.0
 */
public class WorldFilter implements Matcher{

    private static AtomicInteger seq = new AtomicInteger(0);
    private Node root = new BranchNode(null, "");
    
    /** 
     * 加载敏感词，初始化时使用
     */
    public void addKeyWord(String keyword) {
        
        if(StringUtils.isEmpty(keyword)) return;
        root.parse(keyword.toCharArray(), 0);
    }
    
    public void clear() {
        
        root = new BranchNode(null, "");
    }
    
    /** 
     * 判断是否非法，返回 True 时非法， False 合法
     */
    public boolean isIllegal(String content) {
        
        return matches(content).isHit();
    }
    
    public Result matches(String content) {
    	
    	if(StringUtils.isEmpty(content)) return Result.ZERO_HIT;
        char[] chars = content.toCharArray();
        Result result;
        for(int i = 0; i < chars.length; i ++) {
            if((result = root.matches(chars, i)).isHit()) return result;
        }
        return Result.ZERO_HIT;
    }
    
    public Matcher optimize() {
        
        try {
            ClassPool pool = ClassPool.getDefault();
            pool.importPackage("com.ssf.core.Matcher");
            pool.importPackage("com.ssf.core.Result");
            CtClass cc = pool.makeClass("A" + seq.incrementAndGet());
            cc.addInterface(pool.getCtClass("com.ssf.core.Matcher"));
            
            cc.addMethod(CtMethod.make("public void addKeyWord(String keyword) { throw new UnsupportedOperationException(\"不可更改\"); }", cc));
            cc.addMethod(CtMethod.make("public void clear(String keyword) { throw new UnsupportedOperationException(\"不可更改\"); }", cc));
            cc.addMethod(CtMethod.make("public Result matches(String content) { throw new UnsupportedOperationException(\"不可更改\"); }", cc));
            cc.addMethod(CtMethod.make("public boolean m(String content, int index, char expect){return content.charAt(index) == expect; }", cc));
            cc.addMethod(CtMethod.make(getMethod(), cc));
            
            return (Matcher) cc.toClass().newInstance();
        } catch (Exception e) {
            System.err.println("尝试优化失败，代码长度：" + getMethod().getBytes().length);
            e.printStackTrace();
            return this;
        }
    }
    
    private String getMethod() {
        
        StringBuilder sb = new StringBuilder();
        sb.append("public boolean isIllegal(String c) {\r\n");
        sb.append("for(int i = 0; i < c.length(); i ++) {\r\n");
        loopAppend(sb, 0, root);
        sb.append("}");
        sb.append("return false;");
        sb.append("}");
        return sb.toString();
    }

    private void loopAppend(StringBuilder code, int index, Node map) {
        
        StringBuilder sb = new StringBuilder();
        for(Node each : map.children()) {
            char label = each.path().charAt(each.path().length() - 1);
            if(label == '\'') continue;
            if(each instanceof LeafNode)
                sb.append("else if(m(c, " + index + " + i, '" + label + "')) return true;");
            else {
                sb.append("else if(m(c, " + index + " + i, '" + label + "')) {\r\n");
                this.loopAppend(sb, index + 1, each);
                sb.append("}");
            }
        }
        if(sb.length() > 0) sb.delete(0, 4);
        code.append(sb);
    }
    
}
