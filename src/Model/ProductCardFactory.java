package Model;

import Controller.BackendController;
import Controller.iMatController;
import javafx.scene.image.Image;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

public class ProductCardFactory {
    /**
     * Given a product and controller, returns a product card.
     * @param product
     * @return
     */
    public static ProductCard createProductCard(Product product, iMatController parentController) {
//        Image image = IMatDataHandler.getInstance().getFXImage(product);
        return new ProductCard(product, parentController);
    }
}
