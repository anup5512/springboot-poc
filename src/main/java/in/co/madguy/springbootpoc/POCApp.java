package in.co.madguy.springbootpoc;

import in.co.madguy.springbootpoc.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.TimeZone;

@Slf4j
@SpringBootApplication
public class POCApp {
    public static void main(String[] args) {
        SpringApplication.run(POCApp.class, args);
    }

    @PostConstruct
    public void init() {
        log.info("Before setting timezone:: " + LocalDateTime.now().toString());
        TimeZone.setDefault(TimeZone.getTimeZone(Constants.USER_TZ));
        log.info("After setting timezone:: " + LocalDateTime.now().toString());
    }
}
