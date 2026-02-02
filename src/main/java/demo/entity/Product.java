package demo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 商品クラス
 * デーブル：product
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	/**
	 * 商品ID、PRIMARY KEY、自動増加
	 */
	private Long id;
	/**
	 * 商品名、NOT NULL
	 */
	private String productName;
	/**
	 * 商品コード、NOT NULL、ユニット
	 */
	private String productCode;
	/**
	 * 商品分類
	 */
	private String category;
	/**
	 * 商品価格、NOT NULL
	 */
	private BigDecimal price;
	/**
	 * 商品在庫数、NOT NULL、デフォルト0
	 */
	private Integer stock;
	/**
	 * 商品説明
	 */
	private String description;
	/**
	 * 状態（0-購入不可，1-購入可能）
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
