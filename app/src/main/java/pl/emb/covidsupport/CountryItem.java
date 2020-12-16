package pl.emb.covidsupport;

import android.content.Context;

import java.util.Comparator;
import java.util.Locale;

public class CountryItem implements Comparable<CountryItem> {
    private String countryName;
    private String apiCountry;
    private int countryId;

    public CountryItem(Context context, Locale locale) {
        this.countryId = context.getResources().getIdentifier(
                locale.getCountry().toLowerCase(), "raw", context.getPackageName());

        Locale.setDefault(new Locale("en", "EN"));
        this.apiCountry = locale.getDisplayCountry();
        Locale.setDefault(new Locale("pl", "PL"));
        this.countryName = locale.getDisplayCountry();
    }

    public String getApiCountry() {
        return apiCountry;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getCountryId() {
        return countryId;
    }

    @Override
    public int compareTo(CountryItem c2) {
        return getCountryName().compareTo(c2.getCountryName());
    }
}
