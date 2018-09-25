package club.gclmit.download.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.download.util
 * @author: gclm
 * @date: 2018/9/12 上午10:18
 * @description: 文件输出测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileOutputTest {

    @Test
    public void imgToPdfTest(){

        List<String> list = new ArrayList<>();

        list.add("https://wkretype.bdimg.com/retype/zoom/24b84244804d2b160b4ec0a9?pn=1&o=jpg_6&md5sum=d8a53520a3894717412042059122e8cc&sign=5d61f204f0&png=0-59494&jpg=0-62871");
        list.add("https://wkretype.bdimg.com/retype/zoom/24b84244804d2b160b4ec0a9?pn=2&o=jpg_6&md5sum=d8a53520a3894717412042059122e8cc&sign=5d61f204f0&png=59495-88543&jpg=62872-221176");
        list.add("https://wkretype.bdimg.com/retype/zoom/24b84244804d2b160b4ec0a9?pn=3&o=jpg_6&md5sum=d8a53520a3894717412042059122e8cc&sign=5d61f204f0&png=88544-129160&jpg=221177-393701");

        new FileOutput().imgToPdf("./tes.pdf",list);
    }

    @Test
    public void downloadWordTest(){
        ResultMsg resultMsg = new FileOutput().downloadWord("./ASD.docx", "http://47.95.226.123/getDoc.do?docUrl=https://wenku.baidu.com/view/6cc5512808a1284ac9504304.html?from=search");

        System.out.println(resultMsg);
    }

}
