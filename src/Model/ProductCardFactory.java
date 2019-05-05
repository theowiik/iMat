package Model;

import se.chalmers.cse.dat216.project.Product;

public class ProductCardFactory {

    /**
     * Given a product, returns a product card.
     * @param product
     * @return
     */
    public static ProductCard createProductCard(Product product) {
        return new ProductCard(product);
    }

    /**
     *
     * @param name
     * @param price
     * @param unit
     * @return
     */
    public static ProductCard createProductCard(String name, String price, String unit) {
        return new ProductCard(name, price, unit);
    }

}
