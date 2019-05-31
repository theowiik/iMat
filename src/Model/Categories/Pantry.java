package Model.Categories;

import Controller.CategoryName;

import java.util.Arrays;

public class Pantry extends Category {
    public Pantry() {
        super(true,
                Arrays.asList(92, 95, 91, 93, 94, 96, 58, 151),
                CategoryName.PANTRY,
                "PANTRY.jpg",
                null);
    }

    @Override
    void initSubCategories() {
        addChild(new Seeds(this));
        addChild(new Pod(this));
        addChild(new Potatoes(this));
        addChild(new Rice(this));
    }
}
