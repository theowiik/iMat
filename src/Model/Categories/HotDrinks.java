package Model.Categories;

import Controller.CategoryName;

import java.util.Arrays;

public class HotDrinks extends Category {
    public HotDrinks(Category parent) {
        super(false,
                Arrays.asList(27, 28, 29, 30, 31),
                CategoryName.HOT_DRINKS,
                "hot_drinks.png",
                parent);
    }

    @Override
    void initSubCategories() {

    }
}
