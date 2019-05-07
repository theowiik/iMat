import javafx.scene.image.Image;
import se.chalmers.cse.dat216.project.*;

/**
 * Type stuff here to test the backend
 */
public class BackendTestExample {
    public static void main(String[] args) {
        IMatDataHandler dataHandler = IMatDataHandler.getInstance();
//        for (Product product : dataHandler.getProducts()) {
//            System.out.println(product);
//        }

        dataHandler.getProduct(6).getImageName();

    }
}
