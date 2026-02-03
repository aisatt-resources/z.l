package demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import demo.entity.Product;



@Mapper
public interface ProductMapper {
	/*
	 * 商品リスト画面
	 * return 商品リスト
	 */
	List<Product> findAll();
	
	/*
	 * 商品更新画面
	 * param id　商品ID条件で画面遷移
	 * 
	 */
	Product findById(@Param("id") Long id);
	
	/*
	 * 商品コード条件検索
	 * param productCode　商品コード
	 * 
	 */
	Product findByproductCode(@Param("productCode") String productCode);
	
	/*
	 * 商品削除　
	 * param id　商品IDで検索して削除
	 * 
	 */
	int deleteById(@Param("id")Long id);
	
	/*
	 * 商品データ更新
	 * param product商品情報
	 * 
	 */
	int update(Product product);
	
	/*
	 * 商品データ追加
	 * param product商品情報
	 * 
	 */
	int insert(Product product);


}
