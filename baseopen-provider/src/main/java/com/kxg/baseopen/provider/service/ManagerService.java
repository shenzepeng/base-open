package com.kxg.baseopen.provider.service;

import com.kxg.baseopen.provider.web.request.FindNeedHandlerInfoRequest;
import com.kxg.baseopen.provider.web.response.FindAllNeedHandlerResponse;
import com.kxg.baseopen.provider.web.response.FindNeedHandlerInfoResponse;

/**
 * 销售业务逻辑层
 */
public interface ManagerService {
    FindAllNeedHandlerResponse findAllNeedHandler();
    FindNeedHandlerInfoResponse findNeedHandlerInfo(FindNeedHandlerInfoRequest request);
}
