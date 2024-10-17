package sample.cafekiosk.spring.api.controller.product;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.cafekiosk.spring.api.controller.product.dto.request.ProductCraeteReqeust;
import sample.cafekiosk.spring.api.service.product.ProductResponse;
import sample.cafekiosk.spring.api.service.product.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductContorller {

    private final ProductService productService;

    @PostMapping("/api/v1/products/new")
    public void createProduct(ProductCraeteReqeust reqeust){
        productService.createProduct(reqeust);

    }

    @GetMapping("/api/v1/products/selling")
    public List<ProductResponse> getSellingProducts() {
        return productService.getSellingProduct();
    }
}
