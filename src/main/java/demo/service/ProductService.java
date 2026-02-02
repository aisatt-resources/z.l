package demo.service;

import java.util.List;

import demo.dto.ProductDTO;
import demo.entity.Product;


/*
 * 商品クラスインターフェース
 * 
 */
public interface ProductService {
	/*
	 * 商品一覧検索
	 */
	List<Product> findAll();
	
	/*
	 * 商品情報編集ID
	 * @param ID
	 */
	Product findById(Long id);
	
	/*
	 * 商品削除ID
	 * @param ID
	 */
	void deleteById(Long id);
	
	/*
	 * 商品更新ID
	 * @param productDTO 商品情報
	 */
	Product update(ProductDTO productDTO);
	

}
