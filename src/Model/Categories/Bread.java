package Model.Categories;

import Controller.CategoryName;

import java.util.ArrayList;
import java.util.Arrays;

public class Bread extends Category {
    public Bread() {
        super(true,
                new ArrayList<>(Arrays.asList(8, 9, 10, 11, 12, 13, 14)),
                CategoryName.BREAD,
                "bread.jpg",
                null);
    }

    @Override
    void initSubCategories() {

    }
}
