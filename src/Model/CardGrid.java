package Model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import java.io.IOException;
import java.util.List;

public class CardGrid extends AnchorPane implements CustomComponent {
    @FXML
    public TilePane cardTilePane;

    @FXML
    public Text superText;

    private final int vGap;
    private final int hGap;

    public CardGrid(List<AnchorPane> cards, int vGap, int hGap) {
        this.vGap = vGap;
        this.hGap = hGap;
        setRoot();
        addCards(cards);
        initGaps();
    }

    /**
     * Adds a gap to the grid.
     */
    private void initGaps() {
        cardTilePane.setHgap(hGap);
        cardTilePane.setVgap(vGap);
    }

    /**
     * Adds multiple cards to the grid.
     * @param cards A list of cards.
     */
    private void addCards(List<AnchorPane> cards) {
        for (AnchorPane card : cards) {
            addCard(card);
        }
    }

    /**
     *
     */
    @Override
    public void setRoot() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cardGrid.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Adds a card to the grid.
     * @param card
     */
    public void addCard(AnchorPane card) {
        cardTilePane.getChildren().add(card);
        superText.setText("hiiii");
    }

    /**
     * Wasd.
     * @param columns
     */
    public void setColumns(int columns) {
        cardTilePane.setPrefColumns(columns);
    }
}
