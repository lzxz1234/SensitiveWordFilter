package com.ssf.core;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @class MatcherTest
 * @author lzxz1234<lzxz1234@gmail.com>
 * @version v1.0
 */
public class MatcherTest {

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

}
