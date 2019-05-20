package Model.Categories;

import Controller.CategoryName;

import java.util.ArrayList;
import java.util.Arrays;

public class Shellfish extends Category {

    public Shellfish(Category parent) {
        super(false,
                new ArrayList<>(Arrays.asList(49, 51)),
                CategoryName.SHELLFISH,
                "shellfish.jpg",
                parent);
    }

    @Override
    void initSubCategories() {
    }
}
