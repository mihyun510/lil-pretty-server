package com.lil.pretty.domain.admin.user.dto;
import com.lil.pretty.domain.user.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
@AllArgsConstructor
public class UserSaveReq {
	private String us_id;
    private String us_pw;
    private String us_nm;
    private String us_email;
    private String us_phone;
    private String us_role;
    private String us_img;
    
    public User toEntity() {
        return User.builder()
                .usId(us_id)
                .usPw(us_pw)
                .usNm(us_nm)
                .usEmail(us_email)
                .usPhone(us_phone)
                .usRole(us_role)
                .usImg(us_img)
                .build();
    }
}
