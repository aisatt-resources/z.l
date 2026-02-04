package demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import demo.entity.Product;

/**
 * 商品Mapperインターフェス
 * 
 * @author 柳志恒
 * @since 2026/1/30
 * @version 1.0.1
 */
@Mapper
public interface ProductMapper {
	
	/**
	 * 商品リスト画面処理
	 * return 商品リスト
	 */
	List<Product> findAll();
	
	/**
	 * 商品更新画面レイアウト
	 * param id　商品ID条件で画面遷移
	 * 
	 */
	Product findById(@Param("id") Long id);
	
	/**
	 * 商品データ更新処理
	 * param product商品情報
	 * 
	 */
	int update(Product product);
	
	/**
	 * 商品削除処理　
	 * param id　商品IDで検索して削除
	 * 
	 */
	int deleteById(@Param("id")Long id);
	
	/**
	 * 商品データ追加処理
	 * param product商品情報
	 * 
	 */
	int insert(Product product);
	
	/**
	 * 商品追加でコード存在チェック処理
	 * param productCode　商品コード
	 * 
	 */
	Product findByproductCode(@Param("productCode") String productCode);
	
	/**
	 * 商品名で検索処理
	 * param productName　商品名
	 * 
	 */
	List<Product> findBysearchName(@Param("productName") String productName);


}
