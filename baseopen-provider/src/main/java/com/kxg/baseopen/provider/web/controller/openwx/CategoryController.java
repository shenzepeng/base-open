//package com.kxg.baseopen.provider.web.controller.openwx;
//
//import com.kxg.baseopen.provider.config.SzpJsonResult;
//import com.kxg.baseopen.provider.dto.request.*;
//import com.kxg.baseopen.provider.dto.response.*;
//import com.kxg.baseopen.provider.openwx.CategoryService;
//import io.swagger.annotations.ApiOperation;
//import me.chanjar.weixin.common.error.WxErrorException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * 类目设置
// */
//@RestController
//@RequestMapping("category")
//public class CategoryController {
//    @Autowired
//    private CategoryService categoryService;
//
//    /**
//     * 获取授权小程序帐号的可选类目
//     * <p>
//     * 注意：该接口可获取已设置的二级类目及用于代码审核的可选三级类目。
//     * </p>
//     *
//     * @return the category list
//     * @throws WxErrorException the wx error exception
//     */
//    /**
//     * 获取可设置类目
//     * @param request
//     * @return
//     */
//    @ApiOperation("获取可设置类目")
//    @PostMapping("get")
//    public SzpJsonResult<GetCanAddCategoryResponse> getCategoryList(@RequestBody GetAppCategoryListRequest request){
//        return SzpJsonResult.ok(categoryService.getCategoryList(request));
//    }
//
//    /**
//     * 获取设置过的类目
//     * @param request
//     * @return
//     */
//    @ApiOperation("获取设置过的类目")
//    @PostMapping("get/setted")
//    public SzpJsonResult<GetAPPSettedCategoryListResponse> getSettedCategoryList(@RequestBody GetAppSettedCategoryListRequest request){
//        return SzpJsonResult.ok(categoryService.getSettedCategoryList(request));
//    }
//
//    @ApiOperation("通过一级类目名称获取二级类目")
//    @PostMapping("get/cat/info")
//    public SzpJsonResult<GetSecondInfoByFirstResponse> getSecondByFirst(@RequestBody GetSecondInfoByFirstRequest request){
//        return SzpJsonResult.ok(categoryService.getSecondInfoByFirst(request));
//    }
//    /**
//     * 添加类目
//     * @param request
//     * @return
//     */
//    @ApiOperation("添加类目")
//    @PostMapping("add")
//    public SzpJsonResult<AddAppCategoryResponse> addAppCategory(@RequestBody AddAppCategoryRequest request){
//        return SzpJsonResult.ok(categoryService.addAppCategory(request));
//    }
//
//    /**
//     * 删除类目
//     * @param request
//     * @return
//     */
//    @ApiOperation("删除类目")
//    @PostMapping("delete")
//    public SzpJsonResult<DeleteAppCategoryResponse> deleteAppCategory(@RequestBody DeleteAppCategoryRequest request){
//        return SzpJsonResult.ok(categoryService.deleteAppCategory(request));
//    }
//}
