package Model.Categories;

import Controller.CategoryName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Apples extends Category {
    public Apples(Category parent) {
        super(true,
                new ArrayList<>(Arrays.asList()),
                CategoryName.FRUITS,
                "fruits.png",
                null);
    }

    @Override
    void initSubCategories() {

    }
}
