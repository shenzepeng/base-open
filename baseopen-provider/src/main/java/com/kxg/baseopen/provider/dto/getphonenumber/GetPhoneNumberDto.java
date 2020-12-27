package com.kxg.baseopen.provider.dto.getphonenumber;

import lombok.Data;

/**
 * 要写注释呀
 */
@Data
public class GetPhoneNumberDto {
    private String phoneNumber;

    private String purePhoneNumber;

    private String countryCode;

    private Watermark watermark;
}
