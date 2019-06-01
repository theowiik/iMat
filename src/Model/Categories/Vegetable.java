package Model.Categories;

import Controller.CategoryName;

import java.util.ArrayList;
import java.util.Arrays;

public class Vegetable extends Category {
    public Vegetable() {
        super(true,
                new ArrayList<>(Arrays.asList(63, 64, 65, 66, 67, 68, 69, 70, 62, 55, 56, 57, 59, 60, 61, 152, 153)),
                CategoryName.VEGETABLES,
                "VEGETABLE.png",
                null);
    }

    @Override
    void initSubCategories() {
        addChild(new RootVegetables(this));
    }
}
