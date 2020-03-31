package guru.springframework.controllers;

import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigInteger;

@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("thankyou")
    public String showProduct(Model model){
        Integer id = getCurrentId();
        model.addAttribute("product", productService.getProductById(id));
        System.out.println(id);
        System.out.println(model);
        return "blog";
    }

    @RequestMapping("/donate")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());
        return "donate";
    }
    public Integer currentId;
    public Integer getCurrentId() {
        return currentId;
    }

    public void setCurrentId(Integer currentId) {
        this.currentId = currentId;
    }

    @RequestMapping(value = "product", method = RequestMethod.POST)
    public String saveProduct(Product product){
        productService.saveProduct(product);
        setCurrentId(product.getId());
        System.out.println("All Okay");
        return "redirect:/thankyou";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("products", productService.listAllProducts());
        System.out.println("Returning products:");
        return "about";
    }

}
