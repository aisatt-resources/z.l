package demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 結果クラス統一
 * カプセル化したAPIインターフェースのデータを返却
 * 
 * @param <T> 応答データ型
 * @author 柳志恒
 * @since 2026/1/29
 * @version 1.0.1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    
    /**
     * 対応状況コード（200-成功，他-失敗）
     */
    private Integer code;
    
    /**
     * 対応メッセージ
     */
    private String message;
    
    /**
     * 対応データ
     */
    private T data;
    
    /**
     * 対応成功（データなし時）
     * 
     * @return 結果成功
     */
    public static <T> Result<T> success() {
        return new Result<>(200, "操作成功", null);
    }
    
    /**
     * 対応成功（データあり時）
     * 
     * @param data 対応データ
     * @return 結果成功
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }
    
    /**
     * 対応成功（カスタムメッセージとデータ）
     * 
     * @param message 対応メッセージ
     * @param data 対応データ
     * @return 結果成功
     */
    public static <T> Result<T> success(String message, T data) {
        return new Result<>(200, message, data);
    }
    
    /**
     * 失敗対応
     * 
     * @param message メッセージミス
     * @return 結果失敗
     */
    public static <T> Result<T> error(String message) {
        return new Result<>(500, message, null);
    }
    
    /**
     * 失敗対応（カスタマステータスコード）
     * 
     * @param code ステータスコード
     * @param message メッセージミス
     * @return 結果失敗
     */
    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(code, message, null);
    }
}
