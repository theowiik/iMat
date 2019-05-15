package Model;

import Controller.BackendController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyAccountPersonalDiscounts extends AnchorPane implements CustomComponent {

    @FXML
    public FlowPane productContainer;

    @FXML
    public ScrollPane productScroll;



    public MyAccountPersonalDiscounts() {
        setRoot();
        //productScroll.setFitToWidth(true);
        List<ProductCard> productCards = new ArrayList<>();
        Product product = BackendController.getInstance().getProduct(45);
        productCards.add(BackendController.getInstance().getProductCard(product));
        populateDiscounts(productCards);
    }

    public void populateDiscounts(List<ProductCard> products){
        for(ProductCard product : products)
            productContainer.getChildren().add(product);
    }

    @Override
    public void setRoot() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("myAccountPersonalDiscounts.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
