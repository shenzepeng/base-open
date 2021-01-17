package com.test.open;

import com.alibaba.nacos.api.config.annotation.NacosValue;

import com.kxg.baseopen.provider.pay.WxPayService;
import com.kxg.baseopen.provider.utils.DownLoadFileUtils;
import lombok.SneakyThrows;
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

    @Autowired
    private WxPayService wxPayService;
    @Test
    public void test1(){
        String orderId = wxPayService.getOrderId();
        System.out.println(orderId);
    }
    private static final String P12_URL="https://kxg-neituibao-jianli.oss-cn-beijing.aliyuncs.com/sss/1609336977085.p12";
    private static final String P12_PATH="/p12";
    private static final String P12_FILE_PATH="A_B_C_D_E.p12";
//    @SneakyThrows
//    @Test
//    public void test2(){
//        DownLoadFileUtils.downLoadFromUrl(P12_URL,P12_PATH,P12_FILE_PATH);
//        System.out.println(P12_PATH+"/"+P12_FILE_PATH);
//    }
   @NacosValue(value = "${WX_CALL_BACK_URL}",autoRefreshed = true)
    private String WX_CALL_BACK_URL;

    @Test
    public  void testNacos(){
        System.out.println(WX_CALL_BACK_URL);
    }
}
