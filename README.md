#SensitiveWordFilter#

基于树形结构的敏感词过滤系统，效率可以接受

__注意__:可能有未知BUG

## 示例 ##

    //添加敏感词
    Matcher.addKeyWord(keyword); 
    //执行校验，返回True时非法
    Matcher.isIllegal("大江东去浪淘尽，千古风流人物");
