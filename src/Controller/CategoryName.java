package Controller;

public enum CategoryName {
    DAIRY("Mejeri"),
    MEAT("Chark"),
    NUTS("Nötter"),
    FRUITS("Frukter"),
    VEGETABLES("Grönsaker"),
    SWEETS("Söta Saker"),
    DRINKS("Drickor"),
    BERRIES("Bär"),
    EXOTIC_FRUITS("Exotiska frukter"),
    FISH("Fisk"),
    SHELLFISH("Skaldjur");

    private String prettyName;

    CategoryName(String prettyName) {
        this.prettyName = prettyName;
    }

    public String getPrettyName() {
        return this.prettyName;
    }
}
