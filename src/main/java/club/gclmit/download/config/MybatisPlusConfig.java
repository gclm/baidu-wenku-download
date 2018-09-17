package club.gclmit.download.config;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: top.gclmit.mp.config
 * @author: gclm
 * @date: 2018/8/8 4:41 PM
 * @description: mybatis-plus 配置类
 */

@EnableTransactionManagement
@Configuration
@MapperScan("club.gclmit.download.mapper.*.mapper*")
public class MybatisPlusConfig {

    /**
     * 性能分析拦截器，不建议生产使用
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
//      SQL 语句格式
        performanceInterceptor.setFormat(true);
//      设置最大执行时间
//      performanceInterceptor.setMaxTime(5);
        return performanceInterceptor;
    }

    /**
     * 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


}
