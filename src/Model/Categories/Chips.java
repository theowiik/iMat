package Model.Categories;

import Controller.CategoryName;

import java.util.Arrays;

public class Chips extends Category {
    public Chips(Category parent) {
        super(false,
                Arrays.asList(134),
                CategoryName.CHIPS,
                "CHIPS.jpg",
                parent);
    }

    @Override
    void initSubCategories() {

    }
}
