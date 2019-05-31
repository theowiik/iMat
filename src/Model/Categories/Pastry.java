package Model.Categories;

import Controller.CategoryName;

import java.util.ArrayList;
import java.util.Arrays;

public class Pastry extends Category {

    public Pastry() {
        super(true,
                new ArrayList<>(Arrays.asList(137, 138, 139, 140)),
                CategoryName.PASTRY,
                "PASTRY.png",
                null);
    }

    @Override
    void initSubCategories() {

    }
}
