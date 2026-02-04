package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * トップページコントローラ
 * トップページHttp処理
 * @author 柳志恒
 * @since 2026-1-29
 * @version 1.0.1
 */
@Slf4j
@Controller
public class IndexController {
    
    /**
     * 初期画面
     * 
     * @return ログイン画面へリダイレクトする
     */
    @GetMapping("/")
    public String index() {
        log.debug("初期化画面訪問");
        return "redirect:/user/login";
    }
}
