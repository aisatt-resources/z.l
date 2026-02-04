package demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.dto.ProductDTO;
import demo.entity.Product;
import demo.service.ProductService;
import demo.vo.Result;
import lombok.extern.slf4j.Slf4j;


/**
 * 商品コントローラー
 * 商品関するHTTPリクエスト処理
 * 
 * @author 柳志恒
 * @since 2026/1/27
 * @version 1.0.1
 */
@Controller
@RequestMapping("/product")
@Slf4j
public class ProductController {
	
	/**
     * 商品リスト画面
     * 
     * @param model 
     * @param session HTTP
     * @return 商品リスト画面ビュー
     */
	@Autowired
	private ProductService productService;
	
	@GetMapping("/list")
	public String listPage(Model model,HttpSession session) {
		   // 登録情報チェック
        if (session.getAttribute("currentUser") == null) {
            log.warn("未登録，登録画面に戻す");
            return "redirect:/user/login";
        }
        
	log.debug("商品リスト画面訪問");
	List<Product> products= productService.findAll();
	model.addAttribute("products", products);
	model.addAttribute("username", session.getAttribute("username"));
		return "product-list";
	}
	
	/**
     * 商品追加画面
     * 
     * @param session 
     * @return 商品追加ビュー
     */
    @GetMapping("/add")
    public String addpage(HttpSession session) {
    		if(session.getAttribute("currentUser") == null) {
    			log.warn("未登録，登録画面に戻す");
    			return "redirect:/user/login";
    		}
    		log.debug("商品追加画面訪問");
    		
    	 return "product-add";
    }
	
	/**
     * すべて商品情報検索
     * 
     * @return 商品リスト
     */
    @GetMapping("/all")
    @ResponseBody
    public Result<List<Product>> findAll() {
        log.debug("すべて商品検索");
        try {
            List<Product> products = productService.findAll();
            return Result.success(products);
        } catch (Exception e) {
            log.error("商品検索失敗：{}", e.getMessage());
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 商品更新画面
     * 
     * @param id 商品ID
     * @param model 
     * @param session
     * @return product-edit
     */
    @GetMapping("/edit/{id}")
    public String editpage(@PathVariable Long id,Model model, HttpSession session) {
    	//登録情報チェック
    	if(session.getAttribute("currentUser") == null) {
    		log.warn("未登録，リスト画面に戻す");
            return "redirect:/user/login";
    	}
    	
    	log.debug("商品更新、商品ID：{}", id);
        Product product= productService.findById(id);
        if (product == null) {
            return "redirect:/product/list";
        }
        model.addAttribute("product", product);
        return "product-edit";	
    }
    
    /**
     * 商品削除
     * 
     * @param id 商品ID
     * @return 削除結果
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public Result<Void> deleteById(@PathVariable Long id){
    		log.info("商品削除処理，商品ID：{}", id);
        
        try {
            productService.deleteById(id);
            log.info("商品削除成功：{}", id);
            return Result.success("削除成功", null);
        } catch (Exception e) {
            log.error("商品削除失敗：{}", e.getMessage());
            return Result.error(e.getMessage());
        }
    }
    
    /**
     * 商品データ追加
     * 
     * @param　productDto 商品情報
     * @param bindingResult データチェック結果
     * @return 追加結果
     * 
     */
    @PostMapping("/add")
    @ResponseBody
    public Result<Product> add(@Valid @RequestBody ProductDTO productDTO,
    							BindingResult bindingResult){
    	log.info("商品追加リクエスト処理,商品コード：{}",productDTO.getProductCode());
    	//リクエストパラメータチェック
    	if(bindingResult.hasErrors()) {
    	String errorMsg = bindingResult.getFieldError().getDefaultMessage();
        log.warn("商品追加パラメータチェック失敗：{}", errorMsg);
        return Result.error(errorMsg);
    	}
        //商品追加
      try {  
    	  Product product = productService.add(productDTO);
    	  log.info("商品追加成功：{}", product.getProductCode());
         return Result.success("商品追加成功", product);
         }catch (Exception e) {
         log.error("商品追加失敗:{}",e.getMessage());	 
		 return Result.error(e.getMessage());
	   }
    
    }
    
    /**
     * 商品データ更新機能
     * 
     * @param productDTO 商品情報
     * @param bindingResult データチェック結果
     * @return 更新結果
     */
    @PostMapping("/update")
    @ResponseBody
    public Result<Product> update(@Valid @RequestBody ProductDTO productDTO,
                                  BindingResult bindingResult) {
        log.info("商品更新リクエスト処理，商品ID：{}", productDTO.getId());
        
        // リクエストパラメータチェック
        if (bindingResult.hasErrors()) {
            String errorMsg = bindingResult.getFieldError().getDefaultMessage();
            log.warn("商品更新パラメータチェック失敗：{}", errorMsg);
            return Result.error(errorMsg);
        }
        //商品更新
        try {
            Product product = productService.update(productDTO);
            log.info("商品更新成功：{}", product.getId());
            return Result.success("更新成功", product);
        } catch (Exception e) {
            log.error("商品更新失敗：{}", e.getMessage());
            return Result.error(e.getMessage());
        }
    }
    
    
}