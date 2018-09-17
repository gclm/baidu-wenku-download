package club.gclmit.download.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: top.gclmit.sso.config.sso-user
 * @author: gclm
 * @date: 2018/8/1 下午7:42
 * @description: 配置Druid  数据源
 */
@Configuration
public class DruidConfig {

    /**
     * 初始化 Druid  数据源
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource Druid(){
        return new DruidDataSource();
    }


    /**
     *  注册一个 StatViewServlet
     * @return
     */
    @Bean
    public ServletRegistrationBean  druidStatViewServelet(){

        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");

        Map<String,String> initParams=new HashMap<>();
//      设置白名单
        initParams.put("allow","");
//      设置黑名单
        initParams.put("deny","192.168.1.73");

//      设置账户密码
        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","123456");
//      是否能够重置数据
        initParams.put("resetEnable","false");


        servletRegistrationBean.setInitParameters(initParams);

        return  servletRegistrationBean;
    }

    /**
     *  注册一个 filterRegistrationBean
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
//      添加过滤 规则
        filterRegistrationBean.addUrlPatterns("/*");

//       添加不需要忽略的信息
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.css,*.jpg,*.png,*.gif,*.ico,/druid/*");

        return filterRegistrationBean;

    }

}
