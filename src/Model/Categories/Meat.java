package Model.Categories;

import Controller.CategoryName;

import java.util.ArrayList;
import java.util.Arrays;

public class Meat extends Category {
    public Meat() {
        super(true,
                new ArrayList<>(Arrays.asList(72, 73, 74, 75, 76, 149, 150)),
                CategoryName.MEAT,
                "meat.png",
                null);
    }

    @Override
    void initSubCategories() {

    }

    public void sayHi() {

    }
}
