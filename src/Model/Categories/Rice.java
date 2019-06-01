package Model.Categories;

import Controller.CategoryName;

import java.util.Arrays;

public class Rice extends Category {
    public Rice(Category parent) {
        super(false,
                Arrays.asList(113, 114, 115, 120),
                CategoryName.RICE,
                "RICE.png",
                parent);
    }

    @Override
    void initSubCategories() {

    }
}
