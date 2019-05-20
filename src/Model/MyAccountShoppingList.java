package Model;

import Controller.BackendController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyAccountShoppingList extends AnchorPane implements CustomComponent {

    @FXML
    public AnchorPane recieptArea;

    @FXML
    public ScrollPane scrollLists;

    @FXML
    public Accordion listAccordion;



    public listItemTitled listItemTitled;
    public ArrayList<listItemTitled> lists = new ArrayList<>();

    public void newList(listItemTitled lit, List<Product> products) {
        double price = 0;
        for (Product p : products) {
            lit.addProduct(p);
            price += p.getPrice();
        }

        lit.spawncartItems();

        lit.setPrice(price);
        lists.add(lit);
    }

    public void newList(List<Product> products) {
        listItemTitled lit = new listItemTitled("Namnlös lista", "Övrigt", 1234.56);
        newList(lit, products);
    }


    public void spawnLists() {

        TitledPane tp;
        for (listItemTitled lit : lists){
            tp = lit;
            tp.setAnimated(true);
            listAccordion.getPanes().add(tp);
        }

    }

    public MyAccountShoppingList() {
        setRoot();

        listItemTitled = new listItemTitled("Veckohandlingen", "Blandat", 1234.56);
        List<Product> products = BackendController.getInstance().getRandomProducts(6);
        newList(listItemTitled,products);
        spawnLists();
        scrollLists.setFitToWidth(true);
    }

    @Override
    public void setRoot() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("myAccountShoppingList.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void addToCart() {
        listItemTitled.addToCart();
    }
}
