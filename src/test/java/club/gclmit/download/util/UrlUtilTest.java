package club.gclmit.download.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URLDecoder;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.download.util
 * @author: gclm
 * @date: 2018/9/23 9:06 AM
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlUtilTest {

    @Test
    public void getURLDecoderStringTest(){
        System.out.println(new UrlUtil().getURLDecoderString("https%3A%2F%2Fwenku.baidu.com%2Fview%2F6cc5512808a1284ac9504304.html%3Ffrom%3Dsearch",UrlUtil.GBK));
    }

    @Test
    public void getURLEncoderStringTest(){
        System.out.println(new UrlUtil().getURLEncoderString("https://wenku.baidu.com/view/6cc5512808a1284ac9504304.html?from=search",UrlUtil.GBK));
    }
}
