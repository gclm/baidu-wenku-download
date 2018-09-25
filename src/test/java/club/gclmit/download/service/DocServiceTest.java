package club.gclmit.download.service;

import club.gclmit.download.config.BaiduWenkuApiConfigTest;
import club.gclmit.download.entity.Doc;
import club.gclmit.download.mapper.DocMapper;
import club.gclmit.download.util.ResultMsg;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.download.service
 * @author: gclm
 * @date: 2018/9/15 下午9:30
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DocServiceTest {

   @Autowired
   private DocService docService;

    private final  String WordUrl="https://wenku.baidu.com/view/123d438fb84ae45c3a358c0e.html?from=search";
    private final  String PDFUrl="https://wenku.baidu.com/view/3f4aa1cebdeb19e8b8f67c1cfad6195f312be8b4.html?from=search";
    private final  String PPTUrl="https://wenku.baidu.com/view/b76021c8b1717fd5360cba1aa8114431b80d8e59.html";
    private final  String TextUrl="https://wenku.baidu.com/view/642142fced630b1c58eeb599.html?from=search";


    @Test
   public void findDocTest(){

        ResultMsg docUrl = docService.findDoc("https://wenku.baidu.com/view/23870a4e2e3f5727a5e962b6.html?from=search");

        System.out.println("文件链接："+docUrl);
    }

}
