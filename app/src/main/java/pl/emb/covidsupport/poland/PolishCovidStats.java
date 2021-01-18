package pl.emb.covidsupport.poland;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;

/***
 * Class for response from https://api.apify.com/v2/datasets/L3VCmhMeX0KUQeJto/items?format=json&clean=1
 */

public class PolishCovidStats {

    //Wszystkie pola, jakie mamy w API
    @SerializedName("infected")
    public Integer infected;
    @SerializedName("deceased")
    public Integer deceased;
    @SerializedName("infectedByRegion")
//    public Map<String,Map<String,String>> infectedByRegion;
    public ArrayList<Map<String, String>> infectedByRegion;
    @SerializedName("sourceUrl")
    public String sourceUrl;
    @SerializedName("lastUpdatedAtApify")
    public String lastUpdatedAtApify;
    @SerializedName("readMe")
    public String readMe;

    public PolishCovidStats(Integer infected, Integer deceased,
                            ArrayList<Map<String, String>> infectedByRegion, String sourceUrl,
                            String lastUpdatedAtApify, String readMe) {
        this.infected = infected;
        this.deceased = deceased;
        this.infectedByRegion = infectedByRegion;
        this.sourceUrl = sourceUrl;
        this.lastUpdatedAtApify = lastUpdatedAtApify;
        this.readMe = readMe;
    }

    public Integer getInfected() {
        return infected;
    }

    public void setInfected(Integer infected) {
        this.infected = infected;
    }

    public Integer getDeceased() {
        return deceased;
    }

    public void setDeceased(Integer deceased) {
        this.deceased = deceased;
    }

    public ArrayList<Map<String, String>> getInfectedByRegion() {
        return infectedByRegion;
    }

    public void setInfectedByRegion(ArrayList<Map<String, String>> infectedByRegion) {
        this.infectedByRegion = infectedByRegion;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getLastUpdatedAtApify() {
        return lastUpdatedAtApify;
    }

    public void setLastUpdatedAtApify(String lastUpdatedAtApify) {
        this.lastUpdatedAtApify = lastUpdatedAtApify;
    }

    public String getReadMe() {
        return readMe;
    }

    public void setReadMe(String readMe) {
        this.readMe = readMe;
    }

    @Override
    public String toString() {
        return "PolishCovidStats{" +
                "infected=" + infected +
                ", deceased=" + deceased +
                ", infectedByRegion=" + infectedByRegion +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", lastUpdatedAtApify='" + lastUpdatedAtApify + '\'' +
                ", readMe='" + readMe + '\'' +
                '}';
    }
}
