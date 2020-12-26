package com.kxg.baseopen.provider.openwx;

import com.kxg.baseopen.provider.dto.request.AddAppCategoryRequest;
import com.kxg.baseopen.provider.dto.request.DeleteAppCategoryRequest;
import com.kxg.baseopen.provider.dto.request.GetAppCategoryListRequest;
import com.kxg.baseopen.provider.dto.request.GetAppSettedCategoryListRequest;
import com.kxg.baseopen.provider.dto.response.*;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 类目设置
 */
public interface CategoryService {
    /**
     * 3. 获取授权小程序帐号的可选类目
     */
    String API_GET_CATEGORY = "https://api.weixin.qq.com/cgi-bin/wxopen/getallcategories";
    /**
     * 获取已经设置的类目
     */
    String API_GET_SETTED_CATEGORY="https://api.weixin.qq.com/cgi-bin/wxopen/getcategory";
    /**
     * 添加类目
     */
    String API_POST_SET_CATEGORY="https://api.weixin.qq.com/cgi-bin/wxopen/addcategory";
    /**
     * 删除类目
     */
    String API_POST_DELETE_CATEGORY="https://api.weixin.qq.com/cgi-bin/wxopen/deletecategory";

    /**
     * 获取授权小程序帐号的可选类目
     * <p>
     * 注意：该接口可获取已设置的二级类目及用于代码审核的可选三级类目。
     * </p>
     *
     * @return the category list
     * @throws WxErrorException the wx error exception
     */
    GetCanAddCategoryResponse getCategoryList(GetAppCategoryListRequest request) ;

    GetAPPSettedCategoryListResponse getSettedCategoryList(GetAppSettedCategoryListRequest request);

    AddAppCategoryResponse addAppCategory(AddAppCategoryRequest request);

    DeleteAppCategoryResponse deleteAppCategory(DeleteAppCategoryRequest request);
}
