package demo.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

/**
 * ユーザーログインデータ送信オブジェクト
 * フロントエンドからログインフォームデータを受信して​​検証
 * 
 * @author 柳志恒
 * @since 2026-1-28
 * @version 1.0.0
 */
@Data
public class UserLoginDTO {
    
    /**
     * ユーザー名
     * チェック規則：NOT　NULL
     */
	@NotBlank(message = "ユーザー名入力してください")
    private String username;
    
    /**
     * パスワード
     * チェック規則：NOT NULL
     */
    @NotBlank(message = "パスワード入力してください")
    private String password;
}
