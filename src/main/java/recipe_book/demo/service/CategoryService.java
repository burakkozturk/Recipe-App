package recipe_book.demo.service;

import org.springframework.stereotype.Service;
import recipe_book.demo.exceptions.ResourceNotFoundException;
import recipe_book.demo.model.Category;
import recipe_book.demo.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category>  getAllCategories(){

        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }

    public List<Category> getFeaturedCategories() {
        return categoryRepository.findAll().stream()
                .sorted((c1, c2) -> Integer.compare(c2.getRecipes().size(), c1.getRecipes().size()))
                .limit(4)
                .collect(Collectors.toList());
    }

    public Category saveCategory(Category category){

        Category category1 = new Category();
        category1.setName(category.getName());
        category1.setImage_url(category.getImage_url());

        return categoryRepository.save(category1);
    }

    public Category updateCategory(Long id, Category updatedCategory) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        existingCategory.setName(updatedCategory.getName());
        existingCategory.setImage_url(updatedCategory.getImage_url());

        return categoryRepository.save(existingCategory);
    }

    public List<Category> updateFeaturedCategories(List<Long> categoryIds) {
        // Fetch categories based on provided IDs
        List<Category> categories = categoryRepository.findAllById(categoryIds);

        if (categories.size() != categoryIds.size()) {
            throw new ResourceNotFoundException("One or more categories not found.");
        }

        // Logic to mark these categories as featured (if applicable)
        // For this example, we'll assume all provided IDs are valid.

        return categories;
    }

    public void removeCategory(Long id){
        categoryRepository.deleteById(id);
    }
}

