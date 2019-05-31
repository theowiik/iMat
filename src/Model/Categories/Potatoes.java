package Model.Categories;

import Controller.CategoryName;

import java.util.Arrays;

public class Potatoes extends Category {
    public Potatoes(Category parent) {
        super(false,
                Arrays.asList(116, 117, 118, 119),
                CategoryName.POTATOES,
                "POTATOES.png",
                parent);
    }

    @Override
    void initSubCategories() {

    }
}
