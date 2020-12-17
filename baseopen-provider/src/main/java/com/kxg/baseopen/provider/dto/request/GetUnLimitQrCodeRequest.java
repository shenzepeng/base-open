package com.kxg.baseopen.provider.dto.request;

import lombok.Data;

/**
 * 要写注释呀
 */
@Data
public class GetUnLimitQrCodeRequest {
    /**
     * 属性	类型	默认值	必填	说明
     * access_token	string		是	第三方平台接口调用令牌authorizer_access_token
     * scene	string		是	最大32个可见字符，只支持数字，大小写英文以及部分特殊字符：!#$&'()*+,/:;=?@-._~，其它字符请自行编码为合法字符（因不支持%，中文无法使用 urlencode 处理，请使用其他编码方式）
     * page	string	主页	否	必须是已经发布的小程序存在的页面（否则报错），例如 pages/index/index, 根路径前不要填加 /,不能携带参数（参数请放在scene字段里），如果不填写这个字段，默认跳主页面
     * width	number	430	否	二维码的宽度，单位 px，最小 280px，最大 1280px
     * auto_color	boolean	false	否	自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调，默认 false
     * line_color	Object	{"r":0,"g":0,"b":0}	否	auto_color 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"} 十进制表示
     * is_hyaline	boolean	false	否	是否需要透明底色，为 true 时，生成透明底色的小程序
     */
    private String appId;
    private String scene;
    private String page;
    private Integer width=430;
    private boolean auto_color=true;
    private Object line_color;
    private boolean is_hyaline=true;

}
