package pl.emb.covidsupport;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public class VirusRepository {
    private String url = "https://www.worldometers.info/coronavirus/country/poland/";
    private final Executor executor;

    interface RepositoryCallback<T> {
        void onComplete(Result<List<VirusStatistics>> result);
    }

    public VirusRepository(Executor executor) {
        this.executor = executor;
    }

    public void makeDataRequest(final RepositoryCallback<List<VirusStatistics>> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = connectToUrl();
                    Result<List<VirusStatistics>> result = makeSynchronousNewDataRequest(doc);
                    callback.onComplete(result);
                } catch (Exception e) {
                    Result<List<VirusStatistics>> errorResult = new Result.Error<>(e);
                    callback.onComplete(errorResult);
                }
            }
        });
    }

    public Document connectToUrl() throws IOException {
        return  Jsoup.connect(url).get();
    }

    public Result<List<VirusStatistics>> makeSynchronousNewDataRequest(Document doc) {
        Elements data = doc.select("ul.news_ul").get(0).select("strong");
        String formattedCases = data.get(0).text()
                .replace(",", "").replace(" new cases", "");
        String formattedDeaths = data.get(1).text()
                .replace(",", "").replace(" new deaths", "");

        Elements data1 = doc.select("div.maincounter-number");
        String totalCases = data1.get(0).select("span").text();
        String totalDeaths = data1.get(1).select("span").text();

        VirusStatistics newStatistics = new VirusStatistics(formattedCases, formattedDeaths);
        VirusStatistics totalStatistics =  new VirusStatistics(totalCases, totalDeaths);
        List<VirusStatistics> statistics = new ArrayList<>();
        statistics.add(newStatistics);
        statistics.add(totalStatistics);
        return new Result.Success<>(statistics);
    }
}
