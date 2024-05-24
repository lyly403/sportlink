package com.firstteam.sportsLink.Controller;

import com.firstteam.sportsLink.DTO.ProductDto;
import com.firstteam.sportsLink.Entity.ProductEntity;
import com.firstteam.sportsLink.Repository.ProductRepository;
import com.firstteam.sportsLink.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    String uploadDirectory = "src/main/resources/static/image/upload"; // 경로 직접 지정

    @GetMapping("/ticket/ticket_write")
    public String showProductForm(Model model) {
        model.addAttribute("productDto", new ProductDto());
        return "ticket/ticket_write";
    }

    @PostMapping("/ticket/ticket_write")
    public String createProduct(@ModelAttribute ProductDto productDto, @RequestParam("image") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename()); // 파일명 정리
                Path uploadPath = Paths.get(uploadDirectory); // 업로드 디렉토리 경로
                Path filePath = uploadPath.resolve(fileName); // 파일 경로 설정
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING); // 파일 복사

                // 파일 경로를 DTO에 설정
                productDto.setImageUrl("/uploads/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                // 파일 업로드에 실패한 경우 예외 처리
                // 적절한 방법으로 처리하거나 사용자에게 알리는 메시지를 반환
                // 여기서는 간단히 로그 출력 후 리다이렉트
                return "redirect:/ticket/ticket_write?uploadError";
            }
        }
        ProductEntity product = productDto.toEntity();
        productService.saveProduct(product);
        return "redirect:/ticket";
    }

    @GetMapping("/ticket")
    public String showProduct(Model model){
        List<ProductEntity> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "ticket/ticket";
    }
}
