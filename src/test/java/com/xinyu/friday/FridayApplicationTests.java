package com.xinyu.friday;

import com.xinyu.friday.util.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FridayApplicationTests {
    @Autowired
    private MailClient mailClient;

    @Test
    void contextLoads() {
        mailClient.sendMail("853940981@qq.com","Test"," Welcome to my channel!");
    }

}
