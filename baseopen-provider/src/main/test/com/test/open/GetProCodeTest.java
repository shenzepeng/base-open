package com.test.open;

import com.alibaba.nacos.api.config.annotation.NacosValue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 要写注释呀
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.kxg.baseopen.provider.DubboProviderBootstrap.class)
public class GetProCodeTest {
//    @Autowired
//    private CreateSmallApplicationService createSmallApplicationService;
//    @NacosValue(value = "${WX_CALL_BACK_URL}")
//    private String WX_CALL_BACK_URL;
    @Test
    public void getPreCode(){
        StringBuilder sb=new StringBuilder();
        sb.append("<html style='height:100%;width:100%;padding:0;margin:0;'>");
        sb.append("<body style='height:100%;width:100%;padding:0;margin:0;'>");
        sb.append("<a style='width:320px;height:70px;display:flex;justify-content:center;align-items: center;border:1px solid #3688FF;border-radius:10px;position:absolute;top:50%;left:50%;transform: translate(-50%,-50%); font-size:30px;font-weight:900;text-decoration: none; color: #3688FF;' href=\""+123+" \">");
        sb.append("点我进行授权登录");
        sb.append("</a >");
        sb.append("</body>");
        sb.append("</html>");

        System.out.println(sb.toString());
    }
}
