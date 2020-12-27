package com.kxg.baseopen.provider.dto.getMaBaseInfo;

import lombok.Data;

/**
 * 要写注释呀
 */
@Data
public class Wx_verify_info {
    private boolean qualification_verify;
    private boolean naming_verify;
    private boolean annual_review;
    private long annual_review_begin_time;
    private long annual_review_end_time;
}
