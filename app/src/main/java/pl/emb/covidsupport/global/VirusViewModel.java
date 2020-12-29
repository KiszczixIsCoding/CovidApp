package pl.emb.covidsupport.global;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import pl.emb.covidsupport.global.repositories.MainStatsRepository;
import pl.emb.covidsupport.global.repositories.HistoricalRepository;
import pl.emb.covidsupport.global.stats.HistoricalCovidStats;
import pl.emb.covidsupport.global.stats.MainCovidStats;


public class VirusViewModel extends ViewModel {
    private MutableLiveData<MainCovidStats> novelMainStats;
    private MutableLiveData<HistoricalCovidStats> novelHistoricalStats;

    public MutableLiveData<MainCovidStats> getNovelMainStats(String countryName) {
        MainStatsRepository mainStatsRepository = new MainStatsRepository();
        novelMainStats = new MutableLiveData<>();
        mainStatsRepository.makeCountries(result -> {
            if (result instanceof Result.Success) {
                novelMainStats.postValue(((Result.Success<MainCovidStats>) result).data);
            }
        }, countryName);
        return novelMainStats;
    }

    public MutableLiveData<HistoricalCovidStats> getHistorical(String countryName) {
        HistoricalRepository repo = new HistoricalRepository();
        novelHistoricalStats = new MutableLiveData<>();
        repo.makeCountries(result -> {
            if (result instanceof Result.Success) {
                novelHistoricalStats.postValue(((Result.Success<HistoricalCovidStats>) result).data);
            }
        }, countryName);
        return novelHistoricalStats;
    }

    public String prepareUrl(String countryName) {
        return "total/country/" + countryName;
    }
}
