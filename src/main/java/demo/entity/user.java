package demo.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザーエンティティ
 * デーブル：users
 * 
 * @author 柳志恒
 * @since 2026/1/30
 * @version 1.0.1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class user {
	
	/**
     * ユーザーID
     */
	private	Integer	id;
	
	/**
     * ユーザー名
     */
	private	String	username;
	
	/**
     * パスワード
     */
	private String	password;
	
    /**
     * 役割（ADMIN-管理員）
     */
    private String role;
    
    /**
     * status（0-禁止，1-起動）
     */
    private Integer status;
    
    /**
     * 作成時間
     */
    private LocalDateTime createTime;
    
    /**
     * 更新時間
     */
    private LocalDateTime updateTime;
	

}
