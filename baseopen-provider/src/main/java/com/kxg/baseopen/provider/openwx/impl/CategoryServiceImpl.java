package com.kxg.baseopen.provider.openwx.impl;

import com.kxg.baseopen.provider.dto.secondinfo.Children;
import com.kxg.baseopen.provider.dto.secondinfo.First;
import com.kxg.baseopen.provider.dto.secondinfo.GetSecondInfoByFirstDto;
import com.kxg.baseopen.provider.dto.getcanaddcategory.Categories;
import com.kxg.baseopen.provider.dto.getcanaddcategory.GetCanAddCategoryRoot;
import com.kxg.baseopen.provider.dto.request.*;
import com.kxg.baseopen.provider.dto.response.*;
import com.kxg.baseopen.provider.openwx.CategoryService;
import com.kxg.baseopen.provider.openwx.TokenService;
import com.kxg.baseopen.provider.utils.HttpClientUtil;
import com.kxg.baseopen.provider.utils.JsonUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public GetCanAddCategoryResponse getCategoryList(GetAppCategoryListRequest request) {
        String targetUrl=API_GET_CATEGORY+"?access_token="+tokenService.getSmallAppLastAccessToken(request.getAppId());
        String info = getInfo(targetUrl, null);
        GetCanAddCategoryRoot getCanAddCategoryRoot = JsonUtils.toBean(info, GetCanAddCategoryRoot.class);
        GetCanAddCategoryResponse response=new GetCanAddCategoryResponse();
        response.setCanAddCategoryRoot(getCanAddCategoryRoot);
        return response;
    }

    @Override
    public GetSecondInfoByFirstResponse getSecondInfoByFirst(GetSecondInfoByFirstRequest request) {
        if (StringUtils.isEmpty(request.getFirstName())){
            throw new RuntimeException("当前的搜索的名称不能为空");
        }
        GetSecondInfoByFirstResponse getSecondInfoByFirstResponse=new GetSecondInfoByFirstResponse();
        String targetUrl=API_GET_CATEGORY+"?access_token="+tokenService.getSmallAppLastAccessToken(request.getAppId());
        String info = getInfo(targetUrl, null);
        GetCanAddCategoryRoot getCanAddCategoryRoot = JsonUtils.toBean(info, GetCanAddCategoryRoot.class);
        List<Categories> categories = getCanAddCategoryRoot.getCategories_list().getCategories();
        if (CollectionUtils.isEmpty(categories)){
            return getSecondInfoByFirstResponse;
        }
        List<First> firstList=new ArrayList<>();
        for (Categories category : categories) {
            if (StringUtils.isEmpty(category.getName())){
                continue;
            }
            if (category.getName().contains(request.getFirstName())
                    ||category.getName().equals(request.getFirstName())){
                First first=new First();
                first.setId(category.getId());
                first.setName(category.getName());
                List<Integer> childrenIds = category.getChildren();
                if (CollectionUtils.isEmpty(childrenIds)){
                    continue;
                }
                List<Children> childrenList=new ArrayList<>();
                for (Categories cat : categories) {
                    if (childrenIds.contains(cat.getId())){
                        Children children=new Children();
                        children.setId(cat.getId());
                        children.setName(cat.getName());
                        children.setQualify(cat.getQualify());
                        childrenList.add(children);
                    }
                }
                if (!CollectionUtils.isEmpty(childrenList)) {
                    first.setChildren(childrenList);
                    firstList.add(first);
                }
            }

        }
        GetSecondInfoByFirstDto dto=new GetSecondInfoByFirstDto();
        dto.setFirsts(firstList);
        getSecondInfoByFirstResponse.setGetSecondInfoByFirstDto(dto);
        return getSecondInfoByFirstResponse;
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
        map.put("categories",request.getAddCategoryDto().getCategories());
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
