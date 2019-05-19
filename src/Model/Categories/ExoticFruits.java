package Model.Categories;

import Controller.CategoryName;

import java.util.ArrayList;
import java.util.Arrays;

public class ExoticFruits extends Category {
    ExoticFruits(Category parent) {
        super(false,
                new ArrayList<>(Arrays.asList(87, 41, 42, 43, 44, 45, 46, 47)),
                CategoryName.EXOTIC_FRUITS,
                "exotic_fruits.jpg",
                parent);
    }

    @Override
    void initSubCategories() {
    }
}
