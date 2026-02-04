package demo.service;


import demo.dto.UserLoginDTO;
import demo.dto.UserRegisterDTO;
import demo.entity.user;

/**
 * ユーザーインターフェース
 * 
 * @author 柳志恒
 * @since 2026/1/29
 * @version 1.0.1* 
 */
public interface userService {
	
	/**
	 * ユーザーログイン
	 * @param loginDTO ユーザーログインオブジェクト
	 */
	user login(UserLoginDTO loginDTO);
	
	/**
	 * 新規作成登録
	 * @param registerDTO 新規作成オブジェクト
	 */
	user register(UserRegisterDTO registerDTO);
}
