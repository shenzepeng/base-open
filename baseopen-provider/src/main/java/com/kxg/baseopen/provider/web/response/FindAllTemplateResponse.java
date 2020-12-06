package com.kxg.baseopen.provider.web.response;

import lombok.Data;
import me.chanjar.weixin.open.bean.WxOpenMaCodeTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 要写注释
 *呀
 */
@Data
public class FindAllTemplateResponse implements Serializable {
    private List<WxOpenMaCodeTemplate> wxOpenMaCodeTemplateList=new ArrayList<>();
}
