package com.kxg.baseopen.provider.web.controller;

import com.kxg.baseopen.provider.config.SzpJsonResult;
import com.kxg.baseopen.provider.service.CreateSmallApplicationService;
import com.kxg.baseopen.provider.web.request.FastRegisterRequest;
import com.kxg.baseopen.provider.web.request.FastRegisterSearchRequest;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.bean.result.WxOpenResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 要写注释呀
 */
@Slf4j
@RestController
@RequestMapping("create/sa")
public class CreateSAController {
    @Autowired
    private CreateSmallApplicationService createSmallApplicationService;
    /**
     * https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=21538208049W8uwq&token=&lang=zh_CN
     * 第三方平台快速创建小程序.
     * 注意：创建任务逻辑串行，单次任务结束后才可以使用相同信息下发第二次任务，请注意规避任务阻塞
     *
     * @ name               企业名（需与工商部门登记信息一致）
     * @ code               企业代码
     * @ codeType           企业代码类型 1：统一社会信用代码（18位） 2：组织机构代码（9位xxxxxxxx-x） 3：营业执照注册号(15位)
     * @ legalPersonaWechat 法人微信号
     * @ legalPersonaName   法人姓名（绑定银行卡）
     * @ componentPhone     第三方联系电话（方便法人与第三方联系）
     * @return .
     * @throws WxErrorException .
     */
    @PostMapping("fast/register")
    public SzpJsonResult<WxOpenResult> fastRegisterWeapp(@RequestBody FastRegisterRequest registerRequest){
        return SzpJsonResult.ok(createSmallApplicationService.fastRegisterWeapp(registerRequest));
    }

    /**
     * https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=21538208049W8uwq&token=&lang=zh_CN
     * 查询第三方平台快速创建小程序的任务状态
     * 注意：该接口只提供当下任务结果查询，不建议过分依赖该接口查询所创建小程序。
     * 小程序的成功状态可在第三方服务器中自行对账、查询。
     * 不要频繁调用search接口，消息接收需通过服务器查看。调用search接口会消耗接口整体调用quato
     *
     * @ name               企业名（需与工商部门登记信息一致）
     * @ legalPersonaWechat 法人微信号
     * @ legalPersonaName   法人姓名（绑定银行卡）
     * @throws WxErrorException .
     */
    @PostMapping("fast/search")
    public SzpJsonResult<WxOpenResult> fastRegisterWeappSearch(@RequestBody FastRegisterSearchRequest registerSearchRequest) {
        return SzpJsonResult.ok(createSmallApplicationService.fastRegisterWeappSearch(registerSearchRequest));

    }

    /**
     * 获取预售权码
     * @return
     */
    @PostMapping("pre/code")
    public SzpJsonResult<String> getPreCode(){
        return SzpJsonResult.ok(createSmallApplicationService.getPreAuthCode());
    }

}
