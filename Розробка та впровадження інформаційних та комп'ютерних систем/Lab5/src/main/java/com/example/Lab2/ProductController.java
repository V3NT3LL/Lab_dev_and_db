package com.example.Lab2;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product, @RequestParam Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + categoryId));
        product.setCategory(category);
        return productRepository.save(product);
    }

    @PostMapping("/stock-items")
    public StockItem createStockItem(@RequestBody StockItem stockItem, @RequestParam Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + categoryId));
        stockItem.setCategory(category);
        return stockItemRepository.save(stockItem);
    }

    @GetMapping("/stock-items")
    public List<StockItem> getStockItemsByCategory(@RequestParam Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + categoryId));
        return stockItemRepository.findByCategory(category);
    }

}
