package Model.Categories;

import Controller.CategoryName;

import java.util.Arrays;
import java.util.List;

public class Icecream extends Category {
    public Icecream(Category parent) {
        super(false,
                Arrays.asList(136),
                CategoryName.ICECREAM,
                "ICECREAM.png",
                parent);
    }

    @Override
    void initSubCategories() {

    }
}
