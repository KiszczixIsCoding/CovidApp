package pl.emb.covidsupport.global.stats;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/***
 * Class for response from https://corona.lmao.ninja/v2/historical/{country}?lastdays=all
 */
public class HistoricalCovidStats {
    public String country;
    public Map<String, Map<String, Integer>> timeline;

    public String getCountry() {
        return country;
    }

    public Map<String, Map<String, Integer>> getTimeline() {
        return timeline;
    }

    public List<String> getDates() {
        return new ArrayList<>(timeline.get("cases").keySet());
    }
    public List<String> getLastDates(int days) {
        return new ArrayList<>(timeline.get("cases").keySet()).subList(
                getDates().size() - days, getDates().size());
    }

    public List<Integer> getCases() {
        return new ArrayList<>(timeline.get("cases").values());
    }
    public List<Integer> getLastCases(int days) {
        List<Integer> lastCases = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            int index = getCases().size() - days + i;
            lastCases.add(getCases().get(index) - getCases().get(index - 1));
        }
        return lastCases;
    }

    public List<Integer> getDeaths() {
        return new ArrayList<>(timeline.get("deaths").values());
    }
    public List<Integer> getLastDeaths(int days) {
        List<Integer> lastDeaths = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            int index = getCases().size() - days + i;
            lastDeaths.add(getDeaths().get(index) - getDeaths().get(index - 1));
        }
        return lastDeaths;
    }

    public List<Integer> getRecovered() {return new ArrayList<>(timeline.get("recovered").values());
    }
    public List<Integer> getLastRecovered(int days) {
        List<Integer> lastRecovered = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            int index = getCases().size() - days + i;
            lastRecovered.add(getRecovered().get(index) - getRecovered().get(index - 1));
        }
        return lastRecovered;
    }
}
