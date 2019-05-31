package Model.Categories;

import Controller.CategoryName;

import java.util.Arrays;

public class Nuts extends Category {
    public Nuts() {
        super(true,
                Arrays.asList(97, 98, 99, 101, 102, 103),
                CategoryName.NUTS,
                "NUTS.jpg",
                null);
    }

    @Override
    void initSubCategories() {

    }
}
