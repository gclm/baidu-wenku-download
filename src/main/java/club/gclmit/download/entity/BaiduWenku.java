package club.gclmit.download.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.download.entity
 * @author: gclm
 * @date: 2018/9/12 上午11:06
 * @description:
 */
@Component
@ConfigurationProperties(prefix = "gclm.download.baidu.wenku")
public class BaiduWenku {

    @Getter @Setter
    private String ppt;
    @Getter @Setter
    private String word;
    @Getter @Setter
    private String pdf;
    @Getter @Setter
    private String txt;
    @Getter @Setter
    private String doc;
    @Getter @Setter
    private String docPath;


}
