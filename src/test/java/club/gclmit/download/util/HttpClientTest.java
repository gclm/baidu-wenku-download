package club.gclmit.download.util;

import club.gclmit.download.api.BaiduWenkuApi;
import club.gclmit.download.config.BaiduWenkuApiConfigTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.download.util
 * @author: gclm
 * @date: 2018/9/12 下午8:50
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpClientTest {


    private HttpClient httpClient = new HttpClient();


    @Test
    public void getViewSourceTest() throws Exception {
        httpClient.getViewSource("https://wenku.baidu.com/view/b305abea5ebfc77da26925c52cc58bd630869348.html?from=search","UTF-8",new BaiduWenkuApi().getHeaders());
    }


    @Test
    public void sendGetTest() throws IOException {

    }

}
