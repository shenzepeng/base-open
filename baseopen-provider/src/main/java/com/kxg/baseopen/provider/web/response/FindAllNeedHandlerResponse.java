package com.kxg.baseopen.provider.web.response;

import com.kxg.baseopen.provider.dto.AllNeedHandlerDto;
import lombok.Data;

import java.util.List;

/**
 * 要写注释呀
 */
@Data
public class FindAllNeedHandlerResponse {
    private List<AllNeedHandlerDto> allNeedHandlerDtos;
}
