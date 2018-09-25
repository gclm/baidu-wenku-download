package club.gclmit.download.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.download.controller
 * @author: gclm
 * @date: 2018/9/17 下午5:09
 * @description: docController测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DocControllerTest {


    @Autowired
    private WebApplicationContext wac;


    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void getAnalysisUrlTest() throws Exception {
//        String content= "{\"url\":\"https://wenku.baidu.com/view/6cc5512808a1284ac9504304.html?from=search\"}";
        String content= "https://wenku.baidu.com/view/820a902433d4b14e8424687d.html?from=search";
        String result = mockMvc.perform(post("/doc/search").contentType(MediaType.APPLICATION_JSON_UTF8)
                 .content(content))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println("result:" + result);
    }
}
