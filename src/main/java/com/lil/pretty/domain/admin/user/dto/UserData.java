package com.lil.pretty.domain.admin.user.dto;

import java.util.List;

import com.lil.pretty.domain.user.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserData {
	
	private String us_id;
    private String us_pw;
    private String us_nm;
    private String us_email;
    private String us_phone;
    private String us_role;
  
    
    public static UserData fromEntity(User user) {
    	return new UserData(user.getUsId(), user.getUsPw(), user.getUsNm(), user.getUsEmail(), user.getUsPhone(), user.getUsRole());
    }
    
    public static List<UserData> fromEntities(List<User> users) {
    	return users.stream().map(UserData::fromEntity).toList();
    	
    }
}
