package demo.service;


import jakarta.validation.Valid;

import demo.dto.UserLoginDTO;
import demo.entity.user;


public interface userService {
	
	/*
	 * 新規口座作成
	 */
	void register(user user);
	
	/*
	 * ユーザーIDログイン
	 */
	user login(@Valid UserLoginDTO loginDTO);
}
