package pl.emb.covidsupport;

public class CountryItem {
    private String countryName;
    private int countryId;

    public CountryItem(String countryName, int countryId) {
        this.countryName = countryName;
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getCountryId() {
        return countryId;
    }
}
