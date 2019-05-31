package Controller;

public enum CategoryName {
    DAIRY("Mejeri"),
    MEAT("Chark"),
    NUTS("Nötter"),
    FRUITS("Frukter"),
    VEGETABLES("Grönsaker"),
    SWEETS("Söta Saker"),
    DRINKS("Drycker"),
    BERRIES("Bär"),
    EXOTIC_FRUITS("Exotiska frukter"),
    FISH("Fisk"),
    SHELLFISH("Skaldjur"),
    COLD_DRINKS("Kalla drycker"),
    HOT_DRINKS("Varma drycker"),
    CITRUS_FRUITS("Citrus frukter"),
    MELONS("Meloner"),
    APPLES("Äpplen"),
    PASTRY("Bakelser"),
    BREAD("Bröd"),
    HERB("Kryddor"),
    PANTRY("Skafferi"),
    SNACKS("Snacks"),
    SEEDS("Frön"),
    POD("Linser och bönor"),
    POTATOES("Potatisar"),
    RICE("Ris"),
    CHIPS("Chips"),
    ICECREAM("Glass"),
    CANDY("Godis"),
    ROOT_FRUITS("Rot frukter");


    private String prettyName;

    CategoryName(String prettyName) {
        this.prettyName = prettyName;
    }

    public String getPrettyName() {
        return this.prettyName;
    }
}
