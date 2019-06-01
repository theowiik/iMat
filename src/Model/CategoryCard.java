package Model;

import Controller.CategoryName;
import Model.Categories.Category;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class CategoryCard extends AnchorPane implements CustomComponent {
    @FXML
    private Text categoryName;
    private CategoryName categoryNameEnum;
    @FXML
    private ImageView image;

    public CategoryCard(Category category) {
        setRoot();
        this.categoryName.setText(category.getCategoryName().getPrettyName());
        this.categoryNameEnum = category.getCategoryName();
        this.image.setImage(category.getImage());
        Tooltip tooltip = new Tooltip("Visa produkter inom " + categoryNameEnum.getPrettyName().toLowerCase() + ".");
        Tooltip.install(this, tooltip);
    }

    public CategoryName getCategoryNameEnum() {
        return categoryNameEnum;
    }

    @Override
    public void setRoot() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("categoryCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}