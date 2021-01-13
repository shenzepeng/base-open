package com.kxg.baseopen.provider.task;

import com.kxg.baseopen.provider.dao.TemplateDao;
import com.kxg.baseopen.provider.openwx.CodeManageService;
import com.kxg.baseopen.provider.pojo.WechatTemplate;
import com.kxg.baseopen.provider.web.response.FindAllTemplateResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.open.bean.WxOpenMaCodeTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 要写注释呀
 */
@Slf4j
@Component
public class GetAllNewTemplateTask {
    @Autowired
    private TemplateDao templateDao;
    @Autowired
    private CodeManageService codeManageService;
    @Scheduled(cron = "0/1 * * * * ?")
    private void configureTasks() {
        log.info("执行静态定时任务时间 {}",LocalDateTime.now());
        FindAllTemplateResponse templateList = codeManageService.getTemplateList();
        List<WxOpenMaCodeTemplate> wxOpenMaCodeTemplateList = templateList.getWxOpenMaCodeTemplateList();
        List<WechatTemplate> wechatTemplateList = wxOpenMaCodeTemplateList.stream().map(new Function<WxOpenMaCodeTemplate, WechatTemplate>() {
            @Override
            public WechatTemplate apply(WxOpenMaCodeTemplate wxOpenMaCodeTemplate) {
                WechatTemplate wechatTemplate = new WechatTemplate();
                wechatTemplate.setTemplateid(wxOpenMaCodeTemplate.getTemplateId());
                wechatTemplate.setUserDesc(wxOpenMaCodeTemplate.getUserDesc());
                wechatTemplate.setUserVersion(wxOpenMaCodeTemplate.getUserVersion());
                wechatTemplate.setCreateTime(new Date());
                return wechatTemplate;
            }
        }).collect(Collectors.toList());

        List<WechatTemplate> all = templateDao.findAll();
        List<Long> localTemplateIds = all.stream().map(t -> t.getTemplateid()).collect(Collectors.toList());
        List<Long> remoteTemplateIds = wechatTemplateList.stream().map(t -> t.getTemplateid()).collect(Collectors.toList());

        //add
        if (!CollectionUtils.isEmpty(remoteTemplateIds)){
            List<Long> addList=new ArrayList<>(remoteTemplateIds);
            addList.removeAll(localTemplateIds);
            if (!CollectionUtils.isEmpty(addList)){
                List<WechatTemplate> list=new ArrayList<>();
                for (WechatTemplate wechatTemplate : wechatTemplateList) {
                    if (addList.contains(wechatTemplate.getTemplateid())) {
                        list.add(wechatTemplate);
                    }
                }
                templateDao.addWeChatTemplateList(list);
            }
        }
        //delete
        if (!CollectionUtils.isEmpty(localTemplateIds)){
            List<Long> deleteList=new ArrayList<>(localTemplateIds);
            deleteList.removeAll(remoteTemplateIds);
            if (!CollectionUtils.isEmpty(deleteList)){
                List<Long> deleteIds=new ArrayList<>();
                for (WechatTemplate wechatTemplate : all) {
                   if (deleteList.contains(wechatTemplate.getTemplateid())){
                       deleteIds.add(wechatTemplate.getId());
                   }
                }
                for (Long deleteId : deleteIds) {
                    templateDao.deleteWeChatTemplateById(deleteId);
                }
            }
        }
        //TODO update
    }


}
