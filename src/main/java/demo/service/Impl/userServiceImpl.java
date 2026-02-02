package demo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.dto.UserLoginDTO;
import demo.entity.user;
import demo.mapper.userMapper;
import demo.service.userService;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class userServiceImpl implements userService {

	//userMapper注入
	@Autowired
	private	userMapper userMapper;
	
	/*
	 * ユーザーIDログイン
     * 業務ロジック：
     * 1. ユーザー名で検索
     * 2. ユーザー名が存在チェック
     * 3. パスワードが存在チェック
     * 4. ユーザーステータスチェック
     * 
     * @param loginDTO ログイン情報
     * @return ユーザーオブジェクト
     * @throws RuntimeException ログインに失敗した場合に例外をスローします。
	*/
	@Override
	public user login(UserLoginDTO loginDTO) {
		log.info("ユーザー登録，ユーザー名：{}", loginDTO.getUsername());
        
        // 1. ユーザー名で検索
        user user = userMapper.findByuserName(loginDTO.getUsername());
        
        // 2. ユーザー名が存在チェック
        if (user == null) {
            log.warn("ユーザー不存在：{}", loginDTO.getUsername());
            throw new RuntimeException("ユーザー名またはパスワードが正しくありません。");
        }
        
        // 3. パスワードが存在チェック
        if (!user.getPassword().equals(loginDTO.getPassword())) {
            log.warn("パスワードが正しくありません，ユーザー名：{}", loginDTO.getUsername());
            throw new RuntimeException("ユーザー名またはパスワードが正しくありません。");
        }
        
        // 4. ユーザーステータスチェック
        if (user.getStatus() == 0) {
            log.warn("ユーザーID使用禁止：{}", loginDTO.getUsername());
            throw new RuntimeException("ユーザーID使用禁止，管理員さんに連絡してください。");
        }
        
        log.info("ユーザーログイン成功，ユーザー名：{}", user.getUsername());
        return user;
		
	}
	/*
	 * 新規ユーザー登録
	*/
	@Override
	public void  register(user user) {
		userMapper.insert(user);
	} 
}
