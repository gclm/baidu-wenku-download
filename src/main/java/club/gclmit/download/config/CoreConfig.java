package club.gclmit.download.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.download.config
 * @author: gclm
 * @date: 2018/9/9 上午10:16
 * @description: springboot 自定义 核心配置文件
 */
@Configuration
public class CoreConfig {

    // 加载YML格式自定义配置文件
//    @Bean
////    public static PropertySourcesPlaceholderConfigurer properties() {
////        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
////        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
////        yaml.setResources(new FileSystemResource("resources/api/download.yml"));//File引入
//////		yaml.setResources(new ClassPathResource("youryml.yml"));//class引入
////        configurer.setProperties(yaml.getObject());
////        return configurer;
////    }
}
