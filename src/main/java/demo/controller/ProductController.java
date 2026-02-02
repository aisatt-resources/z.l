package demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.entity.Product;
import demo.service.ProductService;
import demo.vo.Result;
import lombok.extern.slf4j.Slf4j;

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
     * すべて商品検索
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
     * 商品編集画面
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
    	
    	log.debug("商品編集、商品ID：{}", id);
        Product product= productService.findById(id);
        if (product == null) {
            return "redirect:/product/list";
        }
        model.addAttribute("product", product);
        return "product-edit";	
    }
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
}