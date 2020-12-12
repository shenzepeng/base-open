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
public class FuncInfo {

    @JsonProperty("funcscope_category")
    private FuncscopeCategory funcscopeCategory;
    public void setFuncscopeCategory(FuncscopeCategory funcscopeCategory) {
         this.funcscopeCategory = funcscopeCategory;
     }
     public FuncscopeCategory getFuncscopeCategory() {
         return funcscopeCategory;
     }

}