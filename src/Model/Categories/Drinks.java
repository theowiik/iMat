package Model.Categories;

import Controller.CategoryName;

import java.util.Arrays;
import java.util.List;

public class Drinks extends Category{

    public Drinks() {
        super(true,
                Arrays.asList(),
                CategoryName.DRINKS,
                "drinks.png",
                null);
    }

    @Override
    void initSubCategories() {
        addChild(new ColdDrinks(this));
        addChild(new HotDrinks(this));
    }
}
