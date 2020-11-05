package pl.emb.covidsupport;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class VirusRepository {
    private final Executor executor;

    interface RepositoryCallback<T> {
        void onComplete(Result<List<VirusStatistics>> result);
    }

    public VirusRepository(Executor executor) {
        this.executor = executor;
    }

    public void makeDataRequest(final RepositoryCallback<List<VirusStatistics>> callback,
                                final String countryName) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = connectToUrl(countryName);
                    Result<List<VirusStatistics>> result = makeSynchronousNewDataRequest(doc);
                    callback.onComplete(result);
                } catch (Exception e) {
                    Result<List<VirusStatistics>> errorResult = new Result.Error<>(e);
                    callback.onComplete(errorResult);
                }
            }
        });
    }

    public Document connectToUrl(String countryName) throws IOException {
        String url = "https://www.worldometers.info/coronavirus/country/";
        return  Jsoup.connect(url + countryName.toLowerCase()
                .replace(" ", "-")
                .replace("&", "and") + "/").get();
    }

    public Result<List<VirusStatistics>> makeSynchronousNewDataRequest(Document doc) {
        Elements data = doc.select("ul.news_ul").get(0).select("strong");
        String newCases = data.get(0).text()
                .replace(",", "").replace(" new cases", "");
        String newDeaths = data.get(1).text()
                .replace(",", "").replace(" new deaths", "");

        Elements data1 = doc.select("div.maincounter-number");
        String totalCases = data1.get(0).select("span").text()
                .replace(",", "");
        String totalDeaths = data1.get(1).select("span").text()
                .replace(",", "");

        newCases = verifyData(newCases);
        newDeaths = verifyData(newDeaths);
        totalCases = verifyData(totalCases);
        totalDeaths = verifyData(totalDeaths);

        VirusStatistics newStatistics = new VirusStatistics(newCases, newDeaths);
        VirusStatistics totalStatistics =  new VirusStatistics(totalCases, totalDeaths);

        List<VirusStatistics> statistics = new ArrayList<>();
        statistics.add(newStatistics);
        statistics.add(totalStatistics);
        return new Result.Success<>(statistics);
    }

    public String verifyData(String data) {
        if (!StringUtils.isNumeric(data)) {
            return "0";
        } else {
            return data;
        }
    }
}
