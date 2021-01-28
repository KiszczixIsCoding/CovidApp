package pl.emb.covidsupport.global.stats;

/***
 * Class for response from https://corona.lmao.ninja/v2/countries/{country}?yesterday=true
 */
public class MainCovidStats {
    private String updated;
    private String country;
    private String cases;
    private String todayCases;
    private String deaths;
    private String todayDeaths;
    private String recovered;
    private String active;
    private String critical;
    private String tests;
    private String population;
    private String continent;
    private String oneCasePerPeople;
    private String oneDeathPerPeople;
    private String oneTestPerPeople;
    private String activePerOneMilion;
    private String recoveredPerOneMilion;
    private String criticalPerOneMilion;

    public String getUpdated() {
        return updated;
    }

    public String getCountry() {
        return country;
    }

    public String getCases() {
        return cases;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public String getDeaths() {
        return deaths;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getActive() {
        return active;
    }

    public String getCritical() {
        return critical;
    }

    public String getTests() {
        return tests;
    }

    public String getPopulation() {
        return population;
    }

    public String getContinent() {
        return continent;
    }

    public String getOneCasePerPeople() {
        return oneCasePerPeople;
    }

    public String getOneDeathPerPeople() {
        return oneDeathPerPeople;
    }

    public String getOneTestPerPeople() {
        return oneTestPerPeople;
    }

    public String getActivePerOneMilion() {
        return activePerOneMilion;
    }

    public String getRecoveredPerOneMilion() {
        return recoveredPerOneMilion;
    }

    public String getCriticalPerOneMilion() {
        return criticalPerOneMilion;
    }
}
