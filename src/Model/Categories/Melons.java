package Model.Categories;

import Controller.CategoryName;

import java.util.Arrays;
import java.util.List;

public class Melons extends Category {
    public Melons(Category parent) {
        super(false,
                Arrays.asList(86,88,89,90),
                CategoryName.MELONS,
                "melons.png",
                parent);
    }

    @Override
    void initSubCategories() {

    }
}
