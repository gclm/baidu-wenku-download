package club.gclmit.download;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("club.gclmit.download.mapper")
public class DownloadApplication {

    public static void main(String[] args) {

        SpringApplication.run(DownloadApplication.class, args);
    }
}
