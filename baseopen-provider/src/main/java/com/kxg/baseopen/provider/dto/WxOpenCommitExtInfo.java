package com.kxg.baseopen.provider.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.open.bean.ma.*;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 要写注释呀
 */
@Data
public class WxOpenCommitExtInfo implements Serializable {
    private static final long serialVersionUID = 1948219753446593990L;

    WxOpenCommitExtInfo() {

    }

    /**
     * 授权小程序Appid，可填入商户小程序AppID，以区分不同商户
     */
    private String extAppid;

    /**
     * 配置 ext.json 是否生效
     */
    private Boolean extEnable = Boolean.TRUE;

    /**
     * 是否直接提交到待审核列表
     */
    private Boolean directCommit = Boolean.FALSE;

    @SerializedName("ext")
    private Map<String, Object> extMap;

    @SerializedName("extPages")
    private Map<String, WxMaOpenPage> extPages;

    /**
     * 页面路径列表(和app.json结构一致)
     */
    @SerializedName("pages")
    private List<String> pageList;

    /**
     * 分包结构配置
     */
    @SerializedName("subpackages")
    private List<WxMaOpenSubpackage> subpackageList;

    @SerializedName("window")
    private WxMaOpenWindow window;

    @SerializedName("networkTimeout")
    private WxMaOpenNetworkTimeout networkTimeout;

    @SerializedName("tabBar")
    private WxMaOpenTabBar tabBar;

    /**
     * 添加扩展项
     *
     * @param key
     * @param value
     */
    public void addExt(String key, String value) {
        if (extMap == null) {
            extMap = new HashMap<>();
        }
        if (StringUtils.isNoneBlank(key, value)) {
            extMap.put(key, value);
        }
    }

    /**
     * 添加扩展页面
     *
     * @param pagePath
     * @param page
     */
    public void addExtPage(String pagePath, WxMaOpenPage page) {
        if (extPages == null) {
            extPages = new HashMap<>();
        }
        if (StringUtils.isNotBlank(pagePath) && page != null) {
            extPages.put(pagePath, page);
        }
    }

    /**
     * 添加页面
     *
     * @param pagePath
     */
    public void addPage(String pagePath) {
        if (pageList == null){
            pageList = new ArrayList<>();
        }
        if (StringUtils.isNotBlank(pagePath)) {
            pageList.add(pagePath);
        }
    }

    public static WxOpenCommitExtInfo INSTANCE() {
        return new WxOpenCommitExtInfo();
    }

    public String toJson() {
        return WxOpenGsonBuilder.create().toJson(this);
    }
}
