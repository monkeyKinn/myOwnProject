package club.shengcong.sendemail;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableApolloConfig
public class BatchSendEmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchSendEmailApplication.class, args);
    }

}
