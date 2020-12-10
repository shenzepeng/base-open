package com.test.open;

import com.kxg.baseopen.provider.service.CreateSmallApplicationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 要写注释呀
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.kxg.baseopen.provider.DubboProviderBootstrap.class)
public class GetProCodeTest {
    @Autowired
    private CreateSmallApplicationService createSmallApplicationService;
    @Test
    public void getPreCode(){
        String preAuthCode = createSmallApplicationService.getPreAuthCode();
        System.out.println(preAuthCode);
    }
}
