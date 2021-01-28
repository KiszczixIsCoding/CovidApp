package pl.emb.covidsupport.global;

import android.content.Context;
import android.util.Log;
import java.util.Locale;

import pl.emb.covidsupport.R;

public class CountryItem implements Comparable<CountryItem> {
    private final String countryName;
    private final String iso2;
    private final Context context;

    public CountryItem(Context context, Locale locale) {
        Locale.setDefault(new Locale("en", "EN"));
        this.countryName = locale.getDisplayName();
        this.iso2 = locale.getCountry();
        this.context = context;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getIso2() {
        return iso2;
    }

    public int getCountryId() {
        return context.getResources().getIdentifier(
                iso2.toLowerCase(), "raw", context.getPackageName());
    }

    public String getPolishCountryName() {
        Locale.setDefault(new Locale("pl", "PL"));
        String[] excludedCountries =
                context.getResources().getStringArray(R.array.countriesNames);
        for (String country : excludedCountries) {
            String[] pair = country.split(":");
            if (pair[0].equals(iso2)) {
                return pair[1];
            }
        }
        return new Locale("", iso2).getDisplayCountry();
    }

    @Override
    public int compareTo(CountryItem c2) {
        return getPolishCountryName().compareTo(c2.getPolishCountryName());
    }

    @Override
    public String toString() {
        return getPolishCountryName();
    }
}








