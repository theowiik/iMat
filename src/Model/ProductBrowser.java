package Model;

import Controller.CategoryName;
import Model.Categories.Category;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
    @FXML
    public ScrollPane categoryScrollPane;
    @FXML
    public VBox categoryVBox;
    private Category currentCategory;
    private List<Category> rootCategories = new ArrayList<>();

    private final List<CategoryTab> tabs = new ArrayList<>();
    private final List<CardGrid> cardGrids = new ArrayList<>();

    private final int hGap = 30;
    private final int vGap = 15;

    /**
     * Constructor.
     */
    public ProductBrowser(List<Category> categories) {
        setRoot();
        initCardVBox();
        this.rootCategories.addAll(categories);
        initTabs(this.rootCategories);
        categoryScrollPane.setFitToWidth(true);
    }

    public void showAllProducts() {
        clearCardVBox();

        // Categories
        spawnTitledSection("Kategorier");
        List<Category> categories = new ArrayList<>();
        for (Category category : rootCategories) {
            categories.add(category);
            categories.addAll(category.getAllSubCategories());
        }
        spawnCategoryCardGrid(categories);

        // Products
        spawnTitledSection("Produkter");
        List<AnchorPane> products = new ArrayList<>();
        for (Category category : rootCategories) {
            products.addAll(category.getAllProductCards());
        }
        spawnCardGrid(products);
    }

    private void initTabs(List<Category> categories) {
        for (Category category : categories) {
            CategoryTab categoryTab = new CategoryTab(category);
            tabs.add(categoryTab);
            categoryTab.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    categoryClicked(categoryTab.categoryName);
                }
            });
            categoryVBox.getChildren().add(categoryTab);
        }
    }

    /**
     * Gets a list of a categories category and all of its children's sub categories.
     * @param rootCategory
     * @return
     */
    private List<Category> getCurrentCategoriesCategories(Category rootCategory) {
        List<Category> categories = new ArrayList<>();
        categories.add(rootCategory);
        categories.addAll(rootCategory.getAllSubCategories());
        return categories;
    }

    /**
     * Shows the appropriate category. Is called when a category is clicked.
     *
     * @param categoryName the name of the category.
     */
    private void categoryClicked(CategoryName categoryName) {
        // Loop through each root category
        for (Category rootCategory : rootCategories) {
            List<Category> categoriesToSearchFor = getCurrentCategoriesCategories(rootCategory);

            // For each root category, loop through all its subcategories.
            for (Category category : categoriesToSearchFor) {

                // Does the category exist?
                if (category.getCategoryName() == categoryName) {
                    clearCardVBox();

                    // - Breadcrumb ---
                    CategoryName backPath = getCategoriesBackPath(category);
                    spawnBreadcrumb(category.getPath(), backPath);

                    // - Titled Section ---
                    List<Category> subCategories = category.getChildren();
                    if (!subCategories.isEmpty()) {
                        spawnTitledSection("Kategorier inom " + categoryName.getPrettyName().toLowerCase());
                    }

                    spawnCategoryCardGrid(subCategories);

                    // - Spawn product cards ---
                    spawnTitledSection("Produkter inom " + category.getCategoryName().getPrettyName());
                    List<AnchorPane> anchorPanes = new ArrayList<>();
                    anchorPanes.addAll(category.getAllProductCards());
                    spawnCardGrid(anchorPanes);

                    // - Highlight the correct tab ---
                    updateTabs(category.getCategoryName());
                    break;
                }
            }
        }
    }

    /**
     * Gets the CategoryName of which the back button should redirect to.
     * @param category
     * @return
     */
    private CategoryName getCategoriesBackPath(Category category) {
        if (category.getParent() != null) {
            return category.getParent().getCategoryName();
        }

        return null;
    }

    /**
     * Spawns a card grid with category cards given a list of categories.
     * @param categories a list of categories.
     */
    public void spawnCategoryCardGrid(List<Category> categories) {
        List<AnchorPane> categoryCards = new ArrayList<>();
        for (Category subCategory : categories) {
            CategoryCard categoryCard = new CategoryCard(subCategory);
            categoryCard.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    categoryClicked(categoryCard.getCategoryNameEnum());
                    System.out.println("you just clicked on a subcategory");
                }
            });
            categoryCards.add(categoryCard);
        }
        spawnCardGrid(categoryCards);
    }

    /**
     * Colors the clicked category tab.
     * @param categoryName
     */
    private void updateTabs(CategoryName categoryName) {
        for (CategoryTab categoryTab : tabs) {
            if (categoryTab.hasCategory(categoryName)) {
                categoryTab.setStyle("-fx-background-color: #E88A87");
            } else {
                categoryTab.setStyle("-fx-background-color: white");
            }
        }
    }

    /**
     * Initializes the VBox
     */
    private void initCardVBox() {
        cardVBox = new VBox();
        cardVBox.setFillWidth(true);
        productViewMainScrollPane.setContent(this.cardVBox);
        productViewMainScrollPane.setFitToWidth(true);
    }

    /**
     * @param title
     */
    public void addTitledSection(String title) {
        TitledSection titledSection = new TitledSection(title);
        cardVBox.getChildren().add(titledSection);
    }

    /**
     * @param cards
     */
    public void spawnCardGrid(List<AnchorPane> cards) {
        CardGrid cardGrid = new CardGrid(cards, hGap, vGap);
        cardGrids.add(cardGrid);
        cardVBox.getChildren().add(cardGrid);
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

    /**
     * @param cols
     */
    public void updateGaps(int cols) {
        for (CardGrid cardGrid : cardGrids) {
            cardGrid.setColumns(cols);
        }
    }

    public void clearCardVBox() {
        cardVBox.getChildren().clear();
        System.out.println("clear");
    }

    public void spawnBreadcrumb(String path, CategoryName backPath) {
        Breadcrumb breadcrumb = new Breadcrumb(path, backPath);

        if (backPath != null) {
            breadcrumb.getBackButton().setOnAction(event -> {
                categoryClicked(backPath);
                System.out.println("clicked breadcrump button, goto: " + backPath);
            });
        }

        cardVBox.getChildren().add(breadcrumb);
    }
}
