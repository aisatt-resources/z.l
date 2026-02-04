package demo.service;

import java.util.List;

import demo.dto.ProductDTO;
import demo.entity.Product;


/**
 * 商品インターフェース
 * 
 * @author 柳志恒
 * @since 2026/1/30
 * @version 1.0.1
 */
public interface ProductService {
	
	/**
	 * 商品リスト画面処理
	 */
	List<Product> findAll();
	
	/**
	 * 商品更新画面レイアウト
	 * @param ID　商品IDで画面遷移
	 */
	Product findById(Long id);
	
	/**
	 * 商品削除ID処理
	 * @param ID 商品IDで検索して削除
	 */
	void deleteById(Long id);
	
	/**
	 * 商品データ更新処理
	 * @param productDTO 商品情報
	 */
	Product update(ProductDTO productDTO);
	
	/**
	 * 商品データ追加処理
	 * @param productDTO 商品情報
	 */
	Product add(ProductDTO productDTO);
	
	/**
	 * 商品名で検索処理
	 * @param　productName 商品名
	 */
	List<Product> searchByname(String productName);
	

}
