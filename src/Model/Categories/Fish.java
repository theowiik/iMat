package Model.Categories;

import Controller.CategoryName;

import java.util.ArrayList;
import java.util.Arrays;

public class Fish extends Category {

    public Fish() {
        super(true,
                new ArrayList<>(Arrays.asList(48, 50, 52, 53, 54)),
                CategoryName.FISH,
                "fish.png",
                null);
    }

    @Override
    void initSubCategories() {
        addChild(new Shellfish(this));
    }
}
