package Model.Categories;

import Controller.CategoryName;

import java.util.ArrayList;
import java.util.Arrays;

public class Dairy extends Category {
    public Dairy() {
        super(true,
                new ArrayList<>(Arrays.asList(78, 79, 80, 81, 82, 83, 84, 85)),
                CategoryName.DAIRY,
                "dairy.jpg",
                null);

    }

    @Override
    void initSubCategories() {
    }
}
