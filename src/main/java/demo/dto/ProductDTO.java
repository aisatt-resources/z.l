package demo.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

/**
 * 商品データ送信オブジェクト
 * フロントエンドからログインフォームデータを受信して​​検証
 */
@Data
public class ProductDTO {
    
    /**
     * 商品ID（更新され時用）
     */
    private Long id;
    
    /**
     * 商品名
     * 検証規則：NOT NULL，長さ2-200個文字
     */
    @NotBlank(message = "商品名NOT　NULL")
    @Size(min = 2, max = 200, message = "商品名長さは2-200個文字間")
    private String productName;
    
    /**
     * 商品コード
     * 検証規則：NOT NULL，長さ2-50個文字，文字、数字、-線
     */
    @NotBlank(message = "商品コードNOT　NULL")
    @Size(min = 2, max = 50, message = "商品コード長さは2-50字間")
    @Pattern(regexp = "^[a-zA-Z0-9-]+$", message = "商品コード文字，文字、数字、-線")
    private String productCode;
    
    /**
     * 商品分類
     * 検証規則：文字の長さは最大100
     */
    @Size(max = 100, message = "商品分類の長さは100文字以内")
    private String category;
    
    /**
     * 価格
     * 検証規則：NOT NULL，0より大きい数字
     */
    @NotNull(message = "価格はNOT　NULL")
    @DecimalMin(value = "0", message = "価格は0より大きい数字")
    @Digits(integer = 8, fraction = 0, message = "価格形式が正しくありません，最大8整数まで")
    private BigDecimal price;
    
    /**
     * 在庫数
     * 検証規則：NOT NULL，0か、0より大きい数字
     */
    @NotNull(message = "在庫数NOT　NULL")
    @Min(value = 0, message = "在庫数は0以下ではできません")
    private Integer stock;
    
    /**
     * 商品コメント
     * 検証規則：最大1000文字まで
     */
    @Size(max = 1000, message = "商品コメントは1000文字以上超えることができません")
    private String description;
       
    /**
     * ステータス（0-購入不可，1-購入可能）
     * 検証規則：NOT NULL，ステータス値0 or 1を選択
     */
    @NotNull(message = "ステータスNOT　NULL")
    @Min(value = 0, message = "ステータス値0 or 1を選択")
    @Max(value = 1, message = "ステータス値0 or 1を選択")
    private Integer status;
}
