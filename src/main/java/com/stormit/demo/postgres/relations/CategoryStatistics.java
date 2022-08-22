package com.stormit.demo.postgres.relations;

public class CategoryStatistics {
    private String categoryName;
    private Long count;

    public CategoryStatistics(String categoryName, Long count) {
        this.categoryName = categoryName;
        this.count = count;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
