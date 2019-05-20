package Model.Categories;

import Controller.CategoryName;

import java.util.ArrayList;
import java.util.Arrays;

public class Fruits extends Category {
    public Fruits() {
        super(true,
                new ArrayList<>(Arrays.asList(22)),
                CategoryName.FRUITS,
                "fruits.jpg",
                null);
    }

    @Override
    void initSubCategories() {
        this.addChild(new ExoticFruits(this));
        this.addChild(new Berries(this));
    }
}
