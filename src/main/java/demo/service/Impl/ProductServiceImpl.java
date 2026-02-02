package demo.service.Impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.dto.ProductDTO;
import demo.entity.Product;
import demo.mapper.ProductMapper;
import demo.service.ProductService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductMapper ProductMapper;
	/*
	 * 商品一覧検索
	 * return 商品検索リスト
	 */
	@Override
	public List<Product> findAll(){
		log.debug("商品一覧画面表示");
		return ProductMapper.findAll();
		
	}
	
	/*
	 * 商品情報編集
	 * return 商品検索リスト
	 */
	@Override
	public Product findById(Long id) {
		log.debug("商品ID編集：{}",id);
		return ProductMapper.findById(id);
	}
	
	/*
	 * 商品削除
	 * @param ID
	 * return 削除ID
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteById(Long id) {
		log.info("商品削除成功ID{}",id);
		   // 1. 商品が存在するかチェック
        Product product = ProductMapper.findById(id);
        if (product == null) {
            log.warn("商品が存在しない，商品ID：{}", id);
            throw new RuntimeException("商品が存在しない");
        }
        
        // 2. 商品删除情報
        int result = ProductMapper.deleteById(id);
        if (result > 0) {
            log.info("商品删除成功，商品ID：{}", id);
        } else {
            log.error("商品删除失败，商品ID：{}", id);
            throw new RuntimeException("商品删除失敗");
        }
	}
	/**
     * 商品更新情報
     * 
     * @param productDTO 商品情報
     * @return 商品情報が更新された
     */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Product update(ProductDTO productDTO) {
		log.info("商品の更新,商品ID：{}",productDTO.getId()); 
		//1. 商品が存在するかチェック
		Product existingProduct	=ProductMapper.findById(productDTO.getId());
		if(existingProduct == null) {
			log.warn("商品が存在しない，商品ID：{}", productDTO.getId());
            throw new RuntimeException("商品が存在しない");
		}
		//2. 商品コードが更新されている場合は、新しいコードがすでに別の商品で使用されているかどうかを確認します。
		if(!existingProduct.getProductCode().equals(productDTO.getProductCode())){
		  //再利用可能な商品コードを取得する
		  Product duplicateProdpudt = ProductMapper.findById(productDTO.getId());
		  if(duplicateProdpudt!= null && duplicateProdpudt.getId().equals(productDTO.getId())) {
			  	log.warn("商品コードがすでに別の商品で使用されている，商品ID：{}", productDTO.getId());
	            throw new RuntimeException("商品コードがすでに別の商品で使用されている");
		   }	
		}	
		//3. 商品更新する
		Product product = new Product();
		BeanUtils.copyProperties(productDTO, product);
		int result = ProductMapper.update(product);
		if(result > 0) {
		    log.info("商品更新成功，商品ID：{}", product.getId());
            return ProductMapper.findById(product.getId());
        } else {
            log.error("商品更新失敗，商品ID：{}", productDTO.getId());
            throw new RuntimeException("商品更新失敗");
        }
		
	}

}