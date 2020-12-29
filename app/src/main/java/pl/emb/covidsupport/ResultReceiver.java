package pl.emb.covidsupport;

import androidx.lifecycle.LiveData;

import pl.emb.covidsupport.global.VirusViewModel;
import pl.emb.covidsupport.global.stats.MainCovidStats;

public interface ResultReceiver {
    LiveData<MainCovidStats> getCountriesAdapter();
    VirusViewModel getVirusViewModel();
}
