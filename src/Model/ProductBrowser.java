package Model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class ProductBrowser extends AnchorPane {
    @FXML
    public Text testText;
    @FXML
    public AnchorPane productBrowserRootPane;
    @FXML
    public TilePane productTilePane;
    @FXML
    public AnchorPane productContainer;

    private int vGap = 30;
    private int hGap = 30;

    public ProductBrowser() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("productBrowserView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Configs the tile pane containing products and categories.
     */
    public void initTilePane() {
        productTilePane.setHgap(hGap);
        productTilePane.setVgap(vGap);
    }

    /**
     * Spawns a basic card
     */
    public void spawnSampleData(Product product) {
        ProductCard card = ProductCardFactory.createProductCard(product);
        productTilePane.getChildren().add(card);
    }

    /**
     * Given a the width to work with, and a card, returns the recommended amount of columns that will fit.
     *
     */
    public int getRecommendedCols(double cardWidth) {
        double width = productContainer.getWidth();
        double hSpacePerCard = (hGap + cardWidth);
        return (int)(width / hSpacePerCard);
    }

    /**
     *
     * @param cols
     */
    public void setCols(int cols) {
        productTilePane.setPrefColumns(cols);
    }

    /**
     *
     * @return
     */
    public double getProductContainerWidth() {
        return productContainer.getWidth();
    }

}
