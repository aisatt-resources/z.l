package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * 首页控制器
 * 处理首页相关的HTTP请求
 * 
 * @author Mall Team
 * @version 1.0.0
 * @since 2026-01-27
 */
@Slf4j
@Controller
public class IndexController {
    
    /**
     * 初期化画面表示
     * 
     * @return 重定向到登录页面
     */
    @GetMapping("/")
    public String index() {
        log.debug("初期化画面訪問");
        return "redirect:/user/login";
    }
}
