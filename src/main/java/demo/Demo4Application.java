package demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("demo.mapper")
public class Demo4Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo4Application.class, args);
		System.out.println("========================================");
        System.out.println("商品管理システム起動！");
        System.out.println("訪問アクセス: http://localhost:8080/zhl");
        System.out.println("========================================");
	}

}
