package com.yuesheng.partnermatch;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

@SpringBootTest
class PartnerMatchApplicationTests {

    @Test
    void testDigest(){
        String newPassword = DigestUtils.md5DigestAsHex(("abcd" + "mypassword").getBytes());
        System.out.println(newPassword);
    }
    @Test
    void contextLoads() {
    }

}
