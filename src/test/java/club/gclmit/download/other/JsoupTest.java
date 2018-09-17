package club.gclmit.download.other;

import club.gclmit.download.api.BaiduWenkuApi;
import club.gclmit.download.util.HttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.download.other
 * @author: gclm
 * @date: 2018/9/12 下午9:19
 * @description:  jsoup 测试
 */
public class JsoupTest {

    public static void main(String[] args) throws IOException {

        String s = new HttpClient().sendGet("https://wenku.baidu.com/view/123d438fb84ae45c3a358c0e.html?from=search", "UTF-8", new BaiduWenkuApi().getHeaders());


        System.out.println(s);

        Document doc = Jsoup.parse(s);
        Element htmlUrls = doc.getElementById("htmlUrls");


//        //解析网页，得到文档对象
//        Document doc = Jsoup.parse(toString);
//        //获取tag 是title的所有dom元素
//        Elements elements = doc.getElementsByTag("title");
//        //获取第一个元素
//        Element element = elements.get(0);
//        //返回元素的文本
//        String text = element.text();
//        System.out.println("网页标题是:"+elements);
//
//        Element element2=doc.getElementById("site_nav_top"); // 获取id=site_nav_top的DOM元素
//        String navTop=element2.text(); // 返回元素的文本
//        System.out.println("口号："+navTop);
//
//        Document document = new
    }
}
