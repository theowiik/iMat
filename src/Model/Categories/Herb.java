package Model.Categories;

import Controller.CategoryName;

import java.util.Arrays;

public class Herb extends Category {
    public Herb() {
        super(true,
                Arrays.asList(141, 142, 143, 144, 145, 146, 147, 148),
                CategoryName.HERB,
                "HERB.PNG",
                null);
    }

    @Override
    void initSubCategories() {

    }
}
