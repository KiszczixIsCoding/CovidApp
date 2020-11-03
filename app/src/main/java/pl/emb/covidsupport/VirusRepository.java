package pl.emb.covidsupport;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.Executor;

public class VirusRepository {
    private String url = "https://www.worldometers.info/coronavirus/country/poland/";
    private final Executor executor;

    interface RepositoryCallback<T> {
        void onComplete(Result<VirusStatistics> result);
    }

    public VirusRepository(Executor executor) {
        this.executor = executor;
    }

    public void makeDataRequest(final RepositoryCallback<VirusStatistics> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = connectToUrl();
                    Result<VirusStatistics> result = makeSynchronousDataRequest(doc);
                    callback.onComplete(result);
                } catch (Exception e) {
                    Result<VirusStatistics> errorResult = new Result.Error<>(e);
                    callback.onComplete(errorResult);
                }
            }
        });
    }

    public Document connectToUrl() throws IOException {
        return  Jsoup.connect(url).get();
    }

    public Result<VirusStatistics> makeSynchronousDataRequest(Document doc) {
        Elements data = doc.select("ul.news_ul").get(0).select("strong");
        String formattedCases = data.get(0).text()
                .replace(",", "").replace(" new cases", "");
        String formattedDeaths = data.get(1).text()
                .replace(",", "").replace(" new deaths", "");

        VirusStatistics virusData = new VirusStatistics(formattedCases, formattedDeaths);
        return new Result.Success<>(virusData);
    }

}
