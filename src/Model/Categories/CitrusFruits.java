package Model.Categories;

import Controller.CategoryName;

import java.util.Arrays;
import java.util.List;

public class CitrusFruits extends Category {
    public CitrusFruits(Category parent) {
        super(false,
                Arrays.asList(22, 23, 24, 25, 26),
                CategoryName.CITRUS_FRUITS,
                "citrus_fruits.png",
                parent);
    }

    @Override
    void initSubCategories() {

    }
}
