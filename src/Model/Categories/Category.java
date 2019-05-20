package Model.Categories;

import Controller.BackendController;
import Controller.CategoryName;
import Model.CategoryCard;
import Model.ProductCard;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

import java.util.ArrayList;
import java.util.List;

public abstract class Category {
    public final boolean isRoot;
    private CategoryName categoryName;
    private List<Category> children = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private List<ProductCard> productCards = new ArrayList<>();
    private List<CategoryCard> categoryCards = new ArrayList<>();
    private CategoryCard categoryCard;
    private static Category instance = null;
    private Image image;
    private Category parent;

    public Category(boolean isRoot, List<Integer> productIds, CategoryName categoryName, String imageName, Category parent) {
        this.isRoot = isRoot;
        this.categoryName = categoryName;
        List<Product> products = BackendController.getInstance().getProducts(productIds);
        List<ProductCard> productCards = BackendController.getInstance().getProductCards(products);
        this.productCards.addAll(productCards);
        this.parent = parent;

        // Image
        String imagePath = "resources/categoryImages/" + imageName;
        this.image = new Image(imagePath);

        initProducts(productIds);
        initSubCategories();
    }

    abstract void initSubCategories();

    private void initProducts(List<Integer> productIds) {
        for (Integer id : productIds) {
            Product product = BackendController.getInstance().getProduct(id);
            products.add(product);
        }
    }

    public List<Category> getAllSubCategories() {
        List<Category> allSubCategories = new ArrayList<>();
        for (Category category : getChildren()) {
            allSubCategories.add(category);
            allSubCategories.addAll(category.getAllSubCategories());
        }
        return allSubCategories;
    }

    /**
     * Returns a list of all the categories products including its children's products.
     * @return
     */
    public List<AnchorPane> getAllProductCards() {
        List<AnchorPane> products = new ArrayList<>();
        products.addAll(this.getProductCards());
        for (Category child : children) {
            products.addAll(child.getAllProductCards());
        }
        return products;
    }

    public boolean hasChildren() {
        return children.size() > 0;
    }

    public boolean hasProducts() {
        return products.size() > 0;
    }

    public void addChild(Category category) {
        children.add(category);
    }

    public void addProducts(List<Product> products) {
        this.products.addAll(products);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public boolean isRoot() {
        return isRoot;
    }

    public CategoryName getCategoryName() {
        return categoryName;
    }

    public List<Category> getChildren() {
        List<Category> categories = new ArrayList<>();
        categories.addAll(this.children);
        return categories;
    }

    public Category getParent() {
        return parent;
    }

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        this.products.addAll(products);
        return products;
    }

    public List<ProductCard> getProductCards() {
        List<ProductCard> productCards = new ArrayList<>(this.productCards);
        return productCards;
    }

    public String getPath() {
        StringBuilder sb = new StringBuilder();

        if (!isRoot) {
            String parentPath = parent.getPath();
            sb.append(parentPath);
            sb.append(" > ");
        }

        sb.append(this.categoryName.getPrettyName());
        return sb.toString();
    }

    public Image getImage() {
        return image;
    }

    public boolean hasCategory(CategoryName categoryName) {
        if (this.categoryName == categoryName) return true;

        for (Category child : children) {
            // A child has the category
            if (child.getCategoryName() == categoryName) {
                return true;
            }

            // A childs childs ... has the category.
            if (child.hasCategory(categoryName)) {
                return true;
            }
        }

        return false;
    }
}
