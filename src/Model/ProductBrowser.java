package Model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class ProductBrowser extends AnchorPane implements CustomComponent {
    @FXML
    public Text testText;
    @FXML
    public AnchorPane productBrowserRootPane;
    @FXML
    public TilePane productTilePane;
    @FXML
    public AnchorPane productContainer;
    @FXML
    public VBox productContainerFlow;
    @FXML
    ScrollPane productViewMainScrollPane;

    private int vGap = 30;
    private int hGap = 30;

    public ProductBrowser() {
        setRoot();
        addShit();
        spawnSampleCardGrid();
    }

    public void addShit() {
        VBox myVBox = new VBox();
        myVBox.setFillWidth(true);
        moreShit(myVBox);
        myVBox.setStyle("-fx-background-color: #FF0000;");
        productViewMainScrollPane.setContent(myVBox);
    }

    public void moreShit(VBox myVBox) {
        for (int i = 0; i < 30; i++) {
            TitledSection titledSection = new TitledSection("Potatisar", "#FF0000");
            CardGrid cardGrid = new CardGrid();
            myVBox.getChildren().add(cardGrid);
            myVBox.getChildren().add(titledSection);
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
     */
    public int getRecommendedCols(double cardWidth) {
        double width = productContainer.getWidth();
        double hSpacePerCard = (hGap + cardWidth);
        return (int) (width / hSpacePerCard);
    }

    public void spawnSampleCardGrid() {
        CardGrid cardGrid = new CardGrid();
    }

    /**
     * @param cols
     */
    public void setCols(int cols) {
        productTilePane.setPrefColumns(cols);
    }

    /**
     * @return
     */
    public double getProductContainerWidth() {
        return productContainer.getWidth();
    }

    /**
     *
     */
    public void spawnSampleTitledSection() {
        TitledSection titledSection = new TitledSection("Potatisar");
        productContainerFlow.getChildren().add(titledSection);
    }

    @Override
    public void setRoot() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("productBrowserView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
