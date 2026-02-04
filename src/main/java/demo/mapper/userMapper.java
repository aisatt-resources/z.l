package demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import demo.entity.user;

/**
 * ユーザーMapperインターフェス
 * 
 * @author 柳志恒
 * @since 2026/1/29
 * @version 1.0.1
 */
@Mapper
public interface userMapper {
	
	/**
	 * ユーザー名を検索　ログイン用
	 */
	user findByuserName(@Param("username") String username);
	
	/**
	 * 新規ユーザー登録
	 */
	int insert(user user);

}
