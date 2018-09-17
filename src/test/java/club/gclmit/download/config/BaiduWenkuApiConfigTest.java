package club.gclmit.download.config;

import club.gclmit.download.DownloadApplication;
import club.gclmit.download.api.BaiduWenkuApi;
import club.gclmit.download.util.FileOutput;
import club.gclmit.download.util.HttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.*;
import java.util.List;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.download.config
 * @author: gclm
 * @date: 2018/9/9 上午10:01
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DownloadApplication.class)
public class BaiduWenkuApiConfigTest {

    @Autowired
    BaiduWenkuApi baiduWenkuApi;

    HttpClient httpClient = new HttpClient();

    private final  String WordUrl="https://wenku.baidu.com/view/123d438fb84ae45c3a358c0e.html?from=search";
    private final  String PDFUrl="https://wenku.baidu.com/view/3f4aa1cebdeb19e8b8f67c1cfad6195f312be8b4.html?from=search";
    private final  String PPTUrl="https://wenku.baidu.com/view/b76021c8b1717fd5360cba1aa8114431b80d8e59.html";
    private final  String TextUrl="https://wenku.baidu.com/view/642142fced630b1c58eeb599.html?from=search";

    @Test
    public void testDoc() throws Exception {

        String doc_id = baiduWenkuApi.getDoc_id(WordUrl);

        Map<String, String> docInfo = baiduWenkuApi.getDocInfo(doc_id);

        String docType = docInfo.get("docType");
        String docTitle = docInfo.get("docTitle");


        if ("4".equals(docType) || "1".equals(docType)) {
            System.out.println("下载文件格式为 word");
            String s = baiduWenkuApi.getWord(WordUrl);
            System.out.println(s);

        }

        if ("8".equals(docType)) {
            System.out.println("下载文件格式为 Txt");
            List<String> texts = baiduWenkuApi.getText(doc_id, docInfo.get("totalPageNum"), docInfo.get("md5sum"), docInfo.get("rsign"));
            new FileOutput().txtWriteFile(baiduWenkuApi.getDocPath(docTitle,"txt"),texts);
        }

        if ("7".equals(docType)) {
            System.out.println("下载文件格式为 pdf");

        }

        if ("3".equals(docType) ||"6".equals(docType) ) {
            System.out.println("下载文件格式为 ppt");
            List<String> imgs = baiduWenkuApi.getPpt(doc_id);
            new FileOutput().imgToPdf(baiduWenkuApi.getDocPath(docTitle,"pdf"),imgs);
        }
    }

}
