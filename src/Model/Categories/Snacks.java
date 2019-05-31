package Model.Categories;

import Controller.CategoryName;

import java.util.Arrays;

public class Snacks extends Category {
    public Snacks() {
        super(true,
                Arrays.asList(),
                CategoryName.SNACKS,
                "SNACKS.jpg",
                null);
    }

    @Override
    void initSubCategories() {
        addChild(new Chips(this));
        addChild(new Icecream(this));
        addChild(new Candy(this));
    }
}
