package com.kxg.baseopen.provider.dto.request;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.open.bean.ma.WxOpenMaPreviewInfo;
import me.chanjar.weixin.open.bean.ma.WxOpenMaSubmitAudit;

import java.util.List;

/**
 * 要写注释呀
 */
@Data
public class SubmitAuditRequest {
    /**
     * 提交审核项的一个列表（至少填写1项，至多填写5项）
     */

    private List<WxOpenMaSubmitAudit> itemList;

    /**
     * 预览信息（小程序页面截图和操作录屏）
     */

    private List<WxOpenMaPreviewInfo> previewInfo;

    /**
     * 小程序版本说明和功能解释
     */

    private String versionDesc;

    /**
     * 反馈内容，不超过200字
     */

    private String feedbackInfo;

    /**
     * 图片media_id列表，中间用“丨”分割，xx丨yy丨zz，不超过5张图片, 其中 media_id 可以通过新增临时素材接口上传而得到
     */
    private String feedbackStuff;

    private String appId;
}
