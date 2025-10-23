package src.Test_1.model;

import java.util.Objects;

public class Category {
    private final String categoryId;
    private final String categoryName;
    private final Category parentCategory;
    private final String description;

    public Category(String categoryId, String categoryName, Category parentCategory, String description) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.parentCategory = parentCategory;
        this.description = description;
    }

    // Getter方法
    public String getCategoryId() { return categoryId; }
    public String getCategoryName() { return categoryName; }
    public Category getParentCategory() { return parentCategory; }
    public String getDescription() { return description; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(categoryId, category.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId);
    }

    @Override
    public String toString() {
        return String.format("分类[ID:%s, 名称:%s, 父分类:%s]",
                categoryId, categoryName,
                parentCategory != null ? parentCategory.getCategoryName() : "无");
    }
}
