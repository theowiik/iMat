package Model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import java.io.IOException;
import java.util.List;

public class CardGrid extends FlowPane implements CustomComponent {
    private final int vGap;
    private final int hGap;

    /**
     * Constructor
     * @param cards
     * @param vGap
     * @param hGap
     */
    public CardGrid(List<AnchorPane> cards, int vGap, int hGap) {
        this.vGap = vGap;
        this.hGap = hGap;
        addCards(cards);
        initGaps();
    }

    /**
     * Adds a gap to the grid.
     */
    private void initGaps() {
        this.setHgap(hGap);
        this.setVgap(vGap);
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

    @Override
    public void setRoot() {
    }

    /**
     * Adds a card to the grid.
     * @param card
     */
    public void addCard(AnchorPane card) {
        this.getChildren().add(card);
    }

    /**
     * Wasd.
     * @param columns
     */
    public void setColumns(int columns) {
//        cardFlowPane.setPrefColumns(columns);
    }
}
