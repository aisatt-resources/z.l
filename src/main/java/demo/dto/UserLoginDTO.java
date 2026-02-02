package demo.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

/**
 * ユーザーログインデータ送信オブジェクト
 * フロントエンドからログインフォームデータを受信して​​検証
 * 
 */
@Data
public class UserLoginDTO {
    
    /**
     * ユーザー名
     * ​​検証規則：NOT　NULL
     */
	@NotBlank(message = "ユーザー名入力してください。")
    private String username;
    
    /**
     * パスワード
     * 検証規則：NOT NULL
     */
    @NotBlank(message = "パスワード入力してください。")
    private String password;
}
