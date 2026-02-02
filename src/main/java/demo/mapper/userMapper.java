package demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import demo.entity.user;


@Mapper
public interface userMapper {
	
	//ユーザー名を検索　ログイン用
	user findByuserName(@Param("username") String username);
	//新規ユーザー登録
	void insert(user user);

}
