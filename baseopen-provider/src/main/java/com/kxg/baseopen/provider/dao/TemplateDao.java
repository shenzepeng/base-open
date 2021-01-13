package com.kxg.baseopen.provider.dao;

import com.kxg.baseopen.provider.mapper.WechatTemplateMapper;
import com.kxg.baseopen.provider.pojo.WechatTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 要写注释呀
 */
@Repository
public class TemplateDao {
    @Autowired
    private WechatTemplateMapper wechatTemplateMapper;

    public Integer addWeChatTemplateList(List<WechatTemplate> wechatTemplates){
        if (CollectionUtils.isEmpty(wechatTemplates)){
            return 0;
        }
        return wechatTemplateMapper.insertList(wechatTemplates);
    }

    public Integer deleteWeChatTemplateById(Long id){
        return wechatTemplateMapper.deleteByPrimaryKey(id);
    }

    public Integer updateWeChatTemplate(WechatTemplate wechatTemplate){
        return wechatTemplateMapper.updateByPrimaryKeySelective(wechatTemplate);
    }

    public List<WechatTemplate> findAll(){
        return wechatTemplateMapper.selectAll();
    }
}
