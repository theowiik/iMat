package Model.Categories;

import Controller.CategoryName;

import java.util.Arrays;

public class RootVegetables extends Category {
    public RootVegetables(Category parent) {
        super(false,
                Arrays.asList(121, 122, 123, 124, 125, 126, 127, 128),
                CategoryName.ROOT_FRUITS,
                "ROOT_FRUITS.png",
                parent);
    }

    @Override
    void initSubCategories() {

    }
}
