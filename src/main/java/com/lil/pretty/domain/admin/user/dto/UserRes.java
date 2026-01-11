package com.lil.pretty.domain.admin.user.dto;

import com.lil.pretty.domain.user.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRes {
	private UserData userData;
	
	public static UserRes createRes(User user) {
		return new UserRes(UserData.fromEntity(user));
	}
}
