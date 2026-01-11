package com.lil.pretty.domain.admin.user.dto;

import java.util.List;

import com.lil.pretty.domain.user.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserListRes {
	private List<UserData> userDatas;

	public static UserListRes createRes(List<User> users) {
		return new UserListRes(UserData.fromEntities(users));
	}

}
