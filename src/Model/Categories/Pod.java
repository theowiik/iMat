package Model.Categories;

import Controller.CategoryName;

import java.util.Arrays;

public class Pod extends Category {
    public Pod(Category parent) {
        super(false,
                Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                CategoryName.POD,
                "POD.jpg",
                parent);
    }

    @Override
    void initSubCategories() {

    }
}
