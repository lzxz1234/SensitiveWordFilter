/*
 * @title: MatcherTest2.java
 * @package com.ssf.core
 * @author lzxz1234<lzxz1234@gmail.com>
 * @date 2014-1-10 下午3:37:17
 * @version V1.0
 * Copyright (c) 2012 Aspirecn.com. All Right Reserved
 */
package com.ssf.core;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.ssf.utils.IOUtils;

/**
 * @class MatcherTest2
 * @author lzxz1234<lzxz1234@gmail.com>
 * @version v1.0
 */
public class MatcherTest2 {

    public static void main(String[] args) {
        
        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream("keywords2");
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
        
        String s = getTestString();
        long start = System.nanoTime();
        for(int i = 0; i < 100000; i ++) {
            Matcher.isIllegal(getTestString());
        }
        System.out.println((System.nanoTime() - start) / 1000000.0);
        System.out.println("测试文件长度：" + s.length());
        System.out.println("测试匹配结果：" + Matcher.isIllegal(s));
    }
    
    public static String getTestString() {
        
        return  "好吧，既然大家都一致鄙视百度，为谷歌欢喜鼓舞的叫好，我就来插一脚，为百度打抱不平一下吧。\r\n" + 
                "声明立场：我承认谷歌在很多方面很强，这里只是说一些百度也不错的地方。给大家提供一些新的思维，希望能够对大家有启发。\r\n" + 
                
                "1，在搜索江湖的早期历史中，李彦宏的技术创新领先于谷歌。\r\n" + 
                "1994年，infoseek公司成立，其后推出搜索引擎服务，很快成为了市场上最受欢迎的搜索技术提供商。作为技术的领先者，这家公司的产品被网景浏览器设置为默认的搜索引擎。要知道，在那个年代的美国市场上，网景浏览器占有了90%以上的市场份额。于是，在网景春风得意的岁月里，搜索引擎=infoseek。\r\n" + 
                "——infoseek的CTO是威廉张，曾经以“次线性文本匹配算法上的突破性成果获得了美国加州大学的计算机博士学位”，其后于2006年加入百度成为首席科学家。\r\n" + 
                "——infoseek的核心研发工程师是李彦宏，其首创的“超链分析”技术是现代搜索引擎的基础发明之一。这个技术率先解决了如何将基于网页质量的排序与基于相关性的排序完美结合的问题，并获得了美国专利。\r\n" + 
                "在搜索引擎的早期发展史中，李彦宏+威廉张，无疑是技术的领先者。在1998年的布里斯班世界互联网大会上，李彦宏是主讲台上的技术布道者，而谷歌的两位创始人仍是坐在台下聆听的学生而已。\r\n" + 
                "infoseek的衰落，不是因为技术，而是因为商业模式，他仅仅是隐藏在网景浏览器背后的技术提供者。随着网景在与微软IE浏览器的竞争中失利，infoseek无可避免的出现了大幅亏损，贱卖给迪士尼后不适应于传统企业的官僚管理作风，更加加速了自己的末日到来。\r\n" + 
                "同一年，谷歌推出自己的搜索引擎，以精准广告为商业模式，成功解决了持续成长的问题，在搜索江湖的较量中熬到了最后。谷歌今日引以为荣的网页评级机制pagerank技术，直到2001年才被授予美国专利，比李彦宏的1996年申请的超链分析专利技术晚了5年。\r\n" + 
                
                "2，百度从一开始就和谷歌走上了不同的发展方向。\r\n" + 
                "谷歌的网站首页一直以来都是以简洁著称，他的搜索能够带你去到互联网世界的任何一个幽暗偏僻的角落，找出网站中最不起眼的内页信息。\r\n" + 
                "百度如果要跟谷歌比拼搜索精度，那是飞蛾扑火，李彦宏当然不会犯下这种蠢事。从一开始，百度所选择的就是一条农村包围城市，侧翼攻击的路线。\r\n" + 
                "在早期的网页搜索上，百度的原则是能用就行。所以，你在百度上搜到的，几乎都是网站的首页，而谷歌的都是内页。\r\n" + 
                "百度的真正重点是，提供谷歌不能提供的服务。2002年，百度率先推出mp3音乐搜索，2003年推出图片，贴吧，新闻，搜索风云榜服务。正是这些多元化的垂直服务，让百度实现了在中国市场上的后来者居上。如果你了解百度的历史，你就知道，在早期的百度流量中，mp3盗版音乐的搜索和下载，一度贡献了40-50%的用户来源。贴吧同样获得巨大成功，在2004年的超级女声热潮中，大量的音乐粉丝涌入贴吧为他们的偶像加油，这个产品一度为百度带来了超过20%的流量。\r\n" + 
                "mp3，图片，贴吧，是百度早期最重要的三项服务，他们的流量贡献加起来甚至超过了网页搜索。\r\n" + 
                
                "3，2009年后，百度推出框计算，2013年推出轻应用，在一站式生活搜索方面，是超过了谷歌的。\r\n" + 
                "简而言之，\r\n" + 
                "谷歌追求的是精准，像德国车，百度追求的是人性化，像日本车。\r\n" + 
                "谷歌追求的是最快找到你需要的网页，然后离开；百度追求的是在百度上找到你所需要的所有东西，然后留下来。\r\n" + 
                "虽然玩技术的都喜欢谷歌，不过大多数中国小白用户其实还是更喜欢百度的。\r\n" + new Object();
    }
}
