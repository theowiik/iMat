package Model.Categories;

import Controller.CategoryName;

import java.util.Arrays;

public class Seeds extends Category {
    public Seeds(Category parent) {
        super(false,
                Arrays.asList(104, 105),
                CategoryName.SEEDS,
                "SEEDS.png",
                parent);
    }

    @Override
    void initSubCategories() {

    }
}
