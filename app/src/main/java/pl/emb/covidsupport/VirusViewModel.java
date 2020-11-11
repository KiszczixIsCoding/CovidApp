package pl.emb.covidsupport;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

public class VirusViewModel extends ViewModel {
    private MutableLiveData<List<VirusStatistics>> statistics;

    public MutableLiveData<List<VirusStatistics>> getStatistics(String countryName) {
        if (statistics == null) {
            statistics = new MutableLiveData<>();
        }
        getCovidData(countryName);
        return statistics;
    }

    public void getCovidData(String countryName) {
        VirusRepository virusRepository = new VirusRepository();
        statistics = new MutableLiveData<>();
        virusRepository.make(result -> {
            if (result instanceof Result.Success) {
                statistics.postValue(((Result.Success<List<VirusStatistics>>) result).data);
            }
        }, prepareUrl(countryName));
    }

    public String prepareUrl(String countryName) {
        String formattedCountry = countryName.toLowerCase()
                .replace(" ", "-");
        return "total/country/" + formattedCountry;
    }
}
