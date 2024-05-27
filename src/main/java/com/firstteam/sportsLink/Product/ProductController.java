package com.firstteam.sportsLink.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
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

    String uploadDirectory = "src/main/resources/static/image"; // 경로 직접 지정

    @GetMapping("/ticket/ticket_write")
    public String showProductForm(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        return "ticket/ticket_write";
    }
    @GetMapping("/ticket/activity_write")
    public String showActivityForm(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        return "ticket/activity_write";
    }

    @PostMapping("/ticket/ticket_write")
    public String createProduct(@ModelAttribute ProductDTO productDTO, @RequestParam("image") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename()); // 파일명 정리
                Path uploadPath = Paths.get(uploadDirectory); // 업로드 디렉토리 경로
                Path filePath = uploadPath.resolve(fileName); // 파일 경로 설정
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING); // 파일 복사

                // 파일 경로를 DTO에 설정
                productDTO.setImageUrl("/image/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                // 파일 업로드에 실패한 경우 예외 처리
                return "redirect:/ticket/ticket_write?uploadError";
            }
        }
        ProductEntity product = productDTO.toEntity();
        productService.saveProduct(product);
        return "redirect:/ticket";
    }
    @PostMapping("/ticket/activity_write")
    public String createActivity(@ModelAttribute ProductDTO productDTO, @RequestParam("image") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename()); // 파일명 정리
                Path uploadPath = Paths.get(uploadDirectory); // 업로드 디렉토리 경로
                Path filePath = uploadPath.resolve(fileName); // 파일 경로 설정
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING); // 파일 복사

                // 파일 경로를 DTO에 설정
                productDTO.setImageUrl("/image/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                // 파일 업로드에 실패한 경우 예외 처리
                return "redirect:/ticket/ticket_write?uploadError";
            }
        }
        ProductEntity product = productDTO.toEntity();
        productService.saveProduct(product);
        return "redirect:/activity";
    }
    @GetMapping("/ticket")
    public String showViewTickets(Model model) {
        List<ProductEntity> viewingTickets = productService.findViewingTickets();
        model.addAttribute("view_ticket", viewingTickets);
        return "ticket/ticket";
    }

    @GetMapping("/activity")
    public String showActivityTickets(Model model) {
        List<ProductEntity> activityTickets = productService.findActivityTickets();
        model.addAttribute("activity_ticket", activityTickets);
        return "ticket/activity";
    }
    @GetMapping("/ticket/ticket_inner/{id}")
    public String showProductDetail(@PathVariable("id") Long id, Model model) {
        ProductEntity product = productService.findProductById(id);
        model.addAttribute("product", product);
        return "ticket/ticket_inner";
    }

    @GetMapping("/ticket/go_ticket/{title}")
    public String goProduct(@PathVariable("title") String title, Model model) {
        ProductEntity product = productService.findProductByTitle(title);
        model.addAttribute("product", product);
        return "ticket/ticket_inner";
    }

    @GetMapping("/ticket/go_ticket/order/{title}")
    public String goOrderProduct(@PathVariable("title") String title, Model model) {
        ProductEntity product = productService.findProductByTitle(title);
        model.addAttribute("product", product);
        return "ticket/ticket_inner";
    }

    @GetMapping("/ticket/edit_product/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        ProductEntity product = productService.findProductById(id);
        model.addAttribute("product", product);
        if ("Viewingticket".equals(product.getProducttype())) {
            return "ticket/product_edit";
        } else {
            return "ticket/activity_edit";
        }
    }


    @PostMapping("/ticket/update/{id}")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute ProductDTO product, @RequestParam("image") MultipartFile file) {
        String imageUrl = "";
        if (!file.isEmpty()) {
            try {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename()); // 파일명 정리
                Path uploadPath = Paths.get(uploadDirectory); // 업로드 디렉토리 경로
                Path filePath = uploadPath.resolve(fileName); // 파일 경로 설정
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING); // 파일 복사
                imageUrl = "/image/" + fileName;
                product.setImageUrl(imageUrl);
            } catch (IOException e) {
                e.printStackTrace();
                // 파일 업로드에 실패한 경우 예외 처리
                // 적절한 방법으로 처리하거나 사용자에게 알리는 메시지를 반환
                // 여기서는 간단히 로그 출력 후 리다이렉트
                return "redirect:/ticket/ticket_write?uploadError";
            }
        }
        productService.updateProduct(id, product);
        return "redirect:/ticket/ticket_inner/" + id;
    }
    // 삭제 기능 추가
    @PostMapping("/ticket/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
        return "redirect:/ticket";
    }
}