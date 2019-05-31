package Model.Categories;

import Controller.CategoryName;

import java.util.Arrays;

public class ColdDrinks extends Category {
    public ColdDrinks(Category parent) {
        super(false,
                Arrays.asList(32, 33, 34, 35, 36, 37, 38, 39, 40),
                CategoryName.COLD_DRINKS,
                "cold_drinks.jpg",
                parent);
    }

    @Override
    void initSubCategories() {

    }
}
