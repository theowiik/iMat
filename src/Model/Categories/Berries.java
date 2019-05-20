package Model.Categories;

import Controller.CategoryName;

import java.util.ArrayList;
import java.util.Arrays;

public class Berries extends Category {

    Berries(Category parent) {
        super(false,
                new ArrayList<>(Arrays.asList(130)),
                CategoryName.BERRIES,
                "berries.jpg",
                parent);
    }

    @Override
    void initSubCategories() {

    }
}
