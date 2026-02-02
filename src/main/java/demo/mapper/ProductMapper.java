package demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import demo.entity.Product;



@Mapper
public interface ProductMapper {
	/*
	 * 商品一覧検索
	 * return 商品検索リスト
	 */
	List<Product> findAll();
	
	/*
	 * 商品一覧検索
	 * param id
	 * 
	 */
	Product findById(@Param("id") Long id);
	
	/*
	 * 商品削除
	 * param id
	 * 
	 */
	int deleteById(@Param("id")Long id);
	
	/*
	 * 商品更新ｎ
	 * param product商品情報
	 * 
	 */
	int update(Product product);
	

	

}
