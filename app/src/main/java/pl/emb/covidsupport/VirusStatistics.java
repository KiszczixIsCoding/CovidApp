package pl.emb.covidsupport;

public class VirusStatistics {

    private String Country;
    private String CountryCode;
    private String Province;
    private String City;
    private String CityCode;
    private String Lat;
    private String Lon;
    private String Status;
    private String Date;

    private int Confirmed;
    private int Deaths;
    private int Recovered;
    private int Active;
    private int newCases;
    private int newDeaths;

    public void setNewCases(int newCases) {
        this.newCases = newCases;
    }

    public void setNewDeaths(int newDeaths) {
        this.newDeaths = newDeaths;
    }

    public int getNewCases() {
        return newCases;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public String getCountry() {
        return Country;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public String getProvince() {
        return Province;
    }

    public String getCity() {
        return City;
    }

    public String getLat() {
        return Lat;
    }

    public String getLon() {
        return Lon;
    }

    public String getCityCode() {
        return CityCode;
    }

    public int getConfirmed() {
        return Confirmed;
    }

    public int getDeaths() {
        return Deaths;
    }

    public int getRecovered() {
        return Recovered;
    }

    public int getActive() {
        return Active;
    }

    public String getStatus() {
        return Status;
    }

    public String getDate() {
        return Date;
    }
}
