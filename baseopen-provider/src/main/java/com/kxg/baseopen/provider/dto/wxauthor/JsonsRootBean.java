package com.kxg.baseopen.provider.dto.wxauthor;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
/**
 * Auto-generated: 2020-12-12 11:24:49
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
@Data
public class JsonsRootBean {

    @JsonProperty("authorization_info")
    private AuthorizationInfo authorizationInfo;
    public void setAuthorizationInfo(AuthorizationInfo authorizationInfo) {
         this.authorizationInfo = authorizationInfo;
     }
     public AuthorizationInfo getAuthorizationInfo() {
         return authorizationInfo;
     }

}