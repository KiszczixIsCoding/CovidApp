package pl.emb.covidsupport;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirusViewModel extends ViewModel {
    private MutableLiveData<VirusStatistics> statistics;

    public MutableLiveData<VirusStatistics> getStatistics() {
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
        virusRepository.makeDataRequest(new VirusRepository.RepositoryCallback<VirusStatistics>() {
            @Override
            public void onComplete(Result<VirusStatistics> result) {
                if (result instanceof Result.Success) {
                    statistics.postValue(((Result.Success<VirusStatistics>) result).data);
                } else {
                    statistics = new MutableLiveData<>();
                    statistics.postValue(new VirusStatistics("None", "None"));
                }
            }
        });
    }
}
