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
import java.util.ArrayList;
import java.util.List;

public class ProductBrowser extends AnchorPane implements CustomComponent {
    @FXML
    public Text testText;
    @FXML
    public AnchorPane productContainer;
    @FXML
    private VBox cardVBox;
    @FXML
    public ScrollPane productViewMainScrollPane;

    private final List<CardGrid> cardGrids = new ArrayList<>();

    private final int hGap = 30;
    private final int vGap = 15;

    /**
     * Constructor.
     */
    public ProductBrowser() {
        setRoot();
        initCardVBox();
    }

    /**
     *
     */
    private void initCardVBox() {
        cardVBox = new VBox();
        cardVBox.setFillWidth(true);
        System.out.println("hi");
        System.out.println(productViewMainScrollPane.getId());
        productViewMainScrollPane.setContent(this.cardVBox);
        productViewMainScrollPane.setFitToWidth(true);
    }

    /**
     *
     * @param title
     */
    public void addTitledSection(String title) {
        TitledSection titledSection = new TitledSection(title);
        cardVBox.getChildren().add(titledSection);
    }

    /**
     *
     * @param cards
     */
    public void spawnCardGrid(List<AnchorPane> cards) {
        CardGrid cardGrid = new CardGrid(cards, hGap, vGap);
        cardGrids.add(cardGrid);
        cardVBox.getChildren().add(cardGrid);
    }

    /**
     *
     * @return
     */
    public double getProductContainerWidth() {
        return productContainer.getWidth();
    }

    /**
     *
     */
    public void spawnTitledSection(String title) {
        TitledSection titledSection = new TitledSection(title);
        cardVBox.getChildren().add(titledSection);
    }

    /**
     * Given a the width to work with, and a card, returns the recommended amount of columns that will fit.
     */
    public int getRecommendedAmountOfColumns(double cardWidth, double maxWidth) {
        double hSpacePerCard = (hGap + cardWidth);
        return (int) (maxWidth / hSpacePerCard);
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

    public void updateGaps(int cols) {
        for (CardGrid cardGrid : cardGrids) {
            cardGrid.setColumns(cols);
        }
    }
}
