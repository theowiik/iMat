package Model.Categories;

import Controller.CategoryName;

import java.util.Arrays;
import java.util.List;

public class Candy extends Category {
    public Candy(Category parent) {
        super(false,
                Arrays.asList(135),
                CategoryName.CANDY,
                "CANDY.png",
                parent);
    }

    @Override
    void initSubCategories() {

    }
}
