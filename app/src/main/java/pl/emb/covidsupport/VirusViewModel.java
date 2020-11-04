package pl.emb.covidsupport;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirusViewModel extends ViewModel {
    private MutableLiveData<List<VirusStatistics>> statistics;
    private MutableLiveData<VirusStatistics> totalStatistics;

    public MutableLiveData<List<VirusStatistics>> getStatistics() {
        if (statistics == null) {
            statistics = new MutableLiveData<>();
        }
        getCovidData();
        return statistics;
    }

    public void getCovidData() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        VirusRepository virusRepository = new VirusRepository(executorService);
        statistics = new MutableLiveData<>();
        virusRepository.makeDataRequest(new VirusRepository.RepositoryCallback<List<VirusStatistics>>() {
            @Override
            public void onComplete(Result<List<VirusStatistics>> result) {
                if (result instanceof Result.Success) {
                    statistics.postValue(((Result.Success<List<VirusStatistics>>) result).data);
                    statistics.postValue(((Result.Success<List<VirusStatistics>>) result).data);
                }
            }
        });
    }
}
