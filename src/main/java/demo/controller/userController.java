package demo.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.dto.UserLoginDTO;
import demo.entity.user;
import demo.service.userService;
import demo.vo.Result;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/user")
public class userController {
	@Autowired
	private userService userService;
	
	/*
	 *ログイン画面表示
	 */
	@GetMapping("/login")
	public String loginFrom() {
		log.debug("ログイン画面訪問");
		//login,htmlに返す
		return "login";
	}
	
	/*
	 * 画面一覧
	 */
		@GetMapping("/product-list")
		public String productFrom() {
			log.debug("商品管理一覧画面訪問");
			//login,htmlに返す
			return "product-list";
		}
	
	/**
     * ユーザー登録処理
     * 
     * @param loginDTO 登録メッセージ
     * @param bindingResult データ検証結果
     * @param session HTTP会话
     * @return 登録結果
     */
    @PostMapping("/login")
    @ResponseBody
    public Result<user> login(@Valid @RequestBody UserLoginDTO loginDTO, 
                              BindingResult bindingResult,
                              HttpSession session) {
        log.info("登録リクエスト処理，ユーザー名：{}", loginDTO.getUsername());
        
        // 	検証リクエストパラメータ
        if (bindingResult.hasErrors()) {
            String errorMsg = bindingResult.getFieldError().getDefaultMessage();
            log.warn("登録パラメータ検証失敗：{}", errorMsg);
            return Result.error(errorMsg);
        }
        
        try {
            // servcie側呼び出し
            user user = userService.login(loginDTO);
            
            // 登録成功　ユーザー名データsessionに保存
            session.setAttribute("currentUser", user);
            session.setAttribute("userId", user.getId());
            session.setAttribute("username", user.getUsername());
            
            // パスワード情報が返されない
            user.setPassword(null);
            
            log.info("ユーザーID登録成功：{}", user.getUsername());
            return Result.success("登録成功", user);
        } catch (Exception e) {
            log.error("登録失敗：{}", e.getMessage());
            return Result.error(e.getMessage());
        }
    }
    
	//新規登録画面表示
	@GetMapping("/register")
	public String registerFrom() {
		log.debug("新規登録画面訪問");
		return "register";
	}
	//新規登録処理
	@PostMapping("/register")
	public String register(user user,Model model) {
		//新規ユーザー登録処理
		 userService.register(user);
		 log.debug("新規登録画面出来ました");
		return "register";
		
	}
}
