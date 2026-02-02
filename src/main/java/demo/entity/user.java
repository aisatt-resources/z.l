package demo.entity;

import java.time.LocalDateTime;

import lombok.Data;

/*
 * ユーザーエンティティ
*/

@Data
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
