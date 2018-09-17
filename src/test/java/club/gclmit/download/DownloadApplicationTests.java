package club.gclmit.download;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DownloadApplicationTests {


    @Autowired
    private DataSource dataSource;

    /**
     * 查看数据源情况
     * @throws SQLException
     */
    @Test
    public void getDataSource() throws SQLException {
        System.out.println("数据源类型："+dataSource.getClass()+"\n数据源链接状态：" + dataSource.getConnection());
    }

    /**
     * mybatis-plus 代码生成配置
     *
     *  配置详情：http://mp.baomidou.com/#/generate-code
     */
    @Test
    public void autoGenerator(){
//      1. 全局配置
        GlobalConfig config=new GlobalConfig();
//       是否开启AR 模式
        config.setActiveRecord(false)
//            生成路径  这里我采用的是绝对路径  其他人可以采用相对路径试试
                .setOutputDir("/Volumes/Workspace/codes/java/me/download/src/main/java")
//            文件覆盖
                .setFileOverride(true)
//            主键生成策略
                .setIdType(IdType.AUTO)
//            作者
                .setAuthor("孤城落寞")
//            XML  ResultMap 配置 这里因为打算采用mybatis 注解配置 所有不打算生成 xml 的所有配置
                .setBaseResultMap(false)
//             XML  ColumnList 配置 这里因为打算采用mybatis 注解配置 所有不打算生成 xml 的所有配置
                .setBaseColumnList(false)
//             XML  二级缓存 配置 这里因为打算采用mybatis 注解配置 所有不打算生成 xml 的所有配置
                .setEnableCache(false)
//             设置生成的接口的名字 首字母是否为I 一下类似
//                .setMapperName("%sMapper")
                .setServiceName("%sService");
//                .setServiceImplName("%sServiceImpl")
//                .setControllerName("%sController");

//      2. 数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
//      设置数据库类型
        dsConfig.setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.jdbc.Driver")
                .setUrl("jdbc:mysql:///download")
                .setUsername("root")
                .setPassword("gclmroot159");


//      3. 策略配置
        StrategyConfig stConfig = new StrategyConfig();
//       全局大写命名
        stConfig.setCapitalMode(true)
//               数据库表映射到实体的命名策略
                .setNaming(NamingStrategy.underline_to_camel)
//               表名前缀
                .setTablePrefix("tb_")
//               用于生成的表
                .setInclude("tb_doc");

//      4. 包名策略配置
        PackageConfig pkConfig = new PackageConfig();
//      设置全局包名
        pkConfig.setParent("club.gclmit.download")
                .setMapper("mapper")
                .setService("service")
//                .setServiceImpl("impl")
                .setController("controller")
                .setEntity("entity");

//      5. 整合配置
        AutoGenerator ag = new AutoGenerator();
        ag.setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setPackageInfo(pkConfig)
                .setStrategy(stConfig);
//      6. 执行
        ag.execute();

    }

}
