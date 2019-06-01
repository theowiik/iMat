package Model.Categories;

import Controller.CategoryName;

import java.util.ArrayList;
import java.util.Arrays;

public class Fruits extends Category {
    public Fruits() {
        super(true,
                new ArrayList<>(Arrays.asList(129, 131, 132, 133)),
                CategoryName.FRUITS,
                "fruits.png",
                null);
    }

    @Override
    void initSubCategories() {
        this.addChild(new ExoticFruits(this));
        this.addChild(new Berries(this));
        this.addChild(new CitrusFruits(this));
        this.addChild(new Melons(this));
    }
}
