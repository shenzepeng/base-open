package com.kxg.baseopen.provider.openwx.impl;

import com.kxg.baseopen.provider.dto.request.AddAppCategoryRequest;
import com.kxg.baseopen.provider.dto.request.DeleteAppCategoryRequest;
import com.kxg.baseopen.provider.dto.request.GetAppCategoryListRequest;
import com.kxg.baseopen.provider.dto.request.GetAppSettedCategoryListRequest;
import com.kxg.baseopen.provider.dto.response.AddAppCategoryResponse;
import com.kxg.baseopen.provider.dto.response.DeleteAppCategoryResponse;
import com.kxg.baseopen.provider.dto.response.GetAPPSettedCategoryListResponse;
import com.kxg.baseopen.provider.dto.response.GetAppCategoryListResponse;
import com.kxg.baseopen.provider.openwx.CategoryService;
import com.kxg.baseopen.provider.openwx.TokenService;
import com.kxg.baseopen.provider.utils.HttpClientUtil;
import com.kxg.baseopen.provider.utils.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 要写注释呀
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private TokenService tokenService;
    /**
     * 获取授权小程序帐号的可选类目
     * <p>
     * 注意：该接口可获取已设置的二级类目及用于代码审核的可选三级类目。
     * </p>
     *
     * @return the category list
     * @throws WxErrorException the wx error exception
     */
    @Override
    public GetAppCategoryListResponse getCategoryList(GetAppCategoryListRequest request) {
        String targetUrl=API_GET_CATEGORY+"?access_token="+tokenService.getSmallAppLastAccessToken(request.getAppId());
        String info = getInfo(targetUrl, null);
        return JsonUtils.toBean(info,GetAppCategoryListResponse.class);
    }

    /**
     * 获取已经设置的类目
     * @param request
     * @return
     */
    @Override
    public GetAPPSettedCategoryListResponse getSettedCategoryList(GetAppSettedCategoryListRequest request) {
        String targetUrl=API_GET_SETTED_CATEGORY+"?access_token="+tokenService.getSmallAppLastAccessToken(request.getAppId());
        String info = getInfo(targetUrl, null);
        return  JsonUtils.toBean(info, GetAPPSettedCategoryListResponse.class);

    }

    /**
     * 添加类目
     * @param request
     * @return
     */
    @Override
    public AddAppCategoryResponse addAppCategory(AddAppCategoryRequest request) {
        String targetUrl=API_POST_SET_CATEGORY+"?access_token="+tokenService.getSmallAppLastAccessToken(request.getAppId());
        Map<String,Object> map=new HashMap<>();
        map.put("categories",request.getAddCategoryDto());
        String postInfo = postInfo(targetUrl, map);
        return JsonUtils.toBean(postInfo,AddAppCategoryResponse.class);
    }

    /**
     * 删除类目
     * @param request
     * @return
     */
    @Override
    public DeleteAppCategoryResponse deleteAppCategory(DeleteAppCategoryRequest request) {
        String targetUrl=API_POST_DELETE_CATEGORY+"?access_token="+tokenService.getSmallAppLastAccessToken(request.getAppId());
        Map<String,Object> map=new HashMap<>();
        map.put("first",request.getFirst());
        map.put("second",request.getSecond());
        String postInfo = postInfo(targetUrl, map);
        return JsonUtils.toBean(postInfo,DeleteAppCategoryResponse.class);
    }

    private String postInfo(String targetUrl, Map<String, Object> bodyMsg) {
        return HttpClientUtil.postJson(targetUrl, bodyMsg, null);
    }

    private String getInfo(String targetUrl, Map<String, String> headerMap) {
        return HttpClientUtil.get(targetUrl,null,headerMap);
    }
}
