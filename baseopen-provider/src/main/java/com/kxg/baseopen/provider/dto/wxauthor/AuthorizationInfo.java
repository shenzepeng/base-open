package com.kxg.baseopen.provider.dto.wxauthor;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


/**
 * Auto-generated: 2020-12-12 11:24:49
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
@Data
public class AuthorizationInfo {

    @JsonProperty("authorizer_appid")
    private String authorizerAppid;
    @JsonProperty("authorizer_access_token")
    private String authorizerAccessToken;
    @JsonProperty("expires_in")
    private int expiresIn;
    @JsonProperty("authorizer_refresh_token")
    private String authorizerRefreshToken;
    @JsonProperty("func_info")
    private List<FuncInfo> funcInfo;
    public void setAuthorizerAppid(String authorizerAppid) {
         this.authorizerAppid = authorizerAppid;
     }
     public String getAuthorizerAppid() {
         return authorizerAppid;
     }

    public void setAuthorizerAccessToken(String authorizerAccessToken) {
         this.authorizerAccessToken = authorizerAccessToken;
     }
     public String getAuthorizerAccessToken() {
         return authorizerAccessToken;
     }

    public void setExpiresIn(int expiresIn) {
         this.expiresIn = expiresIn;
     }
     public int getExpiresIn() {
         return expiresIn;
     }

    public void setAuthorizerRefreshToken(String authorizerRefreshToken) {
         this.authorizerRefreshToken = authorizerRefreshToken;
     }
     public String getAuthorizerRefreshToken() {
         return authorizerRefreshToken;
     }

    public void setFuncInfo(List<FuncInfo> funcInfo) {
         this.funcInfo = funcInfo;
     }
     public List<FuncInfo> getFuncInfo() {
         return funcInfo;
     }

}