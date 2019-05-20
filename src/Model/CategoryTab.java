package Model;

import Controller.CategoryName;
import Model.Categories.Category;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class CategoryTab extends AnchorPane implements CustomComponent {

    @FXML
    public Text title;
    public final CategoryName categoryName;
    private Category category;

    public CategoryTab(Category category) {
        setRoot();
        this.title.setText(category.getCategoryName().getPrettyName());
        this.categoryName = category.getCategoryName();
        this.category = category;
    }

    public boolean hasCategory(CategoryName categoryName) {
        return this.category.hasCategory(categoryName);
    }

    @Override
    public void setRoot() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("categoryTab.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
