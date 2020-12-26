package com.kxg.baseopen.provider.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.open.bean.ma.WxOpenMaCategory;

import java.util.List;

/**
 * 要写注释呀
 */
@Data
public class GetAppCategoryListResponse {
    private List<WxOpenMaCategory> category_list;
}
