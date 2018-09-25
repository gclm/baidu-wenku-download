package club.gclmit.download.other;

import java.util.regex.Pattern;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.download.other
 * @author: gclm
 * @date: 2018/9/17 下午7:51
 * @description: 正则表达式测试类
 */
public class PattenTest {

    public static void main(String[] args) {

        String content = "https://wenku.baidu.com/view/642142fced630b1c58eeb599.html?from=search";

        String pattern = "(?i)\\b((?:[a-z][\\w-]+:(?:/{1,3}|[a-z0-9%])|www\\d{0,3}[.]|[a-z0-9.\\-]+[.][a-z]{2,4}/)(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]{};:\\'\".,<>?«»“”‘’]))";

        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println("判断网址是否是百度网盘的网址? " + isMatch);


        int i = content.indexOf("wenku.baidu.com/view/");

        System.out.println(i);

    }

}
