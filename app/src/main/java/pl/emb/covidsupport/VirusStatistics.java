package pl.emb.covidsupport;

public class VirusStatistics {
    private String cases;
    private String deaths;

    public VirusStatistics(String cases, String deaths) {
        this.cases = cases;
        this.deaths = deaths;
    }

    public String getCases() {
        return cases;
    }

    public String getDeaths() {
        return deaths;
    }

}
