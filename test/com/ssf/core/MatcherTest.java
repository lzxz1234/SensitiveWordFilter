package com.ssf.core;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.ssf.utils.IOUtils;

/**
 * @class MatcherTest
 * @author lzxz1234<lzxz1234@gmail.com>
 * @version v1.0
 */
public class MatcherTest {

    @Before
    public void before() {
        
        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream("keywords");
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
            String tmp = null;
            while((tmp = br.readLine()) != null) {
                Matcher.addKeyWord(tmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(is);
        }
    }
    @Test
    public void test() {
        Matcher.addKeyWord("大江");
        Matcher.addKeyWord("大学");
        Matcher.addKeyWord("成");
        
        Assert.assertFalse(Matcher.isIllegal("习近平走访哥国农户摘花给彭丽媛闻香"));
        Assert.assertTrue(Matcher.isIllegal("大江习近平走访哥国农户摘花给彭丽媛闻香"));
        Assert.assertTrue(Matcher.isIllegal("习近平走访哥国农户摘花给彭丽媛闻香大江"));
        Assert.assertTrue(Matcher.isIllegal("习近平走访哥国农户大江摘花给彭丽媛闻香"));
        Assert.assertTrue(Matcher.isIllegal("大学习近平走访哥国农户摘花给彭丽媛闻香"));
        Assert.assertTrue(Matcher.isIllegal("习近平走访哥国农户摘花给彭丽媛闻香大学"));
        Assert.assertTrue(Matcher.isIllegal("习近平走访哥国农户大学摘花给彭丽媛闻香"));
        Assert.assertTrue(Matcher.isIllegal("成习近平走访哥国农户摘花给彭丽媛闻香"));
        Assert.assertTrue(Matcher.isIllegal("习近平走访哥国农户摘花给彭丽媛闻香成"));
        Assert.assertTrue(Matcher.isIllegal("习近平走访哥国农户成摘花给彭丽媛闻香"));
        Assert.assertFalse(Matcher.isIllegal("大连习近平走访哥国农户摘花给彭丽媛闻香"));
        Assert.assertFalse(Matcher.isIllegal("习近平走访哥国农户摘花给彭丽媛闻香大连"));
        Assert.assertFalse(Matcher.isIllegal("习近平走访哥国农户大连摘花给彭丽媛闻香"));
    }
    
    @Test
    public void test2() {
        
        String s = "大江东去浪淘尽，千古风流人物,大江东去浪淘尽，千古风流人物,大江东去浪淘尽，" +
                "千古风流人物,大江东去浪淘尽，千古风流人物,大江东去浪淘尽，千古风流人物,大江东" +
                "去浪淘尽，千古风流人物,大江东去浪淘尽，千古风流人物,大江东去浪淘尽，千古风流" +
                "人物,大江东去浪淘尽，千古风流人物" + new Object().toString();
        long start = System.nanoTime();
        for(int i = 0; i < 100000; i ++) {
            Matcher.isIllegal(s);
        }
        System.out.println((System.nanoTime() - start) / 1000000.0);
        System.out.println(Matcher.isIllegal("大江东去浪淘尽，千古风流人物,大江东去浪淘尽，千古风流人物,大江东去浪淘尽"));
        System.out.println(Matcher.isIllegal("王涵"));
        System.out.println(Matcher.isIllegal("王涵大江东去浪淘尽，千古风流人物,大江东去浪淘尽，千古风流人物,大江东去浪淘尽"));
        System.out.println(Matcher.isIllegal("大江东去浪淘尽，千古风流人物,大江东去浪淘尽，千古风流人物,大江东去浪淘尽王涵"));
        System.out.println(Matcher.isIllegal("王涵大江东去浪淘尽，千古风流人物,大江东去浪淘尽，千古风流人物,大江东去浪淘尽王涵"));
        System.out.println(Matcher.isIllegal("'"));
        System.out.println(Matcher.isIllegal("大江东去浪淘尽，千古风'流人物,大江东去浪淘尽，千古风流人物,大江东去浪淘尽"));
        System.out.println(Matcher.isIllegal("'大江东去浪淘尽，千古风流人物,大江东去浪淘尽，千古风流人物,大江东去浪淘尽"));
        System.out.println(Matcher.isIllegal("大江东去浪淘尽，千古风流人物,大江东去浪淘尽，千古风流人物,大江东去浪淘尽'"));
        System.out.println(Matcher.isIllegal("'大江东去浪淘尽，千古风流人物,大江东去浪淘尽，千古风流人物,大江东去浪淘尽'"));
        System.out.println(Matcher.isIllegal("王涵万"));
        System.out.println(Matcher.isIllegal("王涵万大江东去浪淘尽，千古风流人物,大江东去浪淘尽，千古风流人物,大江东去浪淘尽"));
        System.out.println(Matcher.isIllegal("大江东去浪淘尽，千古风流人物,大江东去浪淘尽，千古风流人物,大江东去浪淘尽王涵万"));
        System.out.println(Matcher.isIllegal("王涵万大江东去浪淘尽，千古风流人物,大江东去浪淘尽，千古风流人物,大江东去浪淘尽王涵万"));
    }

}
