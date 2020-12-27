package com.kxg.baseopen.provider.web.controller.openwx;

import com.kxg.baseopen.provider.config.SzpJsonResult;
import com.kxg.baseopen.provider.dto.CheckWxNameDto;
import com.kxg.baseopen.provider.dto.request.*;
import com.kxg.baseopen.provider.dto.response.*;
import com.kxg.baseopen.provider.openwx.BaseInfoSettingService;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 要写注释呀
 */
@RequestMapping("base/info/setting")
@RestController
public class BaseInfoSettingController {
    @Autowired
    private BaseInfoSettingService baseInfoSettingService;
    /**
     * 设置小程序的名称
     * @param request
     * @return
     */
    @ApiOperation("设置小程序的名称")
    @PostMapping("set/app/name")
    public SzpJsonResult<SettingWxNameResponse> setAppName(@RequestBody SettingWxNameRequest request){
        return SzpJsonResult.ok(baseInfoSettingService.setAppName(request));
    }

    /**
     * 检查小程序的名称
     * @param appId
     * @param name
     * @return
     */
    @ApiOperation("检查小程序的名称")
    @PostMapping("check/app/name")
    public SzpJsonResult<CheckWxNameDto> checkAppName(@RequestParam String appId, @RequestParam String name){
        return SzpJsonResult.ok(baseInfoSettingService.checkAppName(appId, name));
    }


    /**
     * 操作服务器域名
     *
     *   域名操作参数
     *                     除了 webViewDomain，都是有效的
     * @return 以下字段仅在 get 时返回完整字段
     * @throws WxErrorException 操作失败时抛出，具体错误码请看文档
     */
    @ApiOperation("设置服务器域名")
    @PostMapping("update/wx/address")
    public SzpJsonResult<UpdateWxServerAddressResponse> updateWxServiceAddress(@RequestBody UpdateWxServerAddressRequest updateWxServerAddressRequest){
        return SzpJsonResult.ok(baseInfoSettingService.updateWxServiceAddress(updateWxServerAddressRequest));
    }

    /**
     * 设置小程序业务域名（仅供第三方代小程序调用）
     * 授权给第三方的小程序，其业务域名只可以为第三方的服务器，
     * 当小程序通过第三方发布代码上线后，小程序原先自己配置的业务域名将被删除，
     * 只保留第三方平台的域名，所以第三方平台在代替小程序发布代码之前，需要调用接口为小程序添加业务域名。
     * 提示：需要先将域名登记到第三方平台的小程序业务域名中，才可以调用接口进行配置。
     *
     *  domainAction  域名操作参数
     *                     只有 action 和 webViewDomain 是有效的
     * @return 以下字段仅在 get 时返回完整字段
     * @throws WxErrorException 操作失败时抛出，具体错误码请看文档
     */
    @ApiOperation("设置小程序业务域名")
    @PostMapping("set/web/view/domain")
    public SzpJsonResult<SetWxUrlResponse> setWebViewDomain(@RequestBody SetWxUrlRequest request){
        return SzpJsonResult.ok(baseInfoSettingService.setWebViewDomain(request));
    }


    /**
     * 绑定小程序体验者
     *
     * @ wechatId 体验者微信号（不是openid）
     * @return wx open ma bind tester result
     * @throws WxErrorException the wx error exception
     */
    @ApiOperation("绑定小程序体验者")
    @PostMapping("bind/tester")
    public SzpJsonResult<BindTesterResponse> bindTester(@RequestBody BindTesterRequest request){
        return SzpJsonResult.ok(baseInfoSettingService.bindTester(request));
    }

    /**
     * 解除绑定小程序体验者
     *
     *  wechatId 体验者微信号（不是openid）
     * @return the wx open result
     * @throws WxErrorException the wx error exception
     */
    @ApiOperation("解除绑定小程序体验者")
    @PostMapping("unbind/tester")
    public SzpJsonResult<UnBindTesterResponse> unbindTester(@RequestBody UnBindTesterRequest request){
        return SzpJsonResult.ok(baseInfoSettingService.unbindTester(request));
    }


    /**
     * 设置小程序隐私设置（是否可被搜索）
     *
     * @ status 1表示不可搜索，0表示可搜索
     * @return the wx open result
     * @throws WxErrorException the wx error exception
     */
    @ApiOperation("设置小程序隐私设置")
    @PostMapping("change/wx/search/status")
    public SzpJsonResult<ChangeWxaSearchStatusResponse> changeWxaSearchStatus(@RequestBody ChangeWxaSearchStatusRequest request) {
        return SzpJsonResult.ok(baseInfoSettingService.changeWxaSearchStatus(request));
    }

    /**
     * 2. 查询小程序当前隐私设置（是否可被搜索）
     *
     * @return the wxa search status
     * @throws WxErrorException the wx error exception
     */
    @ApiOperation("解除绑定小程序体验者")
    @PostMapping("wx/search/status")
    public SzpJsonResult<WxOpenMaSearchStatusResponse> getWxaSearchStatus(@RequestBody WxOpenMaSearchStatusRequest request){
        return SzpJsonResult.ok(baseInfoSettingService.getWxaSearchStatus(request));
    }


    @ApiOperation("修改头像")
    @PostMapping("wx/fix/head")
    public SzpJsonResult<FixHeaderImgResponse> fixImgHeader(@RequestBody FixHeaderImgRequest fixHeaderImgRequest){
        return SzpJsonResult.ok(baseInfoSettingService.fixHeader(fixHeaderImgRequest));
    }

}
