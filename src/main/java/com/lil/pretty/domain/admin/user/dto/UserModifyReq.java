package com.lil.pretty.domain.admin.user.dto;

import com.lil.pretty.domain.user.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserModifyReq {
	private String us_id;
    private String us_pw;
    private String us_nm;
    private String us_email;
    private String us_phone;
    private String us_role;
    
    public void modifyEntity(User user) {
    	user.update(us_pw, us_nm, us_id, us_email, us_role, us_phone);
    }
}
