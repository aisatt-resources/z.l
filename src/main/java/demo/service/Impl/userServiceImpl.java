package demo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.dto.UserLoginDTO;
import demo.dto.UserRegisterDTO;
import demo.entity.user;
import demo.mapper.userMapper;
import demo.service.userService;
import lombok.extern.slf4j.Slf4j;

/**
 * ユーザー実装
 * 
 * @author 柳志恒
 * @since 2026/1/29
 * @version 1.0.1* 
 */
@Service
@Slf4j
public class userServiceImpl implements userService {

	//userMapper注入
	@Autowired
	private	userMapper userMapper;
	
	/**
	 * ユーザーログイン
     * 業務ロジック：
     * 1. ユーザー名で検索
     * 2. ユーザー名が存在チェック
     * 3. パスワードが存在チェック
     * 4. ユーザーステータスチェック
     * @param loginDTO ログイン情報
     * @return ユーザーオブジェクト
     * @throws RuntimeException ログインに失敗した場合に例外をスローします。
	*/
	@Override
	public user login(UserLoginDTO loginDTO) {
		
		log.info("ユーザーログイン，ユーザー名：{}", loginDTO.getUsername());
        
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
	
	/**
	 * 新規ユーザー作成
	 * １．ユーザー名が存在チェック
	 * ２．入力したパスワード一致チェック
	 * ３．入力結果をDBに保存
	 * ４．新規ユーザー作成完了
	 * @param registerDTO 新規作成DTOオブジェクト
     * @return ユーザーオブジェクト
     * @throws RuntimeException 新規作成に失敗した場合に例外をスローします。
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public user register(UserRegisterDTO registerDTO) {
		
		log.info("新規作成登録,ユーザー名：{}",registerDTO.getUsername());
		
		//ユーザー名が存在チェック
	    user existingUser = userMapper.findByuserName(registerDTO.getUsername());
	    if(existingUser != null) {
	     log.warn("ユーザー名が存在います,ユーザー名：{}",registerDTO.getUsername());
	     throw new RuntimeException("ユーザー名が存在います");
	    }
	    
	    //入力したパスワード一致チェック
	    if(!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
	    	log.warn("パスワード入力が一致していません");
	    	throw new RuntimeException("パスワード入力が一致していません");
	    }
	    
	    //入力結果をDBに保存
	   user user = new user();
	   user.setUsername(registerDTO.getUsername());//ユーザー名取得
	   user.setPassword(registerDTO.getPassword());//パスワード取得　　
	   user.setRole("ADMIN");//役割ド取得
	   user.setStatus(1);//ステータス取得
	   
		int result = userMapper.insert(user);
		if(result > 0) {
		    log.info("新規ユーザー作成成功，ユーザー名：{}", user.getUsername());
            return user;
        } else {
            log.error("新規ユーザー作成失敗，ユーザー名：{}", user.getUsername());
            throw new RuntimeException("新規ユーザー作成失敗");
        }
		
	}
}
