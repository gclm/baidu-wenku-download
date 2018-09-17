package club.gclmit.download.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.download.entity
 * @author: gclm
 * @date: 2018/9/12 上午11:12
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BaiduWenkuTest {

    @Autowired
    private BaiduWenku baiduWenku;

    @Test
    public void testDisplayConfig() {
        System.out.println("ppt:" + baiduWenku.getPpt());
        System.out.println("pdf:" + baiduWenku.getPdf());
        System.out.println("word:" + baiduWenku.getWord());
        System.out.println("text:" + baiduWenku.getTxt());
        System.out.println("doc:" + baiduWenku.getDoc());
        System.out.println("docPath:" + baiduWenku.getDocPath());
    }

}
